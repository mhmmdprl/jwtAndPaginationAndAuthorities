package com.muhammedpiral.whatsapp.repository;


import com.muhammedpiral.whatsapp.entity.Role;

public interface RoleRepository extends BaseRepository<Role> {

	Role findByCode(String code);
}
