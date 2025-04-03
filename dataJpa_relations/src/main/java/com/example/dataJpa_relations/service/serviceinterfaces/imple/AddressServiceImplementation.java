package com.example.dataJpa_relations.service.serviceinterfaces.imple;

import com.example.dataJpa_relations.dto.AddressDTO;
import com.example.dataJpa_relations.entity.Address;
import com.example.dataJpa_relations.entity.Order;
import com.example.dataJpa_relations.mapper.AddressMapper;
import com.example.dataJpa_relations.repo.AddressRepository;
import com.example.dataJpa_relations.repo.OrderRepository;
import com.example.dataJpa_relations.service.serviceinterfaces.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class AddressServiceImplementation implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImplementation.class);

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final OrderRepository orderRepository;

    @Autowired
    public AddressServiceImplementation(AddressRepository addressRepository, AddressMapper addressMapper, OrderRepository orderRepository) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<String> CreateAddress(AddressDTO addressDTO) {
        logger.info("Creating an address: {}", addressDTO);

        if (addressDTO == null) {
            logger.error("Address is null");
            return CompletableFuture.failedFuture(new IllegalArgumentException("Address is null"));
        }

        if (addressDTO.getId() != null && addressRepository.existsById(addressDTO.getId())) {
            logger.error("Address with ID {} already exists", addressDTO.getId());
            return CompletableFuture.failedFuture(new IllegalStateException("Address with ID already exists"));
        }

        Order order = orderRepository.findById(addressDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Address addressEntity = addressMapper.toEntity(addressDTO, order);
        Address savedAddress = addressRepository.save(addressEntity);
        logger.info("Address created successfully with ID: {}", savedAddress.getId());

        return CompletableFuture.completedFuture("Address created successfully with ID: " + savedAddress.getId());
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<AddressDTO> showAllAddress(Long id) {
        logger.info("Fetching address with ID: {}", id);

        return CompletableFuture.supplyAsync(() ->
                addressRepository.findById(id)
                        .map(addressMapper::toAddressDTO)
                        .orElseThrow(() -> new RuntimeException("Address not found with ID: " + id))
        );
    }

    @Override
    public CompletionStage<AddressDTO> giveAllAddress() {

        logger.info("Fetching all addresses");
        return CompletableFuture.supplyAsync(() -> {
            Iterable<Address> addresses = addressRepository.findAll();
            return addressMapper.toAddressDTO((Address) addresses);
        });
    }
}
