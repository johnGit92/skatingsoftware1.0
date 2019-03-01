package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import controller.CompetitionController;
import controller.GUIController;
import dao.IscrizioneDao;
import dao.Service;
import dao.ValutazioneDao;
import model.Iscrizione;
import model.Valutazione;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.SwingConstants;

public class IscrizioniGUI {

	private JFrame frmNuovaCompetizione;
	private GUIController guiController;
	private CompetitionController compController;
	private JTable tableIscrizioni;
	private JTextField textSelezionati;

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
		frmNuovaCompetizione.setBounds(10, 10, 1102, 674);
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

		String column_names[]= {"Numero","ASD","Categoria","Specialità","Disciplina","Classe","Unità"};
		DefaultTableModel modelIscrizioni=new DefaultTableModel(column_names,0);
		tableIscrizioni = new JTable(modelIscrizioni);
		tableIscrizioni.setShowVerticalLines(true);
		tableIscrizioni.setShowHorizontalLines(true);
		tableIscrizioni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()>1) {
		        	ValutazioneDao dao=Service.getValutazioneDao();
		        	List<Valutazione> list=dao.getValutazioni(Integer.valueOf((String) modelIscrizioni.getValueAt(tableIscrizioni.getSelectedRow(), 0)));
		        	listModel.clear();
		        	for(Valutazione v:list) {
		        		listModel.addElement(v.toString());
		        	}
				}
			}
		});
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

		String column_names_giudici[]= {"ID","Nome","Cognome"};
		DefaultTableModel modelGiudici=new DefaultTableModel(column_names_giudici,0);

		JLabel label = new JLabel(new ImageIcon(System.getProperty("user.home")+"/icon_resized.png"));
		label.setBounds(996, 11, 80, 103);
		frmNuovaCompetizione.getContentPane().add(label);

		JButton btnVota = new JButton("Vota");
		btnVota.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnVota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tableIscrizioni.getSelectedRowCount()<1) {
					JOptionPane.showMessageDialog(null, "Nessun iscritto selezionato!", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
				}
				else if(tableIscrizioni.getSelectedRowCount()>1) {
					JOptionPane.showMessageDialog(null, "E' possibile valutare un iscritto per volta!", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int row=tableIscrizioni.getSelectedRow();
					String numero=(String) modelIscrizioni.getValueAt(row, 0);
					String asd=(String) modelIscrizioni.getValueAt(row, 1);
					guiController.showVotazioni(numero, asd, compController);					
				}
			}
		});
		btnVota.setBounds(957, 338, 100, 28);
		frmNuovaCompetizione.getContentPane().add(btnVota);
		
		JButton btnClassifica = new JButton("Competizione");
		btnClassifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		});
		btnClassifica.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnClassifica.setBounds(957, 378, 100, 28);
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
		
		JButton btnNuovo = new JButton("");
		btnNuovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guiController.showNuovaIscrizione();
			}
		});
		btnNuovo.setFont(new Font("Corbel", Font.PLAIN, 12));
		btnNuovo.setBounds(957, 263, 40, 40);
		btnNuovo.setIcon(new ImageIcon("icons/plus.png"));
		frmNuovaCompetizione.getContentPane().add(btnNuovo);
		
		JButton btnAggiorna = new JButton("");
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
							String.valueOf(i.getNumero()),i.getAsd(),String.valueOf(i.getCategoria().getVal()),
							String.valueOf(i.getSpecialita().getVal()),String.valueOf(i.getDisciplina().getVal()),
							String.valueOf(i.getClasse().getVal()),String.valueOf(i.getUnita().getVal())
					});
				}
				
				JOptionPane.showMessageDialog(null, iscrizioni.size()+" iscrizioni trovate!", "INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAggiorna.setBounds(957, 178, 40, 40);
		btnAggiorna.setIcon(new ImageIcon("icons/refresh.png"));
		frmNuovaCompetizione.getContentPane().add(btnAggiorna);
		
		//ottieni lista iscrizioni e riempi tabella
		List<Iscrizione> iscrizioni=compController.getIscrizioni();
		for(Iscrizione i:iscrizioni) {
			modelIscrizioni.addRow(new Object[] {
					String.valueOf(i.getNumero()),i.getAsd(),String.valueOf(i.getCategoria().getVal()),
					String.valueOf(i.getSpecialita().getVal()),String.valueOf(i.getDisciplina().getVal()),
					String.valueOf(i.getClasse().getVal()),String.valueOf(i.getUnita().getVal())
			});
		}

	}

	public JFrame getFrame() {
		return frmNuovaCompetizione;
	}

	public void setFrame(JFrame frame) {
		this.frmNuovaCompetizione = frame;
	}
}
