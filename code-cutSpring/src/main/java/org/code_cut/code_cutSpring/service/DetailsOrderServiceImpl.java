package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.DetailsOrder;
import org.code_cut.code_cutSpring.model.Orders;
import org.code_cut.code_cutSpring.repository.DetailsOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Marca esta clase como un componente de servicio de Spring
public class DetailsOrderServiceImpl implements DetailsOrderService {

    private final DetailsOrderRepository detailsOrderRepository;

    @Autowired
    public DetailsOrderServiceImpl(DetailsOrderRepository detailsOrderRepository) {
        this.detailsOrderRepository = detailsOrderRepository;
    }

    @Override
    public List<DetailsOrder> findAll() {
        return detailsOrderRepository.findAll();
    }

    @Override
    public DetailsOrder findById(Long id) {
        return detailsOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de orden no encontrado con ID: " + id));
    }

    @Override
    public DetailsOrder save(DetailsOrder detailsOrder) {
        return detailsOrderRepository.save(detailsOrder);
    }

    @Override
    public void deleteById(Long id) {
        detailsOrderRepository.deleteById(id);
    }

    @Override
    public DetailsOrder addDetailIntoOrder(Long id, Orders order) {
        return null; //Llenar logica
    }
    /*
    @Override
    public List<DetailsOrder> findByOrderId(Long orderId) {
        return detailsOrderRepository.findByOrder_Id(orderId);
    }*/
}
