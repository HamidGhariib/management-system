package com.management.system.inventory.service;


import com.management.system.enumeration.ProductStatus;
import com.management.system.inventory.dto.request.InventoryUpdateReqDto;
import com.management.system.inventory.dto.request.NewProductReqDto;
import com.management.system.inventory.dto.response.NewProductResDto;
import com.management.system.inventory.exception.ProductNotAvailableException;
import com.management.system.inventory.exception.ProductOutOfRangeException;
import com.management.system.inventory.model.Product;
import com.management.system.inventory.repository.InventoryRepo;
import com.management.system.util.CommonUtilService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class InventoryService {

    private final InventoryRepo inventoryRepo;
    private final Logger logger = Logger.getLogger(InventoryService.class.getName());

    public InventoryService(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    public NewProductResDto createNewProduct(NewProductReqDto newProductReqDto) {
        Product product = CommonUtilService.convertModel(newProductReqDto, Product.class);
        product.setStatusId(ProductStatus.AVAILABLE.toString());

        var inventoryRes = inventoryRepo.save(product);
        logger.info("New product " + newProductReqDto + " created by id " + inventoryRes.getId());
        return NewProductResDto.builder().productId(inventoryRes.getId()).build();
    }

    public void updateInventory(InventoryUpdateReqDto inventoryUpdateReqDto) {

        logger.info("Update productId by id " + inventoryUpdateReqDto.getProductId() + " and request count " + inventoryUpdateReqDto.getRequestCount());
        var inventoryRes = inventoryRepo.findById(Long.parseLong(inventoryUpdateReqDto.getProductId()));

        this.checkProductAvailability(inventoryUpdateReqDto.getRequestCount(), inventoryRes);

        inventoryRepo.updateById(Long.parseLong(inventoryUpdateReqDto.getProductId())
                , inventoryRes.get().getRemainingCapacity() - inventoryUpdateReqDto.getRequestCount());
    }

    private void checkProductAvailability(Integer requestCount, Optional<Product> inventoryRes) {
        if (inventoryRes.isPresent() && !inventoryRes.get().getStatusId().equals(ProductStatus.AVAILABLE.toString())) {
            throw new ProductNotAvailableException();
        }
        if (inventoryRes.isPresent() && (requestCount > inventoryRes.get().getRemainingCapacity())) {
            throw new ProductOutOfRangeException();
        }
    }
}
