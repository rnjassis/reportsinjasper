package jasperreport.reports;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportWithSub {

	private String nome;
	private String cpf;

	Map<String, Object> parameters;
	List<SubReportData> subReport;

	JasperReport relatorio;

	public ReportWithSub() {
		
		try {
			
			relatorio = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/reports/relatorio.jasper"));

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

	public JasperPrint makeReport() {
		this.setParameters();
		
		try {

			JasperPrint print = JasperFillManager.fillReport(relatorio, parameters, new JREmptyDataSource());

			return print;

		} catch (JRException e) {
			e.printStackTrace();
		}

		return new JasperPrint();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
