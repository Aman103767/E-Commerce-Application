package com.hb.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {
//    @Autowired
//    private HttpSession httpSession;
//    @GetMapping("/logout")
//    public ResponseEntity<String> logout(HttpServletResponse response) {
//       Cookie cookie = new Cookie("JSESSIONID","");
//       response.addCookie(cookie);
//       System.out.println(cookie);
//       return new ResponseEntity<String>("Logout successfully",HttpStatus.ACCEPTED);
//    }
}