package jasperreport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import jasperreport.generator.ReportGenerator;
import jasperreport.reports.ReportWithSub;

public class JasperReportApplication {

	public static void main(String[] args) {

		ReportWithSub relatorio = new ReportWithSub();
		int id = 1;
		relatorio.setNome("Noome");
		relatorio.setCpf("12345678912");
		relatorio.addOnSubReport(relatorio.new SubReportData("txt " + id, Integer.toString(id++)));
		relatorio.addOnSubReport(relatorio.new SubReportData("txt " + id, Integer.toString(id)));

		try {
			FileOutputStream outFile = new FileOutputStream(new File(UUID.randomUUID().toString()) + ".pdf");
			outFile.write(new ReportGenerator().generateReport(relatorio).toByteArray());
			outFile.flush();
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
