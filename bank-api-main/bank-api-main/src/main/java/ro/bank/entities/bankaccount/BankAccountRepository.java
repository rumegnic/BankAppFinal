package ro.bank.entities.bankaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    int countByProfile_User_Username(String username);

    List<BankAccount> findAllByProfile_User_Username(String username);

    @Query("select ba from BankAccount ba where ba.iban.ibanNumber = :ibanNumber and ba.profile.user.username = :username")
    Optional<BankAccount> findByIbanAndUsername(String ibanNumber, String username);

    Optional<BankAccount> findByIban_IbanNumber(String ibanNumber);
}