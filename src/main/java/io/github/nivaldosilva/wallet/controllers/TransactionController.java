package io.github.nivaldosilva.wallet.controllers;

import io.github.nivaldosilva.wallet.dto.TransactionDTO;
import io.github.nivaldosilva.wallet.dto.TransactionResponseDTO;
import io.github.nivaldosilva.wallet.entities.Transaction;
import io.github.nivaldosilva.wallet.services.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransferService transferService;

   
    @PostMapping
    public ResponseEntity<Transaction> processTransfer(@Valid @RequestBody TransactionDTO transactionDTO) {
        Transaction processedTransaction = transferService.processTransfer(transactionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(processedTransaction);
    }

    
    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> listAllTransactions() {
        List<TransactionResponseDTO> transactions = transferService.listAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}