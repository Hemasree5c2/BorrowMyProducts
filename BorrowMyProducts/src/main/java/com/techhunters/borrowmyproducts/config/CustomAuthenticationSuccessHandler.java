package com.techhunters.borrowmyproducts.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.techhunters.borrowmyproducts.entity.User;
import com.techhunters.borrowmyproducts.service.UserService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {


		String username = authentication.getName();

		User theUser = userService.findByUserName(username);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);
		
		response.sendRedirect(request.getContextPath() + "/mainPage");
	}

}