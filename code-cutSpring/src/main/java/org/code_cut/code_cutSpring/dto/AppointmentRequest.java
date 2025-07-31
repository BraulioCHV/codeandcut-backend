package org.code_cut.code_cutSpring.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentRequest {
    private Long id;
    private LocalDateTime dateHour;
    private String status;
}
