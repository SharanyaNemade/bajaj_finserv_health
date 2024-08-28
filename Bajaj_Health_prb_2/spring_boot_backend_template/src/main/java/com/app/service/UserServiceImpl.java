package com.app.service;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.AuthenticationException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.RegisterDto;
import com.app.entities.User;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	// depcy
	@Autowired
	private UserDao userDao;
	@Autowired
	private ModelMapper mapper;
	@Override
	public ApiResponse addUser(RegisterDto request,String rollno) {
		
		User user=mapper.map(request, User.class);
			
		if(rollno==null)
		{
		  throw new ResourceNotFoundException("please enter the roll number in request header");	
		}
		else if(user.getFirstName()==null || user.getFirstName().isBlank())
		{
		  //AuthenticationException	
			throw new AuthenticationException(" first name cannot be empty");	
		}
		else if(user.getLastName()==null || user.getLastName().isBlank())
		{
		  //AuthenticationException	
			throw new AuthenticationException(" last name cannot be empty");	
		}
		else if(user.getEmail()==null ||  user.getEmail().isBlank())
		{
		  //AuthenticationException	
			throw new AuthenticationException("email cannot be empty");	
		}
		else if(user.getPhonenumber()==null || user.getPhonenumber().isBlank())
		{
		  //AuthenticationException	
			throw new AuthenticationException(" phone number cannot be empty");	
		}
		
		List<User> users= userDao.findAll();
		
		for (User user2 : users)
		{
		  if(user2.getEmail().compareTo(user.getEmail())==0)
		  {
			  throw new AuthenticationException("please enter Unique email id");
		  }
		  else if(user2.getPhonenumber().compareTo(user.getPhonenumber())==0)
		  {
			  throw new AuthenticationException("please enter Unique phone number");  
		  }
		}
		userDao.save(user);		
		return new ApiResponse("User Registered Successfully");
	}

}
