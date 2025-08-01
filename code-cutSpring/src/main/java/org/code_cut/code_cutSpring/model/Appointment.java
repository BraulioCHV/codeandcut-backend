package org.code_cut.code_cutSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name="appointment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idappointment")
    //modificacion de lomg a integer; el tipo de dato INT de una base de datos corresponde directamente con el tipo Integer
    private Integer idAppointment;

    @Column  (name = "datehour", nullable = false)
    private LocalDateTime dateHour;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_iduser", nullable = false)
    @JsonIgnore
    //@Column
    private User user;
    //Mapear Services
    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Services> services;
    //Mapear Empleados
    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employee;



}