package com.hifood.infrastructure.repository.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hifood.domain.filter.VendaDiariaFilter;
import com.hifood.domain.service.VendaQueryService;
import com.hifood.domain.service.VendaReportService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfVendaReportService implements VendaReportService {

	@Autowired
	private VendaQueryService vendaQueryService;

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String userOffset) {

		try {
			var inputStream = this.getClass().getResourceAsStream("/reports/vendas_diarias.jasper");

			var params = new HashMap<String, Object>();
			params.put("REPORT_LOCALE", new Locale("pt", "BR"));

			var vendasDiarias = vendaQueryService.consultarVendasDiarias(filtro, userOffset);

			var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

			var jasperPrint = JasperFillManager.fillReport(inputStream, params, dataSource);

			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException("Não foi possivel emitir o relatório de vendas diarias");
		}
	}

}
