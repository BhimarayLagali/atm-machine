package com.example.atmmachine.model;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DenominationTwenty extends Denomination{

    @Override
    public String getDenomination() {
        return "Twenty";
    }

    @Override
    public int getDenominationValue() {
        return 20;
    }

}
