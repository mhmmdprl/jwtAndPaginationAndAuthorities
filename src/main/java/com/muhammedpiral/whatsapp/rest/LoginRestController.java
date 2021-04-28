package com.muhammedpiral.whatsapp.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muhammedpiral.whatsapp.entity.User;
import com.muhammedpiral.whatsapp.filter.TokenProvider;
import com.muhammedpiral.whatsapp.request.LoginRequest;
import com.muhammedpiral.whatsapp.service.UserService;

@RestController
@RequestMapping
public class LoginRestController {
	@Autowired
	private TokenProvider jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;



	
	    
	    @PostMapping(path = "/login" )
	    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
	       	
	    	User user = null;
	    	String token = null;
	    	try {
	            final Authentication authentication = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                    		loginRequest.getUsername(),
	                    		loginRequest.getPassword()
	                    )
	            );
	        	user = userService.findByUsername(loginRequest.getUsername());
	      
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            token = jwtTokenUtil.generateToken(authentication,user);
			} catch (Exception e) {
				 System.out.println("hata" +e);
			}

	        return  ResponseEntity.ok(token);
	 
	    }
}
