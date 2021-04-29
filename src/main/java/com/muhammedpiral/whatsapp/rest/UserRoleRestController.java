package com.muhammedpiral.whatsapp.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.muhammedpiral.whatsapp.entity.User;
import com.muhammedpiral.whatsapp.entity.UserRoles;
import com.muhammedpiral.whatsapp.exceptions.UserNotFoundExceptions;
import com.muhammedpiral.whatsapp.service.UserRolesService;
import com.muhammedpiral.whatsapp.service.UserService;
import com.muhammedpiral.whatsapp.util.CurrentUser;

@RestController
@RequestMapping
public class UserRoleRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRolesService userRolesService;

	@PostMapping("/roles/add")
	public ResponseEntity<String> addRole(@RequestParam String userUuid, @RequestParam String roleCode)
			throws UserNotFoundExceptions {
		User user = this.userService.findByUuid(userUuid);
		if (user != null) {
			UserRoles userRoles = new UserRoles();
			userRoles.setUserId(user.getId());
			userRoles.setRoleCode(roleCode);
			this.userRolesService.save(userRoles);
			return ResponseEntity.ok("Role Atandı");
		}

		throw new UserNotFoundExceptions(userUuid);

	}
	
	
	@PutMapping("/roles/updaterole")
	public ResponseEntity<String> update(@RequestParam String userUuid, @RequestParam String roleCode,@CurrentUser UserDetails details)
			throws UserNotFoundExceptions {
		User user = this.userService.findByUuid(userUuid);
		
		if (user != null) {
			User admin=this.userService.findByUsername(details.getUsername());
			UserRoles userRoles = new UserRoles();
			userRoles.setUserId(user.getId());
			userRoles.setRoleCode(roleCode);
			userRoles.setCreatedBy(admin.getId());
			userRoles.setCreatedDate(new Date());
			this.userRolesService.save(userRoles);
			return ResponseEntity.ok("Role Atandı");
		}

		throw new UserNotFoundExceptions(userUuid);

	}


}
