package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Payment;
import org.code_cut.code_cutSpring.model.Products;
import org.code_cut.code_cutSpring.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PaymentServiceImpl {
    private final PaymentRepository productsRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El pago con el id "+id+" no existe")
        ) ;
    }

    @Override
    public Payment deletePaymentBtId(int id) {
        //Crear una varariable temporal para guardar el producto eliminado
        Payment temp = null;
        //Usamos early return para evaluar si no existe el producto, en caso de que no exista, termina la ejecución en ese momento
        if(!paymentRepository.existsById(id)) return temp;
        //Si el producto existe, guardamos el producto
        temp = paymentRepository.findById(id).get();
        //Eliminamos el producto
        paymentRepository.deleteById(id);
        //Retornamos el producto eliminado
        return temp;
    }

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePaymentById(int id, Payment paymentUpdate) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if(optionalPayment.isEmpty()) throw new IllegalArgumentException("El pago con el id "+id+" no existe");
        Payment originalPayment = optionalPayment.get();
        if(paymentUpdate.getTypePayment() != 0) originalPayment.setTypePayment(paymentUpdate.getTypePayment());
        if(paymentUpdate.getStatus() != null) originalPayment.setStatus(paymentUpdate.getStatus());
        if(paymentUpdate.getStock() != 0) originalPayment.setStock(paymentUpdate.getStock());
        return paymentRepository.save(originalPayment);
    }
}
