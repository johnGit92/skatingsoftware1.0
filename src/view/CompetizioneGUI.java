package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import dao.ClassificaDao;
import dao.IscrizioneDao;
import dao.Service;
import dao.ValutazioneDao;
import model.Classifica;
import model.Gruppo;
import model.Iscrizione;
import model.Valutazione;

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
					Map<String,List<Valutazione>> map=compController.generaClassifica(list);
					
					//ottieni lista gruppi con valutazioni
					List<Gruppo> gruppi=new ArrayList<Gruppo>();
					ValutazioneDao dao=Service.getValutazioneDao();
					int rows=table.getRowCount();
					int i=0;
					for(i=0;i<rows;i++) {
						int numero=Integer.parseInt((String) model.getValueAt(i, 0));
						Gruppo g=new Gruppo(numero);
						g.setValutazioni(dao.getValutazioni(numero)); System.out.println(g);
						gruppi.add(g);
					}
					
					//ordina gruppi per numero
					Collections.sort(gruppi, new Comparator<Gruppo>() {
						@Override
						public int compare(Gruppo o1, Gruppo o2) {
							if(o1.getNumero()>o2.getNumero()) return 1;
							else if(o1.getNumero()<o2.getNumero()) return -1;
							return 0;
						}
						
					});
					
					//genera classifiche parziali
					
					//classe interna locale usata per generare le classifiche per giudice
					class Parziale implements Comparable<Parziale>{
						String id;
						int numero;
						double tot;
						int posizione;
						boolean pari;
						
						public Parziale(String id, int numero, double tot, int posizione) {
							super();
							this.id = id;
							this.numero = numero;
							this.tot = tot;
							this.posizione = posizione;
							pari=false;
						}

						@Override
						public int compareTo(Parziale o) {
							if(tot<o.tot) return 1;
							else if(tot>o.tot) return -1;
							pari=true;
							o.pari=true;
							return 0;
						}

						@Override
						public String toString() {
							return "Parziale [id=" + id + ", numero=" + numero + ", tot=" + tot + ", posizione=" + posizione
									+ ", pari=" + pari + "]";
						}
					}
					
					//classifiche parziali
					List<Parziale> c1=new ArrayList<Parziale>();
					List<Parziale> c2=new ArrayList<Parziale>();
					List<Parziale> c3=new ArrayList<Parziale>();
					List<Parziale> c4=new ArrayList<Parziale>();
					List<Parziale> c5=new ArrayList<Parziale>();
					
					//genera classifiche parziali per ogni gruppo
					for(Gruppo g : gruppi) {
						Iterator<Valutazione> it = g.getValutazioni().iterator();
						
						Valutazione v1,v2,v3,v4,v5;
						v1=it.next();
						v2=it.next();
						v3=it.next();
						v4=it.next();
						v5=it.next();
						
						Parziale p1,p2,p3,p4,p5;
						double tot=v1.getCoreografico()+v1.getTecnico();
						p1=new Parziale(v1.getId(),g.getNumero(),(double) Math.round(tot * 10) / 10,0);
						
						tot=v2.getCoreografico()+v2.getTecnico();
						p2=new Parziale(v2.getId(),g.getNumero(),(double) Math.round(tot * 10) / 10,0);
						
						tot=v3.getCoreografico()+v3.getTecnico();
						p3=new Parziale(v3.getId(),g.getNumero(),(double) Math.round(tot * 10) / 10,0);
						
						tot=v4.getCoreografico()+v4.getTecnico();
						p4=new Parziale(v4.getId(),g.getNumero(),(double) Math.round(tot * 10) / 10,0);
						
						tot=v5.getCoreografico()+v5.getTecnico();
						p5=new Parziale(v5.getId(),g.getNumero(),(double) Math.round(tot * 10) / 10,0);
						
						c1.add(p1); c2.add(p2); c3.add(p3); c4.add(p4); c5.add(p5);
					}
					
					//ordina classifiche parziali
					Collections.sort(c1);
					Collections.sort(c2);
					Collections.sort(c3);
					Collections.sort(c4);
					Collections.sort(c5);
					
					//aggiorna posizioni classifiche parziali
					List<List<Parziale>> classifiche=new ArrayList<List<Parziale>>();
					classifiche.add(c1);
					classifiche.add(c2);
					classifiche.add(c3);
					classifiche.add(c4);
					classifiche.add(c5);
					for(List<Parziale> cp : classifiche) {					
						Iterator<Parziale> it=cp.iterator();
						int pos=1;
						Parziale p=it.next();
						while(p!=null) {
							p.posizione=pos;
							if(it.hasNext()) {
								Parziale next=it.next();
								if(p.tot!=next.tot) {
									pos++;
								}
								p=next;
							}
							else
								p=null;
						}
					}
					
					//ordina classifiche parziali per numero
					Comparator<Parziale> ordinaPerNumero=new Comparator<Parziale>() {

						@Override
						public int compare(Parziale o1, Parziale o2) {
							if(o1.numero>o2.numero) return 1;
							else if(o1.numero<o2.numero) return -1;
							return 0;
						}
						
					};
					for(List<Parziale> cp : classifiche) {
						Collections.sort(cp,ordinaPerNumero);
					}
					
					//merging classifiche parziali
					
					//iteratore lista di classifiche parziali
					Iterator<List<Parziale>> itClassifiche=classifiche.iterator();
					
					//Classifiche parziali
					List<Parziale> parziale1=itClassifiche.next();
					List<Parziale> parziale2=itClassifiche.next();
					List<Parziale> parziale3=itClassifiche.next();
					List<Parziale> parziale4=itClassifiche.next();
					List<Parziale> parziale5=itClassifiche.next();
					
					//iteratori classifiche parziali
					Iterator<Parziale> it1=parziale1.iterator();
					Iterator<Parziale> it2=parziale2.iterator();
					Iterator<Parziale> it3=parziale3.iterator();
					Iterator<Parziale> it4=parziale4.iterator();
					Iterator<Parziale> it5=parziale5.iterator();
					
					ClassificaDao classDao=Service.getClassificaDao();
					List<Classifica> classifica=new ArrayList<Classifica>();
					for(Gruppo g: gruppi) {
						Classifica row=new Classifica();
						row.setNumero(g.getNumero());
						row.setTotTecnico((double) Math.round(g.getTecnico() * 10) / 10);
						
						//Parziale 1
						Parziale p1=it1.next();
						String id1=p1.id;
						double tot1=p1.tot;
						int pos1=p1.posizione;
						
						//Parziale 2
						Parziale p2=it2.next();
						String id2=p2.id;
						double tot2=p2.tot;
						int pos2=p2.posizione;
						
						//Parziale 3
						Parziale p3=it3.next();
						String id3=p3.id;
						double tot3=p3.tot;
						int pos3=p3.posizione;
						
						//Parziale 4
						Parziale p4=it4.next();
						String id4=p4.id;
						double tot4=p4.tot;
						int pos4=p4.posizione;
						
						//Parziale 5
						Parziale p5=it5.next();
						String id5=p5.id;
						double tot5=p5.tot;
						int pos5=p5.posizione;
						
						//crea istanza classifica
						row.setId1(id1); row.setId2(id2); row.setId3(id3); row.setId4(id4); row.setId5(id5);
						row.setPos1(pos1); row.setPos2(pos2); row.setPos3(pos3); row.setPos4(pos4); row.setPos5(pos5);
						row.setTot1(tot1); row.setTot2(tot2); row.setTot3(tot3); row.setTot4(tot4); row.setTot5(tot5);
						row.setPt(pos1+pos2+pos3+pos4+pos5);
						
						classifica.add(row);
						
					}
					
					//ordina classifica
					Collections.sort(classifica);
					
					//aggiorna posizioni
					Iterator<Classifica> it=classifica.iterator();
					int pos=1;
					Classifica c=it.next();
					while(c!=null) {
						c.setPosFin(pos);
						if(it.hasNext()) {
							Classifica next=it.next();
							if(c.getPt()!=next.getPt() || c.getTotTecnico()!=next.getTotTecnico()) {
								pos++;
							}
							c=next;			
						}
						else
							c=null;	
					}
					
					//memorizza classifica nel database
					IscrizioneDao daoIscrizione=Service.getIscrizioneDao();
					for(Classifica cl : classifica) {
						cl.setAsd(daoIscrizione.retrieve(cl.getNumero()).getAsd());
						classDao.create(cl);
					}
					
					JOptionPane.showMessageDialog(null, "Classifica generata correttamente!", "Messaggio", JOptionPane.INFORMATION_MESSAGE);
					
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
