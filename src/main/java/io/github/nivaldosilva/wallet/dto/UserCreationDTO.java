package io.github.nivaldosilva.wallet.dto;

import io.github.nivaldosilva.wallet.entities.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreationDTO(

        @NotBlank(message = "Full name is required") 
        String fullName,

        @NotBlank(message = "Email is required") 
        @Email(message = "Email should be valid") 
        String email,

        @NotBlank(message = "CPF or CNPJ is required") 
        String cpfCnpj,

        @NotBlank(message = "Password is required") 
        String password,

        UserType userType
        
) {}
