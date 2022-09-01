package ro.bank.entities.values;

import lombok.*;
import org.springframework.util.StringUtils;
import ro.bank.exceptions.BadRequestException;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Setter
@Getter
public class Location {

    String country;
    String county;
    String address;

    public Location(String country, String county, String address) {
        if (!StringUtils.hasText(country)) {
            throw new BadRequestException("Country is empty");
        }
        this.country = country;
        if (!StringUtils.hasText(county)) {
            throw new BadRequestException("County is empty");
        }
        this.county = county;
        if (!StringUtils.hasText(address)) {
            throw new BadRequestException("Address is empty");
        }
        this.address = address;
    }
}
