package com.muhammedpiral.whatsapp.rest;

import java.rmi.AlreadyBoundException;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.muhammedpiral.whatsapp.entity.User;
import com.muhammedpiral.whatsapp.exceptions.AlreadyUseExecpitons;
import com.muhammedpiral.whatsapp.exceptions.UserNotFoundExceptions;
import com.muhammedpiral.whatsapp.repository.spefication.UserSpecification;
import com.muhammedpiral.whatsapp.request.ListRequest;
import com.muhammedpiral.whatsapp.request.UserUpdateRequest;
import com.muhammedpiral.whatsapp.service.UserService;
import com.muhammedpiral.whatsapp.util.CurrentUser;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserRestController {

	private final UserService userService;

	@GetMapping("/users/searcing")
	public Page<User> getUserList(@RequestBody ListRequest list) {

		UserSpecification spec = new UserSpecification(list.getSearch());
		Page<User> userList = userService.getList(spec, list.getSortedByName());
		return userList;
	}

	@DeleteMapping("/user/delete/{uuid}")
	public ResponseEntity<String> delete(@PathVariable String uuid, @CurrentUser UserDetails userDetails)
			throws NotFoundException {
		User user = this.userService.findByUuid(uuid);
		User admin = this.userService.findByUsername(userDetails.getUsername());

		if (user != null) {
			this.userService.delete(admin.getId(), uuid);
			return ResponseEntity.ok(user.getName() + " İsimli kullanıcıyı sildiniz!");
		}

		else
			throw new UserNotFoundExceptions(uuid);
	}

	@GetMapping("/myprofile")
	public ResponseEntity<User> myProfile(@CurrentUser UserDetails userDetails) {

		return ResponseEntity.ok(this.userService.findByUsername(userDetails.getUsername()));
	}

	@PutMapping("/user/update")
	public ResponseEntity<String> update(@RequestBody UserUpdateRequest userUpdateRequest, @CurrentUser UserDetails userDetails)
			throws NotFoundException {

		if (userUpdateRequest != null) {
			
			User user= new User();
			user.setAge(userUpdateRequest.getAge());
			user.setMyAbout(userUpdateRequest.getMyAbout());
			user.setProfilePhoto(userUpdateRequest.getProfilePhoto());
			user.setPhoneNumber(userUpdateRequest.getPhoneNumber());
			user.setName(userUpdateRequest.getName());
			user.setUpdatedBy(this.userService.findByUsername(userDetails.getUsername()).getId());
			user.setUpdatedDate(new Date());
			this.userService.update(user);
			return ResponseEntity.ok(user.getName() + " İsimli kullanıcıyı sildiniz!");
		} else
			throw new NotFoundException("NotFoundException");
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAll() {

		return ResponseEntity.ok(this.userService.findAll());

	}

	@PostMapping("user/save")
	public ResponseEntity<User> save(@RequestBody User user, @CurrentUser UserDetails userDetails) throws AlreadyBoundException {

		if (this.userService.existsByUsername(user.getUsername())) {

			throw new AlreadyUseExecpitons(user.getUsername());
		}
		user.setCreatedBy(this.userService.findByUsername(userDetails.getUsername()).getId());
		return ResponseEntity.ok(this.userService.userSave(user));

	}
	
	@GetMapping("users/{uuid}")
	public ResponseEntity<User> getUser(@PathVariable String uuid) throws UserNotFoundExceptions{
		User user=this.userService.findByUuid(uuid);
		
		if (user==null) {
			throw new UserNotFoundExceptions(uuid);
		}
		
		return ResponseEntity.ok(user);
	}

}
