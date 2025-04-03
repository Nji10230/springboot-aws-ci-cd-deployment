package com.example.dataJpa_relations.mapper;


import com.example.dataJpa_relations.dto.AddressDTO;
import com.example.dataJpa_relations.dto.ItemDTO;
import com.example.dataJpa_relations.dto.OrderDTO;
import com.example.dataJpa_relations.entity.Address;
import com.example.dataJpa_relations.entity.Item;
import com.example.dataJpa_relations.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class OrderMapper {

    private final AddressMapper addressMapper;
    private final ItemMapper itemMapper;

    @Autowired
    public OrderMapper(AddressMapper addressMapper, ItemMapper itemMapper) {
        this.addressMapper = addressMapper;
        this.itemMapper = itemMapper;
    }

    public OrderDTO toOrderDto(Order order) {
        if (order == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
       // orderDTO.setId(order.getId());
        orderDTO.setOrderedDate(order.getOrderedDate());
        orderDTO.setAddress(addressMapper.toAddressDTO(order.getAddress()));

        if (order.getItems() != null) {
            orderDTO.setItems(order.getItems().stream()
                    .map(itemMapper::toItemDto)
                    .toList());
        }

        return orderDTO;
    }

    public Order toOrderEntity(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }
        Order order = new Order();
       // order.setId(orderDTO.getId());
        order.setOrderedDate(orderDTO.getOrderedDate());
        order.setAddress(addressMapper.toEntity(orderDTO.getAddress(), order));

        if (orderDTO.getItems() != null) {
            order.setItems(orderDTO.getItems().stream()
                    .map(itemDTO -> itemMapper.toItemEntity(itemDTO, order))
                    .toList());
        }

        return order;
    }
    public void updateOrderEntityFromDto(OrderDTO orderDTO, Order order) {
        if (orderDTO.getOrderedDate() != null) {
            order.setOrderedDate(orderDTO.getOrderedDate());
        }

        // Update nested entities if needed
        if (orderDTO.getAddress() != null) {
            addressMapper.updateEntityFromDto(orderDTO.getAddress(), order.getAddress());
        }

        // Handling items is more complex and might need a more sophisticated approach
        if (orderDTO.getItems() != null) {
            // Example of updating existing items
            for (ItemDTO itemDTO : orderDTO.getItems()) {
                // You might want to match existing items or add/remove as needed
                // This is a simplified example
                Optional<Item> existingItem = order.getItems().stream()
                        .filter(item -> item.getId().equals(itemDTO.getId()))
                        .findFirst();

                existingItem.ifPresent(item -> {
                    itemMapper.updateEntityFromDto(itemDTO, item);
                });
            }
        }


    }
    public List<OrderDTO> toOrderDtoList(List<Order> orders) {
        return orders.stream().map(this::toOrderDto).toList();
    }
}
