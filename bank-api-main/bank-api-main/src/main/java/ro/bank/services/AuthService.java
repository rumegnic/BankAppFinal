package ro.bank.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.bank.api.generic.GenericResponse;
import ro.bank.config.security.Roles;
import ro.bank.entities.auth.UserAuthorities;
import ro.bank.entities.auth.User;
import ro.bank.exceptions.BadRequestException;
import ro.bank.entities.auth.UserAuthoritiesRepositories;
import ro.bank.entities.auth.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserAuthoritiesRepositories userAuthoritiesRepositories;
    private final PasswordEncoder passwordEncoder;

    public GenericResponse createAccount(String username, String password) {
        userRepository.findByUsername(username)
                .ifPresent(user -> {
                    throw new BadRequestException("Username exists already");
                });

        userRepository.save(new User(username, passwordEncoder.encode(password), true));
        userAuthoritiesRepositories.save(
                UserAuthorities.builder()
                        .authority(Roles.ROLE_USER.name())
                        .username(username)
                        .build()
        );

        return GenericResponse.from(HttpStatus.OK, "Account created successfully");
    }

}
