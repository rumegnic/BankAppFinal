package ro.bank.api.bankaccount.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class DepositMoneyDto {

    private BigDecimal sum;
    private String currency;

}
