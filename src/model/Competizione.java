package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="competizioni")
public class Competizione {

	private int id;
	private Categoria categoria;
	private Specialita specialita;
	private Disciplina disciplina;
	private Classe classe;
	private Unita unita;

	public Competizione(Categoria categoria, Specialita specialita, Disciplina disciplina, Classe classe,
			Unita unita) {
		super();
		this.categoria = categoria;
		this.specialita = specialita;
		this.disciplina = disciplina;
		this.classe = classe;
		this.unita = unita;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	@Enumerated(EnumType.STRING)
	public Unita getUnita() {
		return unita;
	}

	public void setUnita(Unita unita) {
		this.unita = unita;
	}
	
}