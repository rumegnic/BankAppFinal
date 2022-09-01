package ro.bank.entities.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t where " +
            "t.destinationBankAccount.profile.user.username = :username OR " +
            "t.sourceBankAccount.profile.user.username = :username")
    List<Transaction> findAllByUsername(String username);

}