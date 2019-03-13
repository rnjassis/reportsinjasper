package jasperreport.generator;

import java.io.ByteArrayOutputStream;

import jasperreport.reports.ReportBase;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ReportGenerator {
	
	public ReportGenerator() {
		
	}
	
	public ByteArrayOutputStream generateReport(ReportBase base) {
		ByteArrayOutputStream stream  = new ByteArrayOutputStream();
		
		try {
			JasperPrint print = JasperFillManager.fillReport(base.getReport(), base.getParameters(), base.getDataSource());
			
			stream  = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(print, stream);

			
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return stream;
	}
}
