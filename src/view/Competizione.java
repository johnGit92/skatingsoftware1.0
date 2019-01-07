package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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

public class Competizione {

	private JFrame frmNuovaCompetizione;
	private GUIController guiController;
	private CompetitionController compController;
	private JTable tableIscrizioni;
	private JTextField txtCategoria;
	private JTextField txtSpecialit;
	private JTextField txtDisciplina;
	private JTextField txtClasse;
	private JTextField txtUnit;

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
		frmNuovaCompetizione.setBounds(10, 10, 1102, 744);
		frmNuovaCompetizione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuovaCompetizione.getContentPane().setLayout(null);
		frmNuovaCompetizione.getContentPane().setBackground(new Color(37, 61, 105));

		JButton btnIndietro = new JButton("Indietro");
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
		tableIscrizioni.setBorder(UIManager.getBorder("Table.cellNoFocusBorder"));
		tableIscrizioni.setBounds(10, 170, 234, 89);
		frmNuovaCompetizione.getContentPane().add(tableIscrizioni);

		JScrollPane scrollPaneVal = new JScrollPane(tableIscrizioni);
		scrollPaneVal.setBorder(UIManager.getBorder("TitledBorder.border"));
		scrollPaneVal.setBounds(10, 331, 947, 320);
		frmNuovaCompetizione.getContentPane().add(scrollPaneVal);

		String column_names_giudici[]= {"ID","Nome","Cognome"};
		DefaultTableModel modelGiudici=new DefaultTableModel(column_names_giudici,0);

		JLabel label = new JLabel(new ImageIcon(System.getProperty("user.home")+"/icon_resized.png"));
		label.setBounds(996, 11, 80, 103);
		frmNuovaCompetizione.getContentPane().add(label);

		txtCategoria = new JTextField();
		txtCategoria.setText("Categoria");
		txtCategoria.setToolTipText("Categoria");
		txtCategoria.setBounds(10, 86, 383, 28);
		frmNuovaCompetizione.getContentPane().add(txtCategoria);
		txtCategoria.setColumns(10);

		txtSpecialit = new JTextField();
		txtSpecialit.setText("Specialit\u00E0");
		txtSpecialit.setBounds(10, 126, 383, 28);
		frmNuovaCompetizione.getContentPane().add(txtSpecialit);
		txtSpecialit.setColumns(10);

		txtDisciplina = new JTextField();
		txtDisciplina.setText("Disciplina");
		txtDisciplina.setColumns(10);
		txtDisciplina.setBounds(10, 166, 383, 28);
		frmNuovaCompetizione.getContentPane().add(txtDisciplina);

		txtClasse = new JTextField();
		txtClasse.setText("Classe");
		txtClasse.setColumns(10);
		txtClasse.setBounds(10, 206, 383, 28);
		frmNuovaCompetizione.getContentPane().add(txtClasse);

		txtUnit = new JTextField();
		txtUnit.setText("Unit\u00E0");
		txtUnit.setColumns(10);
		txtUnit.setBounds(10, 246, 383, 28);
		frmNuovaCompetizione.getContentPane().add(txtUnit);

		JButton btnVota = new JButton("Vota");
		btnVota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=tableIscrizioni.getSelectedRow();
				String numero=(String) modelIscrizioni.getValueAt(row, 0);
				String asd=(String) modelIscrizioni.getValueAt(row, 1);
				guiController.showVotazioni(numero, asd);
			}
		});
		btnVota.setBounds(969, 337, 90, 28);
		frmNuovaCompetizione.getContentPane().add(btnVota);
		
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
