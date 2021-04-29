package com.muhammedpiral.whatsapp.entity;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ROLES")

@EqualsAndHashCode(callSuper=false)

public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ROLES", sequenceName = "SEQ_ROLES", allocationSize = 1)
	@GeneratedValue(generator = "SEQ_ROLES", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "ROLE_NAME")
	private String name;

	@Column(name = "CODE")
	private String code;

	@Column(name = "DESCRIPTION")
	private String description;

	

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY )
	@JoinTable(name = "ROLES_OPERATIONS", joinColumns = @JoinColumn(name = "ROLE_CODE", referencedColumnName = "CODE"), 
	inverseJoinColumns = @JoinColumn(name = "OPERATION_CODE", referencedColumnName = "CODEE"))
    private List<Operation> operations;
}
