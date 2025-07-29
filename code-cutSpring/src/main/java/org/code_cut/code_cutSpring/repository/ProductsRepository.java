package org.code_cut.code_cutSpring.repository;

import org.code_cut.code_cutSpring.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Integer> {

}
