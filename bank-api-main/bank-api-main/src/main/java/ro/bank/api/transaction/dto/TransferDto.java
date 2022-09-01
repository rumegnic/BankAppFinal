package ro.bank.api.transaction.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferDto {

    private String sourceIban; // you must be the owner
    private String destinationIban;
    private BigDecimal sum;
    private String currency;
    private String description;
}
