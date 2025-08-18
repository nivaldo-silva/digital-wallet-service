package io.github.nivaldosilva.wallet.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TransactionResponseDTO(

        UUID id,
        UUID payerId,
        UUID payeeId,
        BigDecimal value,
        String currency,
        Instant transactionDateTime
        
) {}
