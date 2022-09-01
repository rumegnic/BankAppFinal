package ro.bank.entities.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.bank.entities.profile.Profile;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUser_Username(String username);
}