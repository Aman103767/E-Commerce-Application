package com.hb.config;

import java.util.Collections;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.hb.security.JwtAuthenticationFilter;
import com.hb.security.JwtauthEntry;
import com.hb.service.CustomerDetailsService;

@Configuration
@EnableWebSecurity
public class ProjectConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter ;

	@Autowired
	private JwtauthEntry jwtauthEntry;
	
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 // TODO Auto-generated method stub
		 auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	 }
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		 // TODO Auto-generated method stub
		 http.cors();
		 http.csrf()
		 .disable()
		 .authorizeRequests()
		 .antMatchers("/login","/Customer/create","/product/pagination","/current-user").permitAll()
         .antMatchers("/Customer/update/{customerId}",
			"/Customer/cart/{customerId}/{quantity}/{productId}",
			"/Customer/getAllProductAddedInCart/{customerId}",
			"/Customer/removeProductFromCart/{productId}/{customerId}",
			"/Customer/updatingQuantity/{productId}/{quantity}/{customerId}",
			"/Customer/removeAllProductfromCart/{customerId}",
			"/Customer/cancelOrder/{orderId}",
			"/Customer/delete/{customerId}",
			"/Customer/getAllOrdersByCustomer/{customerId}").hasAnyAuthority("USER","ADMIN")
         .anyRequest().hasAuthority("ADMIN")
         .and()
		.exceptionHandling().authenticationEntryPoint(this.jwtauthEntry)
		.and()
		 .sessionManagement()
		 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		 http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	 }
	 @Bean 
	 public BCryptPasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	 
	  @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

}
