package com.muhammedpiral.whatsapp.repository;



import com.muhammedpiral.whatsapp.entity.Operation;

public interface OperationRepository extends BaseRepository<Operation>{
	
	Operation findByCode(String code);
	
	Operation findByMethodAndPath(String method, String path);
	
	Operation findByStatusAndMethodAndPath(int status, String method, String path);

}
