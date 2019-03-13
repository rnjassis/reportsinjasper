package jasperreport.reports;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportWithSub extends ReportBase{

	private String nome;
	private String cpf;

	List<SubReportData> subReport;


	public ReportWithSub () {
		super();
		try {
			
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/reports/relatorio.jasper"));

			subReport = new ArrayList<>();

		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private void setParameters() {
		parameters = new LinkedHashMap<>();
		try {
			parameters.put("SUBREPORT", new JRBeanCollectionDataSource(subReport));
			parameters.put("nome", nome);
			parameters.put("cpf", cpf);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addOnSubReport(SubReportData subRepData) {
		
		if(subReport == null) {
			subReport = new ArrayList<>();
		}
		
		subReport.add(subRepData);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public Map<String, Object> getParameters() {
		setParameters();
		return parameters;
	}

	@Override
	public JasperReport getReport() {
		return report;
	}

	@Override
	public JRDataSource getDataSource() {
		return new JREmptyDataSource();
	}
	
	public class SubReportData {

		private String texto;
		private String id;

		public SubReportData(String texto, String id) {
			super();
			this.texto = texto;
			this.id = id;
		}

		public String getTexto() {
			return texto;
		}

		public void setTexto(String texto) {
			this.texto = texto;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}

}
