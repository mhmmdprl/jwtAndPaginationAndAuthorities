package com.muhammedpiral.whatsapp.service;

import org.springframework.stereotype.Service;

import com.muhammedpiral.whatsapp.entity.Role;
import com.muhammedpiral.whatsapp.repository.spefication.RoleSpecification;

@Service
public interface RoleService extends BaseService<Role, RoleSpecification>{
	
	
public Role save(Role role);

Role findByCode(String code);

}
