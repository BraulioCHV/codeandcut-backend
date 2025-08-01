package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.dto.DetailsOrderRequest;
import org.code_cut.code_cutSpring.model.DetailsOrder;
import org.code_cut.code_cutSpring.model.Products;
import org.code_cut.code_cutSpring.repository.DetailsOrderRepository;
import org.code_cut.code_cutSpring.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;
    @Autowired
    private DetailsOrderRepository detailsOrderRepository;

    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Products getProductById(int id) {
        return productsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El producto con el id "+id+" no existe")
        ) ;
    }

    @Override
    public Products deleteProductBtId(int id) {
        //Crear una varariable temporal para guardar el producto eliminado
        Products temp = null;
        //Usamos early return para evaluar si no existe el producto, en caso de que no exista, termina la ejecución en ese momento
        if(!productsRepository.existsById(id)) return temp;
        //Si el producto existe, guardamos el producto
        temp = productsRepository.findById(id).get();
        //Eliminamos el producto
        productsRepository.deleteById(id);
        //Retornamos el producto eliminado
        return temp;
    }

    @Override
    public Products addProduct(Products product) {
        return productsRepository.save(product);
    }

    @Override
    public Products updateProductById(int id, Products productUpdate) {
        Optional<Products> optionalProduct = productsRepository.findById(id);
        if(optionalProduct.isEmpty()) throw new IllegalArgumentException("El producto con el id "+id+" no existe");
        Products originalProduct = optionalProduct.get();
        if(productUpdate.getName() != null) originalProduct.setName(productUpdate.getName());
        if(productUpdate.getDescription() != null) originalProduct.setDescription(productUpdate.getDescription());
        if(productUpdate.getStock() != 0) originalProduct.setStock(productUpdate.getStock());
        if(productUpdate.getPrice() != null) originalProduct.setPrice(productUpdate.getPrice());
        return productsRepository.save(originalProduct);
    }

    @Override
    public Products addProductintoDetailOrders(int id, DetailsOrderRequest detailsOrderRequest) {
        Products product = productsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("El producto con el id "+id+" no existe"));
        DetailsOrder detailsOrder = detailsOrderRepository.findById(detailsOrderRequest.getIdDetailsOrder()).orElseThrow(() -> new IllegalArgumentException("El detalle del pedido "+detailsOrderRequest.getIdDetailsOrder()+" no existe"));
        product.setDetailsOrder(detailsOrder);
        return productsRepository.save(product);
    }
}
