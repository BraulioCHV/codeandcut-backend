package org.code_cut.code_cutSpring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
}
