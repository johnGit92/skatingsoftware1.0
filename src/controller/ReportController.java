package controller;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

public class ReportController {

	public static void showReports() {

		try {
			// caricamento file jrxml
			JasperDesign jasperDesign = JRXmlLoader.load("reports\\Cedolino_Giudici.jrxml");

			// compilazione del file e generazione del file jasper
			JasperCompileManager.compileReportToFile(jasperDesign, "reports\\Cedolino_Giudici.jasper");

			// inizializzazione connessione al database
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/skating?useSSL=false&amp&serverTimezone=UTC&allowPublicKeyRetrieval=true",
					"Giovanni", "123456");

			// rendering e generazione del file PDF
			JasperPrint jp = JasperFillManager.fillReport("reports\\Cedolino_Giudici.jasper", null, conn);
			// JasperExportManager.exportReportToPdfFile(jp,
			// "reports\\Cedolino_Giudici.pdf");

			JFrame frame = new JFrame("Jasper report");

			JRViewer viewer = new JRViewer(jp);

			frame.add(viewer);
			frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}