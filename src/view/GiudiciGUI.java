package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.CompetitionController;
import controller.GUIController;
import model.Giudice;

public class GiudiciGUI {

	private JFrame frame;
	private JTable table;
	protected GUIController guiController;
	private CompetitionController compController;

	public GiudiciGUI(GUIController controller, CompetitionController compCtrl) {
		guiController=controller;
		compController=compCtrl;
		initialize();
	}

	//Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Skating Software 1.0 - Giudici");
		frame.setBounds(10, 10, 659, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(37, 61, 105));
		
		JButton button = new JButton("Indietro");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				guiController.showMainMenu();
				frame.setVisible(false);
			}
		});
		button.setBounds(10, 11, 89, 30);
		frame.getContentPane().add(button);
		
		String column_names[]= {"ID","Nome","Cognome"};
		DefaultTableModel model=new DefaultTableModel(column_names,0);
		table = new JTable(model);
		table.setBounds(10, 170, 234, 89);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(UIManager.getBorder("TitledBorder.border"));
		scrollPane.setBounds(10, 151, 633, 316);
		frame.getContentPane().add(scrollPane);
		
		try {
			//carica dati in tabella giudici
			final String dir=System.getProperty("user.home");
			List<Giudice> giudici=compController.caricaGiudici(new File(dir+"/giudici.csv"));
			for(Giudice g: giudici) {
				model.addRow(new Object[] {g.getId(),g.getNome(),g.getCognome()});
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.toString(), "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel.setOpaque(false);
		panel.setBounds(10, 116, 382, 38);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton button_1 = new JButton("Aggiungi");
		button_1.setBounds(7, 5, 83, 28);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Elimina");
		button_2.setBounds(97, 5, 83, 28);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Salva");
		button_3.setBounds(187, 5, 90, 28);
		panel.add(button_3);
		
		JButton button_4 = new JButton("Carica");
		button_4.setBounds(284, 5, 90, 28);
		panel.add(button_4);
		
		JLabel label = new JLabel(new ImageIcon(System.getProperty("user.home")+"/icon_resized.png"));
		label.setBounds(563, 6, 80, 103);
		frame.getContentPane().add(label);
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//seleziona file
				JFileChooser chooser=new JFileChooser();
				chooser.showOpenDialog(null);
				
				//ottieni categoria e disciplina
				File f=chooser.getSelectedFile();
				if(f!=null) {
					
					try {
						//carica dati in tabella
						List<Giudice> giudici=compController.caricaGiudici(f);
						for(Giudice g: giudici) {
							model.addRow(new Object[] {g.getId(),g.getNome(),g.getCognome()});
						}
					} catch (Exception e1) {
						
						JOptionPane.showMessageDialog(null, "Formato File non valido!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//genera lista valutazioni dai dati inseriti in tabella.
				TableModel model=table.getModel();
				int rows=model.getRowCount(), index=0;
				List<Giudice> giudici=new ArrayList<Giudice>();
				String id,nome,cognome;
				while(index<rows) {
					id=(String)model.getValueAt(index, 0);
					nome=(String)model.getValueAt(index, 1);
					cognome=(String)model.getValueAt(index, 2);
					giudici.add(new Giudice(id, nome, cognome));
					index++;
				}
				
				compController.salvaGiudici(giudici);
			}
		});
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
				else
					JOptionPane.showMessageDialog(null, "Nessuna riga selezionata!", "Attenzione", JOptionPane.WARNING_MESSAGE);
			}
		});
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model.addRow(new Object[]{"Nuova riga"});
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frmGiudici) {
		this.frame = frmGiudici;
	}
}
