package dao;

public class Service {
	
	private static IscrizioneDao iscrizioneDao;
//	private static CompetizioneDao competizioneDao;
//	private static GiudiceDao giudiceDao;
//	private static ValutazioneDao valutazioneDao;
	
	public static IscrizioneDao getIscrizioneDao(){
		if(iscrizioneDao==null) iscrizioneDao = new IscrizioneHibernateDao();
		return iscrizioneDao;
	}
	
//	public static CompetizioneDao getCompetizioneDao() {
//		if(competizioneDao==null) competizioneDao=new CompetizioneHibernateDao();
//		return competizioneDao;
//	}
//	
//	public static GiudiceDao getGiudiceDao() {
//		if(giudiceDao==null) giudiceDao=new GiudiceHibernateDao();
//		return giudiceDao;
//	}
//
//	public static ValutazioneDao getValutazioneDao() {
//		if(valutazioneDao==null) valutazioneDao=new ValutazioneHibernateDao();
//		return valutazioneDao;
//	}
}
