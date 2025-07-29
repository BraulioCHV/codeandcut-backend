package org.code_cut.code_cutSpring.controller;

import lombok.AllArgsConstructor;
import org.code_cut.code_cutSpring.model.Orders;
import org.code_cut.code_cutSpring.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Mapeo de la ruta
@RequestMapping(path = "/api/orders") // http://localhost:8080/api/orders
@AllArgsConstructor
public class OrdersController {
    //Inyeccion de dependencias
    private OrdersService ordersService;

    //Peticion get
    @GetMapping
    public List<Orders> getAllOrders(){return this.ordersService.getAllOrders();}

    //Peticion get obtener order por id
    @GetMapping(path = "{orderId}")
    public Orders getOrderById(@PathVariable("orderId") Long id){
        return this.ordersService.getOrderById(id);
    }

    //Peticion post crear order
    @PostMapping
    public Orders addOrder(@RequestBody Orders orders){ return this.ordersService.addOrder(orders);}

    //Peticion delete
    @DeleteMapping(path = "{orderId}")
    public Orders deleteOrdersById(@PathVariable("orderId")Long id){
        return this.ordersService.deleteOrderById(id);
    }

    //Peticion put
    @PutMapping(path = "{orderId}")
    public Orders updateOrderById(@PathVariable("orderId") Long id, @RequestBody Orders orderUpdated){
        return this.ordersService.updateOrderById(id, orderUpdated);
    }
}
