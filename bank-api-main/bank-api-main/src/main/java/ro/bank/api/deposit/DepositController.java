package ro.bank.api.deposit;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.bank.api.deposit.dto.CreateDepositDto;
import ro.bank.api.deposit.dto.DepositDto;
import ro.bank.api.generic.GenericResponse;
import ro.bank.entities.values.Balance;
import ro.bank.services.DepositService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deposit")
@RequiredArgsConstructor
public class DepositController {

    private final DepositService depositService;

    @GetMapping("/current-user")
    public List<DepositDto> getUserDeposits() {
        return depositService.getUserDeposits();
    }

    @PostMapping("/create")
    public GenericResponse createDeposit(@RequestBody @Valid CreateDepositDto createDepositDto) {
        return depositService.createDeposit(
                createDepositDto.getBankAccountId(),
                new Balance(createDepositDto.getSum(), createDepositDto.getCurrency()),
                createDepositDto.getYears(),
                createDepositDto.getAnnualInterest()
        );
    }
}
