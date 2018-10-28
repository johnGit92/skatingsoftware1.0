package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.CompetitionController;
import controller.GUIController;
import model.Giudice;

public class Giudici {

	private JFrame frame;
	private JTable table;
	protected GUIController guiController;
	private CompetitionController compController;

	public Giudici(GUIController controller, CompetitionController compCtrl) {
		guiController=controller;
		compController=compCtrl;
		initialize();
	}

	//Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Skating Software 1.0 - Giudici");
		frame.setBounds(100, 100, 670, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("Indietro");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				guiController.showMainMenu();
				frame.setVisible(false);
			}
		});
		button.setBounds(10, 11, 89, 28);
		frame.getContentPane().add(button);
		
		String column_names[]= {"ID","Nome","Cognome"};
		DefaultTableModel model=new DefaultTableModel(column_names,0);
		table = new JTable(model);
		table.setBounds(10, 170, 234, 89);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 113, 633, 387);
		frame.getContentPane().add(scrollPane);
		
		JButton button_1 = new JButton("Aggiungi");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model.addRow(new Object[]{"Nuova riga"});
			}
		});
		button_1.setBounds(10, 74, 83, 28);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Elimina");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListSelectionModel sm = table.getSelectionModel();
				int index0 = sm.getMinSelectionIndex();
				if(index0!=-1) {
					int index1=sm.getMaxSelectionIndex();
					if(index1!=-1) {
						if(index1==index0) {
							model.removeRow(index0);
						}
						else {
							int count=index1-index0+1;
							while(count-->0)
								model.removeRow(sm.getMaxSelectionIndex());
						}
					}
				}
			}
		});
		button_2.setBounds(105, 74, 83, 28);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("Salva");
		button_3.setBounds(200, 74, 90, 28);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("Carica");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//seleziona file
				JFileChooser chooser=new JFileChooser();
				chooser.showOpenDialog(null);
				
				//ottieni categoria e disciplina
				File f=chooser.getSelectedFile();
				if(f!=null) {
					
					//carica dati in tabella
					List<Giudice> giudici=compController.caricaGiudici(f);
					for(Giudice g: giudici) {
						model.addRow(new Object[] {g.getId(),g.getNome(),g.getCognome()});
					}
				}
			}
		});
		button_4.setBounds(302, 74, 90, 28);
		frame.getContentPane().add(button_4);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frmGiudici) {
		this.frame = frmGiudici;
	}
}
