package com.muhammedpiral.whatsapp.repository.specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.muhammedpiral.whatsapp.entity.User;
import com.muhammedpiral.whatsapp.model.SearchCriteria;

public class UserSpecification extends BaseSpecification<User> implements Specification<User>{

	public UserSpecification(List<SearchCriteria> criteriaList) {
		super(criteriaList);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		
		return super.toPredicate(root, criteriaQuery, criteriaBuilder);
	}

	
}
