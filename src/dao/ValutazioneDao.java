package dao;

import java.util.List;

import model.Valutazione;

public interface ValutazioneDao {

	void create(Valutazione valutazione);
	Valutazione retrieve(int pk);
	void update(Valutazione valutazione);
	void delete(int pk);
	List<Valutazione> getValutazioni(int numero);
}
