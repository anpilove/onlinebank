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
 @author Anpilov Kirill
 @version 1.0
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repo;

    /**
     * Retrieves all transactions for a user that match a given keyword.
     *
     * @param user_id the ID of the user whose transactions to retrieve
     * @param keyword the keyword to search for in the transactions (can be null)
     * @return a list of all transactions for the specified user that match the keyword (if provided), ordered by ID in descending order
     */
    public List<Transaction> listAllTranByUser(Long user_id, String keyword){
        if (keyword != null){
            return repo.searchByUser_id(user_id, keyword);
        }
        return repo.findByUser_id(user_id);
    }

    /**
     * Saves a transaction to the database.
     *
     * @param transaction the transaction to save
     */
    public void save(Transaction transaction){
        repo.save(transaction);
    }

    /**
     * Retrieves a transaction by ID.
     *
     * @param id the ID of the transaction to retrieve
     * @return the transaction with the specified ID
     */
    public Transaction get(Long id){
        return repo.findById(id).get();
    }

    /**
     * Deletes a transaction by ID.
     *
     * @param id the ID of the transaction to delete
     */
    public void delete(Long id){
        repo.deleteById(id);
    }
}



