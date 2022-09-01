package ro.bank.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.bank.api.generic.GenericResponse;
import ro.bank.api.transaction.dto.TransactionDto;
import ro.bank.entities.bankaccount.BankAccount;
import ro.bank.entities.bankaccount.BankAccountRepository;
import ro.bank.entities.transaction.Transaction;
import ro.bank.entities.transaction.TransactionRepository;
import ro.bank.entities.values.Balance;
import ro.bank.entities.values.IBAN;
import ro.bank.exceptions.BadRequestException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;


    public List<TransactionDto> getUserTransactions() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return transactionRepository.findAllByUsername(username)
                .stream()
                .map(TransactionDto::new)
                .toList();
    }

    public GenericResponse transfer(IBAN sourceIban, IBAN destinationIban, Balance sum, String description) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        BankAccount bankAccountSource = bankAccountRepository.findByIbanAndUsername(sourceIban.getIbanNumber(), username)
                .orElseThrow(() -> new BadRequestException("Source account couldn't be found for your account"));

        if (bankAccountSource.getBalance().compareTo(sum) < 0) {
            throw new BadRequestException("You don't have enough money in the source bank account");
        }

        BankAccount bankAccountDestination = bankAccountRepository.findByIban_IbanNumber(destinationIban.getIbanNumber())
                .orElseThrow(() -> new BadRequestException("Destination account couldn't be found"));

        Transaction transaction = new Transaction(bankAccountSource, bankAccountDestination, sum, description);
        transactionRepository.save(transaction);

        bankAccountSource.setBalance(bankAccountSource.getBalance().subtract(sum));
        bankAccountDestination.setBalance(bankAccountDestination.getBalance().add(sum));

        return GenericResponse.from(HttpStatus.CREATED, "Transfer successful");
    }
}
