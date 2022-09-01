package ro.bank.api.deposit.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Setter
@Getter
public class CreateDepositDto {

    @NotNull(message = "Bank account is required")
    private Long bankAccountId;
    @Positive(message = "Sum should be positive")
    @NotNull(message = "Sum is required")
    private BigDecimal sum;
    @NotBlank(message = "Currency is required")
    private String currency;
    @Positive(message = "Deposit could be only 1 year or more")
    private Integer years;
    @NotNull(message = "Annual interest should be added by operator")
    @Positive(message = "Annual interest should be positive")
    private BigDecimal annualInterest;
}
