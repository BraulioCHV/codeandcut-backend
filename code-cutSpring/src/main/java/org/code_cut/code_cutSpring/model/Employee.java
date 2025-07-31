package org.code_cut.code_cutSpring.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "idemployee", unique = true, nullable = false)
    private Long idEmployee;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name="last_name")
    private String lastname;
    @Column(nullable = false, name = "age")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "appointment_idappointment", nullable = false)
    @JsonIgnore
    private Appointment appointment;

}
