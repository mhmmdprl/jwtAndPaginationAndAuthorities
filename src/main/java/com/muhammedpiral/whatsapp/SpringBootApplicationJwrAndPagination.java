package com.muhammedpiral.whatsapp;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.muhammedpiral.whatsapp.entity.Operation;
import com.muhammedpiral.whatsapp.entity.Role;
import com.muhammedpiral.whatsapp.entity.RoleOperation;
import com.muhammedpiral.whatsapp.entity.User;
import com.muhammedpiral.whatsapp.entity.UserRoles;
import com.muhammedpiral.whatsapp.repository.OperationRepository;
import com.muhammedpiral.whatsapp.repository.RoleOperationRepository;
import com.muhammedpiral.whatsapp.repository.RoleRepository;
import com.muhammedpiral.whatsapp.repository.UserRepository;
import com.muhammedpiral.whatsapp.repository.UserRolesRepository;

@SpringBootApplication
public class SpringBootApplicationJwrAndPagination implements CommandLineRunner {

	
	
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootApplicationJwrAndPagination.class, args);
		
	}
	
	
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private RoleOperationRepository roleOperationRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRolesRepository userRolesRepository;



	@Override
	public void run(String... args) throws Exception {

		User user = new User();
		user.setName("Sercan");
		user.setPhoneNumber(123);
		user.setUsername("ADMIN");
		user.setMyAbout("!!!!!!!!");
		user.setPassword(passwordEncoder.encode("admin"));

		User user1 = new User();
		user1.setName("Cemal");
		user1.setPhoneNumber(123456);
		user1.setUsername("USER");
		user1.setMyAbout("MAYIN TARLASINDA DOLANIP DURMUŞUM AŞK SANIPTA!!");
		user1.setPassword(passwordEncoder.encode("admin"));

		User user2 = new User();
		user2.setName("Cemil");
		user2.setPhoneNumber(12345);
		user2.setUsername("MANAGER");
		user2.setMyAbout("SOYUNUP KORKUSUZCA ÇIRIL ÇIPLAK KALMIŞIM!!");
		user2.setPassword(passwordEncoder.encode("admin"));

		User user3 = new User();
		user3.setName("Barış");
		user3.setPhoneNumber(1234);
		user3.setUsername("CREATER");
		user3.setMyAbout("SAVAŞ FİLMLERİNDE OLUR YA, YARALI YARALI DEVAM ETMİŞİM!!");
		user3.setPassword(passwordEncoder.encode("admin"));

		User user4 = new User();
		user4.setName("Serhat");
		user4.setPhoneNumber(1234);
		user4.setUsername("AUTHOR");
		user4.setMyAbout("TEL ÖRGÜLERDE DURMAMIŞ Bİ DELİKTEN GEÇMİŞİM");
		user4.setPassword(passwordEncoder.encode("admin"));

		List<User> users = Arrays.asList(user, user1, user2, user3);

		userRepository.saveAll(users);

		// ****************************Roles**************************************

		Role role = new Role();
		role.setCode("Admin");
		role.setName("Admin");
		role.setDescription("All Method");

		Role role1 = new Role();
		role1.setCode("Manager");
		role1.setName("Manager");
		role1.setDescription("Update and Get");

		Role role2 = new Role();
		role2.setCode("User");
		role2.setName("User");
		role2.setDescription("Only Get");

		List<Role> roles = Arrays.asList(role, role1, role2);
		this.roleRepository.saveAll(roles);

		// *****************Operation******************************
		Operation operation = new Operation();
		operation.setMethod("PUT");
		operation.setPath("/user/update");
		operation.setDescription("Update operation");
		operation.setCode("PUT");

		Operation operation1 = new Operation();
		operation1.setMethod("POST");
		operation1.setPath("/user/save");
		operation1.setDescription("Save operation");
		operation1.setCode("POST");

		Operation operation2 = new Operation();
		operation2.setMethod("DELETE");
		operation2.setPath("/user/delete/{uuid}");
		operation2.setDescription("Delete operation");
		operation2.setCode("DELETE");

		Operation operation3 = new Operation();
		operation3.setMethod("GET");
		operation3.setPath("/users");
		operation3.setDescription("Update operation");
		operation3.setCode("GET");

		List<Operation> operations = Arrays.asList(operation, operation1, operation2, operation3);

		this.operationRepository.saveAll(operations);

		RoleOperation roleOperation = new RoleOperation();
		roleOperation.setOperationCode("DELETE");
		roleOperation.setRoleCode("Admin");

		RoleOperation roleOperation1 = new RoleOperation();
		roleOperation1.setOperationCode("POST");
		roleOperation1.setRoleCode("Admin");

		RoleOperation roleOperation2 = new RoleOperation();
		roleOperation2.setOperationCode("GET");
		roleOperation2.setRoleCode("Admin");

		RoleOperation roleOperation3 = new RoleOperation();
		roleOperation3.setOperationCode("PUT");
		roleOperation3.setRoleCode("Admin");

		RoleOperation roleOperation4 = new RoleOperation();
		roleOperation4.setOperationCode("PUT");
		roleOperation4.setRoleCode("Manager");

		RoleOperation roleOperation5 = new RoleOperation();
		roleOperation5.setOperationCode("GET");
		roleOperation5.setRoleCode("Manager");

		RoleOperation roleOperation6 = new RoleOperation();
		roleOperation6.setOperationCode("GET");
		roleOperation6.setRoleCode("User");

		List<RoleOperation> roleOperations = Arrays.asList(roleOperation, roleOperation1, roleOperation2,
				roleOperation3, roleOperation4, roleOperation5, roleOperation6);
		this.roleOperationRepository.saveAll(roleOperations);
		
		
		UserRoles userRoles=new UserRoles();
		userRoles.setUserId(this.userRepository.findByUsername("ADMIN").getId());
		userRoles.setRoleCode("Admin");
		
		UserRoles userRoles1=new UserRoles();
		userRoles1.setUserId(this.userRepository.findByUsername("USER").getId());
		userRoles1.setRoleCode("User");
		
		UserRoles userRoles2=new UserRoles();
		userRoles2.setUserId(this.userRepository.findByUsername("MANAGER").getId());
		userRoles2.setRoleCode("Manager");
		
		
		UserRoles userRoles3=new UserRoles();
		userRoles3.setUserId(this.userRepository.findByUsername("CREATER").getId());
		userRoles3.setRoleCode("Manager");
		
		List<UserRoles> userRoless=Arrays.asList(userRoles,userRoles1,userRoles2,userRoles3);
		
		this.userRolesRepository.saveAll(userRoless);
		
	}

}
