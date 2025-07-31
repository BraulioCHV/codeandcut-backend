package org.code_cut.code_cutSpring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table (name="appointment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAppointment")
    private Long id;

    @Column  (name = "DateHour", nullable = false)
    private LocalDateTime dateHour;

    @Column(name = "Status", nullable = false, length = 45)
    private String status;

//    @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "user_idUser", nullable = false)
//  @Column
    //  private User user;

}