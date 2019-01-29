package dao;

import org.hibernate.Session;

import model.Classifica;

public class ClassificaHibernateDao implements ClassificaDao {

	@Override
	public void create(Classifica classifica) {
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(classifica);
			session.getTransaction().commit();
		}finally {
			session.close();
		}

	}

	@Override
	public Classifica retrieve(int numero) {
		//DA COMPLETARE
		return null;
	}

}
