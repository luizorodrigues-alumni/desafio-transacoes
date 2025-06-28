package com.luiz.desafio_transacoes.transaction.service;

import com.luiz.desafio_transacoes.transaction.dtos.TransactionRequestDTO;
import com.luiz.desafio_transacoes.transaction.exceptions.InvalidDateException;
import com.luiz.desafio_transacoes.transaction.exceptions.InvalidValueException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private final List<TransactionRequestDTO> transactionList = new ArrayList<>();

    public void addNewTransaction(TransactionRequestDTO transaction){
        // Não deve acontecer no futuro
        if(transaction.dataHora().isAfter(OffsetDateTime.now())){
            throw new InvalidDateException("Invalid date provided.");
        }

        // Não deve ter valor negativo
        if (transaction.valor().compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidValueException("Invalid value provided. It must be greater than or equal to zero.");
        }
        transactionList.add(transaction);
    }

    public void deleteTransactions(){
        transactionList.clear();
    }

    public List<TransactionRequestDTO> searchTransactions(Integer time){
        OffsetDateTime limit = OffsetDateTime.now().minusSeconds(time);
        return transactionList.stream()
                .filter(transaction -> transaction.dataHora().isAfter(limit))
                .toList();
    }
}
