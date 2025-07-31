package org.code_cut.code_cutSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detailsorder")
public class DetailsOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetailsorder")
    private Long id;

    @Column(name = "qtyproduct", nullable = false)
    private int qtyProduct;

    // Relación One-to-One con Orders
    @OneToOne
    @JoinColumn(name = "orders_idorder", nullable = false)
    @JsonIgnore
    private Orders orders;

    // Relación muchos a uno con Producto
    @OneToMany(mappedBy = "detailsOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Products> products;



}