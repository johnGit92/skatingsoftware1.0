package dao;

import java.util.List;

import model.Iscrizione;

/**
 * CRUD operation on "Iscrizione" entity.
 *
 */
public interface IscrizioneDao {
	
	void create(Iscrizione iscrizione);
	void update(Iscrizione iscrizione);
	Iscrizione retrieve(int numero);
	void delete(int numero);
	List<Iscrizione> getAll();
}
