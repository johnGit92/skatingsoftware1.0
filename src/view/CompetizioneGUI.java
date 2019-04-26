package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.CompetitionController;
import dao.Service;
import model.Iscrizione;

public class CompetizioneGUI {

	private JFrame frmClassifica;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	private Map<String,String> gruppi;
	private CompetitionController compController;

	/**
	 * Create the application.
	 * @param iscrittiInCompetizione 
	 */
	public CompetizioneGUI(Map<String, String> iscrittiInCompetizione, CompetitionController compController) {
		gruppi=iscrittiInCompetizione;
		this.compController=compController;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClassifica = new JFrame();
		frmClassifica.setTitle("SkatingSoftware 1.0 - Competizione");
		frmClassifica.setBounds(100, 100, 989, 620);
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
		scrollPane.setBounds(10, 243, 850, 327);
		frmClassifica.getContentPane().add(scrollPane);
		
		//aggiorna textfield competizione
		Iterator<String> it=keys.iterator();
		int numero=Integer.valueOf(it.next());
		Iscrizione g=Service.getIscrizioneDao().retrieve(numero);
		textField.setText(g.getCategoria().name());
		textField_1.setText(g.getSpecialita().name());
		textField_2.setText(g.getDisciplina().name());
		textField_3.setText(g.getClasse().name());
		textField_4.setText(g.getUnita().name());
		
		JButton btnClassifica = new JButton("Classifica");
		btnClassifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					
					List<Integer> list=new ArrayList<Integer>();
					for(String key : gruppi.keySet()) {
						list.add(Integer.parseInt(key));
					}
					String categoria=textField.getText();
					String specialita=textField_1.getText();
					String disciplina=textField_2.getText();
					String classe=textField_3.getText();
					String unita=textField_4.getText();
					String filename=categoria+"_"+specialita+"_"+disciplina+"_"+classe+"_"+unita;
					compController.generaClassifica(list, filename);
					
					JOptionPane.showMessageDialog(null, "Classifica generata correttamente!","CONFERMA",JOptionPane.INFORMATION_MESSAGE);
					
					CompetizioneGUI.this.frmClassifica.dispose();
					
				}catch(Exception exc) {
					exc.printStackTrace();
				}	
			}
		});
		btnClassifica.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnClassifica.setBounds(870, 243, 89, 28);
		frmClassifica.getContentPane().add(btnClassifica);
	}

	public JFrame getFrmClassifica() {
		return frmClassifica;
	}

	public void setFrmClassifica(JFrame frmClassifica) {
		this.frmClassifica = frmClassifica;
	}
}
