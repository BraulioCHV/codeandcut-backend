package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayment();
    Payment getPaymentById(int id);
    Payment deletePaymentBtId(int id);
    Payment addPayment(Payment payment);
    Payment updatePaymentById(int id, Payment paymentUpdate);
}
