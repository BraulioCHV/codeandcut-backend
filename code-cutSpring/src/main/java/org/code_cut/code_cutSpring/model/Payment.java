package org.code_cut.code_cutSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpayment")
    private int id;
    @Column( nullable = false)
    private int typepayment;
    @Column(nullable = false)
    private String status;


//llave foranea - uno a uno - Orders
    @OneToOne
    @JoinColumn(name = "orders_idorder", nullable = true)
//evitar ciclo infinito
    @JsonIgnore
    private Orders orders;


}
