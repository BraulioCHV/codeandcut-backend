package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.dto.PaymentRequest;
import org.code_cut.code_cutSpring.dto.UserRequest;
import org.code_cut.code_cutSpring.model.Orders;

import java.util.List;

public interface OrdersService {
    //Obtenemos todo los pedidos
    List<Orders> getAllOrders();
    //Obtener por Id
    Orders getOrderById(Long id);
    //Borrar una order por id
    Orders deleteOrderById(Long id);
    //Agregar order
    Orders addOrder(Orders order);
    //Modificar
    Orders updateOrderById(Long id, Orders orderUpdated);
    //Añadir orden a pago
    Orders addOrderToPayment(Long orderId, PaymentRequest paymentRequest);
    //Añadir usuario a orden
    Orders addUserToOrder(Long orderId, int userId);
}
