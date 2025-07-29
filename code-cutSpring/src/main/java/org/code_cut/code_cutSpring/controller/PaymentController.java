package org.code_cut.code_cutSpring.controller;

import lombok.AllArgsConstructor;
import org.code_cut.code_cutSpring.model.Payment;
import org.code_cut.code_cutSpring.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/api/payment")
@AllArgsConstructor
public class PaymentController {
    private PaymentService paymentService;

    @GetMapping // http://localhost:8080/api/payment
    public List<Payment> getAllPayment(){
        return this.paymentService.getAllPayment();
    }

    @GetMapping(path = "{paymentId}")// http://localhost:8080/api/payment
    public Payment getPaymentById(@PathVariable("paymentId") int id){
        return this.paymentService.getPaymentById(id);
    }

    @PostMapping // a la url que le hace la petición // http://localhost:8080/api/payment
    public Payment addPayment(@RequestBody Payment payment){
        return this.paymentService.addPayment(payment);
    }
    @DeleteMapping(path = "{paymentId}")
    public Payment deletePaymentById(@PathVariable("paymentId") int id){
        return this.paymentService.deletePaymentById(id);
    }

    @PutMapping(path = "{paymentId}") // http://localhost:8080/api/payment/2
    public Payment updatePaymentById(@PathVariable("paymentId") int id, @RequestBody Payment paymentUpdated){
        return this.paymentService.updatePaymentById(id, paymentUpdated);
    }
}
