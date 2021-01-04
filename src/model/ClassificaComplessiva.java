package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import dao.FactoryDao;

public class ClassificaComplessiva {

	private List<ClassificaParzialeGiudice> parziali;
	private List<Integer> numeri;
	private Map<Integer,Integer> punti;
	private Map<Integer,Double> tecnico;
	
	//classe utility usata per generare la classifica finale
	private class Util implements Comparable<Util>{
		Integer pos;
		Integer numero;
		String asd;
		Integer pt;
		Double tecnico;
		
		Util(Integer numero, Integer pt, Double tecnico) {
			super();
			this.numero = numero;
			this.pt = pt;
			this.tecnico = tecnico;
		}

		@Override
		public int compareTo(Util o) {
			if(this.pt<o.pt) return -1;
			else if(this.pt>o.pt) return 1;
			else {
				if(this.tecnico<o.tecnico) return 1;
				else if(this.tecnico>o.tecnico) return -1;
			}
			return 0;
		}

		@Override
		public String toString() {
			return "Util [pos=" + pos + ", numero=" + numero + ", asd=" + asd + ", pt=" + pt + ", tecnico=" + tecnico
					+ "]";
		}
		
	}
	
	private List<Util> utilList;
	
	private ClassificaComplessiva() {
		numeri=new ArrayList<Integer>();
		punti=new HashMap<Integer,Integer>();
		tecnico=new HashMap<Integer,Double>();
		utilList=new ArrayList<Util>();
	}

	public ClassificaComplessiva(List<ClassificaParzialeGiudice> parziali) {
		this();
		this.parziali = parziali;
		
		// Ottieni numero gruppi in competizione
		List<Valutazione> list=parziali.iterator().next().getVoti();
		for(Valutazione v : list) {
			numeri.add(v.getNumero());
		}
		
		// calcola per ogni gruppo, complessivo tecnico e punteggio
		for(Integer n : numeri) {
			double tec=0.0; int pt=0;
			for(ClassificaParzialeGiudice c : parziali) {
				int index=0;
				List<Valutazione> voti=c.getVoti();
				for(Valutazione v : voti) {
					if(v.getNumero()==n) {
						tec+=v.getTecnico();
						break;
					}
					index++;
				}
				pt+=c.getClassifica().get(index);
			}
			punti.put(n, pt);
			tecnico.put(n, tec);
		}
		
		//ottieni mappa punteggi odinata
		punti.entrySet()
		  .stream()
		  .sorted(Map.Entry.comparingByValue());
		Map<Integer, Integer> puntiOrdered = punti.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue())
				  .collect(Collectors.toMap(
				    Map.Entry::getKey, 
				    Map.Entry::getValue, 
				    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		punti=puntiOrdered;
		
		//genera lista utility da ordinare secondo classifica
		Set<Integer> keys=punti.keySet();
		for(Integer key : keys) {
			Util u=new Util(key, punti.get(key), (double)Math.round(tecnico.get(key)*10)/10);
			u.asd=FactoryDao.getIscrizioneDao().retrieve(u.numero).getAsd();
			utilList.add(u);
		}
		Collections.sort(utilList);
		
		//calcola posizioni
		int pos=1;
		Iterator<Util> it=utilList.iterator();
		Util curr=null;
		if(it.hasNext()) {
			curr=it.next();
			curr.pos=pos;
		}
		while(it.hasNext()) {
			Util next=it.next();
			if(!curr.pt.equals(next.pt)) {
				pos++;
			}
			else {
				if(!curr.tecnico.equals(next.tecnico)) {
					pos++;
				}
			}
			next.pos=pos;
			curr=next;
		}
		
		System.out.println(" ");
	}

	public List<ClassificaParzialeGiudice> getParziali() {
		return parziali;
	}

	public void setParziali(List<ClassificaParzialeGiudice> parziali) {
		this.parziali = parziali;
	}

	public List<Integer> getNumeri() {
		return numeri;
	}

	public void setNumeri(List<Integer> numeri) {
		this.numeri = numeri;
	}

	public Map<Integer, Integer> getPunti() {
		return punti;
	}

	public void setPunti(Map<Integer, Integer> punti) {
		this.punti = punti;
	}

	public Map<Integer, Double> getTecnico() {
		return tecnico;
	}

	public void setTecnico(Map<Integer, Double> tecnico) {
		this.tecnico = tecnico;
	}

	@Override
	public String toString() {
		String out="CLASSIFICA\n"+
		"---------------------------------------------------------------------------------\n"+
		"POS\tNUMERO\t\tASD\t\t\t\tPT\tTECNICO\n"+
		"---------------------------------------------------------------------------------\n";
		for(Util u : utilList) {
			out+=u.pos+"°\t"+u.numero+"\t\t"+u.asd+"\t\t\t"+u.pt+"\t"+u.tecnico+"\n";
		}
		out+="---------------------------------------------------------------------------------\n";
		return out;
	}

}
