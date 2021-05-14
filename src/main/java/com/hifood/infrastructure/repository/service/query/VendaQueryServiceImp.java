package com.hifood.infrastructure.repository.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.hifood.domain.filter.VendaDiariaFilter;
import com.hifood.domain.model.Pedido;
import com.hifood.domain.model.StatusPedido;
import com.hifood.domain.model.dto.VendaDiaria;
import com.hifood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImp implements VendaQueryService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {

		var builder = entityManager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);

		var functionConvert_tz = builder.function("convert_tz", Date.class, root.get("dataCriacao"), builder.literal("+00:00"), builder.literal(timeOffset));
		var functionDateDataCriacao = builder.function("date", Date.class, functionConvert_tz);

		var selection = builder.construct(VendaDiaria.class, functionDateDataCriacao, builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));

		query.select(selection);
		
		var predicates = new ArrayList<Predicate>();

		if (filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}

		if (filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
		}

		if (filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
		}
		
		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
		query.where(predicates.toArray(new Predicate[0]));

		query.groupBy(functionDateDataCriacao);

		return entityManager.createQuery(query).getResultList();
	}

}
