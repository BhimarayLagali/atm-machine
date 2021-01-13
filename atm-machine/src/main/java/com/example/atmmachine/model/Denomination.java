package com.example.atmmachine.model;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class Denomination {

    private int denomination;
    private int value;

    public abstract int getDenominationValue();
    public abstract String getDenomination();


}
