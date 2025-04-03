package com.example.dataJpa_relations.controller;


import com.example.dataJpa_relations.dto.ItemDTO;
import com.example.dataJpa_relations.service.serviceinterfaces.ItemService;
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
@RequestMapping("api/v1/item")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<String>> create(@RequestBody @Valid ItemDTO itemDTO) {
        String requestId = MDC.get("requestId");
        logger.info("[RequestID={}] Received request to create item: {}", requestId, itemDTO);

        return itemService.createItem(itemDTO)
                .thenApply(response -> {
                    logger.info("[RequestID={}] Item created successfully: {}", requestId, response);

                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                })
                .exceptionally(ex -> {
                    logger.error("[RequestID={}] Error creating item: {}", requestId, ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create item");
                });
    }
}

