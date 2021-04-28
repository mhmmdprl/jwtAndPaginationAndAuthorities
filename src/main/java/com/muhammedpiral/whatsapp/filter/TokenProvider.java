package com.muhammedpiral.whatsapp.filter;

import java.io.Serializable;
import java.security.Key;

import io.jsonwebtoken.security.Keys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import com.muhammedpiral.whatsapp.entity.Role;
import com.muhammedpiral.whatsapp.entity.User;
import com.muhammedpiral.whatsapp.enums.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider implements Serializable {
	 Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		Long userId = Long.parseLong(claims.get(JwtConfig.USER_ID.getValue()).toString());
		return userId;
	}

	public Long getUserIdFromRequest(HttpServletRequest req) {
		String header = req.getHeader(JwtConfig.HEADER_STRING.getValue());
		if (header == null) {

			return 0L;
		}
		String token = header.replace(JwtConfig.TOKEN_PREFIX.getValue() + " ", " ");
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		Long userId = Long.parseLong(claims.get(JwtConfig.USER_ID.getValue()).toString());
		return userId;
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	  public String generateToken(Authentication authentication,User user) {
//        final String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(",")); //TODO Tum rollerin yetkileri çekilip map'e atanacak
        
        final String roles = user.getRoles().stream()
                .map(Role::getCode)
                .collect(Collectors.joining(","));
        
        Map<String, String> privileges = new HashMap<String, String>();
        user.getRoles().forEach(role->{
        	role.getOperations().forEach(operation->{
             	privileges.put(operation.getCode(), operation.getCode());
        	});
        });
        
        final String authorities = privileges.entrySet().stream().map(k->k.getValue()).collect(Collectors.joining(",")); 
       
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(JwtConfig.AUTHORITIES.getValue(), authorities)
                .claim(JwtConfig.NAME.getValue(), user.getName())
                .claim(JwtConfig.ROLES.getValue(), roles)
                .claim(JwtConfig.USER_NAME.getValue(), user.getUsername())
                .claim(JwtConfig.USER_ID.getValue(), user.getId())
                .signWith(key)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(JwtConfig.ACCESS_TOKEN_VALIDITY_SECONDS.getValue())*1000))
                .compact();
    }
	
	  public List<String> getAuthoritiesFromToken(String token) {
			List<String> authorities = new ArrayList<String>();
			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			authorities = Arrays.asList(claims.get(JwtConfig.AUTHORITIES.getValue()).toString().split(","));
			return authorities;
		}

    public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {

        final JwtParser jwtParser = Jwts.parser().setSigningKey(key);
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        final Claims claims = claimsJws.getBody();
        final Collection<? extends GrantedAuthority> authorities = claims.get(JwtConfig.AUTHORITIES.getValue()).toString().length()> 0 ?
                Arrays.stream(claims.get(JwtConfig.AUTHORITIES.getValue()).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()) : null;

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

}
