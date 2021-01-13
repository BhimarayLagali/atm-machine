package com.example.atmmachine.model;

import com.sun.javafx.beans.IDProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="ACC", sequenceName = "acc_seq", initialValue = 10000, allocationSize = 100)
    private Long accountNo;
    private String customerName;
    private double accountBalance;
    private int pin;
}
