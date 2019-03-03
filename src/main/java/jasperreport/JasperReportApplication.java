package jasperreport;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jasperreport.reports.ReportWithSub;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

public class JasperReportApplication {

	public static void main(String[] args) {
		
		ReportWithSub relatorio = new ReportWithSub();
		
		int id = 1;
		relatorio.setNome("Noome");
		relatorio.setCpf("12345678912");
		relatorio.addOnSubReport(relatorio.new SubReportData("txt "+id, Integer.toString(id++)));
		relatorio.addOnSubReport(relatorio.new SubReportData("txt "+id, Integer.toString(id)));
		
		JasperPrint printer = relatorio.makeReport();
		
		try {
			JasperExportManager.exportReportToPdfFile(printer, "/home/silva/"+UUID.randomUUID().toString()+".pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}
