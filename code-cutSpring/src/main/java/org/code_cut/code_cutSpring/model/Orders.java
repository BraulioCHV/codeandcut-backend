package org.code_cut.code_cutSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Orders {
    //Id autoincrementable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorder")
    private long id;
    //Columna address
    @Column(nullable = false)
    private String address;
    //Columna totalAmount
    @Column(name = "totalamount", nullable = false)
    private Double totalAmount;

    //Realcion tablas - usuario llave foranea
    @ManyToOne
    @JoinColumn(name = "users_iduser", nullable = false)
    //Evitar ciclo infinito
    @JsonIgnore
    private User user;
    //Relacion Inversa - Payment - Uno a uno
    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL,orphanRemoval = true)
    private Payment payment;
    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL,orphanRemoval = true)
    private DetailsOrder detailsOrder;
    public void addPayment(Payment payment) {
        this.payment = payment;
        payment.setOrders(this);
    }

}