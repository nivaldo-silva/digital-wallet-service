package io.github.nivaldosilva.wallet.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import io.github.nivaldosilva.wallet.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}
