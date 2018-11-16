package view;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.CompetitionController;
import controller.GUIController;
import model.Giudice;
import model.Gruppo;
import model.Valutazione;
import java.awt.SystemColor;
import javax.swing.JPanel;

public class NuovaCompetizione {

	private JFrame frmNuovaCompetizione;
	private GUIController guiController;
	private CompetitionController compController;
	private JTable table;
	private JTable tableGiudici;

	/**
	 * Create the application.
	 */
	public NuovaCompetizione(GUIController controller, CompetitionController compCtrl) {
		guiController=controller;
		compController=compCtrl;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuovaCompetizione = new JFrame();
		frmNuovaCompetizione.setResizable(false);
		frmNuovaCompetizione.setTitle("Skating Software 1.0 - Nuova Competizione");
		frmNuovaCompetizione.setBounds(10, 10, 904, 587);
		frmNuovaCompetizione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuovaCompetizione.getContentPane().setLayout(null);
		frmNuovaCompetizione.getContentPane().setBackground(new Color(37, 61, 105));
		
		JComboBox<String> comboBoxCat = new JComboBox<String>();
		comboBoxCat.setBounds(233, 116, 211, 30);
		frmNuovaCompetizione.getContentPane().add(comboBoxCat);
		comboBoxCat.addItem("Seleziona Categoria...");
		comboBoxCat.addItem("UNDER 5");
		comboBoxCat.addItem("UNDER 7");
		comboBoxCat.addItem("UNDER 9");
		comboBoxCat.addItem("UNDER 11");
		comboBoxCat.addItem("UNDER 13");
		comboBoxCat.addItem("UNDER 16");
		comboBoxCat.addItem("UNDER 21");
		comboBoxCat.addItem("OVER 16");
		comboBoxCat.addItem("OVER 35");
		comboBoxCat.addItem("OVER 50 OPEN");
		comboBoxCat.addItem("OPEN DA ANNI 4 IN POI");
		comboBoxCat.addItem("ASSOLUTA");
		
		JComboBox<String> comboBoxClassi = new JComboBox<String>();
		comboBoxClassi.setBounds(10, 116, 211, 30);
		frmNuovaCompetizione.getContentPane().add(comboBoxClassi);
		comboBoxClassi.addItem("Seleziona Classe...");
		comboBoxClassi.addItem("ESORDIENTI");
		comboBoxClassi.addItem("DIVULGATIVA");
		comboBoxClassi.addItem("RISING STARS");
		comboBoxClassi.addItem("GOLD STARS");
		comboBoxClassi.addItem("OPEN");
				
		JComboBox<String> comboBoxDis = new JComboBox<String>();
		comboBoxDis.setBounds(456, 116, 211, 30);
		frmNuovaCompetizione.getContentPane().add(comboBoxDis);
		comboBoxDis.addItem("Seleziona Disciplina...");
		comboBoxDis.addItem("HOBBY DANCE A");
		comboBoxDis.addItem("HOBBY DANCE B");
		comboBoxDis.addItem("SINCRONIZZATO A");
		comboBoxDis.addItem("SINCRONIZZATO B");
		comboBoxDis.addItem("COREOGRAFICO A");
		comboBoxDis.addItem("COREOGRAFICO B");
		comboBoxDis.addItem("SHOW DANCE A");
		comboBoxDis.addItem("SHOW DANCE B");
		comboBoxDis.addItem("DANZE ETNICHE");
		comboBoxDis.addItem("FOLK");
		comboBoxDis.addItem("ORIENTALE");
		comboBoxDis.addItem("FLAMENCO");
		comboBoxDis.addItem("COUNTRY");
		comboBoxDis.addItem("SOLO");
		comboBoxDis.addItem("DUO");
		comboBoxDis.addItem("PICCOLO GRUPPO");
		comboBoxDis.addItem("GRANDE GRUPPO");
		comboBoxDis.addItem("PRODUCTION");
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guiController.showMainMenu();
				frmNuovaCompetizione.setVisible(false);
			}
		});
		btnIndietro.setBounds(10, 11, 89, 30);
		frmNuovaCompetizione.getContentPane().add(btnIndietro);
		
		String column_names[]= {"Numero","Giudice","Tecnico","Coreografico"};
		DefaultTableModel model=new DefaultTableModel(column_names,0);
		table = new JTable(model);
		table.setBorder(UIManager.getBorder("Table.cellNoFocusBorder"));
		table.setBounds(10, 170, 234, 89);
		frmNuovaCompetizione.getContentPane().add(table);
		
		JScrollPane scrollPaneVal = new JScrollPane(table);
		scrollPaneVal.setBorder(UIManager.getBorder("TitledBorder.border"));
		scrollPaneVal.setBounds(10, 224, 463, 320);
		frmNuovaCompetizione.getContentPane().add(scrollPaneVal);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel.setBounds(10, 186, 463, 45);
		frmNuovaCompetizione.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnValutaGruppo = new JButton("Aggiungi");
		btnValutaGruppo.setBounds(12, 6, 76, 28);
		panel.add(btnValutaGruppo);
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.setBounds(100, 6, 76, 28);
		panel.add(btnElimina);
		
		JButton btnClassifica = new JButton("Classifica");
		btnClassifica.setBounds(364, 6, 83, 28);
		panel.add(btnClassifica);
		
		JButton btnSalva = new JButton("Salva");
		btnSalva.setBounds(188, 6, 76, 28);
		panel.add(btnSalva);
		
		JButton btnCaricaDaFile = new JButton("Carica");
		btnCaricaDaFile.setBounds(276, 6, 76, 28);
		panel.add(btnCaricaDaFile);
		btnCaricaDaFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//seleziona file
				JFileChooser chooser=new JFileChooser();
				chooser.showOpenDialog(null);
				
				//ottieni categoria e disciplina
				File f=chooser.getSelectedFile();
				if(f!=null) {
					
					try {
						//pulisci tabella
						while(model.getRowCount()>0)
							model.removeRow(0);
						
						String name=f.getName();
						String cat=name.substring(0, name.indexOf("_"));
						
						name=name.substring(name.indexOf("_")+1);
						String dis=name.substring(0, name.indexOf("_"));
						
						name=name.substring(name.indexOf("_")+1);
						String classe=name.substring(0, name.length()-4);
						
						//ricerca e seleziona categoria, disciplina e classe
						int index=0,count=comboBoxCat.getItemCount();
						while(index++<count) {
							if(cat.equals(comboBoxCat.getItemAt(index)))
								comboBoxCat.setSelectedIndex(index);
						}
						index=0; count=comboBoxClassi.getItemCount();
						while(index++<count) {
							if(classe.equals(comboBoxClassi.getItemAt(index)))
								comboBoxClassi.setSelectedIndex(index);
						}
						index=0; count=comboBoxDis.getItemCount();
						while(index++<count) {
							if(dis.equals(comboBoxDis.getItemAt(index)))
								comboBoxDis.setSelectedIndex(index);
						}
						
						//carica dati in tabella
						List<Valutazione> valutazioni=compController.caricaValutazioni(f);
						for(Valutazione v: valutazioni) {
							model.addRow(new Object[] {String.valueOf(v.getNumero()),v.getId(),String.valueOf(v.getTecnico()),String.valueOf(v.getCoreografico())});
						}
					} catch (Exception e1) {
						
						JOptionPane.showMessageDialog(null, "Formato File non valido!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}});
		btnSalva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//genera lista valutazioni dai dati inseriti in tabella.
				TableModel model=table.getModel();
				int rows=model.getRowCount(), index=0;
				if(rows>0) {
					if(comboBoxCat.getSelectedItem().equals("Seleziona Categoria...")||comboBoxDis.getSelectedItem().equals("Seleziona Disciplina...")
							||comboBoxClassi.getSelectedItem().equals("Seleziona Classe...")) {
						JOptionPane.showMessageDialog(null, "Seleziona Categoria/Classe/Disciplina", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						List<Valutazione> valutazioni=new ArrayList<Valutazione>();
						int numero; String id; double tecnico,coreografico;
						while(index<rows) {
							numero=Integer.valueOf((String)model.getValueAt(index, 0));
							id=(String)model.getValueAt(index, 1);
							tecnico=Double.valueOf((String)model.getValueAt(index, 2));
							coreografico=Double.valueOf((String)model.getValueAt(index, 3));
							valutazioni.add(new Valutazione(numero, id, tecnico, coreografico));
							index++;
						}
						
						//salva valutazioni in un file csv
						String categoria=comboBoxCat.getItemAt(comboBoxCat.getSelectedIndex());
						String disciplina=comboBoxDis.getItemAt(comboBoxDis.getSelectedIndex());
						String classe=comboBoxClassi.getItemAt(comboBoxClassi.getSelectedIndex());
						compController.salvaValutazioni(categoria,disciplina,classe,valutazioni);							
					}				
				}
				else
					JOptionPane.showMessageDialog(null, "Tabella Vuota", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnClassifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				//numero di giudici selezionati per la competizione
				int numeroGiudici=tableGiudici.getSelectedRowCount();
				if(numeroGiudici==0) { //nessun giudice selezionato
					JOptionPane.showMessageDialog(null, "Selezionare giudici competizione", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					
					//genera lista valutazioni dai dati inseriti in tabella.
					TableModel model=table.getModel();
					int rows=model.getRowCount(), index=0;
					if(rows<(numeroGiudici*2)) {
						JOptionPane.showMessageDialog(null, "Voti mancanti", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						List<Valutazione> valutazioni=new ArrayList<Valutazione>();
						int numero; String id; double tecnico,coreografico;
						while(index<rows) {
							numero=Integer.valueOf((String)model.getValueAt(index, 0));
							id=(String)model.getValueAt(index, 1);
							tecnico=Double.valueOf((String)model.getValueAt(index, 2));
							coreografico=Double.valueOf((String)model.getValueAt(index, 3));
							valutazioni.add(new Valutazione(numero, id, tecnico, coreografico));
							index++;
						}
						
						
						//aggiungi valutazioni ai gruppi
						List<Gruppo> gruppi=compController.generaGruppiConValutazioni(valutazioni,numeroGiudici);
						
						//genera csv con la classifica dei gruppi in competizione
						String categoria=comboBoxCat.getItemAt(comboBoxCat.getSelectedIndex());
						String classe=comboBoxClassi.getItemAt(comboBoxClassi.getSelectedIndex());
						String disciplina=comboBoxDis.getItemAt(comboBoxDis.getSelectedIndex());
						compController.generaCsvGruppi(gruppi,categoria,classe,disciplina);					
					}					
				}
			}
		});
		btnElimina.addMouseListener(new MouseAdapter() {
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
		btnValutaGruppo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model.addRow(new Object[]{"Nuova riga"});
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Giudici di Gara", TitledBorder.CENTER, TitledBorder.TOP, null, SystemColor.text));
		scrollPane.setBounds(485, 219, 397, 325);
		frmNuovaCompetizione.getContentPane().add(scrollPane);

		String column_names_giudici[]= {"ID","Nome","Cognome"};
		DefaultTableModel modelGiudici=new DefaultTableModel(column_names_giudici,0);
		tableGiudici = new JTable(modelGiudici);
		tableGiudici.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()=='+') { //alla pressione del tasto '+'
					String NumeroGruppo=JOptionPane.showInputDialog("Numero Gruppo"); //acquisisce da input il numero del gruppo da valutare
					int[] rows=tableGiudici.getSelectedRows(); //giudici selezionati
					for(int index=0;index<rows.length;index++) { //per ciascun giudice selezionato aggiunge una riga per inserire i voti
						model.addRow(new Object[]{NumeroGruppo,modelGiudici.getValueAt(rows[index], 0)});
					}
				}
			}
		});
		tableGiudici.setBorder(null);
		scrollPane.setViewportView(tableGiudici);
		
		try {
			//carica dati in tabella giudici
			final String dir=System.getProperty("user.home");
			List<Giudice> giudici=compController.caricaGiudici(new File(dir+"/giudici.csv"));
			for(Giudice g: giudici) {
				modelGiudici.addRow(new Object[] {g.getId(),g.getNome(),g.getCognome()});
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.toString(), "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public JFrame getFrame() {
		return frmNuovaCompetizione;
	}

	public void setFrame(JFrame frame) {
		this.frmNuovaCompetizione = frame;
	}
}
