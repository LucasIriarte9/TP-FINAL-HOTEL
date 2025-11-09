package Clases;

public abstract class Persona {
        private int id;
        private String nombre;
        private String apellido;
        private String email;
        private int dni;
        private String telefono;

    public Persona() {
    }

    public Persona(int id, String telefono, int dni, String email, String apellido, String nombre) {
        this.id = id;
        this.telefono = telefono;
        this.dni = dni;
        this.email = email;
        this.apellido = apellido;
        this.nombre = nombre;
    }

/// CONSTRUCTORES


    /// SET Y GET
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    ///  metodos
    public String getNombreCompleto() {
        return (nombre == null ? "" : nombre) + " " + (apellido == null ? "" : apellido);
    }

    /// MOSTRAR
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
