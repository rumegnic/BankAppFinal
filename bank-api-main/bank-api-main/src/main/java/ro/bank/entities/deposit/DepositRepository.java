package ro.bank.entities.deposit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    @Query("select d from Deposit d where d.bankAccount.profile.user.username = :username")
    List<Deposit> findAllByUsername(String username);
}