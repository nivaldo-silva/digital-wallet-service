package io.github.nivaldosilva.wallet.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "payer_fk")
    private Wallet payer;

    @ManyToOne
    @JoinColumn(name = "payee_fk")
    private Wallet payee;

    @Column(precision = 19, scale = 2)
    private BigDecimal value;

    @Builder.Default
    private String currency = "BRL";

    @Builder.Default
    private Instant transactionDateTime = Instant.now();

}