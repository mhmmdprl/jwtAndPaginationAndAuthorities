package com.muhammedpiral.whatsapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "OPERATIONS")
@Where(clause="deleted='0'")
public class Operation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_OPERATIONS", sequenceName = "SEQ_OPERATIONS", allocationSize = 1)
	@GeneratedValue(generator = "SEQ_OPERATIONS", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "PATH")
	private String path;

	@Column(name = "METHOD")
	private String method;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CODEE")
	private String code;
	


}