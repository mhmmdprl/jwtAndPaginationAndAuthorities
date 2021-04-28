package com.muhammedpiral.whatsapp.service;

import com.muhammedpiral.whatsapp.entity.Operation;

public interface OperationService {

	Operation save(Operation operation);
	
	Operation findByCode(String code);
	
	Operation findByMethodAndPath(String method, String path);
	
	Operation findByStatusAndMethodAndPath(int status, String method, String path);
}
