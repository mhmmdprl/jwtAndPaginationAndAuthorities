package com.muhammedpiral.whatsapp.service;

import java.util.List;

import com.muhammedpiral.whatsapp.entity.RoleOperation;

public interface RoleOperationService {
	
	
	List<RoleOperation> getRoleOperations(String roleCode);

}
