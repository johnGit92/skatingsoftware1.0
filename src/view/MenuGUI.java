package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.CompetitionController;
import controller.GUIController;
import java.awt.Insets;

public class MenuGUI {

	private JFrame frmSkatingsoftware;
	private GUIController guiController;

	/**
	 * Create the application.
	 * @param compController controller competizioni.
	 */
	public MenuGUI(GUIController controller, CompetitionController compController) {
		guiController=controller;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSkatingsoftware = new JFrame();
		frmSkatingsoftware.getContentPane().setBackground(new Color(37, 61, 105));
		frmSkatingsoftware.setTitle("Skating Software 1.0");
		frmSkatingsoftware.setBounds(10, 10, 552, 259);
		frmSkatingsoftware.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSkatingsoftware.getContentPane().setLayout(null);
		
		JButton btnNuovaCompetizione = new JButton("Iscrizioni");
		btnNuovaCompetizione.setBounds(6, 6, 150, 40);
		frmSkatingsoftware.getContentPane().add(btnNuovaCompetizione);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setMargin(new Insets(2, 5, 2, 14));
		btnEsci.setIconTextGap(10);
		btnEsci.setBounds(6, 175, 150, 40);
		btnEsci.setIcon(new ImageIcon("icons/close.png"));
		frmSkatingsoftware.getContentPane().add(btnEsci);
		
		JButton btnGiudici = new JButton("Giudici");
		btnGiudici.setEnabled(false);
		btnGiudici.setBounds(6, 50, 150, 40);
		frmSkatingsoftware.getContentPane().add(btnGiudici);
		
		JLabel lblSkatingsoftware = new JLabel(new ImageIcon("icons/logo.png"));
		lblSkatingsoftware.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSkatingsoftware.setForeground(Color.WHITE);
		lblSkatingsoftware.setBounds(239, 26, 291, 167);
		frmSkatingsoftware.getContentPane().add(lblSkatingsoftware);
//		btnGiudici.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				guiController.showGiudici();
//				frmSkatingsoftware.setVisible(false);
//			}
//		});
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
