package com.example.atmmachine.model;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DenominationHundred extends Denomination {

    @Override
    public String getDenomination() {
        return "Hundred";
    }

    @Override
    public int getDenominationValue() {
        return 100;
    }

}
