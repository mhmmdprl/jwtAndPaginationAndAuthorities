package com.muhammedpiral.whatsapp.service;

import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.muhammedpiral.whatsapp.entity.User;
import com.muhammedpiral.whatsapp.repository.specification.UserSpecification;


public interface UserService extends BaseService<User, UserSpecification> {


	
	public	User userSave(User user);

	public	User findByUuid(String uuid);

	public	User findByPhoneNumber(int phoneNumber);
	
	public	User findByUsername(String username);

	public Set<SimpleGrantedAuthority> getAuthority(User user);

	public boolean existsByUsername(String username);
	
	void deleteUser(User user);
}
