package com.myproject.onlinestore.controller.auth;

import com.myproject.onlinestore.entity.user.UserEntity;
import com.myproject.onlinestore.service.auth.AuthorizationService;
import com.myproject.onlinestore.exception.auth.AuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthorizationController {

	private AuthorizationService authService;

	@Autowired
	public AuthorizationController(AuthorizationService authService) {
		this.authService = authService;
	}

	@PostMapping("/registration")
	public void registration(@RequestBody UserEntity user, HttpServletResponse response)
	 throws IOException {
		try {
			authService.authorization(user);
			response.sendRedirect("/customer/info");
		} catch (AuthorizationException e) {
			response.sendError(400);
		}
	}
}