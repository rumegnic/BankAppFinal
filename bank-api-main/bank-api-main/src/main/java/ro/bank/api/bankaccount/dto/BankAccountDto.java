package ro.bank.api.bankaccount.dto;

import lombok.Getter;
import lombok.Setter;
import ro.bank.entities.bankaccount.BankAccount;
import ro.bank.entities.profile.Profile;

import java.math.BigDecimal;

@Setter
@Getter
public class BankAccountDto {

    Long bankAccountId;
    Long profileId;
    String fullName;
    String iban;
    BigDecimal balanceValue;
    String balanceCurrency;

    public BankAccountDto(BankAccount bankAccount) {
        this.bankAccountId = bankAccount.getId();
        Profile profile = bankAccount.getProfile();
        this.profileId = profile.getId();
        this.fullName = profile.getFullName().toString();

        this.iban = bankAccount.getIban().getIbanNumber();
        this.balanceValue = bankAccount.getBalance().getSum();
        this.balanceCurrency = bankAccount.getBalance().getCurrency();
    }

}
