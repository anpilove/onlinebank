package com.anpilov.onlinebank.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);

	List<User> getAll();


	User getById(Long id);

}
