package ro.bank.api.deposit.dto;

import lombok.Getter;
import lombok.Setter;
import ro.bank.entities.deposit.Deposit;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class DepositDto {

    private Long depositId;
    private Long bankAccountId;

    private BigDecimal sum;
    private String currency;

    private LocalDate expireAt;
    private BigDecimal annualInterest;

    public DepositDto(Deposit deposit) {
        depositId = deposit.getId();
        bankAccountId = deposit.getBankAccount().getId();

        sum = deposit.getBalance().getSum();
        currency = deposit.getBalance().getCurrency();

        expireAt = deposit.getExpireAt();
        annualInterest = deposit.getAnnualInterest();
    }
}
