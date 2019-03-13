package jasperreport.reports;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;

public abstract class ReportBase {
	
	protected Map<String, Object> parameters;
	protected JasperReport report;
	protected JRDataSource dataSource;
	
	public ReportBase() {
		
	}
	
	public abstract Map<String, Object> getParameters();
	public abstract JasperReport getReport();
	public abstract JRDataSource getDataSource();
	
}
