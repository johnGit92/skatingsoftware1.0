package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.CompetitionController;
import controller.GUIController;
import controller.ReportController;
import model.Categoria;
import model.Classe;
import model.Disciplina;
import model.Iscrizione;
import model.Specialita;
import model.Unita;

public class IscrizioniGUI {

	private JFrame frmNuovaCompetizione;
	private GUIController guiController;
	private CompetitionController compController;
	private JTable tableIscrizioni;
	private JTextField textSelezionati;
	private static JButton btnAggiorna;

	/**
	 * Create the application.
	 */
	public IscrizioniGUI(GUIController controller, CompetitionController compCtrl) {
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
		frmNuovaCompetizione.setTitle("Skating Software 1.0 - Competizione");
		frmNuovaCompetizione.setBounds(10, 10, 1159, 674);
		frmNuovaCompetizione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuovaCompetizione.getContentPane().setLayout(null);
		frmNuovaCompetizione.getContentPane().setBackground(new Color(37, 61, 105));

		JButton btnIndietro = new JButton("");
		btnIndietro.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guiController.showMainMenu();
				frmNuovaCompetizione.setVisible(false);
			}
		});
		btnIndietro.setBounds(10, 11, 40, 40);
		btnIndietro.setIcon(new ImageIcon("icons/leftArrow.png"));		
		frmNuovaCompetizione.getContentPane().add(btnIndietro);

		DefaultListModel<String> listModel=new DefaultListModel<>();

		String column_names[]= {"Numero","ASD","Categoria","Specialita","Disciplina","Classe","Unita"};
		final DefaultTableModel modelIscrizioni=new DefaultTableModel(column_names,0);
		tableIscrizioni = new JTable(modelIscrizioni);
		tableIscrizioni.setShowVerticalLines(true);
		tableIscrizioni.setShowHorizontalLines(true);
