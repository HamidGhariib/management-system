package com.management.system.inventory.repository;

import com.management.system.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    @Modifying
    @Query("update Inventory i set i.remainingCapacity = ?2 where i.id = ?1")
    void updateById(Long id,Integer requestCount);
}