package com.example.atmmachine.service;

import com.example.atmmachine.model.Account;
import com.example.atmmachine.model.Denomination;
import com.example.atmmachine.model.Note;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ATMService {

    ResponseEntity<String> getAccountByAccountNo(Long accountNo, int pin);
    boolean isLoggedIn(Account account);
    List<String> withdrawMoney(double amount);


    ResponseEntity<String> getAccountBalance(Long accountNo, int pin);

    Object withdraw(double amount, Long accountNo);

    List<Note> getAllNotes();

    Note updateNotes(Note note);

}
