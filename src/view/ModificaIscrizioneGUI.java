package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CompetitionController;
import model.Categoria;
import model.Classe;
import model.Disciplina;
import model.Iscrizione;
import model.Specialita;
import model.Unita;

public class ModificaIscrizioneGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CompetitionController controller;
	private JTextField textNumero;
	private JTextField textASD;

	public ModificaIscrizioneGUI(Iscrizione iscrizione, CompetitionController controller) {
		this.controller=controller;
		initialize(iscrizione);
	}

	public void initialize(Iscrizione iscrizione) {
		setBackground(SystemColor.desktop);

		setTitle("SkatingSoftware 1.0 - Modifica Iscrizione");
		setBounds(100, 100, 1175, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(37, 61, 105));
		contentPane.setLayout(null);

		textNumero = new JTextField();
		textNumero.setBounds(8, 40, 74, 28);
		contentPane.add(textNumero);
		textNumero.setColumns(10);
		textNumero.setText(String.valueOf(iscrizione.getNumero()));

		textASD = new JTextField();
		textASD.setBounds(94, 40, 186, 28);
		contentPane.add(textASD);
		textASD.setColumns(10);
		textASD.setText(iscrizione.getAsd());

		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Corbel", Font.PLAIN, 12));
		lblNumero.setForeground(Color.WHITE);
		lblNumero.setBounds(10, 25, 70, 14);
		contentPane.add(lblNumero);

		JLabel lblAsd = new JLabel("ASD");
		lblAsd.setFont(new Font("Corbel", Font.PLAIN, 12));
		lblAsd.setForeground(Color.WHITE);
		lblAsd.setBounds(96, 25, 184, 14);
		contentPane.add(lblAsd);

		JComboBox<String> comboCategoria = new JComboBox<String>();
		comboCategoria.setBounds(290, 40, 121, 28);
		contentPane.add(comboCategoria);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Corbel", Font.PLAIN, 12));
		lblCategoria.setForeground(Color.WHITE);
		lblCategoria.setBounds(292, 23, 119, 14);
		contentPane.add(lblCategoria);

		JComboBox<String> comboSpecialita = new JComboBox<String>();
		comboSpecialita.setBounds(418, 40, 212, 28);
		contentPane.add(comboSpecialita);

		JLabel lblSpecialit = new JLabel("Specialit\u00E0");
		lblSpecialit.setFont(new Font("Corbel", Font.PLAIN, 12));
		lblSpecialit.setForeground(Color.WHITE);
		lblSpecialit.setBounds(421, 23, 209, 14);
		contentPane.add(lblSpecialit);

		JComboBox<String> comboDisciplina = new JComboBox<String>();
		comboDisciplina.setBounds(640, 40, 212, 28);
		contentPane.add(comboDisciplina);

		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Corbel", Font.PLAIN, 12));
		lblDisciplina.setForeground(Color.WHITE);
		lblDisciplina.setBounds(642, 23, 212, 14);
		contentPane.add(lblDisciplina);

		JLabel lblClasse = new JLabel("Classe");
		lblClasse.setForeground(Color.WHITE);
		lblClasse.setFont(new Font("Corbel", Font.PLAIN, 12));
		lblClasse.setBounds(864, 23, 119, 14);
		contentPane.add(lblClasse);

		JComboBox<String> comboClasse = new JComboBox<String>();
		comboClasse.setBounds(862, 40, 121, 28);
		contentPane.add(comboClasse);

		JLabel lblUnita = new JLabel("Unita");
		lblUnita.setForeground(Color.WHITE);
		lblUnita.setFont(new Font("Corbel", Font.PLAIN, 12));
		lblUnita.setBounds(995, 23, 119, 14);
		contentPane.add(lblUnita);

		JComboBox<String> comboUnita = new JComboBox<String>();
		comboUnita.setBounds(993, 40, 121, 28);
		contentPane.add(comboUnita);

		JButton btnConferma = new JButton("Conferma");
		btnConferma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					int numero=Integer.parseInt(textNumero.getText().trim());
					String asd=textASD.getText().trim();

					Categoria categoria=Categoria.valueOf(String.valueOf(comboCategoria.getSelectedItem()).trim());
					Specialita specialita=Specialita.valueOf(String.valueOf(comboSpecialita.getSelectedItem()).trim());
					Disciplina disciplina=Disciplina.valueOf(String.valueOf(comboDisciplina.getSelectedItem()).trim());
					Classe classe=Classe.valueOf(String.valueOf(comboClasse.getSelectedItem()).trim());
					Unita unita=Unita.valueOf(String.valueOf(comboUnita.getSelectedItem()).trim());

					Iscrizione iscrizione=new Iscrizione(asd, numero, categoria, specialita, disciplina, unita, 0, classe, null);
					controller.update(iscrizione);

					JOptionPane.showMessageDialog(null, "Operazione effettuata con Successo!","CONFERMA",JOptionPane.INFORMATION_MESSAGE);

					textNumero.setText("");
					textASD.setText("");

					IscrizioniGUI.update(e);


				}catch(Exception exc) {
					JOptionPane.showMessageDialog(null, exc.toString(), "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConferma.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnConferma.setBounds(1025, 103, 89, 28);
		contentPane.add(btnConferma);

		comboCategoria.addItem("U5");
		comboCategoria.addItem("U7");
		comboCategoria.addItem("U9");
		comboCategoria.addItem("U11");
		comboCategoria.addItem("U13");
		comboCategoria.addItem("U16");
		comboCategoria.addItem("U21");
		comboCategoria.addItem("O16");
		comboCategoria.addItem("O35");
		comboCategoria.addItem("O50");
		comboCategoria.addItem("OPEN");
		comboCategoria.addItem("ASSOLUTA");
		comboCategoria.addItem("ALTRO");

		comboSpecialita.addItem("SINCRO");
		comboSpecialita.addItem("COREO");
		comboSpecialita.addItem("SHOW");
		comboSpecialita.addItem("ALTRO");

		comboDisciplina.addItem("LATINO");
		comboDisciplina.addItem("POP");
		comboDisciplina.addItem("HIPHOP");
		comboDisciplina.addItem("CONTEMPORANEA");
		comboDisciplina.addItem("MODERNA");
		comboDisciplina.addItem("ORIENTALI");
		comboDisciplina.addItem("ASSOLUTA");
		comboDisciplina.addItem("ZUMBA");
		comboDisciplina.addItem("SALSA");
		comboDisciplina.addItem("CARAIBICHE");
		comboDisciplina.addItem("ALTRO");

		comboClasse.addItem("ED");
		comboClasse.addItem("RS");
		comboClasse.addItem("GS");
		comboClasse.addItem("ASSOLUTA");
		comboClasse.addItem("ALTRO");

		comboUnita.addItem("PG");
		comboUnita.addItem("GG");
		comboUnita.addItem("SOLO");
		comboUnita.addItem("DUO");
		comboUnita.addItem("ASSOLUTA");
		comboUnita.addItem("ALTRO");

	}		
}

