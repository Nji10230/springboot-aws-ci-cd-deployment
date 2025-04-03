package com.example.dataJpa_relations.service.serviceinterfaces.imple;

import com.example.dataJpa_relations.dto.OrderDTO;
import com.example.dataJpa_relations.entity.Order;
import com.example.dataJpa_relations.mapper.OrderMapper;
import com.example.dataJpa_relations.repo.OrderRepository;
import com.example.dataJpa_relations.service.serviceinterfaces.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderServiceImplementation implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImplementation.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImplementation(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<String> createOrder(OrderDTO orderDTO) {
        logger.info("Creating an order: {}", orderDTO);

        if (orderDTO == null) {
            logger.error("OrderDTO is null");
            return CompletableFuture.failedFuture(new IllegalArgumentException("OrderDTO is null"));
        }

        if (orderDTO.getId() != null && orderRepository.existsById(orderDTO.getId())) {
            logger.error("Order with ID {} already exists", orderDTO.getId());
            return CompletableFuture.failedFuture(new IllegalStateException("Order with ID already exists"));
        }

        Order order = orderMapper.toOrderEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        logger.info("Order created successfully with ID: {}", savedOrder.getId());

        return CompletableFuture.completedFuture("Order created successfully with ID: " + savedOrder.getId());
    }
}

