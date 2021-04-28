package com.muhammedpiral.whatsapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name="USERS_ROLES")
@Data
@EqualsAndHashCode(callSuper=false)
public class UserRoles extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS_ROLES")
    @SequenceGenerator(sequenceName = "SEQ_USERS_ROLES", allocationSize = 1, name = "SEQ_USERS_ROLES")
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name="USERS_ID")
	private Long userId;
	
	@Column(name="ROLE_CODE")
	private String roleCode;
	

}
