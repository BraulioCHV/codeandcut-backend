package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.DetailsOrder;
import java.util.List;

public interface DetailsOrderService {
    List<DetailsOrder> findAll();
    DetailsOrder findById(Long id);
    DetailsOrder save(DetailsOrder detailsOrder);
    void deleteById(Long id);
    List<DetailsOrder> findByOrderId(Long orderId);
}
