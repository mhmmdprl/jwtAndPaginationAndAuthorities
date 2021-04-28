package com.muhammedpiral.whatsapp.filter;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.muhammedpiral.whatsapp.enums.JwtConfig;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
     
	
			String header = req.getHeader(JwtConfig.HEADER_STRING.getValue());
			String username = null;
			String authToken = null;
			   if (header != null && header.contains("Bearer")) {
				   authToken = header.substring(7);
				try {
					username = jwtTokenUtil.getUsernameFromToken(authToken);
				} catch (IllegalArgumentException e) {
					logger.error("an error occured during getting username from token", e);
				} catch (ExpiredJwtException e) {
					logger.warn("the token is expired and not valid anymore", e);
				}
			} else {
				logger.warn("couldn't find bearer string, will ignore the header");
			}
			
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

					UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					if (jwtTokenUtil.validateToken(authToken, userDetails)) {
						UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken,
								SecurityContextHolder.getContext().getAuthentication(), userDetails);
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
						logger.info("authenticated user " + username + ", setting security context");
						SecurityContextHolder.getContext().setAuthentication(authentication);
//						RequestContextHolder.currentRequestAttributes().setAttribute(
//								StringConstants.USER,
//		        			appUsersService.getUser(authentication.getPrincipal().toString()), 
//		        			RequestAttributes.SCOPE_REQUEST);
					}
			}

		
        chain.doFilter(req, res);
    }
}