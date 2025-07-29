package org.code_cut.code_cutSpring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detailsorder")
public class DetailsOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetailsOrder")
    private Long idDetailsOrder;

    @Column(name = "qtyProduct", nullable = false)
    private int qtyProduct;

    // Relación Many-to-One con Pedido
    @ManyToOne
    @JoinColumn(name = "order_idOrder", nullable = false)
    private Order order;

    // Relación Many-to-One con Producto
    @ManyToOne
    @JoinColumn(name = "products_idProducts", nullable = false)
    private Products product;
}