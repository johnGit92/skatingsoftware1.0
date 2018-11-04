package view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.CompetitionController;
import controller.GUIController;
import model.Giudice;
import model.Gruppo;
import model.Valutazione;
import java.awt.SystemColor;

public class NuovaCompetizione {

	private JFrame frmNuovaCompetizione;
	private GUIController guiController;
	private CompetitionController compController;
	private JTable table;
	private JTable table_1;

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
		frmNuovaCompetizione.setTitle("Skating Software 1.0 - Nuova Competizione");
		frmNuovaCompetizione.setBounds(100, 100, 672, 643);
		frmNuovaCompetizione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuovaCompetizione.getContentPane().setLayout(null);
		
		JComboBox<String> comboBoxCat = new JComboBox<String>();
		comboBoxCat.setBounds(10, 81, 211, 25);
		frmNuovaCompetizione.getContentPane().add(comboBoxCat);
		comboBoxCat.addItem("Seleziona Categoria...");
		comboBoxCat.addItem("UNDER 8");
		comboBoxCat.addItem("UNDER 11");
		comboBoxCat.addItem("UNDER 14");
		comboBoxCat.addItem("UNDER 16");
		
		JComboBox<String> comboBoxDis = new JComboBox<String>();
		comboBoxDis.setBounds(10, 113, 211, 25);
		frmNuovaCompetizione.getContentPane().add(comboBoxDis);
		comboBoxDis.addItem("Seleziona Disciplina...");
		comboBoxDis.addItem("HIP HOP");
		comboBoxDis.addItem("LATINO");
		comboBoxDis.addItem("COREOG A");
		comboBoxDis.addItem("SINCRO B");
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guiController.showMainMenu();
				frmNuovaCompetizione.setVisible(false);
			}
		});
		btnIndietro.setBounds(10, 11, 89, 28);
		frmNuovaCompetizione.getContentPane().add(btnIndietro);
		
		String column_names[]= {"Numero","Giudice","Tecnico","Coreografico"};
		DefaultTableModel model=new DefaultTableModel(column_names,0);
		table = new JTable(model);
		table.setBorder(UIManager.getBorder("Table.cellNoFocusBorder"));
		table.setBounds(10, 170, 234, 89);
		frmNuovaCompetizione.getContentPane().add(table);
		
		JScrollPane scrollPaneVal = new JScrollPane(table);
		scrollPaneVal.setBorder(UIManager.getBorder("Table.cellNoFocusBorder"));
		scrollPaneVal.setBounds(10, 211, 633, 387);
		frmNuovaCompetizione.getContentPane().add(scrollPaneVal);
		
		JButton btnValutaGruppo = new JButton("Aggiungi");
		btnValutaGruppo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model.addRow(new Object[]{"Nuova riga"});
			}
		});
		btnValutaGruppo.setBounds(10, 182, 83, 28);
		frmNuovaCompetizione.getContentPane().add(btnValutaGruppo);
		
		JButton btnElimina = new JButton("Elimina");
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
		btnElimina.setBounds(105, 182, 83, 28);
		frmNuovaCompetizione.getContentPane().add(btnElimina);
		
		JButton btnClassifica = new JButton("Classifica");
		btnClassifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//genera lista valutazioni dai dati inseriti in tabella.
				TableModel model=table.getModel();
				int rows=model.getRowCount(), index=0;
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
				List<Gruppo> gruppi=compController.generaGruppiConValutazioni(valutazioni);
				
				//genera csv con la classifica dei gruppi in competizione
				compController.generaCsvGruppi(gruppi);
			}
		});
		btnClassifica.setBounds(544, 182, 83, 28);
		frmNuovaCompetizione.getContentPane().add(btnClassifica);
		
		JButton btnSalva = new JButton("Salva");
		btnSalva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//genera lista valutazioni dai dati inseriti in tabella.
				TableModel model=table.getModel();
				int rows=model.getRowCount(), index=0;
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
				compController.salvaValutazioni(categoria,disciplina,valutazioni);
				
			}
		});
		btnSalva.setBounds(200, 182, 90, 28);
		frmNuovaCompetizione.getContentPane().add(btnSalva);
		
		JButton btnCaricaDaFile = new JButton("Carica");
		btnCaricaDaFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//seleziona file
				JFileChooser chooser=new JFileChooser();
				chooser.showOpenDialog(null);
				
				//ottieni categoria e disciplina
				File f=chooser.getSelectedFile();
				if(f!=null) {
					
					//pulisci tabella
					while(model.getRowCount()>0)
						model.removeRow(0);
					
					String name=f.getName();
					String cat=name.substring(0, name.indexOf("_"));
					String dis=name.substring(name.indexOf("_")+1, name.length()-4);
					
					//ricerca e seleziona categoria e disciplina
					int index=0,count=comboBoxCat.getItemCount();
					while(index++<count) {
						if(cat.equals(comboBoxCat.getItemAt(index)))
							comboBoxCat.setSelectedIndex(index);
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
				}
			}});
		
		btnCaricaDaFile.setBounds(302, 182, 90, 28);
		frmNuovaCompetizione.getContentPane().add(btnCaricaDaFile);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(UIManager.getBorder("Table.cellNoFocusBorder"));
		scrollPane.setBounds(246, 32, 397, 138);
		frmNuovaCompetizione.getContentPane().add(scrollPane);

		String column_names_giudici[]= {"ID","Nome","Cognome"};
		DefaultTableModel modelGiudici=new DefaultTableModel(column_names_giudici,0);
		table_1 = new JTable(modelGiudici);
		table_1.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		scrollPane.setViewportView(table_1);
		
		JLabel lblGiudiciDiGara = new JLabel("Giudici di Gara");
		lblGiudiciDiGara.setOpaque(true);
		lblGiudiciDiGara.setForeground(SystemColor.inactiveCaptionBorder);
		lblGiudiciDiGara.setBackground(SystemColor.activeCaption);
		lblGiudiciDiGara.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiudiciDiGara.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblGiudiciDiGara.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		lblGiudiciDiGara.setBounds(251, 11, 387, 25);
		frmNuovaCompetizione.getContentPane().add(lblGiudiciDiGara);
		
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
