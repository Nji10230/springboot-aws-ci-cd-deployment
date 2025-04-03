package com.example.dataJpa_relations.service.serviceinterfaces;

import com.example.dataJpa_relations.dto.OrderDTO;
import jakarta.validation.Valid;

import java.util.concurrent.CompletableFuture;

public interface OrderService {
  CompletableFuture< String > createOrder(@Valid OrderDTO orderDTO);
}
