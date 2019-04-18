package dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
		Session session = null;
	      Transaction transaction = null;
	      try {
	         session = HibernateUtil.getSession();
	         transaction = session.beginTransaction();

	         //Remove a persistent object
	         Competizione comp=session.get(Competizione.class, id);
	         if(comp!=null){
	            session.remove(comp);
	            System.out.println("Comp "+id+" is removed");
	         }	         
	         transaction.commit();
	      } catch (Exception e) {
	         if (transaction != null) {
	            transaction.rollback();
	         }
	         e.printStackTrace();
	      } finally {
	         if (session != null) {
	            session.close();
	         }
	      }

	}
	
	@Override
	public List<Competizione> getCompetizioni() {
		Session session=HibernateUtil.getSession();
		List<Object[]> list;
		String queryString="select id,categoria,specialita,disciplina,classe,unita from Competizione";
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
			int id=Integer.valueOf(String.valueOf(tuple[0]).trim());
			Categoria categoria=(Categoria)tuple[1];
			Specialita specialita=(Specialita)tuple[2];
			Disciplina disciplina=(Disciplina)tuple[3];
			Classe classe=(Classe)tuple[4];
			Unita unita=(Unita)tuple[5];
			Competizione competizione=new Competizione(categoria, specialita, disciplina, classe, unita);
			competizione.setId(id);
			competizioni.add(competizione);
		}
		
		return competizioni;
	}

	@Override
	public void deleteAll(List<Competizione> competizioni) {
		for(Competizione c: competizioni) {
			delete(c.getId());
		}
	}

	@Override
	public List<Competizione> generateCompetizioni() {
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
			Categoria categoria=(Categoria)tuple[0];
			Classe classe=(Classe)tuple[1];
			Disciplina disciplina=(Disciplina)tuple[2];
			Specialita specialita=(Specialita)tuple[3];
			Unita unita=(Unita)tuple[4];
			Competizione competizione=new Competizione(categoria, specialita, disciplina, classe, unita);
			competizioni.add(competizione);			
		}
		
		return competizioni;
	}

}
