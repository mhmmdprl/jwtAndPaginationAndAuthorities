package com.muhammedpiral.whatsapp.entity;

import java.util.ArrayList;
import java.util.Date;
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

import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Where(clause="deleted='0'")
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "user_id", allocationSize = 1)
	@GeneratedValue(generator = "user_id", strategy = GenerationType.SEQUENCE)
	private Long id;

	private String profilePhoto;

	private String myAbout;

	@Column(name = "phone_number", nullable = false)
	private int phoneNumber;

	@Column(name = "USERNAME" ,updatable = false,nullable = false,unique = true )
	private String username;
	private String password;

	private Date birtOfDate;
	@Column(name = "name", nullable = false)
	private String name;


	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY )
	@JoinTable(name = "USERS_ROLES", joinColumns = @JoinColumn( name="Users_Id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="ROLE_CODE",referencedColumnName = "CODE")) 
	private List<Role> roles = new ArrayList<Role>();

}
