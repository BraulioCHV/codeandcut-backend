package org.code_cut.code_cutSpring.service;
import org.code_cut.code_cutSpring.dto.PaymentRequest;
import org.code_cut.code_cutSpring.model.Orders;
import org.code_cut.code_cutSpring.model.Payment;
import org.code_cut.code_cutSpring.repository.OrdersRepository;
import org.code_cut.code_cutSpring.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrdersRepository ordersRepository;
    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, OrdersRepository ordersRepository) {
        this.paymentRepository = paymentRepository;
        this.ordersRepository = ordersRepository;
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
    public Payment deletePaymentById(int id) {
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
        if(paymentUpdate.getTypepayment() != 0) originalPayment.setTypepayment(paymentUpdate.getTypepayment());
        if(paymentUpdate.getStatus() != null) originalPayment.setStatus(paymentUpdate.getStatus());
        return paymentRepository.save(originalPayment);
    }
/*
    @Override
    public Payment addPaymentToOrder(Long orderId, PaymentRequest paymentRequest) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("La orden con ID " + orderId + " no existe"));

        Payment payment = new Payment();
        payment.setTypepayment(paymentRequest.getTypePayment());
        payment.setStatus(paymentRequest.getStatus());
        payment.setOrders(order);

        return paymentRepository.save(payment);
        *
         return null;
    }

 */


}
