package com.workintech.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category", schema = "ecommerce")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "title")
    private String title;

    @Column(name = "img")
    private String img;

    @Column(name = "rating")
    private double rating;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;


}