package ro.bank.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.bank.api.bankaccount.dto.BankAccountDto;
import ro.bank.api.generic.GenericResponse;
import ro.bank.entities.bankaccount.BankAccount;
import ro.bank.entities.bankaccount.BankAccountRepository;
import ro.bank.entities.profile.Profile;
import ro.bank.entities.profile.ProfileRepository;
import ro.bank.entities.values.Balance;
import ro.bank.exceptions.BadRequestException;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final ProfileRepository profileRepository;

    public List<BankAccountDto> getUserBankAccounts() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return bankAccountRepository.findAllByProfile_User_Username(username)
                .stream()
                .map(BankAccountDto::new)
                .toList();
    }

    public GenericResponse createBankAccount(String currency) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (bankAccountRepository.countByProfile_User_Username(username) > 10) {
            throw new BadRequestException("You have already 10 bank accounts. You reached the limit!");
        }

        Profile profile = profileRepository.findByUser_Username(username)
                .orElseThrow(() -> new BadRequestException("Profile not found"));
        bankAccountRepository.save(new BankAccount(profile, new Balance(BigDecimal.ZERO, currency)));

        return GenericResponse.from(HttpStatus.OK, "Bank account created");
    }

    public GenericResponse depositMoney(Long bankAccountId, Balance balance) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElseThrow(() -> new BadRequestException("Bank account not found"));
        if (!bankAccount.getProfile().getUser().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            throw new BadRequestException("You can't work on bank accounts where you are not the owner");
        }

        bankAccount.setBalance(bankAccount.getBalance().add(balance));
        bankAccountRepository.save(bankAccount);

        return GenericResponse.from(HttpStatus.OK, "Deposit successfull");
    }
}
