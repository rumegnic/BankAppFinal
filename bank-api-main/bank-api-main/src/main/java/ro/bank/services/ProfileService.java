package ro.bank.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.bank.api.generic.GenericResponse;
import ro.bank.api.profile.dto.ProfileDto;
import ro.bank.api.profile.dto.UpdateProfileDto;
import ro.bank.entities.profile.Profile;
import ro.bank.entities.auth.User;
import ro.bank.entities.values.FullName;
import ro.bank.entities.values.IdentificationNumber;
import ro.bank.entities.values.Location;
import ro.bank.entities.values.PhoneNumber;
import ro.bank.entities.profile.ProfileRepository;
import ro.bank.entities.auth.UserRepository;
import ro.bank.exceptions.BadRequestException;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public GenericResponse updateProfile(UpdateProfileDto updateProfileDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        profileRepository.findByUser_Username(username)
                .ifPresentOrElse(profile -> {
                            updateProfileEntity(updateProfileDto, profile);
                            profileRepository.save(profile);
                        },
                        () -> {
                            Profile profile = new Profile();
                            User user = userRepository.findByUsername(username)
                                    .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
                            profile.setUser(user);
                            updateProfileEntity(updateProfileDto, profile);
                            profileRepository.save(profile);
                        });

        return GenericResponse.from(HttpStatus.OK, "Profil actualizat cu succes");
    }

    private void updateProfileEntity(UpdateProfileDto updateProfileDto, Profile profile) {
        profile.setFullName(new FullName(updateProfileDto.getFirstName(), updateProfileDto.getLastName()));
        profile.setLocation(new Location(updateProfileDto.getCountry(), updateProfileDto.getCounty(), updateProfileDto.getAddress()));
        profile.setPhoneNumber(new PhoneNumber(updateProfileDto.getPhoneNumber()));
        profile.setIdentificationNumber(new IdentificationNumber(updateProfileDto.getIdentificationNumber()));
    }

    public ProfileDto getCurrentUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = profileRepository.findByUser_Username(username)
                .orElseThrow(() -> new BadRequestException("Profile not found for your account"));

        return new ProfileDto(profile);
    }
}
