package com.example.dataJpa_relations.dto;


import com.example.dataJpa_relations.entity.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class ItemDTO {

    private Long id;
    private String itemName;
    private Double itemPrice;
    private Long orderId; // Reference to Order without circular dependency

    // Constructors
    public ItemDTO() {}

    public ItemDTO(Long id, String itemName, Double itemPrice, Long orderId) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.orderId = orderId;
    }

    // Getters and Setters
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}