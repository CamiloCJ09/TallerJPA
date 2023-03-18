package co.edu.icesi.tallerjpa.runableartefact.controller;

import co.edu.icesi.tallerjpa.runableartefact.dto.IcesiAccountDTO;
import co.edu.icesi.tallerjpa.runableartefact.service.IcesiAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class IcesiAccountController {

    private final IcesiAccountService icesiAccountService;

    @PostMapping("/account/create")
    public String createAccount(@RequestBody IcesiAccountDTO icesiAccountDTO) {
        return icesiAccountService.saveNewAccount(icesiAccountDTO);
    }

    @PostMapping("/account/activate")
    public String activateAccount(@RequestBody String accountNumber) {
        return icesiAccountService.activateAccount(accountNumber);
    }

    @PostMapping("/account/deactivate")
    public String deactivateAccount(@RequestBody String accountNumber) {
        return icesiAccountService.deactivateAccount(accountNumber);
    }

    @PostMapping("/account/withdrawal")
    public String withdrawal(@RequestBody Map<String, String> withdrawalInformation, Long amount) {
        return icesiAccountService.withdrawal(withdrawalInformation.get("accountNumber")
                , Long.valueOf(withdrawalInformation.get("amount")));
    }

    @PostMapping("/account/deposit")
    public String deposit(@RequestBody Map<String, String> depositInformation) {
        return icesiAccountService.deposit(depositInformation.get("accountNumber"), Long.valueOf(depositInformation.get("amount")));
    }

    @PostMapping("/account/transfer")
    public String transfer(@RequestBody Map<String,String> transferInformation,String accountNumberOrigin, String accountNumberDestination, Long amount) {
        return icesiAccountService.transfer(transferInformation.get("accountNumberOrigin")
                , transferInformation.get("accountNumberDestination")
                , Long.valueOf(transferInformation.get("amount")));
    }

}
