package com.management.system.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShippingReport extends JpaRepository<com.management.system.shipping.model.ShippingReport, UUID> {

}