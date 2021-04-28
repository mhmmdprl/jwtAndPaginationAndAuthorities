package com.muhammedpiral.whatsapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.muhammedpiral.whatsapp.model.SearchCriteria;

public interface BaseService<E,S> {

	public List<E> findByCriteria(List<SearchCriteria> criteriaList);
	
	
	public Page<E> getList(S spec, Pageable pageable);
	
	public void update(E entity);
	
	void delete(Long updateBy,String uuid);
	
	public List<E> findAll();
}
