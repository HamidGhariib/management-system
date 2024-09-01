package com.management.system.order.repository;

import com.management.system.order.model.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRequestRepo extends JpaRepository<OrderRequest, Long> {

    @Query("update OrderRequest i set i.statusId = ?2 where i.id = ?1")
    void updateStatusByOrderId(String orderId, String statusId);

    List<OrderRequest> getOrderRequestsByStatusId(String statusId);

}