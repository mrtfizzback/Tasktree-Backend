package com.josealmeida.testeJpa2.DTO;

public record UserAuthResponse(String userName, String email, String roles, String accessToken) {
}
