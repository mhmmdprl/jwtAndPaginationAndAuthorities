package com.muhammedpiral.whatsapp.service.ımp;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.muhammedpiral.whatsapp.entity.Role;
import com.muhammedpiral.whatsapp.model.SearchCriteria;
import com.muhammedpiral.whatsapp.repository.RoleRepository;
import com.muhammedpiral.whatsapp.repository.specification.RoleSpecification;
import com.muhammedpiral.whatsapp.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;
	
	@Override
	public Role save(Role role) {
		
		return this.roleRepository.save(role);
	}

	@Override
	public Role findByCode(String code) {
		return this.roleRepository.findByCode(code);
	}

	@Override
	public List<Role> findByCriteria(List<SearchCriteria> criteriaList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Role> getList(RoleSpecification spec, Pageable pageable) {
		
		return this.roleRepository.findAll(spec, pageable);
	}

	@Override
	public void update(Role entity) {
		
		this.roleRepository.save(entity);
	}

	@Override
	public void delete(Long updateBy, String uuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Role> findAll() {
		
		return this.roleRepository.findAll();
	}
	
	

}
