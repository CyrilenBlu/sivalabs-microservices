package blu.cloud.catalogservice.services;

import blu.cloud.catalogservice.model.Product;
import blu.cloud.catalogservice.model.ProductInventoryResponse;
import blu.cloud.catalogservice.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductService(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductByCode(String code) {
        Optional<Product> productOptional = productRepository.findByCode(code);
        if (productOptional.isPresent())
        {
            log.info("Fetching inventory level for product_code: " + code);
            ResponseEntity<ProductInventoryResponse> itemResponseEntity =
                    restTemplate.getForEntity("http://inventory-service/api/v1/items/{code}",
                            ProductInventoryResponse.class,
                            code);

            if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
                Integer quantity = itemResponseEntity.getBody().getAvailableQuantity();
                log.info("Available quantity: " + quantity);
                productOptional.get().setInStock(quantity>0);
            } else {
                log.error("Unable to get inventory level for product_code: " + code +
                        ", StatusCode: " + itemResponseEntity.getStatusCode());
            }
        }
        return productOptional;
    }
}
