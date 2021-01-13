package com.example.atmmachine.model;

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
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String denomination;
    private double quantity;
    
}
