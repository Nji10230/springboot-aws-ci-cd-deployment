package com.example.dataJpa_relations.service.serviceinterfaces.imple;

import com.example.dataJpa_relations.dto.ItemDTO;
import com.example.dataJpa_relations.entity.Item;
import com.example.dataJpa_relations.entity.Order;
import com.example.dataJpa_relations.mapper.ItemMapper;
import com.example.dataJpa_relations.repo.ItemRepository;
import com.example.dataJpa_relations.repo.OrderRepository;
import com.example.dataJpa_relations.service.serviceinterfaces.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.CompletableFuture;


@Service
public class ItemServiceImplementation implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImplementation.class);

    private final OrderRepository orderRepository;

    @Autowired
    public ItemServiceImplementation(ItemRepository itemRepository, ItemMapper itemMapper, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<String> createItem(ItemDTO itemDTO) {
        logger.info("Creating an item: {}", itemDTO);

        if (itemDTO == null) {
            logger.error("ItemDTO is null");
            return CompletableFuture.failedFuture(new IllegalArgumentException("ItemDTO is null"));
        }

        if (itemDTO.getId() != null && itemRepository.existsById(itemDTO.getId())) {
            logger.error("Item with ID {} already exists", itemDTO.getId());
            return CompletableFuture.failedFuture(new IllegalStateException("Item with ID already exists"));
        }
        Order order = orderRepository.findById(itemDTO.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + itemDTO.getOrderId()));

        Item item = itemMapper.toItemEntity(itemDTO, order);

        //Item item = itemMapper.toItemEntity(itemDTO);
        Item savedItem = itemRepository.save(item);
        logger.info("Item created successfully with ID: {}", savedItem.getId());

        return CompletableFuture.completedFuture("Item created successfully with ID: " + savedItem.getId());
    }
}

