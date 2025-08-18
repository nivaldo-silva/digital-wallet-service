package io.github.nivaldosilva.wallet.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import io.github.nivaldosilva.wallet.entities.User;
import io.github.nivaldosilva.wallet.entities.UserType;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByCpfCnpj(String cpfOrCnpj);
    Optional<User> findByEmail(String email);
    List<User> findByUserType(UserType userType);
    

}
