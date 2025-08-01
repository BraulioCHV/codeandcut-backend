package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.dto.DetailsOrderRequest;
import org.code_cut.code_cutSpring.dto.PaymentRequest;
import org.code_cut.code_cutSpring.dto.UserRequest;
import org.code_cut.code_cutSpring.model.DetailsOrder;
import org.code_cut.code_cutSpring.model.Orders;
import org.code_cut.code_cutSpring.model.Payment;
import org.code_cut.code_cutSpring.model.User;
import org.code_cut.code_cutSpring.repository.DetailsOrderRepository;
import org.code_cut.code_cutSpring.repository.OrdersRepository;
import org.code_cut.code_cutSpring.repository.PaymentRepository;
import org.code_cut.code_cutSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService{

    private final OrdersRepository ordersRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final DetailsOrderRepository detailsOrderRepository;
    //Inyección dependencias
    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository, PaymentRepository paymentRepository, UserRepository userRepository, DetailsOrderRepository detailsOrderRepository) {
        this.ordersRepository = ordersRepository;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.detailsOrderRepository = detailsOrderRepository;
    }

    @Override
    public List<Orders> getAllOrders() {
        //Obtenemos todas las ordenes
        return ordersRepository.findAll();
    }

    @Override
    public Orders getOrderById(Long id) {
        return ordersRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("La orden con el id " + id + " no existe")
        );
    }

    @Override
    public Orders deleteOrderById(Long id) {
        //1. Creamos una variable temporal para guardar el producto eliminado
        Orders tmp = null;
        //Senrsioarse que exista el dato
        //2. usamos early return para evaluar si no existe el producto
        //en caso de que no exista termina la ejecución de la función en ese momento
        if (!ordersRepository.existsById(id)) return  tmp;
        //si el producto existe, guardamos el producto en la variable temporal
        tmp = ordersRepository.findById(id).get();
        //eliminamos el producto
        ordersRepository.deleteById(id);
        //retornamos el producto eliminado
        return tmp;
    }

    @Override
    public Orders addOrder(Orders order) {
        return ordersRepository.save(order);
    }

    @Override
    public Orders updateOrderById(Long id, Orders orderUpdated) {
        //1. creando un objeto opcional de tipo pedido
        Optional<Orders> optionalOrder = ordersRepository.findById(id);
        //early return
        if (optionalOrder.isEmpty()) throw new IllegalArgumentException("El pedido con el id " + id + " no existe");
        Orders originalOrder = optionalOrder.get();
        //evaluamos el pedido
        if (orderUpdated.getAddress() != null) originalOrder.setAddress(orderUpdated.getAddress());
        if (orderUpdated.getTotalAmount() != null) originalOrder.setTotalAmount(orderUpdated.getTotalAmount());

        return ordersRepository.save(originalOrder);
    }

    @Override
    public Orders addOrderToPayment(Long orderId, PaymentRequest paymentRequest) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("La orden con ID " + orderId + " no existe"));

        Payment payment = new Payment();
        payment.setTypepayment(paymentRequest.getTypePayment());
        payment.setStatus(paymentRequest.getStatus());
        payment.setOrders(order);


        paymentRepository.save(payment);
        order.setPayment(payment);
        return ordersRepository.save(order);


    }



    @Override
    public Orders addUserToOrder(Long orderId, int userId){
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("La orden con ID " + orderId + " no existe"));
        User user = userRepository.findById( userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario con id "+userId+" no encontrado"));

        order.setUser(user);

        return ordersRepository.save(order);
    }

    @Override
    public Orders addDetailsToOrder(Long orderId, DetailsOrderRequest detailsOrderRequest) {
              Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("La orden con ID " + orderId + " no existe"));

        DetailsOrder detailsOrder = new DetailsOrder();
        detailsOrder.setQtyProduct(detailsOrderRequest.getQtyProduct());


        detailsOrder.setOrders(order);
        detailsOrderRepository.save(detailsOrder);
        order.setDetailsOrder(detailsOrder);
        return ordersRepository.save(order);

    }


}
