package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.GUIController;

public class MainMenu {

	private JFrame frmSkatingsoftware;
	private GUIController guiController;

	/**
	 * Create the application.
	 */
	public MainMenu(GUIController controller) {
		guiController=controller;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSkatingsoftware = new JFrame();
		frmSkatingsoftware.setTitle("Skating Software 1.0");
		frmSkatingsoftware.setBounds(100, 100, 354, 260);
		frmSkatingsoftware.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSkatingsoftware.getContentPane().setLayout(null);
		
		JButton btnNuovaCompetizione = new JButton("Nuova Competizione");
		btnNuovaCompetizione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				guiController.showNuovaCompetizione();
				frmSkatingsoftware.setVisible(false);
			}
		});
		btnNuovaCompetizione.setBounds(86, 33, 142, 28);
		frmSkatingsoftware.getContentPane().add(btnNuovaCompetizione);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnEsci.setBounds(86, 143, 142, 28);
		frmSkatingsoftware.getContentPane().add(btnEsci);
		
		JButton btnGiudici = new JButton("Giudici");
		btnGiudici.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guiController.showGiudici();
				frmSkatingsoftware.setVisible(false);
			}
		});
		btnGiudici.setBounds(86, 73, 142, 28);
		frmSkatingsoftware.getContentPane().add(btnGiudici);
	}

	public JFrame getFrmSkatingsoftware() {
		return frmSkatingsoftware;
	}

	public void setFrmSkatingsoftware(JFrame frmSkatingsoftware) {
		this.frmSkatingsoftware = frmSkatingsoftware;
	}
}
