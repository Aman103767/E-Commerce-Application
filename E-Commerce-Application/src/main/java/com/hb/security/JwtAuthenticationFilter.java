package com.hb.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hb.service.CustomerDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService uService;
	
	@Autowired
	private JwtUtil jhelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//1.  fetch jwt tokens
		// get token
		String requestToken=request.getHeader("Authorization");
		
		System.out.println(requestToken);
		
		String username=null;
		
		String token=null; // from bearer we get the token
		
		if(requestToken!=null && requestToken.startsWith("Bearer")) {
			
			// token without Bearer
			token=requestToken.substring(7);
			 
			try {
			username=this.jhelper.getUsernameFromToken(token);
			}
			catch(IllegalArgumentException e) {
				System.out.println("Unable to get jwt ");
			}
			catch(ExpiredJwtException e) {
				System.out.println(e);
				System.out.println("jwt token expired");
			}catch(MalformedJwtException e) {
				System.out.println("Invalid Exception");
			}
			
			
		}else{
			System.out.println("Jwt does not begin with bearer");
		}
		
		//we get here token
		// validate
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null ) {
			
			UserDetails userdetails=this.uService.loadUserByUsername(username);
			
			
			if(this.jhelper.validateToken(token, userdetails)) {
				// going correct then authenticate
				
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userdetails,null, userdetails.getAuthorities());
				System.out.println("auth---------====-="+userdetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}else {
				System.out.println("Invalid JWT token");
			}
			
		}else {
			System.out.println("Username is null or context not null");
		}
		
		
		filterChain.doFilter(request, response);
	}
}