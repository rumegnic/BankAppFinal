package ro.bank.entities.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.bank.entities.auth.UserAuthorities;

public interface UserAuthoritiesRepositories extends JpaRepository<UserAuthorities, Long> {
}