package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Valutazioni")
public class Valutazione implements Comparable<Valutazione>{

	private int pk;
	/**
	 * numero gruppo.
	 */
	private int numero;
	
	/**
	 * id giudice.
	 */
	private String id;
	
	private double tecnico;
	private double coreografico;
	
	public Valutazione(int numero, String id, double tecnico, double coreografico) {
		super();
		this.numero = numero;
		this.id = id;
		this.tecnico = tecnico;
		this.coreografico = coreografico;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getTecnico() {
		return tecnico;
	}

	public void setTecnico(double tecnico) {
		this.tecnico = tecnico;
	}

	public double getCoreografico() {
		return coreografico;
	}

	public void setCoreografico(double coreografico) {
		this.coreografico = coreografico;
	}

	@Override
	public int compareTo(Valutazione o) {
		if((tecnico+coreografico)>(o.getTecnico()+o.getCoreografico())) {
			return 1;
		}
		else if((tecnico+coreografico)<(o.getTecnico()+o.getCoreografico())) {
			return -1;
		}
		return 0;		
	}

	@Override
	public String toString() {
		return "\tValutazione [numero=" + numero + ", id=" + id + ", tecnico=" + tecnico + ", coreografico="
				+ coreografico + "]\n";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj!=null) {
			if(obj.getClass()==getClass()) {
				Valutazione o=(Valutazione)obj;
				if((tecnico+coreografico)==(o.getTecnico()+o.getCoreografico())) {
					return true;
				}
				return false;
			}
		}
		return false;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk")
	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}
}
