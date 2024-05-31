package com.workintech.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address", schema = "ecommerce")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Title can not be null or blank")
    @Size(min = 3,max = 35,message = "Title should be between 3 and 35 characters.")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Name can not be null or blank")
    @Size(min = 3,max = 35,message = "Name should be between 3 and 35 characters.")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Surname can not be null or blank")
    @Size(min = 3,max = 35,message = "Surname should be between 3 and 35 characters.")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Phone number can not be null or blank")
    @Size(min = 10,max = 35,message = "Phone number should be between 10 and 30 characters.")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "City can not be null or blank")
    @Size(min = 3,max = 35,message = "City should be between 3 and 30 characters.")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "District can not be null or blank")
    @Size(min = 3,max = 35,message = "District should be between 3 and 30 characters.")
    @Column(name = "district")
    private String district;

    @NotBlank(message = "Neighborhood can not be null or blank")
    @Size(min = 3,max = 35,message = "Neighborhood should be between 3 and 30 characters.")
    @Column(name = "neighborhood")
    private String neighborhood;

    @NotBlank(message = "Address can not be null or blank")
    @Size(min = 3,max = 300,message = "Address should be between 3 and 300 characters.")
    @Column(name = "address")
    private String address;


    // bir user birden çok adrese sahip olabilir.

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
}