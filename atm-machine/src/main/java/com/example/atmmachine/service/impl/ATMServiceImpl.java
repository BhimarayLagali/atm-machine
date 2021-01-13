package com.example.atmmachine.service.impl;

import com.example.atmmachine.exception.InsufficientBalance;
import com.example.atmmachine.exception.InvalidPIN;
import com.example.atmmachine.model.Account;
import com.example.atmmachine.model.DenominationFifty;
import com.example.atmmachine.model.DenominationHundred;
import com.example.atmmachine.model.DenominationTen;
import com.example.atmmachine.model.DenominationTwenty;
import com.example.atmmachine.model.Note;
import com.example.atmmachine.repository.AccountRepository;
import com.example.atmmachine.repository.NotesRepository;
import com.example.atmmachine.service.ATMService;
import com.example.atmmachine.exception.InvalidAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ATMServiceImpl implements ATMService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    DenominationHundred denominationHundred;

    @Autowired
    DenominationFifty denominationFifty;

    @Autowired
    DenominationTwenty denominationTwenty;

    @Autowired
    DenominationTen denominationTen;

    @Override
    public ResponseEntity<String> getAccountByAccountNo(Long accountNo, int pin) {
        Account account = accountRepository.findByAccountNo(accountNo);
        if (isValidUser(account, pin)) {
            return new ResponseEntity<String>(account.toString(), HttpStatus.OK);
        } else {
            throw new InvalidPIN("Error");
        }
    }

    @Override
    public ResponseEntity<String> getAccountBalance(Long accountNo, int pin) {
        Account account = accountRepository.findByAccountNo(accountNo);
        if (isValidUser(account, pin)) {
            return new ResponseEntity<String>(String.valueOf(account.getAccountBalance()), HttpStatus.OK);
        } else {
            throw new InvalidPIN("Error");
        }
    }


    public boolean isValidUser(Account account, int pin) {
        return (account.getPin() == pin);
    }

    @Override
    public boolean isLoggedIn(Account account) {
        return false;
    }

    @Override
    public List<String> withdrawMoney(double amount) {
        return getNotes(amount);
    }


    @Override
    public Object withdraw(double amount, Long accountNo) {
        Account account = accountRepository.findByAccountNo(accountNo);
        Map<String, Integer> withdrawalAmount = null;
        if (account.getAccountBalance() >= amount){
            withdrawalAmount = getNotesNew(amount);
            account.setAccountBalance(account.getAccountBalance()- amount);
            accountRepository.save(account);
            return withdrawalAmount;
        } else {
            throw new InsufficientBalance("Error");
        }

    }

    @Override
    public List<Note> getAllNotes() {
        return notesRepository.findAll();
    }

    public Note updateNotes(Note note) {
        return notesRepository.save(note);
    }

    public Map<String, Integer> getNotesNew(double amount) {
        List<Note> notes = getAllNotes();
        Map<String, Integer> withdrawalNotes = new HashMap<>();
        int countTens = 0, countTwenties = 0, countFifties = 0, countHundreds = 0;
        if (amount > 0) {
            double balance = amount;
            while (balance > 0) {
                if (balance >= denominationHundred.getDenominationValue()
                        && (notes.get(0).getQuantity() - countHundreds) > 0) {
                    countHundreds++;
                    balance = balance - denominationHundred.getDenominationValue();
                    continue;
                } else if (balance >= denominationFifty.getDenominationValue()
                        && (notes.get(1).getQuantity() - countFifties) > 0) {
                    countFifties++;
                    balance = balance - denominationFifty.getDenominationValue();
                    continue;
                } else if (balance >= denominationTwenty.getDenominationValue()
                        && (notes.get(2).getQuantity() - countTwenties) > 0) {
                    countTwenties++;
                    balance = balance - denominationTwenty.getDenominationValue();
                    continue;
                } else if (balance >= denominationTen.getDenominationValue()
                        && (notes.get(3).getQuantity() - countTens) > 0) {
                    countTens++;
                    balance = balance - denominationTen.getDenominationValue();
                    continue;
                } else {
                    throw new InvalidAmount("Not Sufficient Change. Please try another product");
                }
            }

            for (Note n : notes) {
                if (n.getDenomination().equalsIgnoreCase(denominationHundred.getDenomination())) {
                    n.setQuantity(n.getQuantity() - countHundreds);
                } else if (n.getDenomination().equalsIgnoreCase(denominationFifty.getDenomination())) {
                    n.setQuantity(n.getQuantity() - countFifties);
                } else if (n.getDenomination().equalsIgnoreCase(denominationTwenty.getDenomination())) {
                    n.setQuantity(n.getQuantity() - countTwenties);
                } else if (n.getDenomination().equalsIgnoreCase(denominationTen.getDenomination())) {
                    n.setQuantity(n.getQuantity() - countTens);
                }
                updateNotes(n);
            }


            withdrawalNotes.put(denominationHundred.getDenomination(), countHundreds);
            withdrawalNotes.put(denominationFifty.getDenomination(), countFifties);
            withdrawalNotes.put(denominationTwenty.getDenomination(), countTwenties);
            withdrawalNotes.put(denominationTen.getDenomination(), countTens);
        }
        return withdrawalNotes;
    }

    public List<String> getNotes(double amount) {
        List<String> notes = Collections.EMPTY_LIST;
        if (amount > 0) {
            notes = new ArrayList<String>();
            double balance = amount;
            while (balance > 0) {
                if (balance >= denominationHundred.getDenominationValue()) {
                    notes.add(denominationHundred.getDenomination());
                    balance = balance - denominationHundred.getDenominationValue();
                    continue;
                } else if (balance >= denominationFifty.getDenominationValue()) {
                    notes.add(denominationFifty.getDenomination());
                    balance = balance - denominationFifty.getDenominationValue();
                    continue;
                } else if (balance >= denominationTwenty.getDenominationValue()) {
                    notes.add(denominationTwenty.getDenomination());
                    balance = balance - denominationTwenty.getDenominationValue();
                    continue;
                } else if (balance >= denominationTen.getDenominationValue()) {
                    notes.add(denominationTen.getDenomination());
                    balance = balance - denominationTen.getDenominationValue();
                    continue;
                } else {
                    throw new InvalidAmount("NotSufficientChange. Please try another product");
                }
            }
        }
        return notes;
    }


}
