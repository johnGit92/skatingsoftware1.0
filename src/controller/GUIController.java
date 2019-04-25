package controller;

import java.util.List;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.Giudice;
import model.Iscrizione;
import view.CompetizioneGUI;
import view.GiudiciGUI;
import view.InserisciVotiGUI;
import view.IscrizioniGUI;
import view.MenuGUI;
import view.ModificaIscrizioneGUI;
import view.SelezionaGiuriaGUI;
import view.NuovaIscrizioneGUI;

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
	
	public void showVotazioni(List<Iscrizione> selezionati, CompetitionController compController, GUIController guiController) {
		new SelezionaGiuriaGUI(selezionati, compController, guiController).getFrmVotazioni().setVisible(true);;
	}
	
	public void showCompetizione(Map<String,String> iscrittiInCompetizione) {
		CompetizioneGUI c=new CompetizioneGUI(iscrittiInCompetizione, compController);
		c.getFrmClassifica().setVisible(true);
	}

	public void showNuovaIscrizione() {
		new NuovaIscrizioneGUI(compController).setVisible(true);
	}

	public void showModificaIscrizione(Iscrizione iscrizione) {
		new ModificaIscrizioneGUI(iscrizione,compController).setVisible(true);;
		
	}

	public void showInserisciVoti(List<Iscrizione> gruppiSelezionati, List<Giudice> giudiciSelezionati) {
		new InserisciVotiGUI(gruppiSelezionati, giudiciSelezionati, compController).getFrmVotazioni().setVisible(true);;
		
	}
}
