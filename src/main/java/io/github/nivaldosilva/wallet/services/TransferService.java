package io.github.nivaldosilva.wallet.services;

import io.github.nivaldosilva.wallet.clients.NotificationClient;
import io.github.nivaldosilva.wallet.dto.TransactionDTO;
import io.github.nivaldosilva.wallet.dto.TransactionResponseDTO;
import io.github.nivaldosilva.wallet.entities.Transaction;
import io.github.nivaldosilva.wallet.entities.UserType;
import io.github.nivaldosilva.wallet.entities.Wallet;
import io.github.nivaldosilva.wallet.exceptions.InsufficientBalanceException;
import io.github.nivaldosilva.wallet.exceptions.MerchantPayerException;
import io.github.nivaldosilva.wallet.exceptions.UserNotFoundException;
import io.github.nivaldosilva.wallet.repositories.TransactionRepository;
import io.github.nivaldosilva.wallet.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final AuthorizationService authorizationService;
    private final NotificationClient notificationClient;

    @Transactional
    public Transaction processTransfer(TransactionDTO transactionDTO) {
        log.info("Starting transfer process for transaction: {}", transactionDTO);

        Wallet payer = walletRepository.findById(transactionDTO.payerId())
                .orElseThrow(() -> new UserNotFoundException("Payer wallet not found."));

        Wallet payee = walletRepository.findById(transactionDTO.payeeId())
                .orElseThrow(() -> new UserNotFoundException("Payee wallet not found."));

        validateTransfer(payer, transactionDTO.value());

        authorizationService.validateTransactionIsAuthorized();

        payer.debit(transactionDTO.value());
        payee.credit(transactionDTO.value());

        walletRepository.save(payer);
        walletRepository.save(payee);

        Transaction newTransaction = Transaction.builder()
                .payer(payer)
                .payee(payee)
                .value(transactionDTO.value())
                .currency("BRL")
                .transactionDateTime(Instant.now())
                .build();
        Transaction savedTransaction = transactionRepository.save(newTransaction);

        log.info("Transaction saved with ID: {}", savedTransaction.getId());

      
        CompletableFuture.runAsync(() -> {
            try {
                notificationClient.sendNotification();
                log.info("Notification sent successfully for transaction ID: {}", savedTransaction.getId());
            } catch (Exception e) {
                log.error("Failed to send notification for transaction ID: {}", savedTransaction.getId(), e);
            }
        });

        return savedTransaction;
    }

    private void validateTransfer(Wallet payerWallet, BigDecimal value) {
        if (payerWallet.getUser().getUserType() == UserType.MERCHANT) {
            throw new MerchantPayerException("Merchant cannot be the payer.");
        }
        if (payerWallet.getBalance().compareTo(value) < 0) {
            throw new InsufficientBalanceException("Insufficient balance.");
        }
    }

    public List<TransactionResponseDTO> listAllTransactions() {
        log.info("Listing all transactions.");
        return transactionRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private TransactionResponseDTO convertToResponseDTO(Transaction transaction) {
        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getPayer().getId(),
                transaction.getPayee().getId(),
                transaction.getValue(),
                transaction.getCurrency(),
                transaction.getTransactionDateTime());
    }
}