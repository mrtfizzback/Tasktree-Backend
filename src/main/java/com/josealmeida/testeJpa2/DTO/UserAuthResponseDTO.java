package com.josealmeida.testeJpa2.DTO;

public record UserAuthResponseDTO(String userName, String email, String roles, String accessToken) {
}
