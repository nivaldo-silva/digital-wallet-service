package io.github.nivaldosilva.wallet.dto;

import java.math.BigDecimal;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TransactionDTO(

    @NotNull(message = "Payer ID is required")
    UUID payerId,

    @NotNull(message = "Payee ID is required")
    UUID payeeId,

    @NotNull(message = "Value is required")
    @DecimalMin(value = "0.01", message = "Value must be greater than zero")
    BigDecimal value

) {}
