package com.muhammedpiral.whatsapp.repository;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.muhammedpiral.whatsapp.entity.User;

public interface UserRepository extends BaseRepository<User> {
	
	User findByUuid(String Uuid);
	
	User findByPhoneNumber(int phoneNumber);
	
	User findByUsername(String username);
	
	@Modifying
	@Query("update User u set u.deleted='1', u.updatedBy=?1  where u.uuid=?2")
	@Transactional
	void delete(Long updateBy,String uuid);
	
	public boolean existsByUsername(String username);


}
