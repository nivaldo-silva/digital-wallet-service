package io.github.nivaldosilva.wallet.config;

import io.github.nivaldosilva.wallet.entities.User;
import io.github.nivaldosilva.wallet.entities.UserType;
import io.github.nivaldosilva.wallet.entities.Wallet;
import io.github.nivaldosilva.wallet.repositories.UserRepository;
import io.github.nivaldosilva.wallet.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DatabaseSeeder {

        private final UserRepository userRepository;
        private final WalletRepository walletRepository;

        @Bean
        public CommandLineRunner seedDatabase() {
                return args -> {

                        if (userRepository.count() == 0) {

                                User user1 = User.builder()
                                                .fullName("Carlos Silva")
                                                .email("carlos@email.com")
                                                .cpfCnpj("10111111111")
                                                .password(("123456"))
                                                .userType(UserType.CUSTOMER)
                                                .build();

                                User user2 = User.builder()
                                                .fullName("Ana Souza")
                                                .email("ana@email.com")
                                                .cpfCnpj("22222222222")
                                                .password(("123456"))
                                                .userType(UserType.CUSTOMER)
                                                .build();

                                User merchant = User.builder()
                                                .fullName("Example Merchant")
                                                .email("loja@email.com")
                                                .cpfCnpj("33333333333")
                                                .password(("123456"))
                                                .userType(UserType.MERCHANT)
                                                .build();

                                List<User> savedUsers = userRepository.saveAll(List.of(user1, user2, merchant));

                                User savedUser1 = savedUsers.get(0);
                                User savedUser2 = savedUsers.get(1);
                                User savedMerchant = savedUsers.get(2);

                                Wallet wallet1 = Wallet.builder()
                                                .balance(new BigDecimal("1000.00"))
                                                .user(savedUser1)
                                                .build();

                                Wallet wallet2 = Wallet.builder()
                                                .balance(new BigDecimal("2000.00"))
                                                .user(savedUser2)
                                                .build();

                                Wallet wallet3 = Wallet.builder()
                                                .balance(new BigDecimal("5000.00"))
                                                .user(savedMerchant)
                                                .build();

                                walletRepository.saveAll(List.of(wallet1, wallet2, wallet3));

                                System.out.println("=== DADOS INSERIDOS NO BANCO ===");
                                System.out.println("User1 (Carlos Silva) - ID: " + savedUser1.getId());
                                System.out.println("User2 (Ana Souza) - ID: " + savedUser2.getId());
                                System.out.println("Merchant (Example Merchant) - ID: " + savedMerchant.getId());
                                System.out.println("---");
                                System.out.println("Wallet1 (User: " + savedUser1.getFullName() + ") - ID: "
                                                + wallet1.getId() + ", Saldo: " + wallet1.getBalance());
                                System.out.println("Wallet2 (User: " + savedUser2.getFullName() + ") - ID: "
                                                + wallet2.getId() + ", Saldo: " + wallet2.getBalance());
                                System.out.println("Wallet3 (User: " + savedMerchant.getFullName() + ") - ID: "
                                                + wallet3.getId() + ", Saldo: " + wallet3.getBalance());
                                System.out.println("================================");

                        }
                };
        }
}