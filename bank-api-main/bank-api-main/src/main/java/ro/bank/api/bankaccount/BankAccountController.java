package ro.bank.api.bankaccount;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.bank.api.bankaccount.dto.BankAccountDto;
import ro.bank.api.bankaccount.dto.DepositMoneyDto;
import ro.bank.api.generic.GenericResponse;
import ro.bank.entities.values.Balance;
import ro.bank.services.BankAccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bank-account")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping("/current-user")
    public List<BankAccountDto> getCurrentUserBankAccounts() {
        return bankAccountService.getUserBankAccounts();
    }

    @PostMapping("")
    public GenericResponse createBankAccount(@RequestParam String currency) {
        return bankAccountService.createBankAccount(currency);
    }

    @PostMapping("/{bankAccountId}/deposit")
    public GenericResponse depositMoney(@PathVariable Long bankAccountId, @RequestBody @Valid DepositMoneyDto depositMoneyDto) {
        return bankAccountService.depositMoney(
                bankAccountId,
                new Balance(depositMoneyDto.getSum(), depositMoneyDto.getCurrency()));
    }
}
