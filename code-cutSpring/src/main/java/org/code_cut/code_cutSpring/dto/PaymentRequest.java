package org.code_cut.code_cutSpring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private int typePayment;
    private String status;
}

