package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Orders;
import org.code_cut.code_cutSpring.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService{

    private final OrdersRepository ordersRepository;

    //Inyección dependencias
    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository) {this.ordersRepository = ordersRepository;}

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
}
