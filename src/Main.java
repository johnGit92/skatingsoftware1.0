import java.util.ArrayList;
import java.util.List;

import controller.CompetitionController;
import controller.GUIController;
import dao.GiudiceDao;
import dao.Service;
import model.Giudice;
import model.Valutazione;

/**
 * Main class used to launch the application.
 * @author Giovanbattista
 *
 */
public class Main {

	public static void main(String[] args) {
		
		CompetitionController controller=new CompetitionController();
		GUIController guiController = new GUIController(controller);
		
		//valutazioni 5 giudici
		Valutazione v1,v2,v3,v4,v5;
		v1=new Valutazione(1562, "H", 5.5, 5.6); 
		v2=new Valutazione(1562, "B", 5.6, 5.8);
		v3=new Valutazione(1562, "N", 5.9, 5.8);
		v4=new Valutazione(1562, "R", 5.7, 5.9);
		v5=new Valutazione(1562, "T", 5.5, 5.6);
		
		List<Valutazione> valutazioni=new ArrayList<Valutazione>();
		valutazioni.add(v1); valutazioni.add(v2); valutazioni.add(v3); valutazioni.add(v4); valutazioni.add(v5);
		controller.salvaValutazioni(valutazioni);
		
		guiController.showMainMenu();
		
	}
}
