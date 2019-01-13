package dao;

import java.util.List;

import model.Giudice;

public interface GiudiceDao {

	void create(Giudice giudice);
	Giudice retrieve(String id);
	void update(Giudice giudice);
	void delete(String id);
	List<Giudice> getAll();
}
