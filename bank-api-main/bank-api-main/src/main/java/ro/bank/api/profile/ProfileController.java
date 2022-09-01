package ro.bank.api.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.bank.api.generic.GenericResponse;
import ro.bank.api.profile.dto.ProfileDto;
import ro.bank.api.profile.dto.UpdateProfileDto;
import ro.bank.services.ProfileService;

import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/current-user")
    public ProfileDto getCurrentUserProfile() {
        return profileService.getCurrentUserProfile();
    }

    @PostMapping("/update")
    public GenericResponse updateProfile(@RequestBody @Valid UpdateProfileDto updateProfileDto) {
        return profileService.updateProfile(updateProfileDto);
    }
}
