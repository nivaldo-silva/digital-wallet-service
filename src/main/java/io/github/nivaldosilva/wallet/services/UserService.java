package io.github.nivaldosilva.wallet.services;

import io.github.nivaldosilva.wallet.dto.UserCreationDTO;
import io.github.nivaldosilva.wallet.dto.UserResponseDTO;
import io.github.nivaldosilva.wallet.entities.User;
import io.github.nivaldosilva.wallet.entities.UserType;
import io.github.nivaldosilva.wallet.entities.Wallet;
import io.github.nivaldosilva.wallet.exceptions.UserAlreadyExistsException;
import io.github.nivaldosilva.wallet.exceptions.UserNotFoundException;
import io.github.nivaldosilva.wallet.repositories.UserRepository;
import io.github.nivaldosilva.wallet.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

        private final UserRepository userRepository;
        private final WalletRepository walletRepository;

        @Transactional
        public UserResponseDTO createUser(UserCreationDTO dto) {
                log.info("Creating a new user with email: {}", dto.email());
                userRepository.findByCpfCnpj(dto.cpfCnpj())
                                .ifPresent(u -> {
                                        throw new UserAlreadyExistsException("User with this CPF/CNPJ already exists");
                                });
                userRepository.findByEmail(dto.email())
                                .ifPresent(u -> {
                                        throw new UserAlreadyExistsException("User with this email already exists");
                                });

                User user = User.builder()
                                .fullName(dto.fullName())
                                .email(dto.email())
                                .cpfCnpj(dto.cpfCnpj())
                                .password(dto.password())
                                .userType(dto.userType() != null ? dto.userType() : UserType.CUSTOMER)
                                .build();

                User savedUser = userRepository.save(user);

                Wallet wallet = Wallet.builder()
                                .balance(BigDecimal.ZERO)
                                .user(savedUser)
                                .build();
                walletRepository.save(wallet);

                log.info("User created successfully with ID: {}", savedUser.getId());
                return convertToResponseDTO(savedUser);
        }

        @Transactional(readOnly = true)
        public UserResponseDTO searchUserById(UUID id) {
                log.info("Searching for user with ID: {}", id);
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
                return convertToResponseDTO(user);
        }

        @Transactional(readOnly = true)
        public List<UserResponseDTO> listAllUsers() {
                log.info("Listing all users.");
                return userRepository.findAll().stream()
                                .map(this::convertToResponseDTO)
                                .collect(Collectors.toList());
        }

        @Transactional(readOnly = true)
        public List<UserResponseDTO> listUsersByType(UserType userType) {
                log.info("Listing users by type: {}", userType);
                return userRepository.findByUserType(userType).stream()
                                .map(this::convertToResponseDTO)
                                .collect(Collectors.toList());
        }

        private UserResponseDTO convertToResponseDTO(User user) {
                Wallet wallet = walletRepository.findByUser(user)
                                .orElse(null);
                BigDecimal balance = (wallet != null) ? wallet.getBalance() : BigDecimal.ZERO;

                return new UserResponseDTO(
                                user.getId(),
                                user.getFullName(),
                                user.getEmail(),
                                user.getCpfCnpj(),
                                user.getUserType(),
                                balance);
        }
}