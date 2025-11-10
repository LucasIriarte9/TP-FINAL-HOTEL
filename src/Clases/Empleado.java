package Clases;

public class Empleado extends Persona {
    private String cargo;

    public Empleado() {
        super();
    }

    public Empleado(String cargo) {
        super();
        this.cargo = cargo;
    }

    public Empleado(String telefono, int dni, String email, String apellido, String nombre, String cargo) {
        super(telefono, dni, email, apellido, nombre);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    @Override
    public String toString() {
        return "Empleado" + getNombreCompleto() +  " | Cargo: " + (cargo != null ? cargo : "N/A");
    }
}
