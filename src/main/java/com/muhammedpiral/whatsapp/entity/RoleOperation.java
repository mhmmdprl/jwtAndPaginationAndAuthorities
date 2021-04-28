package com.muhammedpiral.whatsapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ROLES_OPERATIONS")
@EqualsAndHashCode(callSuper=false)
public class RoleOperation extends BaseEntity {

	private static final long serialVersionUID = 3795110249915259490L;

	@Id
	@SequenceGenerator(name = "SEQ_ROLES_OPERATIONS", sequenceName = "SEQ_ROLES_OPERATIONS", allocationSize = 1)
	@GeneratedValue(generator = "SEQ_ROLES_OPERATIONS", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "ROLE_CODE")
	private String roleCode;

	@Column(name = "OPERATION_CODE")
	private String operationCode;

}