package com.example.dataJpa_relations.mapper;


import com.example.dataJpa_relations.dto.ItemDTO;
import com.example.dataJpa_relations.entity.Item;
import com.example.dataJpa_relations.entity.Order;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class ItemMapper {

    public ItemDTO toItemDto(Item item) {
        if (item == null) {
            return null;
        }
        ItemDTO itemDTO = new ItemDTO();
       // itemDTO.setId(item.getId());
        itemDTO.setItemName(item.getItemName());
        itemDTO.setItemPrice(item.getItemPrice());

        if (item.getOrder() != null) {
            itemDTO.setOrderId(item.getOrder().getId());
        }

        return itemDTO;
    }

    public Item toItemEntity(ItemDTO itemDTO, Order order) {
        if (itemDTO == null) {
            return null;
        }
        Item item = new Item();
        //item.setId(itemDTO.getId());
        item.setItemName(itemDTO.getItemName());
        item.setItemPrice(itemDTO.getItemPrice());
        item.setOrder(order);

        return item;
    }

    public void updateEntityFromDto(ItemDTO itemDTO, Item item) {
        if (itemDTO.getItemName() != null) {
            item.setItemName(itemDTO.getItemName());
        }
        if (itemDTO.getItemPrice() != null) {
            item.setItemPrice(itemDTO.getItemPrice());
        }
    }

    public List<ItemDTO> toDtoList(List<Item> items) {
        return items.stream().map(this::toItemDto).toList();
    }
}

