package ro.bank.entities.values;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.bank.exceptions.BadRequestException;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
public class Balance implements Comparable<Balance>{

    private BigDecimal sum;
    private String currency;

    public Balance(BigDecimal sum, String currency) {
        this.sum = sum;

        try {
            Currency.getInstance(currency);
        } catch (Exception e) {
            throw new BadRequestException("Currency is not supported");
        }

        this.currency = currency;
    }

    @Override
    public int compareTo(Balance o) {
        checkSameCurrency(o);

        return this.sum.compareTo(o.getSum());
    }

    public Balance add(Balance balance) {
        checkSameCurrency(balance);

        return new Balance(sum.add(balance.getSum()), balance.currency);

    }

    private void checkSameCurrency(Balance balance) {
        if (!this.currency.equals(balance.getCurrency())) {
            throw new BadRequestException("Operations with different currencies is not allowed");
        }
    }

    public Balance subtract(Balance balance) {
        checkSameCurrency(balance);

        return new Balance(sum.subtract(balance.getSum()), balance.currency);

    }
}
