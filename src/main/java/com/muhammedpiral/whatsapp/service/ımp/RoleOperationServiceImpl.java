package com.muhammedpiral.whatsapp.service.ımp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muhammedpiral.whatsapp.entity.RoleOperation;
import com.muhammedpiral.whatsapp.repository.RoleOperationRepository;
import com.muhammedpiral.whatsapp.service.RoleOperationService;

@Service
public class RoleOperationServiceImpl implements RoleOperationService{

	@Autowired
	private RoleOperationRepository roleOperationRepository;
	@Override
	public List<RoleOperation> getRoleOperations(String roleCode) {
		// TODO Auto-generated method stub
		return this.roleOperationRepository.findByRoleCode(roleCode);
	}
	
	

}
