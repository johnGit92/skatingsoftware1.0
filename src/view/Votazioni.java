package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

public class Votazioni {

	private JFrame frmVotazioni;
	private JTextField txtNumero;
	private JTextField txtAsd;
	private JComboBox comboGiudice2;
	private JComboBox comboGiudice3;
	private JComboBox comboGiudice4;
	private JComboBox comboGiudice5;
	private JComboBox comboTecnico1;
	private JComboBox comboCoreografico;
	private JPanel panel;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JPanel panel_1;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JComboBox comboBox_4;
	private JComboBox comboBox_5;
	private JComboBox comboBox_6;
	private JComboBox comboBox_7;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JButton btnSalva;
	
	private String numero,asd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Votazioni window = new Votazioni();
					window.frmVotazioni.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Votazioni() {
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
		frmVotazioni.setBounds(100, 100, 804, 365);
		frmVotazioni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVotazioni.getContentPane().setBackground(new Color(37, 61, 105));
		frmVotazioni.getContentPane().setLayout(null);
		
		txtNumero = new JTextField();
		txtNumero.setText("Numero");
		txtNumero.setBounds(28, 26, 96, 20);
		frmVotazioni.getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		txtAsd = new JTextField();
		txtAsd.setText("ASD");
		txtAsd.setBounds(28, 57, 96, 20);
		frmVotazioni.getContentPane().add(txtAsd);
		txtAsd.setColumns(10);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(28, 114, 134, 93);
		frmVotazioni.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboGiudice1 = new JComboBox();
		comboGiudice1.setBounds(0, 0, 134, 20);
		panel.add(comboGiudice1);
		comboGiudice1.addItem("Giudice 1");
		
		comboTecnico1 = new JComboBox();
		comboTecnico1.setBounds(0, 45, 96, 20);
		panel.add(comboTecnico1);
		comboTecnico1.addItem("Tecnico");
		
		comboCoreografico = new JComboBox();
		comboCoreografico.setBounds(0, 73, 96, 20);
		panel.add(comboCoreografico);
		comboCoreografico.addItem("Coreografico");
		
		panel_1 = new JPanel();
		panel_1.setBounds(172, 114, 134, 93);
		frmVotazioni.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		comboGiudice2 = new JComboBox();
		comboGiudice2.setBounds(0, 0, 134, 20);
		panel_1.add(comboGiudice2);
		comboGiudice2.addItem("Giudice 2");
		
		comboBox = new JComboBox();
		comboBox.setBounds(0, 45, 96, 20);
		panel_1.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(0, 73, 96, 20);
		panel_1.add(comboBox_1);
		
		panel_2 = new JPanel();
		panel_2.setBounds(316, 113, 134, 94);
		frmVotazioni.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		comboGiudice3 = new JComboBox();
		comboGiudice3.setBounds(0, 0, 134, 20);
		panel_2.add(comboGiudice3);
		comboGiudice3.addItem("Giudice 3");
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(0, 46, 96, 20);
		panel_2.add(comboBox_2);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setBounds(0, 74, 96, 20);
		panel_2.add(comboBox_3);
		
		panel_3 = new JPanel();
		panel_3.setBounds(460, 113, 134, 94);
		frmVotazioni.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		comboGiudice4 = new JComboBox();
		comboGiudice4.setBounds(0, 0, 134, 20);
		panel_3.add(comboGiudice4);
		comboGiudice4.addItem("Giudice 4");
		
		comboBox_4 = new JComboBox();
		comboBox_4.setBounds(0, 46, 96, 20);
		panel_3.add(comboBox_4);
		
		comboBox_5 = new JComboBox();
		comboBox_5.setBounds(0, 74, 96, 20);
		panel_3.add(comboBox_5);
		
		panel_4 = new JPanel();
		panel_4.setBounds(604, 113, 134, 94);
		frmVotazioni.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		comboGiudice5 = new JComboBox();
		comboGiudice5.setBounds(0, 0, 134, 20);
		panel_4.add(comboGiudice5);
		comboGiudice5.addItem("Giudice 5");
		
		comboBox_6 = new JComboBox();
		comboBox_6.setBounds(0, 46, 96, 20);
		panel_4.add(comboBox_6);
		
		comboBox_7 = new JComboBox();
		comboBox_7.setBounds(0, 74, 96, 20);
		panel_4.add(comboBox_7);
		
		btnSalva = new JButton("Salva");
		btnSalva.setBounds(28, 266, 89, 23);
		frmVotazioni.getContentPane().add(btnSalva);
		
		txtAsd.setText(asd); txtNumero.setText(numero);
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
