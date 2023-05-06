package com.anpilov.onlinebank.config;

import com.anpilov.onlinebank.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 This class provides the configuration for Spring Security.
 It enables web security and provides various beans for authentication, authorization, and encryption.
 @author Anpilov Kirill
 @version 1.0
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private UserService userService;


	/**
	 * Provides an instance of BCryptPasswordEncoder for password encoding.

	 * @return An instance of BCryptPasswordEncoder.
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



	/**
	 * Provides an instance of DaoAuthenticationProvider for authentication.
	 *
	 * @return An instance of DaoAuthenticationProvider.
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	/**
	 * Configures the security filter chain.
	 *
	 * @param http The HttpSecurity object to configure.
	 * @return A SecurityFilterChain object.
	 * @throws Exception If an error occurs while configuring the security filter chain.
	 */

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/registration**").permitAll()
						.requestMatchers("/admin").hasRole("ADMIN")
						.requestMatchers("/user").hasRole("USER")
						.anyRequest().authenticated()

				)
				.formLogin((form) -> form
						.loginPage("/login")
						.permitAll()
				)
				.logout(logout -> logout
						.logoutSuccessUrl("/login"));


		return http.build();
	}


}