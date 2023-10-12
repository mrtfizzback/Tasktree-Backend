package com.josealmeida.testeJpa2.model.enums;

public enum UserRole {

    ADMIN("ADMIN"),
    FREE_USER("FREE_USER"),
    PREMIUM("PREMIUM");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
