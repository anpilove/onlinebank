package com.anpilov.onlinebank.repositories;

import com.anpilov.onlinebank.models.Transaction;
import com.anpilov.onlinebank.user.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 The TransactionRepository interface is a Spring Data JPA repository for Transaction entities.
 It provides methods to perform CRUD operations on transactions and search for transactions by user ID and keyword.
 */


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT p FROM Transaction p WHERE CONCAT(p.id_payee, ' ', p.id_payer , ' ', p.money, ' ', p.type, ' ', p.data) LIKE %?2% and p.id_payer = ?1 or p.id_payee = ?1  ORDER BY p.id DESC ")
    List<Transaction> searchByUser_id(Long user_id, String keyword);


    @Query("SELECT p FROM Transaction p WHERE p.id_payer = ?1 or p.id_payee = ?1 ORDER BY p.id DESC ")
    List<Transaction> findByUser_id(Long user_id);

}
