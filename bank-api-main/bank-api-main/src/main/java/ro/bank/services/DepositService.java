package ro.bank.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.bank.api.deposit.dto.DepositDto;
import ro.bank.api.generic.GenericResponse;
import ro.bank.entities.bankaccount.BankAccount;
import ro.bank.entities.bankaccount.BankAccountRepository;
import ro.bank.entities.deposit.Deposit;
import ro.bank.entities.deposit.DepositRepository;
import ro.bank.entities.values.Balance;
import ro.bank.exceptions.BadRequestException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DepositService {

    private final DepositRepository depositRepository;
    private final BankAccountRepository bankAccountRepository;

    public List<DepositDto> getUserDeposits() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return depositRepository.findAllByUsername(username)
                .stream()
                .map(DepositDto::new)
                .toList();
    }

    public GenericResponse createDeposit(Long bankAccountId, Balance sum, int years, BigDecimal annualInterest) {
        validateInput(years, annualInterest);

        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new BadRequestException("Bank account not found"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!bankAccount.getProfile().getUser().getUsername().equals(username)) {
            throw new BadRequestException("Bank account doesn't belong to your account");
        }

        if (bankAccount.getBalance().compareTo(sum) < 0) {
            throw new BadRequestException("You don't have enough money in your bank account");
        }

        depositRepository.save(new Deposit(bankAccount, sum, LocalDate.now().plusYears(years), annualInterest));
        bankAccount.setBalance(bankAccount.getBalance().subtract(sum));

        return GenericResponse.from(HttpStatus.CREATED, "Deposit created successfully");

    }

    private void validateInput(int years, BigDecimal annualInterest) {
        if (years > 10) {
            throw new BadRequestException("You can't create deposit longer than 10 years");
        }

        if (annualInterest.compareTo(BigDecimal.ZERO) < 0 || annualInterest.compareTo(BigDecimal.ONE) > 0) {
            throw new BadRequestException("Annual interest should be in range [0.00, 1.00]");
        }
    }

}
