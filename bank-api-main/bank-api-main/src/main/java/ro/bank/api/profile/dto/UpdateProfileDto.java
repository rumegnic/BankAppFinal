package ro.bank.api.profile.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class UpdateProfileDto {

    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Country is required")
    private String country;
    @NotBlank(message = "County is required")
    private String county;
    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Phone number is incorrect")
    private String phoneNumber;

    @NotBlank(message = "Identification number is required")
    private String identificationNumber;

}
