import controller.CompetitionController;
import controller.GUIController;
import dao.GiudiceDao;
import dao.Service;
import model.Giudice;

/**
 * Main class used to launch the application.
 * @author Giovanbattista
 *
 */
public class Main {

	public static void main(String[] args) {
		
		CompetitionController controller=new CompetitionController();
		GUIController guiController = new GUIController(controller);
		
		guiController.showMainMenu();
		
	}
}
