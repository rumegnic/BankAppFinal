package ro.bank.api.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class RegisterAccountDto {

    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username is too long")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(max = 500, message = "Password is too long")
    private String password;

}
