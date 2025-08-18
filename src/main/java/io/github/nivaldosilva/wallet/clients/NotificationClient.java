package io.github.nivaldosilva.wallet.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;

@FeignClient(url = "https://util.devi.tools/api/v1/notify" , name = "notification")
public interface NotificationClient {

    @Retry(name = "notification")
    @PostMapping("/notify")
    ResponseEntity<Void> sendNotification();
}
