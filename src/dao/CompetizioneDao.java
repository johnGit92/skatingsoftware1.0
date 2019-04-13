package dao;

import java.util.List;

import model.Competizione;

public interface CompetizioneDao {

	public void create(Competizione competizione);
	public Competizione retrieve(int id);
	public void update(Competizione competizione);
	public void delete(int id);
	public void deleteAll();
	public List<Competizione> getCompetizioni();
}
