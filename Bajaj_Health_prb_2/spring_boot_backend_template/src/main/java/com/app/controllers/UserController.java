package com.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exceptions.AuthenticationException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.RegisterDto;
import com.app.service.UserService;


@RestController
@RequestMapping("/bfhldevapigw.healthrx.co.in/automation-campus/create")
@CrossOrigin
public class UserController {
	@Autowired //byType
	private UserService userService;

	public UserController() {
		System.out.println("in ctor " + getClass());
	}

	
	@PostMapping("/user")                          
	public ResponseEntity<?> registerUser(@RequestHeader String rollno ,@RequestBody RegisterDto request)
	{
		
		try
		{
		   return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(request, rollno));
		}
		catch (AuthenticationException e)
		{
			//System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));					
		}
		catch (ResourceNotFoundException e)
		{
			//System.out.println(e);
		
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage()));
		}
		
		
	}

}
