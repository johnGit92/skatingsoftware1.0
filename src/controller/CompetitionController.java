package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import dao.IscrizioneDao;
import dao.Service;
import dao.ValutazioneDao;
import model.Giudice;
import model.Gruppo;
import model.Iscrizione;
import model.Valutazione;

public class CompetitionController {
	
	/**
	 * Genera la lista dei gruppi in competizione con le relative valutazioni.
	 * @param valutazioni lista di valutazioni ricavate dalla tabella.
	 * @param numeroGiudici numero giudici competizione.
	 * @return lista dei gruppi in competizione con relative valutazioni.
	 */
//	public List<Gruppo> generaGruppiConValutazioni(List<Valutazione> valutazioni, int numeroGiudici) {
//		
//		//ordina lista valutazioni per numero gruppo e id giudice.
//		Collections.sort(valutazioni,new Comparator<Valutazione>() {
//			@Override
//			public int compare(Valutazione arg0, Valutazione arg1) {
//				if(arg0.getNumero()<arg1.getNumero())return -1;
//				else if(arg0.getNumero()>arg1.getNumero())return 1;
//				else
//					return arg0.getId().compareTo(arg1.getId());
//			}
//		});
//		
//		//genera gruppi con valutazioni
//		int count=0; 
//		List<Gruppo> lista=new ArrayList<Gruppo>();
//		Gruppo g=null;
//		for(Valutazione v: valutazioni) {
//			
//			if(count%numeroGiudici==0) {
//				g=new Gruppo(v.getNumero()); //crea nuovo gruppo una volta inserite le valutazioni di tutti i giudici
//				lista.add(g); //aggiungi gruppo alla lista per la classifica
//			}
//			
//			g.getValutazioni().add(v); //aggiungi voti al gruppo
//			g.setCoreografico(g.getCoreografico()+v.getCoreografico()); //aggiorna complessivo coreografico
//			g.setTecnico(g.getTecnico()+v.getTecnico()); //aggiorna complessivo tecnico
//			
//			count++;
//		}
//		
//		//ordina lista gruppi secondo classifica
//		Collections.sort(lista);
//		
//		return lista;
//		
//	}

	/**
	 * Genera file csv dalla lista di gruppi già ordinata secondo classifica.
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
	 * Salva le valutazioni inserite in tabella in un file csv.
	 * @param categoria categoria competizione.
	 * @param disciplina disciplina competizione.
	 * @param valutazioni lista di valutazioni inserite in tabella.
	 */
//	public void salvaValutazioni(String categoria, String disciplina, String classe, List<Valutazione> valutazioni) {
//		try {
//			final String dir = System.getProperty("user.home");
//			String path=dir+"/"+categoria+"_"+disciplina+"_"+classe+".csv";
//			FileWriter file=new FileWriter(path);
//			String header="Numero,Giudice,Tecnico,Coreografico";
//			file.append(header);
//			for(Valutazione v: valutazioni) {
//				file.append("\n"+v.getNumero()+",");
//				file.append(v.getId()+",");
//				file.append(v.getTecnico()+",");
//				file.append(String.valueOf(v.getCoreografico()));
//			}
//			file.close();
//			JOptionPane.showMessageDialog(null, "CSV File created: "+path, "INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
//			Desktop.getDesktop().open(new File(dir));
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(null, e.toString(), "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
//		}		
//	}

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
	
}
