package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.Service;

public class ClassificaParzialeGiudice {
	
	private String id;
	private List<Valutazione> voti;
	private List<Double> complessivo;
	private List<Integer> classifica;

	private ClassificaParzialeGiudice() {
		complessivo=new ArrayList<Double>();
		classifica=new ArrayList<Integer>();
	}

	public ClassificaParzialeGiudice(String id, List<Valutazione> voti) {
		this();
		this.id = id;
		this.voti = voti;
		
		//ottieni lista complessivo (t+c)
		for(Valutazione v : voti) {
			double sum=v.getTecnico()+v.getCoreografico();
			complessivo.add((double)Math.round(sum*10)/10);
			//((double) Math.round(g.getTecnico() * 10) / 10)
		}
		
		//ottieni lista piazzamenti
		Iterator<Valutazione> it=voti.iterator();
		int pos=1;
		Valutazione curr=null;
		if(it.hasNext()) {
			curr=it.next();
			classifica.add(pos);
		}
		while(it.hasNext()) {
			Valutazione next=it.next();
			double currTot=curr.getCoreografico()+curr.getTecnico();
			double nextTot=next.getCoreografico()+next.getTecnico();
			if(currTot!=nextTot) pos++;
			classifica.add(pos);
			curr=next;
		}System.out.println("");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Valutazione> getVoti() {
		return voti;
	}

	public void setVoti(List<Valutazione> voti) {
		this.voti = voti;
	}

	public List<Double> getComplessivo() {
		return complessivo;
	}

	public void setComplessivo(List<Double> complessivo) {
		this.complessivo = complessivo;
	}

	public List<Integer> getClassifica() {
		return classifica;
	}

	public void setClassifica(List<Integer> classifica) {
		this.classifica = classifica;
	}

	@Override
	public String toString() {
		String out="GIUDICE : "+id+"\n"+
		"---------------------------------------------------------------------------------\n"+
		"POS\tNUMERO\t\tASD\t\t\t\tCOMPLESSIVO\n"+
		"---------------------------------------------------------------------------------\n";;
		Iterator<Integer> itPos=classifica.iterator();
		Iterator<Double> itComp=complessivo.iterator();
		Iterator<Valutazione> itVoti=voti.iterator();
		while(itPos.hasNext() && itComp.hasNext() && itVoti.hasNext()) {
			int numero=itVoti.next().getNumero();
			String asd=Service.getIscrizioneDao().retrieve(numero).getAsd();
			out+=itPos.next()+"°\t"+numero+"\t\t"+asd+"\t\t\t"+itComp.next()+"\n";
		}
		
		return out;
	}

}
