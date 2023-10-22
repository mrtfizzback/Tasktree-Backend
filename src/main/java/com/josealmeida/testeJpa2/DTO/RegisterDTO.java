package com.josealmeida.testeJpa2.DTO;

import com.josealmeida.testeJpa2.enums.UserRole;

public record RegisterDTO(String userName, String password,String email, UserRole role) {

}
