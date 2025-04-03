package com.example.dataJpa_relations.repo;

import com.example.dataJpa_relations.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {


}
