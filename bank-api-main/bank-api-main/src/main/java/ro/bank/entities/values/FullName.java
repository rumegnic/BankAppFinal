package ro.bank.entities.values;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;
import ro.bank.exceptions.BadRequestException;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Setter
@Getter
public class FullName {

    private String firstName;
    private String lastName;

    public FullName(String firstName, String lastName) {
        if (!StringUtils.hasText(firstName)) {
            throw new BadRequestException("First name is empty");
        }
        this.firstName = firstName;

        if (!StringUtils.hasText(lastName)) {
            throw new BadRequestException("Last name is empty");
        }
        this.lastName = lastName;
    }

    public String toString() {
        return firstName + " " + lastName;
    }
}
