package com.example.dataJpa_relations.service.serviceinterfaces;

import com.example.dataJpa_relations.dto.AddressDTO;
import com.example.dataJpa_relations.entity.Address;
import jakarta.validation.Valid;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public interface AddressService {
    CompletableFuture<String> CreateAddress(@Valid AddressDTO address);

    CompletableFuture<AddressDTO> showAllAddress(@Valid Long id);

    CompletionStage<AddressDTO> giveAllAddress();
}
