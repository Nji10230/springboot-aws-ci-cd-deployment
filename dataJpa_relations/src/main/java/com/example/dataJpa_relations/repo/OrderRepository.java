package com.example.dataJpa_relations.repo;

import com.example.dataJpa_relations.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface OrderRepository extends JpaRepository<Order,Long> {

}
