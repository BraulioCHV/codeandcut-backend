package org.code_cut.code_cutSpring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Entity
    @Table(name = "products")
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public class Products {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idProducts", unique = true,nullable = false)
        private Long id;
        @Column(nullable = false)
        private String name;
        @Column(nullable = false)
        private Float price;
        @Column(nullable = false)
        private String description;
        @Column(nullable = false)
        private int stock;
}
