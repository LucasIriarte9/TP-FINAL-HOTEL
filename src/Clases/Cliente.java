package Clases;

public class Cliente extends Persona{
    private String nacionalidad;
    private boolean esFrecuente;

    public Cliente(){
        super();
    }

    public Cliente(String telefono, int dni, String email, String apellido, String nombre, String nacionalidad, boolean esFrecuente) {
        super(telefono, dni, email, apellido, nombre);
        this.nacionalidad = nacionalidad;
        this.esFrecuente = esFrecuente;
    }

    // Getters y Setters (sin cambios)
    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public boolean isEsFrecuente() {
        return esFrecuente;
    }

    public void setEsFrecuente(boolean esFrecuente) {
        this.esFrecuente = esFrecuente;
    }

    @Override
    public String toString() {
        return super.toString() + ", Nacionalidad: " + (nacionalidad != null ? nacionalidad : "N/A");
    }
}
