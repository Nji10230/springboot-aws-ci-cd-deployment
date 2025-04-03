package com.example.dataJpa_relations.service.serviceinterfaces;


import com.example.dataJpa_relations.dto.ItemDTO;
import jakarta.validation.Valid;

import java.util.concurrent.CompletableFuture;

public interface ItemService {


  CompletableFuture<String> createItem(@Valid ItemDTO itemDTO);


}

