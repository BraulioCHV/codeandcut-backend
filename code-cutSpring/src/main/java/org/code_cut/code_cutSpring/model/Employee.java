package org.code_cut.code_cutSpring.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="employee")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmployee", unique = true, nullable = false)
    private Long idEmployee;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name="last_name")
    private String lastName;
    @Column(nullable = false, name = "age")
    private Integer age;

}
