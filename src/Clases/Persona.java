package Clases;

import java.util.UUID;

public abstract class Persona {
    private UUID id;
    private String nombre;
    private String apellido;
    private String email;
    private int dni;
    private String telefono;

    public Persona() {
        this.id = UUID.randomUUID();
    }

    public Persona(String telefono, int dni, String email, String apellido, String nombre) {
        this.id = UUID.randomUUID();
        this.telefono = telefono;
        this.dni = dni;
        this.email = email;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    // --- GETTER Y SETTER  ---
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return (nombre == null ? "" : nombre) + " " + (apellido == null ? "" : apellido);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", dni=" + dni +
                '}';
    }
}
