package dao;

import model.Classifica;

public interface ClassificaDao {

	void create(Classifica classifica);
	Classifica retrieve(int pk);
}
