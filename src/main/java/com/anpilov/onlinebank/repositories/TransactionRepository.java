package com.anpilov.onlinebank.repositories;

import com.anpilov.onlinebank.models.Transaction;
import com.anpilov.onlinebank.user.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT p FROM Transaction p WHERE CONCAT(p.id, ' ', p.id_payee, ' ', p.id_payer , ' ', p.money, ' ', p.type, ' ', p.data, ' ', p.details) LIKE %?2% and p.id_payer = ?1 ORDER BY p.data DESC ")
    List<Transaction> searchByUser_id(Long user_id, String keyword);


    @Query("SELECT p FROM Transaction p WHERE p.id_payer = ?1 ORDER BY p.data DESC ")
    List<Transaction> findByUser_id(Long user_id);

}
