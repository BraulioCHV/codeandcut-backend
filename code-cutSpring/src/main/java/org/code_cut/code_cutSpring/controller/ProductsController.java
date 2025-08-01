package org.code_cut.code_cutSpring.controller;

import lombok.AllArgsConstructor;
import org.code_cut.code_cutSpring.model.Products;
import org.code_cut.code_cutSpring.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
@AllArgsConstructor
public class ProductsController {
    private ProductsService productsService;

    @GetMapping // http://localhost:8080/api/products
    public List<Products> getAllProducts(){
        return this.productsService.getAllProducts();
    }

    @GetMapping(path = "{productId}")// http://localhost:8080/api/products
    public Products getProductById(@PathVariable("productId") int id){
        return this.productsService.getProductById(id);
    }

    @PostMapping // a la url que le hace la petición // http://localhost:8080/api/products
    public Products addProducts(@RequestBody Products products){
        return this.productsService.addProduct(products);
    }
    @DeleteMapping(path = "{productId}")
    public Products deleteProductById(@PathVariable("productId") int id){
        return this.productsService.deleteProductBtId(id);
    }

    @PutMapping(path = "{productId}") // http://localhost:8080/api/products/2
    public Products updateProductById(@PathVariable("productId") int id, @RequestBody Products productUpdated){
        return this.productsService.updateProductById(id, productUpdated);
    }


}
