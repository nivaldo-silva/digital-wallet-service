package io.github.nivaldosilva.wallet.dto;

import java.math.BigDecimal;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nivaldosilva.wallet.entities.UserType;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponseDTO(

        UUID id,
        String fullName,
        String email,
        String cpfCnpj,
        UserType userType,
        BigDecimal walletBalance
        
) {}
