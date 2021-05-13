package com.hifood.core.data;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableTranslator {

	public static Pageable translate(Pageable pageable, Map<String, String> fieldMappings) {
		var order = pageable.getSort().stream().filter(sort -> fieldMappings.containsKey(sort.getProperty()))
				.map(sort -> new Sort.Order(sort.getDirection(), fieldMappings.get(sort.getProperty())))
				.collect(Collectors.toList());

		return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(order));
	}

}
