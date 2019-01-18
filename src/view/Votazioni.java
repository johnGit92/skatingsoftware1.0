package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.GiudiceDao;
import dao.Service;
import dao.ValutazioneDao;
import model.Giudice;
import model.Valutazione;

import java.awt.Font;
import java.util.List;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

public class Votazioni {

	private JFrame frmVotazioni;
	private JTextField txtNumero;
	private JTextField txtAsd;
	private JComboBox comboGiudice2;
	private JComboBox comboGiudice3;
	private JComboBox comboGiudice4;
	private JComboBox comboGiudice5;
	private JComboBox comboTecnico1;
	private JComboBox comboCoreo1;
	private JPanel panel;
	private JComboBox comboTecnico2;
	private JComboBox comboCoreo2;
	private JPanel panel_1;
	private JComboBox comboTecnico3;
	private JComboBox comboCoreo3;
	private JComboBox comboTecnico4;
	private JComboBox comboCoreo4;
	private JComboBox comboTecnico5;
	private JComboBox comboCoreo5;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JButton btnSalva;
	
	private String numero,asd;
	private JTable table;

	/**
	 * Create the application.
	 * @param numero numero gruppo iscritto.
	 * @param asd club/scuola gruppo iscritto.
	 */
	public Votazioni(String numero, String asd) {
		this.numero=numero;
		this.asd=asd;
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
		frmVotazioni.setTitle("Votazioni");
		frmVotazioni.setBounds(100, 100, 1188, 365);
		frmVotazioni.getContentPane().setBackground(new Color(37, 61, 105));
		frmVotazioni.getContentPane().setLayout(null);
		
		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Corbel", Font.PLAIN, 12));
		txtNumero.setEditable(false);
		txtNumero.setText(numero);
		txtNumero.setBounds(28, 26, 150, 30);
		frmVotazioni.getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		txtAsd = new JTextField();
		txtAsd.setFont(new Font("Corbel", Font.PLAIN, 12));
		txtAsd.setEditable(false);
		txtAsd.setText(asd);
		txtAsd.setBounds(28, 67, 150, 30);
		frmVotazioni.getContentPane().add(txtAsd);
		txtAsd.setColumns(10);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(28, 114, 134, 93);
		frmVotazioni.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboGiudice1 = new JComboBox();
		comboGiudice1.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboGiudice1.setBounds(0, 0, 134, 20);
		panel.add(comboGiudice1);
		comboGiudice1.addItem("Giudice 1");
		
		comboTecnico1 = new JComboBox();
		comboTecnico1.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboTecnico1.setBounds(0, 45, 96, 20);
		panel.add(comboTecnico1);
		comboTecnico1.addItem("Tecnico");
		comboTecnico1.addItem("5.5");
		comboTecnico1.addItem("5.6");
		comboTecnico1.addItem("5.7");
		comboTecnico1.addItem("5.8");
		comboTecnico1.addItem("5.9");
		comboTecnico1.addItem("6.0");
		
		comboCoreo1 = new JComboBox();
		comboCoreo1.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboCoreo1.setBounds(0, 73, 96, 20);
		panel.add(comboCoreo1);
		comboCoreo1.addItem("Coreografico");
		comboCoreo1.addItem("5.5");
		comboCoreo1.addItem("5.6");
		comboCoreo1.addItem("5.7");
		comboCoreo1.addItem("5.8");
		comboCoreo1.addItem("5.9");
		comboCoreo1.addItem("6.0");
		
		panel_1 = new JPanel();
		panel_1.setBounds(172, 114, 134, 93);
		frmVotazioni.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		comboGiudice2 = new JComboBox();
		comboGiudice2.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboGiudice2.setBounds(0, 0, 134, 20);
		panel_1.add(comboGiudice2);
		comboGiudice2.addItem("Giudice 2");
		
		comboTecnico2 = new JComboBox();
		comboTecnico2.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboTecnico2.setBounds(0, 45, 96, 20);
		panel_1.add(comboTecnico2);
		comboTecnico2.addItem("Tecnico");
		comboTecnico2.addItem("5.5");
		comboTecnico2.addItem("5.6");
		comboTecnico2.addItem("5.7");
		comboTecnico2.addItem("5.8");
		comboTecnico2.addItem("5.9");
		comboTecnico2.addItem("6.0");
		
		comboCoreo2 = new JComboBox();
		comboCoreo2.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboCoreo2.setBounds(0, 73, 96, 20);
		panel_1.add(comboCoreo2);
		comboCoreo2.addItem("Coreografico");
		comboCoreo2.addItem("5.5");
		comboCoreo2.addItem("5.6");
		comboCoreo2.addItem("5.7");
		comboCoreo2.addItem("5.8");
		comboCoreo2.addItem("5.9");
		comboCoreo2.addItem("6.0");
		
		panel_2 = new JPanel();
		panel_2.setBounds(316, 113, 134, 94);
		frmVotazioni.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		comboGiudice3 = new JComboBox();
		comboGiudice3.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboGiudice3.setBounds(0, 0, 134, 20);
		panel_2.add(comboGiudice3);
		comboGiudice3.addItem("Giudice 3");
		
		comboTecnico3 = new JComboBox();
		comboTecnico3.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboTecnico3.setBounds(0, 46, 96, 20);
		panel_2.add(comboTecnico3);
		comboTecnico3.addItem("Tecnico");
		comboTecnico3.addItem("5.5");
		comboTecnico3.addItem("5.6");
		comboTecnico3.addItem("5.7");
		comboTecnico3.addItem("5.8");
		comboTecnico3.addItem("5.9");
		comboTecnico3.addItem("6.0");
		
		comboCoreo3 = new JComboBox();
		comboCoreo3.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboCoreo3.setBounds(0, 74, 96, 20);
		panel_2.add(comboCoreo3);
		comboCoreo3.addItem("Coreografico");
		comboCoreo3.addItem("5.5");
		comboCoreo3.addItem("5.6");
		comboCoreo3.addItem("5.7");
		comboCoreo3.addItem("5.8");
		comboCoreo3.addItem("5.9");
		comboCoreo3.addItem("6.0");
		
		panel_3 = new JPanel();
		panel_3.setBounds(460, 113, 134, 94);
		frmVotazioni.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		comboGiudice4 = new JComboBox();
		comboGiudice4.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboGiudice4.setBounds(0, 0, 134, 20);
		panel_3.add(comboGiudice4);
		comboGiudice4.addItem("Giudice 4");
		
		comboTecnico4 = new JComboBox();
		comboTecnico4.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboTecnico4.setBounds(0, 46, 96, 20);
		panel_3.add(comboTecnico4);
		comboTecnico4.addItem("Tecnico");
		comboTecnico4.addItem("5.5");
		comboTecnico4.addItem("5.6");
		comboTecnico4.addItem("5.7");
		comboTecnico4.addItem("5.8");
		comboTecnico4.addItem("5.9");
		comboTecnico4.addItem("6.0");
		
		comboCoreo4 = new JComboBox();
		comboCoreo4.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboCoreo4.setBounds(0, 74, 96, 20);
		panel_3.add(comboCoreo4);
		comboCoreo4.addItem("Coreografico");
		comboCoreo4.addItem("5.5");
		comboCoreo4.addItem("5.6");
		comboCoreo4.addItem("5.7");
		comboCoreo4.addItem("5.8");
		comboCoreo4.addItem("5.9");
		comboCoreo4.addItem("6.0");
		
		panel_4 = new JPanel();
		panel_4.setBounds(604, 113, 134, 94);
		frmVotazioni.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		comboGiudice5 = new JComboBox();
		comboGiudice5.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboGiudice5.setBounds(0, 0, 134, 20);
		panel_4.add(comboGiudice5);
		comboGiudice5.addItem("Giudice 5");
		
		comboTecnico5 = new JComboBox();
		comboTecnico5.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboTecnico5.setBounds(0, 46, 96, 20);
		panel_4.add(comboTecnico5);
		comboTecnico5.addItem("Tecnico");
		comboTecnico5.addItem("5.5");
		comboTecnico5.addItem("5.6");
		comboTecnico5.addItem("5.7");
		comboTecnico5.addItem("5.8");
		comboTecnico5.addItem("5.9");
		comboTecnico5.addItem("6.0");
		
		comboCoreo5 = new JComboBox();
		comboCoreo5.setFont(new Font("Corbel", Font.PLAIN, 12));
		comboCoreo5.setBounds(0, 74, 96, 20);
		panel_4.add(comboCoreo5);
		comboCoreo5.addItem("Coreografico");
		comboCoreo5.addItem("5.5");
		comboCoreo5.addItem("5.6");
		comboCoreo5.addItem("5.7");
		comboCoreo5.addItem("5.8");
		comboCoreo5.addItem("5.9");
		comboCoreo5.addItem("6.0");
		
		btnSalva = new JButton("Salva");
		btnSalva.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnSalva.setBounds(28, 266, 89, 23);
		frmVotazioni.getContentPane().add(btnSalva);
		
		txtAsd.setText(asd); txtNumero.setText(numero);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Giudici", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		scrollPane.setBounds(770, 26, 369, 289);
		frmVotazioni.getContentPane().add(scrollPane);
		
		String column_names[]= {"ID","Nome","Cognome"};
		DefaultTableModel model=new DefaultTableModel(column_names,0);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		//Ottieni giudici e aggiorna comboGiudici
		GiudiceDao dao=Service.getGiudiceDao();
		List<Giudice> giudici=dao.getAll();
		for(Giudice g:giudici) {
			model.addRow(new Object[] {g.getId(),g.getNome(),g.getCognome()});
			comboGiudice1.addItem(g.getId());
			comboGiudice2.addItem(g.getId());
			comboGiudice3.addItem(g.getId());
			comboGiudice4.addItem(g.getId());
			comboGiudice5.addItem(g.getId());
		}
		
		//ottieni valutazioni già inserite
		ValutazioneDao valDao=Service.getValutazioneDao();
		List<Valutazione> valutazioni=valDao.getValutazioni(Integer.parseInt(numero));
		int count=valutazioni.size(),i=0;
		JComboBox<String>[] comboGiudici=new JComboBox[5];
		comboGiudici[0]=comboGiudice1;
		comboGiudici[1]=comboGiudice2;
		comboGiudici[2]=comboGiudice3;
		comboGiudici[3]=comboGiudice4;
		comboGiudici[4]=comboGiudice5;
		JComboBox<String>[] comboTecnico=new JComboBox[5];
		comboTecnico[0]=comboTecnico1;
		comboTecnico[1]=comboTecnico2;
		comboTecnico[2]=comboTecnico3;
		comboTecnico[3]=comboTecnico4;
		comboTecnico[4]=comboTecnico5;
		JComboBox<String>[] comboCoreografico=new JComboBox[5];
		comboCoreografico[0]=comboCoreo1;
		comboCoreografico[1]=comboCoreo2;
		comboCoreografico[2]=comboCoreo3;
		comboCoreografico[3]=comboCoreo4;
		comboCoreografico[4]=comboCoreo5;
		Valutazione v;
		for(i=0;i<count;i++) {
			v=valutazioni.get(i);
			comboGiudici[i].setSelectedItem(v.getId());
			comboTecnico[i].setSelectedItem(String.valueOf(v.getTecnico()));
			comboCoreografico[i].setSelectedItem(String.valueOf(v.getCoreografico()));
		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAsd() {
		return asd;
	}

	public void setAsd(String asd) {
		this.asd = asd;
	}
}
