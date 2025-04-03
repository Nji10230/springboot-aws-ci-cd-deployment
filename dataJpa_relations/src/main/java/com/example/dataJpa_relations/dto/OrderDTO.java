package com.example.dataJpa_relations.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OrderDTO {

    private Long id;
    private Date orderedDate;
    private AddressDTO address;
    private List<ItemDTO> items;

    // Constructors
    public OrderDTO() {}

    public OrderDTO(Long id, Date orderedDate, AddressDTO address, List<ItemDTO> items) {
        this.id = id;
        this.orderedDate = orderedDate;
        this.address = address;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}