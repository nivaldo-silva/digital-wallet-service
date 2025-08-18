package io.github.nivaldosilva.wallet.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import io.github.nivaldosilva.wallet.dto.authorizationDTO;

@FeignClient(name = "authorization", url = "https://util.devi.tools/api/v2/authorize")
public interface ExternalAuthorizationClient {

    @GetMapping
    authorizationDTO validateAuthorization();

}
