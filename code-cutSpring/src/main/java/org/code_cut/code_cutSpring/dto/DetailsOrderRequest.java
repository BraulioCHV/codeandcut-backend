package org.code_cut.code_cutSpring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailsOrderRequest {
    private Long id;  // ID de DetailsOrder
    private int qtyProduct;  // Cantidad de productos
    private Long orderId;  // ID del pedido relacionado (Orders)
    private Long productId;  // ID del producto relacionado (Products)
}