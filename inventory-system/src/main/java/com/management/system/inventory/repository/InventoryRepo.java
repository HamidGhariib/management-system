package com.management.system.inventory.repository;

import com.management.system.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Product, Long> {

    @Modifying
    @Query("update Product i set i.remainingCapacity = ?2 where i.id = ?1")
    void updateById(Long id,Integer requestCount);
}