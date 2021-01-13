package com.example.atmmachine.controller;

import com.example.atmmachine.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atm")
public class ATMController {

    @Autowired
    ATMService atmService;

    //account Details
    @GetMapping("/getAccountDetails/{id}")
    public ResponseEntity<String> getAccountDetails(@PathVariable Long id,
                                                    @RequestParam int pin) {
        return atmService.getAccountByAccountNo(id,pin);
    }

    //checkBalance
    @GetMapping("/checkBalance/{id}")
    public ResponseEntity<String> checkBalance(@PathVariable Long id,
                               @RequestParam int pin) {
        return atmService.getAccountBalance(id,pin);
    }

    // withDrawMoney
    @GetMapping("/withdraw/{amount}")
    public Object withdraw(@PathVariable double amount,
                                         @RequestParam Long accountId) {
        return atmService.withdraw(amount,accountId);
    }
}
