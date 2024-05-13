package com.workintech.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card", schema = "ecommerce")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "secure")
    private boolean secure;
}