package com.example.assessment.controller;

import com.example.assessment.entity.Transaction;
import com.example.assessment.model.Response;
import com.example.assessment.model.TransactionDTO;
import com.example.assessment.service.TransactionService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @PostMapping()
    public ResponseEntity<Response<Transaction>> createTransaction(@RequestBody TransactionDTO transactionDTO) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.<Transaction>builder().data(service.saveTransaction(transactionDTO)).message("Transaction Created").statusCode(HttpStatus.CREATED.value()).status(HttpStatus.CREATED).build());
    }

    @GetMapping()
    public ResponseEntity<Response<List<Transaction>>> getAllTransactions() {
        return ResponseEntity.ok(Response.<List<Transaction>>builder().data(service.getTransactions()).message("Transactions Retrieved").statusCode(HttpStatus.OK.value()).status(HttpStatus.OK).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Transaction>> getTransactionById(@PathVariable Integer id) {
        return ResponseEntity.ok(Response.<Transaction>builder().data(service.getTransaction(id)).message("Transaction Retrieved").statusCode(HttpStatus.OK.value()).status(HttpStatus.OK).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Transaction>> updateTransaction(@PathVariable Integer id, @RequestBody TransactionDTO transactionDTO) throws BadRequestException {
        return ResponseEntity.ok(Response.<Transaction>builder().data(service.updateTransaction(transactionDTO, id)).message("Transaction Updated").statusCode(HttpStatus.OK.value()).status(HttpStatus.OK).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteTransaction(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Response.<Boolean>builder().data(service.deleteTransaction(id)).message("Transaction Created").statusCode(HttpStatus.NO_CONTENT.value()).status(HttpStatus.NO_CONTENT).build());

    }
}
