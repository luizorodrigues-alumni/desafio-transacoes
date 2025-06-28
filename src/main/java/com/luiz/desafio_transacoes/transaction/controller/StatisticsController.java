package com.luiz.desafio_transacoes.transaction.controller;

import com.luiz.desafio_transacoes.transaction.dtos.StatisticsDTO;
import com.luiz.desafio_transacoes.transaction.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estatistica")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public ResponseEntity<StatisticsDTO> getStatistics(@RequestParam(required = false, defaultValue = "60") Integer time){
        return ResponseEntity.status(200).body(statisticsService.getTransactionStatistics(time));
    }
}
