package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Valutazione;

public class ValutazioneHibernateDao implements ValutazioneDao {

	@Override
	public void create(Valutazione valutazione) {
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(valutazione);
			session.getTransaction().commit();
		}finally {
			session.close();
		}

	}

	@Override
	public Valutazione retrieve(int pk) {
		Session session=HibernateUtil.getSession();
		Valutazione val;
		try {
			session.beginTransaction();
			val=session.get(Valutazione.class, pk);
			session.getTransaction();
		}finally {
			session.close();
		}
		return val;
	}

	@Override
	public void update(Valutazione valutazione) {
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.update(valutazione);
			session.getTransaction().commit();
		}finally {
			session.close();
		}

	}

	@Override
	public void delete(int pk) {
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Valutazione val=session.get(Valutazione.class, pk);
			if(val!=null) {
				session.remove(val);
				System.out.println("Object deleted!");
			}
			session.getTransaction().commit();
		}finally {
			session.close();
		}

	}

	@Override
	public List<Valutazione> getValutazioni(int numero) {
		Session session=HibernateUtil.getSession();
		List<Valutazione> list;
		try {
			session.beginTransaction();
			Query<Valutazione> query=session.createQuery("from Valutazione where numero="+numero);
			list=query.getResultList();
			session.getTransaction().commit();
		}finally {
			session.close();
		}
		return list;
	}

}
