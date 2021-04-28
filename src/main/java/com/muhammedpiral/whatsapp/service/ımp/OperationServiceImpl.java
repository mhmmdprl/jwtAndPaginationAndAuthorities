package com.muhammedpiral.whatsapp.service.ımp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muhammedpiral.whatsapp.entity.Operation;
import com.muhammedpiral.whatsapp.repository.OperationRepository;
import com.muhammedpiral.whatsapp.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationRepository operationRepository;
	
	@Override
	public Operation findByCode(String code) {
		
		return this.operationRepository.findByCode(code);
	}

	@Override
	public Operation findByMethodAndPath(String method, String path) {
		// TODO Auto-generated method stub
		return this.operationRepository.findByMethodAndPath(method, path);
	}

	@Override
	public Operation findByStatusAndMethodAndPath(int status, String method, String path) {
		// TODO Auto-generated method stub
		return this.operationRepository.findByStatusAndMethodAndPath(status, method, path);
	}

	@Override
	public Operation save(Operation operation) {
		
		return this.operationRepository.save(operation);
	}

	

}
