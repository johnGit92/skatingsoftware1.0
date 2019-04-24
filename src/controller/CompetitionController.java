package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

import dao.IscrizioneDao;
import dao.Service;
import dao.ValutazioneDao;
import model.ClassificaComplessiva;
import model.ClassificaParzialeGiudice;
import model.Competizione;
import model.Giudice;
import model.Gruppo;
import model.Iscrizione;
import model.Valutazione;

public class CompetitionController {

	/**
	 * Genera file csv dalla lista di gruppi gi� ordinata secondo classifica.
	 * @param gruppi lista di gruppi ordinata secondo classifica.
	 * @param disciplina disciplina competizione 
	 * @param classe classe competizione
	 * @param categoria categoria competizione
	 */
	public void generaCsvGruppi(List<Gruppo> gruppi, String categoria, String classe, String disciplina) {
		try {
			final String dir = System.getProperty("user.home");
			String path=dir+"/"+categoria+"_"+disciplina+"_"+classe+"_CLASSIFICA.csv";
			FileWriter file=new FileWriter(path);
			file.append("Numero,Tecnico,Coreografico\n");
			for(Gruppo g: gruppi) {
				file.append(g.getNumero()+","+Math.floor(g.getTecnico()*100)/100+","+Math.floor(g.getCoreografico()*100)/100);
				if(g.isPari())
					file.append(" (PARI)");
				file.append("\n");
			}
			file.close();
			JOptionPane.showMessageDialog(null, "CSV File created: "+path, "INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
			Desktop.getDesktop().open(new File(dir));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Genera una lista di valutazioni da un file csv.
	 * @param f file memorizzato precedentemente.
	 * @return lista di valutazioni da visualizzare in tabella.
	 */
	public List<Valutazione> caricaValutazioni(File f) {
		List<Valutazione> valutazioni=new ArrayList<Valutazione>();
		File file = new File(f.getAbsolutePath()); 
	    Scanner sc;
		try {
			sc = new Scanner(file);
		    
			//salta prima riga
			sc.useDelimiter("\\\n");
		    sc.next();
		    
		    //leggi file per riga ed ottieni campi valutazione
		    int numero;
			String id;
			double tecnico;
			double coreografico;
			String line; int begin=0,end;
		    while(sc.hasNext()) {
		    	line = sc.next(); //System.out.println(line);
		    	
		    	end=line.indexOf(",");
		    	numero=Integer.valueOf(line.substring(begin, end));
		    	line=line.substring(end+1); //System.out.println(line);
		    	
		    	end=line.indexOf(",");
		    	id=line.substring(begin, end);
		    	line=line.substring(end+1); //System.out.println(line);
		    	
		    	end=line.indexOf(",");
		    	tecnico=Double.valueOf(line.substring(begin, end));
		    	line=line.substring(end+1); //System.out.println(line);
		    	
		    	coreografico=Double.valueOf(line.substring(begin));
				
		    	valutazioni.add(new Valutazione(numero, id, tecnico, coreografico));
		    }
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return valutazioni;
	}

	/**
	 * Genera una lista di giudici da un file csv.
	 * @param f file contenente i giudici memorizzati precedentemente.
	 * @return lista di giudici da visualizzare in tabella.
	 */
	public List<Giudice> caricaGiudici(File f) {
		List<Giudice> giudici=new ArrayList<Giudice>();
		File file = new File(f.getAbsolutePath()); 
	    Scanner sc;
		try {
			sc = new Scanner(file);
		    
			//salta prima riga
			sc.useDelimiter("\\\n");
		    sc.next();
		    
		    //leggi file per riga ed ottieni campi valutazione
			String id,nome,cognome;
			String line; int begin=0,end;
		    while(sc.hasNext()) {
		    	line = sc.next(); //System.out.println(line);
		    	
		    	end=line.indexOf(",");
		    	id=line.substring(begin, end);
		    	line=line.substring(end+1); //System.out.println(line);
		    	
		    	end=line.indexOf(",");
		    	nome=line.substring(begin, end);
		    	line=line.substring(end+1); //System.out.println(line);
		    	
		    	end=line.indexOf(",");
		    	cognome=line.substring(begin);
				
		    	giudici.add(new Giudice(id, nome, cognome));
		    }
		} catch (FileNotFoundException e) {
			
			JOptionPane.showMessageDialog(null, "Lista giudici vuota!", "Attenzione", JOptionPane.INFORMATION_MESSAGE);
		}
		
		//ordina per nome
		Collections.sort(giudici, new Comparator<Giudice>() {
			@Override
			public int compare(Giudice arg0, Giudice arg1) {
				return arg0.getId().compareTo(arg1.getId());
			}
		});
		
		return giudici;
	}

	/**
	 * Memorizza in un file csv la lista di giudici inserita in tabella.
	 * @param giudici lista di giudici generata dalla tabella.
	 */
	public void salvaGiudici(List<Giudice> giudici) {
		try {
			final String dir = System.getProperty("user.home");
			String path=dir+"/GIUDICI.csv";
			FileWriter file=new FileWriter(path);
			String header="ID,Nome,Cognome";
			file.append(header);
			for(Giudice g: giudici) {
				file.append("\n"+g.getId()+",");
				file.append(g.getNome()+",");
				file.append(g.getCognome());
			}
			file.close();
			JOptionPane.showMessageDialog(null, "CSV File created: "+path, "INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
			Desktop.getDesktop().open(new File(dir));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
		}		
	}

	public void salvaValutazioni(List<Valutazione> valutazioni) {
		ValutazioneDao dao=Service.getValutazioneDao();
		for(Valutazione v: valutazioni) {
			dao.create(v); System.out.println(v);
		}
		
	}

	public List<Giudice> getAllGiudici() {
		return Service.getGiudiceDao().getAll();
	}

	public List<Valutazione> getValutazioni(int numero) {
		return Service.getValutazioneDao().getValutazioni(numero);
	}
	
	public void salvaIscrizione(Iscrizione iscrizione) {
		Service.getIscrizioneDao().create(iscrizione);
	}

	public List<Iscrizione> getIscrizioni() {
		IscrizioneDao dao=Service.getIscrizioneDao();
		List<Iscrizione> iscrizioni=dao.getAll();
		return iscrizioni;
	}

	public void deleteIscrizione(int numero) {
		IscrizioneDao dao=Service.getIscrizioneDao();
		dao.delete(numero);
		
	}

	public void update(Iscrizione iscrizione) {
		Service.getIscrizioneDao().update(iscrizione);		
	}

	public void aggiornaCompetizioni() {
		
		//ottieni lista competizioni
		List<Competizione>competizioni=getCompetizioni();
		
		//nel caso in cui non esistono competizioni vanno generate e ordinate		
		if(competizioni!=null) 
			deleteCompetizioni(competizioni);
		
		//genera e memorizza competizioni ordinate
		competizioni=generateCompetizioni();
		
		JOptionPane.showMessageDialog(null, competizioni.size()+" competizioni trovate!", "INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
		
	}

	/**
	 * Genera e ordina le competizioni.
	 * @return
	 */
	public List<Competizione> generateCompetizioni() {
		List<Competizione> competizioni=Service.getCompetizioneDao().generateCompetizioni();
		competizioni=ordinaCompetizioni(competizioni);
		for(Competizione c:competizioni)
			Service.getCompetizioneDao().create(c);
		
		return competizioni;
	}

	public void deleteCompetizioni(List<Competizione> competizioni) {
		Service.getCompetizioneDao().deleteAll(competizioni);
		
	}

	public List<Competizione> getCompetizioni() {
		return Service.getCompetizioneDao().getCompetizioni();
	}
	
	public List<Competizione> ordinaCompetizioni(List<Competizione> competizioni){
		Comparator<Competizione> comparator=new Comparator<Competizione>() {

			@Override
			public int compare(Competizione o1, Competizione o2) {
				
				//primo livello di ordinamento (categoria)
				if(o1.getCategoria().getVal()<o2.getCategoria().getVal())
					return -1;
				else if(o1.getCategoria().getVal()>o2.getCategoria().getVal())
					return 1;
				else {
					//secondo livello di ordinamento (disciplina)
					if(o1.getDisciplina().getVal()<o2.getDisciplina().getVal())
						return -1;
					else if(o1.getDisciplina().getVal()>o2.getDisciplina().getVal())
						return 1;
					else {
						//terzo livello di ordinamento (specialit�)
						if(o1.getSpecialita().getVal()<o2.getSpecialita().getVal())
							return -1;
						else if(o1.getSpecialita().getVal()>o2.getSpecialita().getVal())
							return 1;
						else {
							//quarto livello di ordinamento (classe)
							if(o1.getClasse().getVal()<o2.getClasse().getVal())
								return -1;
							else if(o1.getClasse().getVal()>o2.getClasse().getVal())
								return 1;
							else {
								//quinto livello di ordinamento (piccolo/grande gruppo)
								if(o1.getUnita().getVal()<o2.getUnita().getVal())
									return -1;
								else if(o1.getUnita().getVal()>o2.getUnita().getVal())
									return 1;								
							}
						}
					}
					
				}
				return 0;
			}
			
		};
		Collections.sort(competizioni,comparator);
		
		return competizioni;
	}
	
	/**
	 * Genera classifica dei gruppi in competizione ricevuti in input.
	 * @param gruppi gruppi in competizione.
	 * @return 
	 */
	public Map<String, List<Valutazione>> generaClassifica(List<Integer> gruppi) {
		
		//genera mappa numero gruppo-lista voti
		Map<Integer,List<Valutazione>> gruppiInCompetizione=new HashMap<Integer,List<Valutazione>>();
		for(Integer numero: gruppi) {
			gruppiInCompetizione.put(numero, Service.getValutazioneDao().getValutazioni(numero));
		}
		
		//ottieni giuria competizione
		List<String> giuria=new ArrayList<String>();
		Integer numero=gruppi.iterator().next();
		List<Valutazione> valutazioniGruppo=gruppiInCompetizione.get(numero);
		for(Valutazione v: valutazioniGruppo) {
			giuria.add(v.getId());
		}
		
		//genera mappa id giudice-lista voti ordinata (classifica per ogni giudice)
		Map<String,List<Valutazione>> classifiche=new HashMap<String,List<Valutazione>>();
		for(String id: giuria) {
			List<Valutazione> valutazioniGiudice=new ArrayList<Valutazione>();
			Set<Integer> keySet=gruppiInCompetizione.keySet();
			for(Integer key: keySet) {
				List<Valutazione> valutazioni=gruppiInCompetizione.get(key);
				for(Valutazione v: valutazioni) {
					if(v.getId().equals(id)) {
						valutazioniGiudice.add(v);
						break;
					}
				}
			}
			classifiche.put(id, valutazioniGiudice);
		}
		Set<String> keySet=classifiche.keySet();
		for(String id : keySet) {
			Collections.sort(classifiche.get(id));
		}
		
		List<ClassificaParzialeGiudice> test=new ArrayList<ClassificaParzialeGiudice>();
		for(String id : keySet) {
			test.add(new ClassificaParzialeGiudice(id, classifiche.get(id)));
		}
		for(ClassificaParzialeGiudice c : test) System.out.println(c);
		System.out.println(new ClassificaComplessiva(test));
		return classifiche;
	}
	
}
