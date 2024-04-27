package com.example.assessment.service;

import com.example.assessment.entity.Transaction;
import com.example.assessment.entity.User;
import com.example.assessment.exception.TransactionNotFoundException;
import com.example.assessment.model.TransactionDTO;
import com.example.assessment.repository.TransactionRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(TransactionDTO transactionDTO) throws BadRequestException {
        User sender = userService.getUser(transactionDTO.getSenderId());
        User receiver = userService.getUser(transactionDTO.getReceiverId());

        if(sender == null || receiver == null) throw new BadRequestException("One of your Users do not exist");

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionDate(new Date());
        return repository.save(transaction);
    }

    public List<Transaction> getTransactions() {
        return repository.findAll();
    }

    public Transaction getTransaction(Integer id) {
        return repository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction with ID " + id + " not found"));
    }

    public Boolean deleteTransaction(Integer id) {
        repository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction with ID " + id + " not found"));
        repository.deleteById(id);
        return TRUE;
    }

    public Transaction updateTransaction(TransactionDTO transactionDTO, Integer id) throws BadRequestException {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction with ID " + id + " not found"));
        User sender = userService.getUser(transactionDTO.getSenderId());
        User receiver = userService.getUser(transactionDTO.getReceiverId());
        if(sender == null || receiver == null) throw new BadRequestException("One of your Users do not exist");

        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(transactionDTO.getAmount());

        return repository.save(transaction);
    }
}
