package com.luiz.desafio_transacoes.transaction.dtos;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionRequestDTO(BigDecimal valor, OffsetDateTime dataHora) {
}
