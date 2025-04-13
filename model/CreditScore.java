package com.fintech.creditscoring.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class CreditScore {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private int score;
    private Date date;
}