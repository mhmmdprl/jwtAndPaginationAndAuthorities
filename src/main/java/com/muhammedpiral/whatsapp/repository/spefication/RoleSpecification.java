package com.muhammedpiral.whatsapp.repository.spefication;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.muhammedpiral.whatsapp.entity.Role;
import com.muhammedpiral.whatsapp.model.SearchCriteria;

public class RoleSpecification extends BaseSpefication<Role> implements Specification<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoleSpecification(List<SearchCriteria> criteriaList) {
		super(criteriaList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		return super.toPredicate(root, query, criteriaBuilder);
	}

}
