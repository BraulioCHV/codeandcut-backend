package org.code_cut.code_cutSpring.model;


import jakarta.persistence.*;
        import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "appoitment")

public class Appoitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCita;

    @Column(name = "FechaHora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "Status", length = 45)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    private User usuario; // Asumiendo que tu clase de usuario se llama User

    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ServicioCita> serviciosCita;

    // Getters y Setters
}