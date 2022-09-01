package ro.bank.entities.values;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.IbanUtil;
import ro.bank.exceptions.BadRequestException;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Setter
@Getter
public class IBAN {

    private String ibanNumber;

    public IBAN(String iban) {
        try {
            IbanUtil.validate(iban);
        } catch (Exception e) {
            throw new BadRequestException("IBAN number is invalid");
        }
        this.ibanNumber = iban;
    }

    public static IBAN generate() {
        // Generate correct IBAN
        return new IBAN(Iban.random(CountryCode.RO).toString());
    }
}
