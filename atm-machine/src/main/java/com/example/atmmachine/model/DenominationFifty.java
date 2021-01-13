package com.example.atmmachine.model;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DenominationFifty extends Denomination{

    @Override
    public String getDenomination() {
        return "Fifty";
    }

    @Override
    public int getDenominationValue() {
        return 50;
    }

}
