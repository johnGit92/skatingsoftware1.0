package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import controller.CompetitionController;
import controller.GUIController;
import dao.IscrizioneDao;
import dao.Service;
import model.Iscrizione;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.SwingConstants;

public class Competizione {

	private JFrame frmNuovaCompetizione;
	private GUIController guiController;
	private CompetitionController compController;
	private JTable tableIscrizioni;
	private JTextField textSelezionati;

	/**
	 * Create the application.
	 */
	public Competizione(GUIController controller, CompetitionController compCtrl) {
		guiController=controller;
		compController=compCtrl;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuovaCompetizione = new JFrame();
		frmNuovaCompetizione.setResizable(false);
		frmNuovaCompetizione.setTitle("Skating Software 1.0 - Competizione");
		frmNuovaCompetizione.setBounds(10, 10, 1102, 674);
		frmNuovaCompetizione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuovaCompetizione.getContentPane().setLayout(null);
		frmNuovaCompetizione.getContentPane().setBackground(new Color(37, 61, 105));

		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guiController.showMainMenu();
				frmNuovaCompetizione.setVisible(false);
			}
		});
		btnIndietro.setBounds(10, 11, 89, 30);
		frmNuovaCompetizione.getContentPane().add(btnIndietro);

		String column_names[]= {"Numero","ASD","Categoria","Specialità","Disciplina","Classe","Unità"};
		DefaultTableModel modelIscrizioni=new DefaultTableModel(column_names,0);
		tableIscrizioni = new JTable(modelIscrizioni);
		tableIscrizioni.setFont(new Font("Corbel", Font.PLAIN, 12));
		tableIscrizioni.setBorder(UIManager.getBorder("Table.cellNoFocusBorder"));
		tableIscrizioni.setBounds(10, 170, 234, 89);
		tableIscrizioni.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

		    public void valueChanged(ListSelectionEvent lse) {
		        if (!lse.getValueIsAdjusting()) {
		        	textSelezionati.setText(String.valueOf(tableIscrizioni.getSelectedRowCount()));
		        }
		    }
		});
		frmNuovaCompetizione.getContentPane().add(tableIscrizioni);

		JScrollPane scrollPaneVal = new JScrollPane(tableIscrizioni);
		scrollPaneVal.setBorder(new TitledBorder(null, "ISCRITTI", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		scrollPaneVal.setBounds(10, 129, 947, 504);
		frmNuovaCompetizione.getContentPane().add(scrollPaneVal);

		String column_names_giudici[]= {"ID","Nome","Cognome"};
		DefaultTableModel modelGiudici=new DefaultTableModel(column_names_giudici,0);

		JLabel label = new JLabel(new ImageIcon(System.getProperty("user.home")+"/icon_resized.png"));
		label.setBounds(996, 11, 80, 103);
		frmNuovaCompetizione.getContentPane().add(label);

		JButton btnVota = new JButton("Vota");
		btnVota.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnVota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=tableIscrizioni.getSelectedRow();
				String numero=(String) modelIscrizioni.getValueAt(row, 0);
				String asd=(String) modelIscrizioni.getValueAt(row, 1);
				guiController.showVotazioni(numero, asd);
			}
		});
		btnVota.setBounds(969, 129, 90, 28);
		frmNuovaCompetizione.getContentPane().add(btnVota);
		
		JButton btnClassifica = new JButton("Classifica");
		btnClassifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Map<String,String> iscrittiInCompetizione=new HashMap<String,String>();
				int[] rows=tableIscrizioni.getSelectedRows();
				int i=0;
				String numero,asd;
				for(i=0;i<rows.length;i++) {
					numero=(String) modelIscrizioni.getValueAt(rows[i], 0);
					asd=(String) modelIscrizioni.getValueAt(rows[i], 1);
					iscrittiInCompetizione.put(numero, asd);
				}
				guiController.showClassifica(iscrittiInCompetizione);
			}
		});
		btnClassifica.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnClassifica.setBounds(969, 169, 90, 28);
		frmNuovaCompetizione.getContentPane().add(btnClassifica);
		
		JLabel lblSelezionati = new JLabel("Selezionati");
		lblSelezionati.setFont(new Font("Corbel", Font.PLAIN, 12));
		lblSelezionati.setForeground(Color.WHITE);
		lblSelezionati.setBounds(30, 101, 69, 16);
		frmNuovaCompetizione.getContentPane().add(lblSelezionati);
		
		textSelezionati = new JTextField();
		textSelezionati.setEditable(false);
		textSelezionati.setHorizontalAlignment(SwingConstants.CENTER);
		textSelezionati.setFont(new Font("Corbel", Font.PLAIN, 12));
		textSelezionati.setBounds(89, 94, 57, 25);
		frmNuovaCompetizione.getContentPane().add(textSelezionati);
		textSelezionati.setColumns(10);
		
		IscrizioneDao dao=Service.getIscrizioneDao();
		List<Iscrizione> iscrizioni=dao.getAll();
		for(Iscrizione i:iscrizioni) {
			modelIscrizioni.addRow(new Object[] {
					String.valueOf(i.getNumero()),i.getAsd(),String.valueOf(i.getCategoria().getVal()),
					String.valueOf(i.getSpecialita().getVal()),String.valueOf(i.getDisciplina().getVal()),
					String.valueOf(i.getClasse().getVal()),String.valueOf(i.getGruppo().getVal())
			});
		}

//		try {
//			//carica dati in tabella giudici
//			final String dir=System.getProperty("user.home");
//			List<Giudice> giudici=compController.caricaGiudici(new File(dir+"/giudici.csv"));
//			for(Giudice g: giudici) {
//				modelGiudici.addRow(new Object[] {g.getId(),g.getNome(),g.getCognome()});
//			}
//		} catch (Exception e1) {
//			JOptionPane.showMessageDialog(null, e1.toString(), "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
//		}

	}

	public JFrame getFrame() {
		return frmNuovaCompetizione;
	}

	public void setFrame(JFrame frame) {
		this.frmNuovaCompetizione = frame;
	}
}
