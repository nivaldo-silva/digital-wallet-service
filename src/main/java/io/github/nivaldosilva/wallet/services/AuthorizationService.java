package io.github.nivaldosilva.wallet.services;

import java.util.Objects;
import org.springframework.stereotype.Service;
import io.github.nivaldosilva.wallet.clients.ExternalAuthorizationClient;
import io.github.nivaldosilva.wallet.exceptions.TransactionException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final ExternalAuthorizationClient client;

    public void validateTransactionIsAuthorized() {
        try {
            boolean isAuthorized = Objects.equals(client.validateAuthorization().data().authorization(), "true");

            if (!isAuthorized) {
                throw new TransactionException("Transfer not authorized by external service.");
            }
        } catch (FeignException e) {
            throw new TransactionException("Transfer not authorized by external service.");
        }
    }

}