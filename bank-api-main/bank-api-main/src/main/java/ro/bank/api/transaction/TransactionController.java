package ro.bank.api.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.bank.api.generic.GenericResponse;
import ro.bank.api.transaction.dto.TransactionDto;
import ro.bank.api.transaction.dto.TransferDto;
import ro.bank.entities.values.Balance;
import ro.bank.entities.values.IBAN;
import ro.bank.services.TransactionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/current-user")
    public List<TransactionDto> getUserTransactions() {
        return transactionService.getUserTransactions();
    }

    @PostMapping("/transfer")
    public GenericResponse transfer(@RequestBody @Valid TransferDto transferDto) {
        return transactionService.transfer(
                new IBAN(transferDto.getSourceIban()),
                new IBAN(transferDto.getDestinationIban()),
                new Balance(transferDto.getSum(), transferDto.getCurrency()),
                transferDto.getDescription()

        );
    }
}
