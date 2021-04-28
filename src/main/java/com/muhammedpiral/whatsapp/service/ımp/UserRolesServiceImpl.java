package com.muhammedpiral.whatsapp.service.ımp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muhammedpiral.whatsapp.entity.UserRoles;
import com.muhammedpiral.whatsapp.repository.UserRolesRepository;
import com.muhammedpiral.whatsapp.service.UserRolesService;


@Service
public class UserRolesServiceImpl implements UserRolesService {

	@Autowired
	private UserRolesRepository userRolesRepository;
	
	
	@Override
	public UserRoles save(UserRoles userRoles) {
		
		return this.userRolesRepository.save(userRoles);
	}


}
