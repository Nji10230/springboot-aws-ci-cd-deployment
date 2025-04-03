package com.example.dataJpa_relations.dto;

import com.example.dataJpa_relations.entity.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class AddressDTO {

    private Long id;
    private String zipcode;
    private String street;
    private String city;
    private Long orderId; // Reference to Order without circular dependency

    // Constructors
    public AddressDTO() {}

    public AddressDTO(Long id, String zipcode, String street, String city, Long orderId) {
        this.id = id;
        this.zipcode = zipcode;
        this.street = street;
        this.city = city;
        this.orderId = orderId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}