package com.anpilov.onlinebank.services;

import com.anpilov.onlinebank.models.Transaction;
import com.anpilov.onlinebank.repositories.TransactionRepository;
import com.anpilov.onlinebank.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



/**

 The TransactionService class is a service that provides methods for managing transactions in the OnlineBank application.
 It interacts with the TransactionRepository to perform CRUD operations on transactions.
 */
@Service
public class TransactionService {


    @Autowired
    private TransactionRepository repo;

    public List<Transaction> listAllTranByUser(Long user_id, String keyword){
        if (keyword != null){
            return repo.searchByUser_id(user_id, keyword);
        }
        return repo.findByUser_id(user_id);
    }


    public void save(Transaction transaction){
        repo.save(transaction);
    }

    public Transaction get(Long id){
        return repo.findById(id).get();
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}



