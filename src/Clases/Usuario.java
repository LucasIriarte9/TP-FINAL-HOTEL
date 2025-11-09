package Clases;

import Enums.Rol;

import java.util.UUID;

public class Usuario {
    private UUID id;
    private String username;
    private String passwordHash;
    private Rol rol;
    private boolean activo;

    public Usuario() { this.id = UUID.randomUUID(); }

    public Usuario(String username, String passwordHash, Rol rol, boolean activo) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.activo = activo;
    }

    public boolean validarPassword(String raw) {
        // Ejemplo simple: comparar strings (en real: hash + salt)
        return passwordHash != null && passwordHash.equals(raw);
    }

    // Getters/Setters...
    public UUID getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}