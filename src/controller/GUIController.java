package controller;

import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import view.GiudiciGUI;
import view.MenuGUI;
import view.VotazioniGUI;
import view.CompetizioneGUI;
import view.IscrizioniGUI;

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
		new MenuGUI(this,compController).getFrmSkatingsoftware().setVisible(true);
	}
	
	public void showNuovaCompetizione() {
		new IscrizioniGUI(this,compController).getFrame().setVisible(true);
	}

	public void showGiudici() {
		new GiudiciGUI(this, compController).getFrame().setVisible(true);;
		
	}
	
	public void showVotazioni(String numero, String asd, CompetitionController compController) {
		new VotazioniGUI(numero,asd, compController).getFrmVotazioni().setVisible(true);;
	}
	
	public void showClassifica(Map<String,String> iscrittiInCompetizione) {
		CompetizioneGUI c=new CompetizioneGUI(iscrittiInCompetizione);
		c.getFrmClassifica().setVisible(true);
	}
}
