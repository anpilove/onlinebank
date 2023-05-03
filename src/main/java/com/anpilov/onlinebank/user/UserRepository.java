package com.anpilov.onlinebank.user;

import com.anpilov.onlinebank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



/**
 * The UserRegistrationDto class represents the data transfer object for user registration in the online bank system.
 *
 * @author Anpilov Kirill
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	Optional<User> findById(Long id);


	@Modifying
	@Query("UPDATE User u SET u.balance = ?2 WHERE u.id = ?1")
	int updateBalanceByUser_id(Long user_id, Double balance);



}
