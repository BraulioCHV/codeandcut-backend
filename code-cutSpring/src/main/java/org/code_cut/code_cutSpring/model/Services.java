package org.code_cut.code_cutSpring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "services")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idservices;
    @Column( nullable = false)
    private String name;
    @Column( nullable = false)
    private String description;
    @Column( nullable = false)
    private float price;


}