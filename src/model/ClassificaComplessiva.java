package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import dao.Service;

public class ClassificaComplessiva {

	private List<ClassificaParzialeGiudice> parziali;
	private List<Integer> numeri;
	private Map<Integer,Integer> punti;
	private Map<Integer,Double> tecnico;
	private Map<Integer,Integer> classifica;
	
	private ClassificaComplessiva() {
		numeri=new ArrayList<Integer>();
		punti=new HashMap<Integer,Integer>();
		classifica=new HashMap<Integer,Integer>();
		tecnico=new HashMap<Integer,Double>();
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
		
		//genera classifica
		Set<Integer> keys=punti.keySet();
		int pos=1;
		Iterator<Integer> it=keys.iterator();
		while(it.hasNext()) {
			Integer curr=it.next();
			classifica.put(curr, pos);
			if(it.hasNext()) {
				Integer next=it.next();
				if(punti.get(curr)!=punti.get(next)) {
					pos++;
				}
				else {
					if(tecnico.get(curr)!=tecnico.get(next)) {
						pos++;
					}
				}
				classifica.put(next, pos);	
			}
		}
		
		System.out.println("");
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

	public Map<Integer, Integer> getClassifica() {
		return classifica;
	}

	public void setClassifica(Map<Integer, Integer> classifica) {
		this.classifica = classifica;
	}

	@Override
	public String toString() {
		String out="CLASSIFICA\n"+
				"------------------------------------------------------------\n";
		Set<Integer> keys=punti.keySet();
		for(Integer key : keys) {
			out+=classifica.get(key)+"°\t"+key+"\t"+Service.getIscrizioneDao().retrieve(key).getAsd()+"\t"+punti.get(key)+"\t"+(double)Math.round(tecnico.get(key)*10)/10+"\n";
		}
		return out;
	}
	
	

}
