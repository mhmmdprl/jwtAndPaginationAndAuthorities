package com.muhammedpiral.whatsapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@Transactional
@NoRepositoryBean
public interface BaseRepository<E> extends JpaRepository<E, Long>,JpaSpecificationExecutor<E> {

  
	E findByUuid(String uuid);
	

}
