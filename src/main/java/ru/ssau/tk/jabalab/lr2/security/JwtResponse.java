package ru.ssau.tk.jabalab.lr2.security;

public class JwtResponse {

    private String token;

    public JwtResponse() {}

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
