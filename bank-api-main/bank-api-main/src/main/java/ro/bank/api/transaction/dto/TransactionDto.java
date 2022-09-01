package ro.bank.api.transaction.dto;

import lombok.Getter;
import lombok.Setter;
import ro.bank.entities.transaction.Transaction;

import java.math.BigDecimal;

@Setter
@Getter
public class TransactionDto {

    private Long id;

    private Long sourceBankAccountId;
    private String sourceIban;

    private Long destinationBankAccountId;
    private String destinationIban;

    private BigDecimal value;
    private String currency;

    private String description;

    public TransactionDto(Transaction transaction) {
        this.id = transaction.getId();

        this.sourceIban = transaction.getSourceBankAccount().getIban().getIbanNumber();
        this.sourceBankAccountId = transaction.getSourceBankAccount().getId();

        this.destinationIban = transaction.getDestinationBankAccount().getIban().getIbanNumber();
        this.destinationBankAccountId = transaction.getDestinationBankAccount().getId();

        this.value = transaction.getBalance().getSum();
        this.currency = transaction.getBalance().getCurrency();

        this.description = transaction.getDescription();
    }
}
