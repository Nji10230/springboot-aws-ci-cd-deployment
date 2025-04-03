package com.example.dataJpa_relations.controller;

import com.example.dataJpa_relations.dto.AddressDTO;
import com.example.dataJpa_relations.service.serviceinterfaces.AddressService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("api/v1/address")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<ResponseEntity<String>> create(@RequestBody @Valid AddressDTO address) {
        String requestId = MDC.get("requestId");
        logger.info("[RequestID={}] Received request to create address: {}", requestId, address);

        return addressService.CreateAddress(address)
                .thenApply(response -> {
                    logger.info("[RequestID={}] Address created successfully: {}", requestId, response);
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                })
                .exceptionally(ex -> {
                    logger.error("[RequestID={}] Error creating address: {}", requestId, ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create address");
                });
    }

    @GetMapping("/get/{id}")
    public CompletableFuture<ResponseEntity<AddressDTO>> getAddress(@PathVariable @Valid Long id) {
        String requestId = MDC.get("requestId");
        logger.info("[RequestID={}] Fetching address with ID: {}", requestId, id);

        return addressService.showAllAddress(id)
                .thenApply(address -> {
                    logger.info("[RequestID={}] Successfully fetched address: {}", requestId, address);
                    return ResponseEntity.status(HttpStatus.OK).body(address);
                })
                .exceptionally(ex -> {
                    logger.error("[RequestID={}] Error fetching address: {}", requestId, ex.getMessage());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                });
    }

    @GetMapping("/greet")
    public String greet() {
        return "Hello, this is a greeting from the AddressController!";
    }
    @GetMapping("/getAll")
    public CompletableFuture<ResponseEntity<AddressDTO>> getAllAddress() {
        String requestId = MDC.get("requestId");
        logger.info("[RequestID={}] Fetching all addresses", requestId);

        return (CompletableFuture<ResponseEntity<AddressDTO>>) addressService.giveAllAddress()
                .thenApply(addresses -> {
                    logger.info("[RequestID={}] Successfully fetched all addresses: {}", requestId, addresses);
                    return ResponseEntity.status(HttpStatus.OK).body(addresses);
                })
                .exceptionally(ex -> {
                    logger.error("[RequestID={}] Error fetching all addresses: {}", requestId, ex.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }
}
