package ro.bank.entities.transaction;

import lombok.*;
import ro.bank.entities.Audit;
import ro.bank.entities.bankaccount.BankAccount;
import ro.bank.entities.values.Balance;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Transaction extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_bank_account_id")
    BankAccount sourceBankAccount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_bank_account_id")
    BankAccount destinationBankAccount;

    @Embedded
    Balance balance;

    @NotBlank
    String description;

    public Transaction(BankAccount sourceBankAccount, BankAccount destinationBankAccount, Balance balance, String description) {
        this.sourceBankAccount = sourceBankAccount;
        this.destinationBankAccount = destinationBankAccount;
        this.balance = balance;
        this.description = description;
    }
}
