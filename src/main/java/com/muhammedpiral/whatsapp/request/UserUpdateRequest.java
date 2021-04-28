package com.muhammedpiral.whatsapp.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

	
	private String profilePhoto;

	private String myAbout;
	
	private String name;
	
	private int age;
	
	private int phoneNumber;
}
