package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.CompetitionController;
import controller.GUIController;
import model.Giudice;
import model.Iscrizione;

public class SelezionaGiuriaGUI {

	private JFrame frmVotazioni;
	private JButton btnSeleziona;
	
	private List<Iscrizione> gruppiSelezionati;
	private JTable table;
	private CompetitionController compController;
	private GUIController guiController;

	/**
	 * Create the application.
	 * @param numero numero gruppo iscritto.
	 * @param asd club/scuola gruppo iscritto.
	 * @param compController controller competizioni.
	 */
	public SelezionaGiuriaGUI(List<Iscrizione> selezionati, CompetitionController compController, GUIController guiController) {
		this.gruppiSelezionati=selezionati;
		this.compController=compController;
		this.guiController=guiController;
		initialize();
	}

	public JFrame getFrmVotazioni() {
		return frmVotazioni;
	}

	public void setFrmVotazioni(JFrame frmVotazioni) {
		this.frmVotazioni = frmVotazioni;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmVotazioni = new JFrame();
		frmVotazioni.setTitle("Votazioni - Seleziona Giuria");
		frmVotazioni.setBounds(100, 100, 508, 419);
		frmVotazioni.getContentPane().setBackground(new Color(37, 61, 105));
		frmVotazioni.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Giudici", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		scrollPane.setBounds(15, 11, 462, 289);
		frmVotazioni.getContentPane().add(scrollPane);
		
		String column_names[]= {"ID","Nome","Cognome"};
		DefaultTableModel model=new DefaultTableModel(column_names,0);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		btnSeleziona = new JButton("");
		btnSeleziona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Giudice> giudiciSelezionati=new ArrayList<Giudice>();
				int[] rows=table.getSelectedRows();
				if(rows.length==0)
					JOptionPane.showMessageDialog(null, "Nessun giudice selezionato!", "Attenzione", JOptionPane.INFORMATION_MESSAGE);
				else {
					int i=0;
					for(i=0;i<rows.length;i++) {
						Giudice g=new Giudice();
						g.setId((String)model.getValueAt(i, 0));
						g.setCognome((String)model.getValueAt(i, 2));
						g.setNome((String)model.getValueAt(i, 1));
						giudiciSelezionati.add(g);
					}
					guiController.showInserisciVoti(gruppiSelezionati, giudiciSelezionati);					
				}
			}
		});
		btnSeleziona.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnSeleziona.setBounds(417, 311, 60, 60);
		frmVotazioni.getContentPane().add(btnSeleziona);
		
		JButton button = new JButton("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmVotazioni.dispose();
			}
		});
		button.setBounds(15, 311, 60, 60);
		frmVotazioni.getContentPane().add(button);
		
		//Ottieni giudici e aggiorna tabella
		List<Giudice> giudici=compController.getAllGiudici();
		for(Giudice g:giudici) {
			model.addRow(new Object[] {g.getId(),g.getNome(),g.getCognome()});
		}
	}

	public List<Iscrizione> getSelezionati() {
		return gruppiSelezionati;
	}

	public void setSelezionati(List<Iscrizione> selezionati) {
		this.gruppiSelezionati = selezionati;
	}
}