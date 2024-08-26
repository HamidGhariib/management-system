package com.management.system.order.repository;

import com.management.system.order.model.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRequestRepo extends JpaRepository<OrderRequest, UUID> {

}