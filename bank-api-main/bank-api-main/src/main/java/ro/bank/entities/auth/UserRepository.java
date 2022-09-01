package ro.bank.entities.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.bank.entities.auth.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

}