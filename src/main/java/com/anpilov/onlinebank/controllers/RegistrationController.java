package com.anpilov.onlinebank.controllers;

import com.anpilov.onlinebank.user.UserRegistrationDto;
import com.anpilov.onlinebank.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *This class provides a controller to handle user registration requests.
 *@author Anpilov Kirill
 *@version 1.0
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

	private UserService userService;

	public RegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	/**
	 *Initializes the user registration form with an empty UserRegistrationDto object.
	 *@return UserRegistrationDto object with default values.
	 */
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}


	/**
	 *Renders the user registration page.
	 *@return Name of the registration view.
	 */
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}


	/**
	 Processes user registration requests.
	 @param registrationDto UserRegistrationDto object with the user data to register.
	 @return Name of the registration view with success message.
	 */
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
