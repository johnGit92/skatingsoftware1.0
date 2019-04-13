package dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Categoria;
import model.Classe;
import model.Competizione;
import model.Disciplina;
import model.Specialita;
import model.Unita;

public class CompetizioneHibernateDao implements CompetizioneDao {

	@Override
	public void create(Competizione competizione) {
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(competizione);
			session.getTransaction().commit();
		}finally {
			session.close();
		}

	}

	@Override
	public Competizione retrieve(int id) {
		Session session=HibernateUtil.getSession();
		Competizione competizione;
		try {
			session.beginTransaction();
			competizione=session.get(Competizione.class, id);
			session.getTransaction().commit();
		}finally {
			session.close();
		}
		return competizione;
	}

	@Override
	public void update(Competizione competizione) {
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.update(competizione);
			session.getTransaction().commit();
		}finally {
			session.close();
		}

	}

	@Override
	public void delete(int id) {
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Competizione competizione=session.get(Competizione.class, id);
			if(competizione!=null) {
				session.remove(competizione);
				System.out.println("Object removed!");
			}
			session.getTransaction().commit();
		}finally {
			session.close();
		}

	}
	
	@Override
	public List<Competizione> getCompetizioni() {
		Session session=HibernateUtil.getSession();
		List<Object[]> list;
		String queryString="select categoria,classe,disciplina,specialita,unita from Iscrizione group by categoria,classe,disciplina,specialita,unita";
		try {
			session.beginTransaction();
			Query<Object[]> query=session.createQuery(queryString);
			list=query.list();
			session.getTransaction().commit();
		}finally {
			session.close();
		}
		
		List<Competizione> competizioni=new LinkedList<Competizione>();
		for(Object[] tuple: list) {
			competizioni.add(new Competizione((Categoria)tuple[0], (Specialita)tuple[3], 
					(Disciplina)tuple[2], (Classe)tuple[1], (Unita)tuple[4]));
		}
		
		return competizioni;
	}

	@Override
	public void deleteAll() {
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			List<Competizione> competizioni=getCompetizioni();
			for(Competizione c: competizioni)
				delete(c.getId());
			
		}finally {
			session.close();
		}
	}

}
