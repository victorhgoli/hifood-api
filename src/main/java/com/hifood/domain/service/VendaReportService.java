package com.hifood.domain.service;

import com.hifood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {
	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String userOffset);
}
