package com.muhammedpiral.whatsapp.exceptions;

import java.rmi.AlreadyBoundException;


public class AlreadyUseExecpitons extends AlreadyBoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyUseExecpitons(String message) {
		super(message+" Kullanıcı adı zaten kullanılmakta! ");
		// TODO Auto-generated constructor stub
	}

}
