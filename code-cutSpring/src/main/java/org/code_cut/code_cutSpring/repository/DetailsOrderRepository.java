package org.code_cut.code_cutSpring.repository;

import org.code_cut.code_cutSpring.model.DetailsOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailsOrderRepository extends JpaRepository<DetailsOrder, Long> {
    List<DetailsOrder> findByOrderid(Long idOrder);

}
