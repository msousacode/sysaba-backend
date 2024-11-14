package br.com.sysaba.modules.acesso.dto;

public class AuthDTO {
    public record LoginRequest(String username, String password) {
    }

    public record Response(String message, String token) {
    }
}
