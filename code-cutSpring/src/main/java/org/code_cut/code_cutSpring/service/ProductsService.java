package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.dto.DetailsOrderRequest;
import org.code_cut.code_cutSpring.model.Products;

import java.util.List;

public interface ProductsService {
    List<Products> getAllProducts();
    Products getProductById(int id);
    Products deleteProductBtId(int id);
    Products addProduct(Products product);
    Products updateProductById(int id, Products productUpdate);
    Products addProductintoDetailOrders(int id, DetailsOrderRequest detailsOrderRequest);
}
