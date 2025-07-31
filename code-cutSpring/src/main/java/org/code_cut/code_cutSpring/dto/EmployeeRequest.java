package org.code_cut.code_cutSpring.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
    private Long idEmployee;
    private String name;
    private String lastName;
    private Integer age;
}
