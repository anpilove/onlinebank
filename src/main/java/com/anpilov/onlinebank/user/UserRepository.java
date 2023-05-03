package com.anpilov.onlinebank.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	@NotNull Optional<User> findById(@NotNull Long user_id);
}
