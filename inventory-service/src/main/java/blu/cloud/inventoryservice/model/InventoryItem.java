package blu.cloud.inventoryservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "items")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String productCode;

    private int availableQuantity;
}
