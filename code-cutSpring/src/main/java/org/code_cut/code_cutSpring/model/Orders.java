package org.code_cut.code_cutSpring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Orders {
    //Id autoincrementable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private long id;
    //Columna address
    @Column(nullable = false)
    private String address;
    //Columna totalAmount
    @Column(name = "totalamount", nullable = false)
    private Double totalAmount;

}