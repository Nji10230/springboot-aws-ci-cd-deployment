package com.example.dataJpa_relations.controller;

import com.example.dataJpa_relations.dto.OrderDTO;
import com.example.dataJpa_relations.entity.Order;
import com.example.dataJpa_relations.service.serviceinterfaces.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<String>> create(@RequestBody @Valid OrderDTO orderDTO) {
        String requestId = MDC.get("requestId");
        logger.info("[RequestID={}] Received request to create order: {}", requestId, orderDTO);

        return orderService.createOrder(orderDTO)
                .thenApply(response -> {
                    logger.info("[RequestID={}] Order created successfully: {}", requestId, response);
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                })
                .exceptionally(ex -> {
                    logger.error("[RequestID={}] Error creating order: {}", requestId, ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create order");
                });
    }
}
