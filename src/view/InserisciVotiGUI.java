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
import model.Giudice;
import model.Iscrizione;
import model.Valutazione;

public class InserisciVotiGUI {

	private JFrame frmVotazioni;
	private JButton btnConferma;
	
	private List<Iscrizione> gruppiSelezionati;
	List<Giudice> giudiciSelezionati;
	private JTable table;
	private CompetitionController compController;

	public InserisciVotiGUI(List<Iscrizione> gruppiSelezionati, List<Giudice> giudiciSelezionati,CompetitionController compController) {
		this.gruppiSelezionati = gruppiSelezionati;
		this.giudiciSelezionati = giudiciSelezionati;
		this.compController = compController;
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
		frmVotazioni.setTitle("Votazioni - Inserisci Voti");
		frmVotazioni.setBounds(100, 100, 508, 623);
		frmVotazioni.getContentPane().setBackground(new Color(37, 61, 105));
		frmVotazioni.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Valutazioni", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		scrollPane.setBounds(15, 11, 462, 491);
		frmVotazioni.getContentPane().add(scrollPane);
		
		String column_names[]= {"ID","Numero","Tecnico","Coreografico"};
		DefaultTableModel model=new DefaultTableModel(column_names,0);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		btnConferma = new JButton("");
		btnConferma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Valutazione> valutazioni=new ArrayList<Valutazione>();
				int rows=table.getRowCount(), i=0;
				for(i=0;i<rows;i++) {
					String id=(String) model.getValueAt(i, 0);
					int numero=(int) model.getValueAt(i, 1);
					double tecnico=Double.parseDouble((String) model.getValueAt(i, 2));
					double coreografico=Double.parseDouble((String) model.getValueAt(i, 3));
					Valutazione v=new Valutazione(numero, id, tecnico, coreografico);
					valutazioni.add(v);
				}
				compController.salvaValutazioni(valutazioni);
				
				JOptionPane.showMessageDialog(null, "Voti memorizzati correttamente!", "Messaggio", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnConferma.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnConferma.setBounds(417, 513, 60, 60);
		frmVotazioni.getContentPane().add(btnConferma);
		
		JButton button = new JButton("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmVotazioni.dispose();
			}
		});
		button.setBounds(15, 513, 60, 60);
		frmVotazioni.getContentPane().add(button);
		
		//Ottieni giudici e aggiorna tabella
		for(Giudice g : giudiciSelezionati) {
			for(Iscrizione i : gruppiSelezionati) {
				model.addRow(new Object[] {g.getId(),i.getNumero()});	
			}
		}
	}
}
