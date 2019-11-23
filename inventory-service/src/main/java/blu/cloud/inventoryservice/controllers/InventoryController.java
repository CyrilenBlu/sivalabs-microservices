package blu.cloud.inventoryservice.controllers;

import blu.cloud.inventoryservice.model.InventoryItem;
import blu.cloud.inventoryservice.repositories.InventoryItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/items")
public class InventoryController {
    private final InventoryItemRepository inventoryItemRepository;

    public InventoryController(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @GetMapping("/")
    public List<InventoryItem> getInventory() {
        return inventoryItemRepository.findAll();
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<InventoryItem> findInventoryByProductCode(@PathVariable String productCode) {
        log.info("Finding inventory for code: " + productCode);
        Optional<InventoryItem> inventoryItem = inventoryItemRepository.findByProductCode(productCode);
        if (inventoryItem.isPresent())
        {
            return new ResponseEntity(inventoryItem, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
