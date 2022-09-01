package ro.bank.api.profile.dto;

import lombok.Getter;
import lombok.Setter;
import ro.bank.entities.profile.Profile;

@Setter
@Getter
public class ProfileDto {
    private Long profileId;

    private String firstName;
    private String lastName;
    private String fullName;

    private String country;
    private String county;
    private String address;

    private String phoneNumber;
    private String identificationNumber;

    public ProfileDto(Profile profile) {
        profileId = profile.getId();

        firstName = profile.getFullName().getFirstName();
        lastName = profile.getFullName().getLastName();
        fullName = profile.getFullName().toString();

        country = profile.getLocation().getCountry();
        county = profile.getLocation().getCounty();
        address = profile.getLocation().getAddress();

        phoneNumber = profile.getPhoneNumber().getPhone();
        identificationNumber = profile.getIdentificationNumber().getCnp();

    }
}
