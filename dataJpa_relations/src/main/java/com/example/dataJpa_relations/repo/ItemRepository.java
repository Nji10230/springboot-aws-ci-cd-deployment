package com.example.dataJpa_relations.repo;

import com.example.dataJpa_relations.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {


}


