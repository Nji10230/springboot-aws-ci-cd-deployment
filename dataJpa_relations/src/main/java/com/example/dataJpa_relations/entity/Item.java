package com.example.dataJpa_relations.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Double itemPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference("order-items")
    private Order order ;


    public Item(Long id, String itemName, Double itemPrice, Order order) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.order = order;
    }

    public Item() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
