package io.github.nivaldosilva.wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MerchantPayerException extends RuntimeException {

    public MerchantPayerException(String message) {
        super(message);
    }

}
