package Clases;

public class Empleado extends Persona {
    private String cargo;

    public Empleado() {
    }

    public Empleado(String cargo) {
        this.cargo = cargo;
    }

    public Empleado(int id, String telefono, int dni, String email, String apellido, String nombre, String cargo) {
        super(id, telefono, dni, email, apellido, nombre);
        this.cargo = cargo;
    }


}
