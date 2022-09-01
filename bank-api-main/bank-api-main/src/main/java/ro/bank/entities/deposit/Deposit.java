package ro.bank.entities.deposit;

import lombok.*;
import ro.bank.entities.Audit;
import ro.bank.entities.bankaccount.BankAccount;
import ro.bank.entities.values.Balance;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "deposits")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Deposit extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_id")
    BankAccount bankAccount;

    @Embedded
    Balance balance;

    @Future
    LocalDate expireAt;
    @Max(1) @Min(0)
    BigDecimal annualInterest;

    public Deposit(BankAccount bankAccount, Balance balance, LocalDate expireAt, BigDecimal annualInterest) {
        this.bankAccount = bankAccount;
        this.balance = balance;
        this.expireAt = expireAt;
        this.annualInterest = annualInterest;
    }
}
