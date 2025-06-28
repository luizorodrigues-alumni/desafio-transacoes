package com.luiz.desafio_transacoes.transaction.service;

import com.luiz.desafio_transacoes.transaction.dtos.StatisticsDTO;
import com.luiz.desafio_transacoes.transaction.dtos.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StatisticsService {

    private final TransactionService transactionService;

    public StatisticsService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public StatisticsDTO getTransactionStatistics(Integer time){
        List<TransactionRequestDTO> transactions = transactionService.searchTransactions(time);
        // No transactions, return 0 for all values
        if (transactions.isEmpty()){
            log.info("No transactions found for the given time.");
            return new StatisticsDTO(
                    0L,
                    0.0,
                    0.0,
                    0.0,
                    0.0
            );
        }
        DoubleSummaryStatistics doubleSummaryStatistics = transactions.stream()
                .collect(
                        Collectors.summarizingDouble(e -> e.valor().doubleValue())
                );

        log.info("Statistics calculated successfully");
        return new StatisticsDTO(
                doubleSummaryStatistics.getCount(),
                doubleSummaryStatistics.getSum(),
                doubleSummaryStatistics.getAverage(),
                doubleSummaryStatistics.getMin(),
                doubleSummaryStatistics.getMax()
        );
    }

}
