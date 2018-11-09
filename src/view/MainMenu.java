package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.GUIController;
import javax.swing.JPanel;
import javax.swing.UIManager;

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
		frmSkatingsoftware.setBounds(100, 100, 308, 260);
		frmSkatingsoftware.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSkatingsoftware.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel.setBounds(6, 6, 280, 209);
		frmSkatingsoftware.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNuovaCompetizione = new JButton("Nuova Competizione");
		btnNuovaCompetizione.setBounds(69, 34, 142, 28);
		panel.add(btnNuovaCompetizione);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setBounds(69, 144, 142, 28);
		panel.add(btnEsci);
		
		JButton btnGiudici = new JButton("Giudici");
		btnGiudici.setBounds(69, 74, 142, 28);
		panel.add(btnGiudici);
		btnGiudici.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guiController.showGiudici();
				frmSkatingsoftware.setVisible(false);
			}
		});
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnNuovaCompetizione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				guiController.showNuovaCompetizione();
				frmSkatingsoftware.setVisible(false);
			}
		});
	}

	public JFrame getFrmSkatingsoftware() {
		return frmSkatingsoftware;
	}

	public void setFrmSkatingsoftware(JFrame frmSkatingsoftware) {
		this.frmSkatingsoftware = frmSkatingsoftware;
	}
}
