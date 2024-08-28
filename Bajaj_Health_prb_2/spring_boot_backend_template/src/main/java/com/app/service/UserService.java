package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.RegisterDto;


public interface UserService {
	ApiResponse addUser(RegisterDto request, String rollno);
}
