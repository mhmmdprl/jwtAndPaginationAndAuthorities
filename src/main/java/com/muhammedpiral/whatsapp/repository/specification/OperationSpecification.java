package com.muhammedpiral.whatsapp.repository.specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.muhammedpiral.whatsapp.entity.Operation;
import com.muhammedpiral.whatsapp.model.SearchCriteria;

public class OperationSpecification extends BaseSpecification<Operation> implements Specification<Operation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperationSpecification(List<SearchCriteria> criteriaList) {
		super(criteriaList);
	}

	@Override
	public Predicate toPredicate(Root<Operation> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return super.toPredicate(root, query, builder);
	}
}
