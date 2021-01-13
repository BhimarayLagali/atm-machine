package com.example.atmmachine.model;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DenominationTen extends Denomination{

    @Override
    public String getDenomination() {
        return "Ten";
    }

    @Override
    public int getDenominationValue() {
        return 10;
    }

}
