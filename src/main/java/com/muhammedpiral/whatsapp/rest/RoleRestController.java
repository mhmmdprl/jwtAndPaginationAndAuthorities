package com.muhammedpiral.whatsapp.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muhammedpiral.whatsapp.entity.Role;
import com.muhammedpiral.whatsapp.exceptions.RoleNotFoundException;
import com.muhammedpiral.whatsapp.repository.specification.RoleSpecification;
import com.muhammedpiral.whatsapp.request.ListRequest;
import com.muhammedpiral.whatsapp.service.RoleService;
import com.muhammedpiral.whatsapp.service.UserService;
import com.muhammedpiral.whatsapp.util.CurrentUser;


@RestController
@RequestMapping
public class RoleRestController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/roles/update")
	public ResponseEntity<String> updateRole(@RequestBody Role role,@CurrentUser UserDetails userDetails) throws RoleNotFoundException{

		if (role==null) {
			throw new RoleNotFoundException("Bulunamadı!");
		}
		
		role.setUpdatedBy(this.userService.findByUsername(userDetails.getUsername()).getId());
		role.setUpdatedDate(new Date());
		this.roleService.update(role);
		return ResponseEntity.ok("Role Güncellendi");
		
	}
	
	@GetMapping("/roles/searching")
	public ResponseEntity<Page<Role>> searchCriteria(@RequestBody ListRequest listRequest){
		
		RoleSpecification roleSpecification=new RoleSpecification(listRequest.getSearch());
		
		Page<Role> roles=this.roleService.getList(roleSpecification, listRequest.getSortedByName());
		
		return ResponseEntity.ok(roles);
	}
	
	@GetMapping("/roles")
	public ResponseEntity<List<Role>> getAll(){
		List<Role> roles=this.roleService.findAll();
		return ResponseEntity.ok(roles);
		
	}
	
	
	
}
