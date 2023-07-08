package com.hb.security;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
@Component
public class JwtUtil {
public static final long jwt_token_validity=5*60*60;
	
	private String secret="tokenkey";
	
	//get username from jwt token
	public String getUsernameFromToken(String token) {
//		return getClaimFromToken(token, Claims::getSubject);
		 return getClaimFromToken(token, Claims::getSubject);
	}

	// get expriration date
	public Date getExpirationfromtoken(String token) {
		return (Date) getClaimFromToken(token,Claims::getExpiration);
	}
	

	private <T> T getClaimFromToken(String token,Function<Claims,T> ClaimResolver) {
		final Claims claims=getAllClaimsFromToken(token);
		return ClaimResolver.apply(claims);
	}

	// from the secrete key 
	private Claims getAllClaimsFromToken(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	
	private Boolean istokenexpire(String token) {
		 Date expiryDate=getExpirationfromtoken(token);
		 return expiryDate.before(new Date());
	}
	
	//generate token
	public String generateTokens(UserDetails e){
		Map<String,Object> claims=new HashMap<>();
		return doGenerateToken(claims,e.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		// TODO Auto-generated method stub
		 return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + jwt_token_validity * 1000))
	                .signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	 public Boolean validateToken(String token, UserDetails userDetails){
	        final String username=getUsernameFromToken(token);
	        return (username.equals(userDetails.getUsername()) && !istokenexpire(token));
	    }
	
}
