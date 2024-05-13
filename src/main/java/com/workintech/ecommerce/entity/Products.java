package com.workintech.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products", schema = "ecommerce")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank(message = "Name must not be null, empty or blank")
    @Size(min = 3,max = 100,message = "Name should be between 3 and 100 characters.")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Description must not be null, empty or blank")
    @Size(min = 3,max = 200,message = "Description should be between 3 and 200 characters.")
    @Column(name = "description")
    private String description;

    @Min(value = 1,message = "Price must not be less than 1")
    @Column(name = "price")
    private String price;

    @Min(value = 0,message = "Stock must not be less than 0")
    @Column(name = "stock")
    private String stock;

    @Min(value = 0,message = "Price must not be less than 0")
    @Column(name = "rating")
    private String rating;

    @Min(value = 0,message = "Sell count must not be less than 0")
    @Column(name = "sell_count")
    private String sellCount;

    @Column(name = "category_id")
    private long categoryId;

    @Column(name = "image")
    private String image;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "order_product",schema = "ecommerce",joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;

    public void addOrder(Order order){
        if(orders==null){
            orders=new ArrayList<>();
        }
        orders.add(order);
    }

}