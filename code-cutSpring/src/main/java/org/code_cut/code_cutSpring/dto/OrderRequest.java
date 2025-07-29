package org.code_cut.code_cutSpring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private long id;
    private String address;
    private Double totalAmount;
}
