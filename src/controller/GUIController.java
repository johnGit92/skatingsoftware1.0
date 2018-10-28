package controller;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import view.Giudici;
import view.MainMenu;
import view.NuovaCompetizione;

public class GUIController {
	
	private CompetitionController compController;

	public GUIController(CompetitionController compController) {
		super();
		this.compController = compController;
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
	}

	public void showMainMenu() {		
		new MainMenu(this).getFrmSkatingsoftware().setVisible(true);
	}
	
	public void showNuovaCompetizione() {
		new NuovaCompetizione(this,compController).getFrame().setVisible(true);
	}

	public void showGiudici() {
		new Giudici(this, compController).getFrame().setVisible(true);;
		
	}
}
