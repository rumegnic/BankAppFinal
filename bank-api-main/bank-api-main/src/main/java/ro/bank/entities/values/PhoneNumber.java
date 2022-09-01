package ro.bank.entities.values;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Setter
@Getter
public class PhoneNumber {

    String phone;

    public PhoneNumber(String phone) {
        this.phone = phone;
    }
}
