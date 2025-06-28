package com.luiz.desafio_transacoes.transaction.controller;

import com.luiz.desafio_transacoes.transaction.dtos.TransactionRequestDTO;
import com.luiz.desafio_transacoes.transaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> receiveTransaction(@RequestBody TransactionRequestDTO transactionDTO){
        transactionService.addNewTransaction(transactionDTO);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransactions(){
        transactionService.deleteTransactions();
        return ResponseEntity.status(200).build();
    }
}
