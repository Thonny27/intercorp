package com.dcompras.gamarra.domain;

import lombok.Data;

import java.util.List;

@Data
public class Usuario {
    private int id;
    private String username;
    private String password;
    private boolean enabled;
    List<Role> roles;
}
