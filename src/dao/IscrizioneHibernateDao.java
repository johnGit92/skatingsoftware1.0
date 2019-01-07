package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Iscrizione;

public class IscrizioneHibernateDao implements IscrizioneDao{
	
	@Override
	public void create(Iscrizione iscrizione) {
		Session session = HibernateUtil.getSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(iscrizione);
			session.getTransaction().commit();
		}
		finally{
			session.close();
		}
	}
	
	@Override
	public void update(Iscrizione iscrizione){
		Session session = HibernateUtil.getSession();
		try{
			session.beginTransaction();
			session.update(iscrizione);
			session.getTransaction().commit();
		}
		finally{
			session.close();
		}
	}
	
	@Override
	public Iscrizione retrieve(int numero) {
		Session session = HibernateUtil.getSession();
		Iscrizione iscrizione;
		try{
			session.beginTransaction();
			iscrizione = session.get(Iscrizione.class, numero);
			session.getTransaction().commit();
		}
		finally{
			session.close();
		}
		return iscrizione;
	}

	@Override
	public void delete(int numero) {
		Session session=HibernateUtil.getSession();
		try {
			session.beginTransaction();
			Iscrizione iscrizione=session.get(Iscrizione.class, numero);
			if(iscrizione!=null) {
				session.remove(iscrizione);
				System.out.println("Object deleted!");
			}
			session.getTransaction().commit();
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<Iscrizione> getAll() {
		Session session=HibernateUtil.getSession();
		List<Iscrizione> list;
		try {
			session.beginTransaction();
			Query<Iscrizione> query=session.createQuery("from Iscrizione"); //use the name of the mapped pojo
			list=query.list();			
			session.getTransaction().commit();
			
		}finally {
			session.close();
		}
		return list;
	}

//	@Override
//	public List<Competizione> getCompetizioni() {
//		Session session=HibernateUtil.getSession();
//		List<Object[]> list;
//		String queryString="select categoria,classe,disciplina,specialita,gruppo from Iscrizione group by categoria,classe,disciplina,specialita,gruppo";
//		try {
//			session.beginTransaction();
//			Query<Object[]> query=session.createQuery(queryString);
//			list=query.list();
//			session.getTransaction().commit();
//		}finally {
//			session.close();
//		}
//		
//		List<Competizione> competizioni=new LinkedList<Competizione>();
//		for(Object[] tuple: list) {
//			competizioni.add(new Competizione((Categoria)tuple[0], (Specialita)tuple[3], 
//					(Disciplina)tuple[2], (Classe)tuple[1], (Unita)tuple[4]));
//		}
//		
//		return competizioni;
//	}

}
