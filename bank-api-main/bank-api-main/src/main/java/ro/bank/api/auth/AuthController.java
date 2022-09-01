package ro.bank.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.bank.api.auth.dto.RegisterAccountDto;
import ro.bank.api.generic.GenericResponse;
import ro.bank.services.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public GenericResponse registerAccount(@Valid @RequestBody RegisterAccountDto registerDto) {
        return authService.createAccount(registerDto.getUsername(), registerDto.getPassword());
    }

}
