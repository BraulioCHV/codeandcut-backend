package org.code_cut.code_cutSpring.controller;

import org.code_cut.code_cutSpring.model.DetailsOrder;
import org.code_cut.code_cutSpring.service.DetailsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detailsOrder")
public class DetailsOrderController {
    @Autowired
    private DetailsOrderService detailsOrderService;

    @GetMapping
    public List<DetailsOrder> getAll() {
        return detailsOrderService.findAll();
    }

    @PostMapping
    public DetailsOrder create(@RequestBody DetailsOrder detailsOrder) {
        return detailsOrderService.save(detailsOrder);
    }

    @GetMapping("/Order/{idOrder}")
    public List<DetailsOrder> getByPedidoId(@PathVariable Long pedidoId) {
        return detailsOrderService.findByPedidoId(pedidoId);
    }
}