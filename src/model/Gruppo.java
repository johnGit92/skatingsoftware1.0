package model;

import java.util.ArrayList;
import java.util.List;

public class Gruppo implements Comparable<Gruppo>{

	private int numero;
	private String nominativo;
	private String categoria;
	private String disciplina;
	private List<Valutazione> valutazioni;
	private double tecnico;
	private double coreografico;
	
	public Gruppo(int numero, String nominativo, String categoria, String disciplina) {
		super();
		this.numero = numero;
		this.nominativo = nominativo;
		this.categoria = categoria;
		this.disciplina = disciplina;
		valutazioni=new ArrayList<Valutazione>();
		tecnico=0;
		coreografico=0;
	}	
	

	public Gruppo(int numero) {
		super();
		this.numero = numero;
		valutazioni=new ArrayList<Valutazione>();
	}



	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNominativo() {
		return nominativo;
	}

	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public List<Valutazione> getValutazioni() {
		return valutazioni;
	}

	public void setValutazioni(List<Valutazione> valutazioni) {
		this.valutazioni = valutazioni;
	}
	
	@Override
	public String toString() {
		return "Gruppo [numero=" + numero + "]";
	}


	/**
	 * Confronta al contrario per sfruttare il metodo sort di collections che ordina in modo crescente.
	 * Cosi definito applicando il metodo sort ad una lista di gruppi si ottiene un ordinamento decrescente, utile per stilare la classifica.
	 */
	@Override
	public int compareTo(Gruppo o) {//Aggiungere confronto tra due gruppi
		
		List<Valutazione> val_da_confrontare=o.getValutazioni();
		int index=0; int vince_1=0,vince_2=0; double tecnico_1=0, tecnico_2=0, coreografico_1=0, coreografico_2=0;
		while(index<2) {//modificare per 5 giudici index<5
			tecnico_1=valutazioni.get(index).getTecnico();
			tecnico_2=val_da_confrontare.get(index).getTecnico();
			coreografico_1=valutazioni.get(index).getCoreografico();
			coreografico_2=val_da_confrontare.get(index).getCoreografico();
			if((tecnico_1+coreografico_1)>(tecnico_2+coreografico_2)) {
				vince_1++;
			}
			else if((tecnico_1+coreografico_1)<(tecnico_2+coreografico_2))
				vince_2++;
			index++;
		}
		if(vince_1>vince_2)
			return -1;
		else if(vince_1<vince_2)
			return 1;
		return 0;
	}



	public double getCoreografico() {
		return coreografico;
	}



	public void setCoreografico(double coreografico) {
		this.coreografico = coreografico;
	}


	public double getTecnico() {
		return tecnico;
	}


	public void setTecnico(double tecnico) {
		this.tecnico = tecnico;
	}
	
}
