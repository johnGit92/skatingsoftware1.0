package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassificaComplessiva {

	private List<ClassificaParzialeGiudice> parziali;
	private List<Integer> numeri;
	private Map<Integer,Integer> punti;
	private Map<Integer,Double> tecnico;
	private Map<Integer,Integer> classifica;
	
	private ClassificaComplessiva() {
		numeri=new ArrayList<Integer>();
		punti=new HashMap<Integer,Integer>();
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
	}
	
	

}
