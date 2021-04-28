package com.muhammedpiral.whatsapp.enums;

import lombok.Getter;

@Getter
public enum JwtConfig {
	
	
	ACCESS_TOKEN_VALIDITY_SECONDS("15000"), //5*60*60
	TOKEN_PREFIX("Bearer"),
	HEADER_STRING("Authorization"),
	AUTHORITIES("Authorities"),
	USER_ID("id"),
	USER_NAME("username"),
	NAME("name"),
	ROLES("roles");

    private String value;

    JwtConfig(String value) {
        this.value = value;
    }


}