//		tableIscrizioni.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(e.getClickCount()>1) {
//					ValutazioneDao dao=Service.getValutazioneDao();
//					List<Valutazione> list=dao.getValutazioni(Integer.valueOf((String) modelIscrizioni.getValueAt(tableIscrizioni.getSelectedRow(), 0)));
//					listModel.clear();
//					for(Valutazione v:list) {
//						listModel.addElement(v.toString());
//					}
//				}
//			}
//		});
		tableIscrizioni.setFont(new Font("Corbel", Font.PLAIN, 12));
		tableIscrizioni.setBorder(UIManager.getBorder("Table.cellNoFocusBorder"));
		tableIscrizioni.setBounds(10, 170, 234, 89);
		tableIscrizioni.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					textSelezionati.setText(String.valueOf(tableIscrizioni.getSelectedRowCount()));
				}
			}
		});
		frmNuovaCompetizione.getContentPane().add(tableIscrizioni);

		JScrollPane scrollPaneVal = new JScrollPane(tableIscrizioni);
		scrollPaneVal.setBorder(new TitledBorder(null, "ISCRITTI", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		scrollPaneVal.setBounds(10, 129, 947, 504);
		frmNuovaCompetizione.getContentPane().add(scrollPaneVal);

		JLabel label = new JLabel(new ImageIcon("icons/icon_resized.png"));
		label.setBounds(996, 11, 80, 103);
		frmNuovaCompetizione.getContentPane().add(label);

		JButton btnVota = new JButton("");
		btnVota.setToolTipText("Inserisci Voti");
		btnVota.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnVota.setIcon(new ImageIcon("icons/vote.png"));
		btnVota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tableIscrizioni.getSelectedRowCount()<1) {
					JOptionPane.showMessageDialog(null, "Nessun iscritto selezionato!", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
				}
				else {
					List<Iscrizione> selezionati=new ArrayList<Iscrizione>();
					int[] rows=tableIscrizioni.getSelectedRows();
					int i=0;
					for(i=0;i<rows.length;i++) {
						Iscrizione selezione=new Iscrizione();
						selezione.setNumero(Integer.parseInt((String)modelIscrizioni.getValueAt(rows[i], 0)));
						selezione.setAsd((String)modelIscrizioni.getValueAt(rows[i], 1));
						selezionati.add(selezione);
					}
					guiController.showVotazioni(selezionati, compController,guiController);					
				}
			}
		});
		btnVota.setBounds(957, 440, 40, 40);
		frmNuovaCompetizione.getContentPane().add(btnVota);

		JButton btnClassifica = new JButton("");
		btnClassifica.setToolTipText("Competizione");
		btnClassifica.setIcon(new ImageIcon("icons/competition.png"));
		btnClassifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tableIscrizioni.getSelectedRowCount()<1) {
					JOptionPane.showMessageDialog(null, "Nessun iscritto selezionato!", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
				}
				else {
					Map<String,String> iscrittiInCompetizione=new HashMap<String,String>();
					int[] rows=tableIscrizioni.getSelectedRows();
					int i=0;
					String numero,asd;
					for(i=0;i<rows.length;i++) {
						numero=(String) modelIscrizioni.getValueAt(rows[i], 0);
						asd=(String) modelIscrizioni.getValueAt(rows[i], 1);
						iscrittiInCompetizione.put(numero, asd);
					}
					guiController.showCompetizione(iscrittiInCompetizione);					
				}
			}
		});
		btnClassifica.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnClassifica.setBounds(957, 494, 40, 40);
		frmNuovaCompetizione.getContentPane().add(btnClassifica);

		JLabel lblSelezionati = new JLabel("Selezionati");
		lblSelezionati.setFont(new Font("Corbel", Font.PLAIN, 12));
		lblSelezionati.setForeground(Color.WHITE);
		lblSelezionati.setBounds(30, 101, 69, 16);
		frmNuovaCompetizione.getContentPane().add(lblSelezionati);

		textSelezionati = new JTextField();
		textSelezionati.setEditable(false);
		textSelezionati.setHorizontalAlignment(SwingConstants.CENTER);
		textSelezionati.setFont(new Font("Corbel", Font.PLAIN, 12));
		textSelezionati.setBounds(89, 94, 57, 25);
		frmNuovaCompetizione.getContentPane().add(textSelezionati);
		textSelezionati.setColumns(10);

		btnAggiorna = new JButton("");
		btnAggiorna.setToolTipText("Aggiorna");
		btnAggiorna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//ottieni lista iscrizioni e aggiorna tabella
				while(tableIscrizioni.getRowCount()>0) {
					modelIscrizioni.removeRow(0);					
				}
				List<Iscrizione> iscrizioni=compController.getIscrizioni();
				for(Iscrizione i:iscrizioni) {
					modelIscrizioni.addRow(new Object[] {
							String.valueOf(i.getNumero()),i.getAsd(),i.getCategoria().name(),i.getSpecialita().name(),i.getDisciplina().name(),
							i.getClasse().name(),i.getUnita().name()
					});
				}

				JOptionPane.showMessageDialog(null, iscrizioni.size()+" iscrizioni trovate!", "INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAggiorna.setBounds(957, 178, 40, 40);
		btnAggiorna.setIcon(new ImageIcon("icons/refresh.png"));
		frmNuovaCompetizione.getContentPane().add(btnAggiorna);

		JButton btnNuovo = new JButton("");
		btnNuovo.setToolTipText("Nuova Iscrizione");
		btnNuovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guiController.showNuovaIscrizione();
			}
		});
		btnNuovo.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnNuovo.setBounds(957, 303, 40, 40);
		btnNuovo.setIcon(new ImageIcon("icons/plus.png"));
		frmNuovaCompetizione.getContentPane().add(btnNuovo);

		JButton deleteButton = new JButton("");
		deleteButton.setToolTipText("Cancella Iscrizione");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int opt=JOptionPane.showConfirmDialog(null, "Sei sicuro di voler procedere con l'eliminazione?", "Attenzione", JOptionPane.YES_NO_CANCEL_OPTION);
				if(opt==JOptionPane.YES_OPTION) {
					int[] selectedRows=tableIscrizioni.getSelectedRows();
					int i=0;
					for(i=0;i<selectedRows.length;i++) {
						int numero=Integer.valueOf(((String)tableIscrizioni.getValueAt(selectedRows[i], 0)).trim());					
						compController.deleteIscrizione(numero);
					}

					IscrizioniGUI.update(e);					
				}			
			}
		});
		deleteButton.setBounds(957, 354, 40, 40);
		deleteButton.setIcon(new ImageIcon("icons/delete.png"));
		frmNuovaCompetizione.getContentPane().add(deleteButton);

		JButton editButton = new JButton("");
		editButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//wrap subscription information
				int row=tableIscrizioni.getSelectedRow();
				int numero=Integer.valueOf(String.valueOf(modelIscrizioni.getValueAt(row, 0)));
				String asd=String.valueOf(modelIscrizioni.getValueAt(row, 1));
				String categoria=String.valueOf(modelIscrizioni.getValueAt(row, 2));
				String specialita=String.valueOf(modelIscrizioni.getValueAt(row, 3));
				String disciplina=String.valueOf(modelIscrizioni.getValueAt(row, 4));
				String classe=String.valueOf(modelIscrizioni.getValueAt(row, 5));
				String unita=String.valueOf(modelIscrizioni.getValueAt(row, 6));
				Iscrizione iscrizione=new Iscrizione(asd, numero, Categoria.valueOf(categoria), Specialita.valueOf(specialita), 
						Disciplina.valueOf(disciplina), Unita.valueOf(unita), Classe.valueOf(classe));

				guiController.showModificaIscrizione(iscrizione);
			}
		});
		editButton.setToolTipText("Modifica Iscrizione");
		editButton.setIcon(new ImageIcon("icons/edit.png"));
		editButton.setBounds(957, 253, 40, 40);
		frmNuovaCompetizione.getContentPane().add(editButton);
		
		JButton printButton = new JButton("");
		printButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				compController.aggiornaCompetizioni();
				ReportController.showReports();
			}
		});
		printButton.setToolTipText("Stampa Cedolini");
		printButton.setBounds(957, 561, 40, 40);
		printButton.setIcon(new ImageIcon("icons/print.png"));
		frmNuovaCompetizione.getContentPane().add(printButton);
		
		JLabel lblAggiorna = new JLabel("Aggiorna");
		lblAggiorna.setForeground(Color.WHITE);
		lblAggiorna.setBounds(1000, 188, 55, 16);
		frmNuovaCompetizione.getContentPane().add(lblAggiorna);
		
		JLabel lblModificaIscrizione = new JLabel("Modifica Iscrizione");
		lblModificaIscrizione.setForeground(Color.WHITE);
		lblModificaIscrizione.setBounds(1000, 263, 101, 16);
		frmNuovaCompetizione.getContentPane().add(lblModificaIscrizione);
		
		JLabel lblNuovaIscrizione = new JLabel("Nuova Iscrizione");
		lblNuovaIscrizione.setForeground(Color.WHITE);
		lblNuovaIscrizione.setBounds(1000, 313, 91, 16);
		frmNuovaCompetizione.getContentPane().add(lblNuovaIscrizione);
		
		JLabel lblCancellaIscrizione = new JLabel("Cancella Iscrizione");
		lblCancellaIscrizione.setForeground(Color.WHITE);
		lblCancellaIscrizione.setBounds(1000, 364, 104, 16);
		frmNuovaCompetizione.getContentPane().add(lblCancellaIscrizione);
		
		JLabel lblInserisciVoti = new JLabel("Inserisci Voti");
		lblInserisciVoti.setForeground(Color.WHITE);
		lblInserisciVoti.setBounds(1000, 450, 69, 16);
		frmNuovaCompetizione.getContentPane().add(lblInserisciVoti);
		
		JLabel lblCompetizione = new JLabel("Competizione");
		lblCompetizione.setForeground(Color.WHITE);
		lblCompetizione.setBounds(1000, 504, 76, 16);
		frmNuovaCompetizione.getContentPane().add(lblCompetizione);
		
		JLabel lblStampaCedolini = new JLabel("Stampa Cedolini");
		lblStampaCedolini.setForeground(Color.WHITE);
		lblStampaCedolini.setBounds(1000, 571, 91, 16);
		frmNuovaCompetizione.getContentPane().add(lblStampaCedolini);

		//ottieni lista iscrizioni e riempi tabella
		List<Iscrizione> iscrizioni=compController.getIscrizioni();
		for(Iscrizione i:iscrizioni) {
			modelIscrizioni.addRow(new Object[] {
					String.valueOf(i.getNumero()),i.getAsd(),i.getCategoria().name(),i.getSpecialita().name(),
					i.getDisciplina().name(),i.getClasse().name(),i.getUnita().name()
			});
		}

	}

	public JFrame getFrame() {
		return frmNuovaCompetizione;
	}

	public void setFrame(JFrame frame) {
		this.frmNuovaCompetizione = frame;
	}

	/**
	 * Aggiorna tabella iscrizioni tramite il pulsante aggiorna.
	 * @param e click event.
	 */
	public static void update(MouseEvent e) {

		//click pulsante aggiorna
		MouseListener[] aggiornaListeners=btnAggiorna.getMouseListeners();
		for(MouseListener m: aggiornaListeners) {
			m.mouseClicked(e);
		}				
	}
}
