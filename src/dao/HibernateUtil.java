package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Iscrizione;

public class HibernateUtil {
	private static SessionFactory factory;
	
	private HibernateUtil(){
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		config.addAnnotatedClass(Iscrizione.class);
		factory = config.buildSessionFactory();
	}
	
	public static synchronized Session getSession(){
		if(factory==null) new HibernateUtil();
		return factory.getCurrentSession();
	}
}