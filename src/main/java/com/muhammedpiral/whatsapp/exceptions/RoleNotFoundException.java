package com.muhammedpiral.whatsapp.exceptions;

import javassist.NotFoundException;

public class RoleNotFoundException extends NotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoleNotFoundException(String msg) {
		super(msg);
	}

}
