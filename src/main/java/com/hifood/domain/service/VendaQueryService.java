package com.hifood.domain.service;

import java.util.List;

import com.hifood.domain.filter.VendaDiariaFilter;
import com.hifood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String userOffset);
}
