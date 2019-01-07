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
	private Unita gruppo;
	private int n;
	private Classe classe;
	private String ballerini;
	
	public Iscrizione() {
		super();
	}

	public Iscrizione(String asd, int numero, Categoria categoria, Specialita specialita, Disciplina disciplina, Unita gruppo,
			int n, Classe classe, String ballerini) {
		super();
		this.asd = asd;
		this.numero = numero;
		this.categoria = categoria;
		this.specialita = specialita;
		this.disciplina = disciplina;
		this.gruppo = gruppo;
		this.n = n;
		this.classe = classe;
		this.ballerini = ballerini;
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
	public Unita getGruppo() {
		return gruppo;
	}

	public void setGruppo(Unita gruppo) {
		this.gruppo = gruppo;
	}

	@Enumerated(EnumType.STRING)
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public String getBallerini() {
		return ballerini;
	}

	public void setBallerini(String ballerini) {
		this.ballerini = ballerini;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	@Override
	public String toString() {
		return "Iscrizioni [asd=" + asd + ", numero=" + numero + ", categoria=" + categoria + ", specialita="
				+ specialita + ", disciplina=" + disciplina + ", gruppo=" + gruppo + ", n=" + n + ", classe=" + classe
				+ ", ballerini=" + ballerini + "]";
	}

}
