package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import java.util.Map;
import java.util.Set;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

public class Classifica {

	private JFrame frmClassifica;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	private Map<String,String> gruppi;

	/**
	 * Create the application.
	 * @param iscrittiInCompetizione 
	 */
	public Classifica(Map<String, String> iscrittiInCompetizione) {
		gruppi=iscrittiInCompetizione;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClassifica = new JFrame();
		frmClassifica.setTitle("Classifica");
		frmClassifica.setBounds(100, 100, 1131, 645);
		frmClassifica.getContentPane().setBackground(new Color(37, 61, 105));
		frmClassifica.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Corbel", Font.PLAIN, 12));
		textField.setToolTipText("Categoria");
		textField.setText("Categoria");
		textField.setColumns(10);
		textField.setBounds(10, 11, 383, 28);
		frmClassifica.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Corbel", Font.PLAIN, 12));
		textField_1.setText("Specialit\u00E0");
		textField_1.setColumns(10);
		textField_1.setBounds(10, 51, 383, 28);
		frmClassifica.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Corbel", Font.PLAIN, 12));
		textField_2.setText("Disciplina");
		textField_2.setColumns(10);
		textField_2.setBounds(10, 91, 383, 28);
		frmClassifica.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Corbel", Font.PLAIN, 12));
		textField_3.setText("Classe");
		textField_3.setColumns(10);
		textField_3.setBounds(10, 131, 383, 28);
		frmClassifica.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Corbel", Font.PLAIN, 12));
		textField_4.setText("Unit\u00E0");
		textField_4.setColumns(10);
		textField_4.setBounds(10, 171, 383, 28);
		frmClassifica.getContentPane().add(textField_4);
		
		String column_names[]= {"Numero","ASD"};
		DefaultTableModel model=new DefaultTableModel(column_names,0);
		table = new JTable(model);
		table.setFont(new Font("Corbel", Font.PLAIN, 12));
		table.setBorder(UIManager.getBorder("Table.cellNoFocusBorder"));
		Set<String> keys=gruppi.keySet();
		for(String numero: keys) {
			model.addRow(new Object[] {numero,gruppi.get(numero)});
		}
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ISCRITTI", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		scrollPane.setBounds(10, 273, 947, 327);
		frmClassifica.getContentPane().add(scrollPane);
		
		JButton btnClassifica = new JButton("Classifica");
		btnClassifica.setBounds(967, 273, 89, 23);
		frmClassifica.getContentPane().add(btnClassifica);
	}

	public JFrame getFrmClassifica() {
		return frmClassifica;
	}

	public void setFrmClassifica(JFrame frmClassifica) {
		this.frmClassifica = frmClassifica;
	}
}
