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

import model.Giudice;
import model.Gruppo;
import model.Valutazione;

public class CompetitionController {

//	Testing method
//	public List<Valutazione> generaValutazioni() {
//		List<Valutazione> valutazioni=new ArrayList<Valutazione>();
//		valutazioni.add(new Valutazione(1, "A", 5.6, 5.9));
//		valutazioni.add(new Valutazione(1, "B", 6, 6));
//		valutazioni.add(new Valutazione(1, "C", 5.7, 5.8));
//		valutazioni.add(new Valutazione(1, "E", 6.0, 6.0));
//			
//		valutazioni.add(new Valutazione(2, "A", 5.7, 5.7));
//		valutazioni.add(new Valutazione(2, "B", 5.5, 5.5));
//		valutazioni.add(new Valutazione(2, "C", 5.6, 5.5));
//		valutazioni.add(new Valutazione(2, "D", 5.8, 5.6));
//		valutazioni.add(new Valutazione(2, "E", 6.0, 6.0));
//		valutazioni.add(new Valutazione(1, "D", 5.6, 6.0));			
//		
//		valutazioni.add(new Valutazione(3, "A", 5.5, 5.9));
//		valutazioni.add(new Valutazione(3, "B", 5.7, 5.6));
//		valutazioni.add(new Valutazione(3, "C", 5.8, 5.9));
//		valutazioni.add(new Valutazione(3, "D", 5.9, 5.9));
//		valutazioni.add(new Valutazione(3, "E", 6.0, 5.9));
//		
////		System.out.println("Before Sorting");
////		for(Valutazione v: valutazioni) {
////			System.out.println(v);
////		}
//		
////		System.out.println("\nAfter Sorting");
////		for(Valutazione v: valutazioni) {
////			System.out.println(v);
////		}
//		
//		return valutazioni;
//	}
	
	/**
	 * Genera la lista dei gruppi in competizione con le relative valutazioni.
	 * @param valutazioni lista di valutazioni ricavate dalla tabella.
	 * @return lista dei gruppi in competizione con relative valutazioni.
	 */
	public List<Gruppo> generaGruppiConValutazioni(List<Valutazione> valutazioni) {
		
		//ordina lista valutazioni per numero e id giudice.
		Collections.sort(valutazioni,new Comparator<Valutazione>() {
			@Override
			public int compare(Valutazione arg0, Valutazione arg1) {
				if(arg0.getNumero()<arg1.getNumero())return -1;
				else if(arg0.getNumero()>arg1.getNumero())return 1;
				else
					return arg0.getId().compareTo(arg1.getId());
			}
		});
		
		//genera gruppi con valutazioni ordinati secondo classifica.
		int count=0; 
		List<Gruppo> lista=new ArrayList<Gruppo>();
		Gruppo g=null;
		for(Valutazione v: valutazioni) {
			if(count==0) {
				g=new Gruppo(v.getNumero());
				g.getValutazioni().add(v);
				g.setCoreografico(g.getCoreografico()+v.getCoreografico());
				g.setTecnico(g.getTecnico()+v.getTecnico());
				lista.add(g);
			}
			else if(count%2!=0) {//modificare per 5 giudici count%5
				g.getValutazioni().add(v);
				g.setCoreografico(g.getCoreografico()+v.getCoreografico());
				g.setTecnico(g.getTecnico()+v.getTecnico());
			}
			else {
				g=new Gruppo(v.getNumero()); //crea gruppo ogni 2 valutazioni (2 giudici)
				g.getValutazioni().add(v);
				g.setCoreografico(g.getCoreografico()+v.getCoreografico());
				g.setTecnico(g.getTecnico()+v.getTecnico());
				lista.add(g);
			}
			
			count++;
		}
		
		Collections.sort(lista);
		
		return lista;
		
	}

	/**
	 * Genera file csv dalla lista di gruppi già ordinata secondo classifica.
	 * @param gruppi lista di gruppi ordinata secondo classifica.
	 */
	public void generaCsvGruppi(List<Gruppo> gruppi) {
		try {
			final String dir = System.getProperty("user.home");
			String path=dir+"/classifica.csv";
			FileWriter file=new FileWriter(path);
			file.append("Numero,Tecnico,Coreografico\n");
			for(Gruppo g: gruppi) {
				file.append(g.getNumero()+","+g.getTecnico()+","+g.getCoreografico()+"\n");
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
	public void salvaValutazioni(String categoria, String disciplina, String classe, List<Valutazione> valutazioni) {
		try {
			final String dir = System.getProperty("user.home");
			String path=dir+"/"+categoria+"_"+disciplina+"_"+classe+".csv";
			FileWriter file=new FileWriter(path);
			String header="Numero,Giudice,Tecnico,Coreografico";
			file.append(header);
			for(Valutazione v: valutazioni) {
				file.append("\n"+v.getNumero()+",");
				file.append(v.getId()+",");
				file.append(v.getTecnico()+",");
				file.append(String.valueOf(v.getCoreografico()));
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
			String path=dir+"/giudici.csv";
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
	
}
