package org.code_cut.code_cutSpring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailsOrderRequest {
    private Long idDetailsOrder;  // ID de DetailsOrder
    private int qtyProduct;  // Cantidad de productos
}