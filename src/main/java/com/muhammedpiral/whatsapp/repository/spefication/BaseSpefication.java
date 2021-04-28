package com.muhammedpiral.whatsapp.repository.spefication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.muhammedpiral.whatsapp.model.SearchCriteria;


public abstract class BaseSpefication<E> {

	protected List<SearchCriteria> criteriaList;

	public BaseSpefication(List<SearchCriteria> criteriaList) {
		this.criteriaList = criteriaList;
	}
	
	public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> filters = new ArrayList<>();
		if (criteriaList.isEmpty()) {
			return null;
		}
		
		for (SearchCriteria criteria : criteriaList) {
			if (criteria.getOperation().equalsIgnoreCase(">")) {
				filters.add(builder.greaterThanOrEqualTo(root.<String> get(criteria.getKey()), criteria.getValue().toString()));
			}
			else if (criteria.getOperation().equalsIgnoreCase("<")) {
				filters.add(builder.lessThanOrEqualTo(root.<String> get(criteria.getKey()), criteria.getValue().toString()));
			}
			else if (criteria.getOperation().equalsIgnoreCase("%")) {
				filters.add(builder.like(root.<String> get(criteria.getKey()), "%" + criteria.getValue().toString() + "%"));
			}
			else if (criteria.getOperation().equalsIgnoreCase("=")) {
				filters.add(builder.equal(root.get(criteria.getKey()), criteria.getValue().toString()));
			}
			else if (criteria.getOperation().equalsIgnoreCase("in")) {
				filters.add(root.<String> get(criteria.getKey()).in(criteria.getValue().toString()));
			}
			else if (criteria.getOperation().equalsIgnoreCase("notNull")) {
				filters.add(builder.isNotNull(root.<String> get(criteria.getKey())));
			}
			else if (criteria.getOperation().equalsIgnoreCase("isNull")) {
				filters.add(builder.isNull(root.<String> get(criteria.getKey())));
			}
		}
		

		return builder.and(filters.toArray(new Predicate[filters.size()]));
	}
}
