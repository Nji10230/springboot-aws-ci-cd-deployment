package com.example.dataJpa_relations.mapper;

import com.example.dataJpa_relations.dto.AddressDTO;
import com.example.dataJpa_relations.entity.Address;
import com.example.dataJpa_relations.entity.Order;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class AddressMapper {


    public AddressDTO toAddressDTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressDTO addressDTO = new AddressDTO();
       // addressDTO.setId(address.getId());
        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setZipcode(address.getZipcode());

        if (address.getOrder() != null) {
            addressDTO.setOrderId(address.getOrder().getId());
        }

        return addressDTO;
    }

    public Address toEntity(AddressDTO addressDTO, Order order) {
        if (addressDTO == null) {
            return null;
        }
        Address address = new Address();
       // address.setId(addressDTO.getId());
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setZipcode(addressDTO.getZipcode());

        address.setOrder(order);

        return address;
    }

    public void updateEntityFromDto(AddressDTO addressDTO, Address address) {
        if (addressDTO.getCity() != null) {
            address.setCity(addressDTO.getCity());
        }
        if (addressDTO.getStreet() != null) {
            address.setStreet(addressDTO.getStreet());
        }
        if (addressDTO.getZipcode() != null) {
            address.setZipcode(addressDTO.getZipcode());
        }
    }

    public List<AddressDTO> toDtoList(List<Address> addresses) {
        return addresses.stream().map(this::toAddressDTO).toList();
    }
}

