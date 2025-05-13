package br.com.fiap.shoexpress_api.model;

public record Token(
    String token,
    String type,
    String email
) {}
