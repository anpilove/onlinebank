package com.anpilov.onlinebank.user;

import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


/**
 * The UserService interface provides the operations to manage user data in the online bank system.
 *
 * @author Anpilov Kirill
 * @version 1.0
 */
public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);

	List<User> getAll();


	User getById(Long id);


	@Transactional
	void updateBalanceByUser_id(Long userId, Double newBalance);

}
