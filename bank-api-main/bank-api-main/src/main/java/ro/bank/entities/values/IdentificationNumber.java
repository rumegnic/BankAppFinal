package ro.bank.entities.values;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;
import ro.bank.exceptions.BadRequestException;

import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
public class IdentificationNumber {

    private String cnp;

    public IdentificationNumber(String cnp) {
        int length = cnp.length();
        if (!StringUtils.hasText(cnp) || length != 13) {
            throw new BadRequestException("CNP is invalid");
        }

        this.cnp = cnp;
    }
}
