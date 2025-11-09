package Clases;

public class Cliente extends Persona{
    private Habitacion habitacion;

    public Cliente(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Cliente(int id, String telefono, int dni, String email, String apellido, String nombre, Habitacion habitacion) {
        super(id, telefono, dni, email, apellido, nombre);
        this.habitacion = habitacion;
    }

    public Cliente() {
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
