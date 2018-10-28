import controller.CompetitionController;
import controller.GUIController;

public class Main {

	public static void main(String[] args) {
		
		CompetitionController controller=new CompetitionController();
		GUIController guiController = new GUIController(controller);
		
		guiController.showMainMenu();
		
//		CompetitionController controller=new CompetitionController();
//		
//		List<Valutazione> listaValutazioni=controller.generaValutazioni();
//		List<Gruppo> listaGruppi=controller.generaGruppiConValutazioni(listaValutazioni);
//		
//		Collections.sort(listaGruppi);
//		for(Gruppo g: listaGruppi) {
//			System.out.println(g);
//		}
//		
//		controller.generaCsvGruppi(listaGruppi);
		
	}
}
