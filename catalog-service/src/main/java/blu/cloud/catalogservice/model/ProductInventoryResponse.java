package blu.cloud.catalogservice.model;

import lombok.Data;

@Data
public class ProductInventoryResponse {
    private String productCode;
    private int availableQuantity;
}
