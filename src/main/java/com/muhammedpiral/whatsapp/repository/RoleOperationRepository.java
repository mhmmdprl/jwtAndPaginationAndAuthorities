package com.muhammedpiral.whatsapp.repository;

import java.util.List;


import com.muhammedpiral.whatsapp.entity.RoleOperation;

public interface RoleOperationRepository extends BaseRepository<RoleOperation> {
	
	List<RoleOperation> findByRoleCode(String roleCode);

}
