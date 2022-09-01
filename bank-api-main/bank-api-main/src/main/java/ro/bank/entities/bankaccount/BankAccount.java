package ro.bank.entities.bankaccount;

import lombok.*;
import ro.bank.entities.Audit;
import ro.bank.entities.profile.Profile;
import ro.bank.entities.values.Balance;
import ro.bank.entities.values.IBAN;

import javax.persistence.*;

@Entity
@Table(name = "bank_accounts")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    Profile profile;

    @Embedded
    IBAN iban;

    @Embedded
    Balance balance;

    public BankAccount(Profile profile, Balance balance) {
        this.profile = profile;
        this.iban = IBAN.generate();
        this.balance = balance;
    }
}
