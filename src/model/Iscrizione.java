package model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "iscrizioni")
public class Iscrizione {
	
	private String asd;
	private int numero;
	private Categoria categoria;
	private Specialita specialita;
	private Disciplina disciplina;
	private Unita unita;
	private Classe classe;
	
	public Iscrizione() {
		super();
	}

	public Iscrizione(String asd, int numero, Categoria categoria, Specialita specialita, Disciplina disciplina, 
			Unita unita, Classe classe) {
		super();
		this.asd = asd;
		this.numero = numero;
		this.categoria = categoria;
		this.specialita = specialita;
		this.disciplina = disciplina;
		this.unita = unita;
		this.classe = classe;
	}

	public String getAsd() {
		return asd;
	}

	public void setAsd(String asd) {
		this.asd = asd;
	}

	@Id
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Enumerated(EnumType.STRING)
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Enumerated(EnumType.STRING)
	public Specialita getSpecialita() {
		return specialita;
	}

	public void setSpecialita(Specialita specialita) {
		this.specialita = specialita;
	}

	@Enumerated(EnumType.STRING)
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	@Enumerated(EnumType.STRING)
	public Unita getUnita() {
		return unita;
	}

	public void setUnita(Unita unita) {
		this.unita = unita;
	}

	@Enumerated(EnumType.STRING)
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	@Override
	public String toString() {
		return "Iscrizione [asd=" + asd + ", numero=" + numero + ", categoria=" + categoria + ", specialita="
				+ specialita + ", disciplina=" + disciplina + ", unita=" + unita + ", classe=" + classe + "]";
	}
	

}
