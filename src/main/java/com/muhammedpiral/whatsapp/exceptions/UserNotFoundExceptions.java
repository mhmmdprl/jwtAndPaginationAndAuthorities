package com.muhammedpiral.whatsapp.exceptions;

import javassist.NotFoundException;

public class UserNotFoundExceptions extends NotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundExceptions(String msg) {
		super(msg+" UUID li kullanıcı bulunamadı!");
		// TODO Auto-generated constructor stub
	}

}
