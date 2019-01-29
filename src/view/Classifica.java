package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.CompetitionController;
import dao.ClassificaDao;
import dao.Service;
import dao.ValutazioneDao;
import model.ClassificaUtil;
import model.Gruppo;
import model.Valutazione;

import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		btnClassifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Gruppo> gruppi=new ArrayList<Gruppo>();
				ValutazioneDao dao=Service.getValutazioneDao();
				int rows=table.getRowCount();
				int i=0;
				for(i=0;i<rows;i++) {
					int numero=Integer.parseInt((String) model.getValueAt(i, 0));
					Gruppo g=new Gruppo(numero);
					g.setValutazioni(dao.getValutazioni(numero)); //System.out.println(g);
					gruppi.add(g);
				}
				
				List<ClassificaUtil> utils1=new ArrayList<ClassificaUtil>(); //classifica giudice 1
				List<ClassificaUtil> utils2=new ArrayList<ClassificaUtil>(); //classifica giudice 2
				List<ClassificaUtil> utils3=new ArrayList<ClassificaUtil>(); //classifica giudice 3
				List<ClassificaUtil> utils4=new ArrayList<ClassificaUtil>(); //classifica giudice 4
				List<ClassificaUtil> utils5=new ArrayList<ClassificaUtil>(); //classifica giudice 5
				
				ClassificaUtil c1=new ClassificaUtil();
				ClassificaUtil c2=new ClassificaUtil(); 
				ClassificaUtil c3=new ClassificaUtil(); 
				ClassificaUtil c4=new ClassificaUtil(); 
				ClassificaUtil c5=new ClassificaUtil(); 
				
				List<model.Classifica> listClassifica=new ArrayList<model.Classifica>();
				
				ClassificaDao daoClass=Service.getClassificaDao();
				for(Gruppo g:gruppi) {
					
					model.Classifica c=new model.Classifica();
					List<Valutazione> list=g.getValutazioni();
					Iterator<Valutazione> it=list.iterator();
					
					c.setNumero(g.getNumero());
					
					Valutazione v1=it.next();
					c.setId1(v1.getId());
					c.setTot1(v1.getTecnico()+v1.getCoreografico());
					
					//gruppo 1 giudice 1
					c1.setTot(v1.getCoreografico()+v1.getTecnico());
					c1.setNumero(g.getNumero());
					utils1.add(c1);
					
					Valutazione v2=it.next();
					c.setId2(v2.getId());
					c.setTot2(v2.getTecnico()+v2.getCoreografico());
					
					//gruppo 2 giudice 2
					c2.setTot(v2.getCoreografico()+v2.getTecnico());
					c2.setNumero(g.getNumero());
					utils2.add(c2);
					
					Valutazione v3=it.next();
					c.setId3(v3.getId());
					c.setTot3(v3.getTecnico()+v3.getCoreografico());
					
					//gruppo 3 giudice 3
					c3.setTot(v3.getCoreografico()+v3.getTecnico());
					c3.setNumero(g.getNumero());
					utils3.add(c3);
					
					Valutazione v4=it.next();
					c.setId4(v4.getId());
					c.setTot4(v4.getTecnico()+v4.getCoreografico());
					
					//gruppo 4 giudice 4
					c4.setTot(v4.getCoreografico()+v4.getTecnico());
					c4.setNumero(g.getNumero());
					utils4.add(c4);
					
					Valutazione v5=it.next();
					c.setId5(v5.getId());
					c.setTot5(v5.getTecnico()+v5.getCoreografico());
					
					//gruppo 5 giudice 5
					c5.setTot(v5.getCoreografico()+v5.getTecnico());
					c5.setNumero(g.getNumero());
					utils5.add(c5);
					
					listClassifica.add(c); System.out.println(c);
				}
				
				Collections.sort(utils1);
				for(ClassificaUtil cu : utils1) {
					System.out.println(cu);
				}
			}
		});
		btnClassifica.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnClassifica.setBounds(967, 273, 89, 28);
		frmClassifica.getContentPane().add(btnClassifica);
	}

	public JFrame getFrmClassifica() {
		return frmClassifica;
	}

	public void setFrmClassifica(JFrame frmClassifica) {
		this.frmClassifica = frmClassifica;
	}
}
