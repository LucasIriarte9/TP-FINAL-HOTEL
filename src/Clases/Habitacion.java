package Clases;



import Interfaz.Reservable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Habitacion implements Reservable {
    private UUID id;
    private int numero;
    private String tipo; // sencillo, doble, suite, etc.
    private boolean disponible; // estado general (ej limpieza/reparacion)
    private List<Reserva> reservas = new ArrayList<>();



    public Habitacion() {
        this.id = UUID.randomUUID();
        this.disponible = true;
    }

    public Habitacion(int numero, String tipo) {
        this();
        this.numero = numero;
        this.tipo = tipo;
    }



    // getters/setters...

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void agregarReserva(Reserva r) { reservas.add(r); }
    public List<Reserva> getReservas() { return reservas; }


    @Override
    public boolean isDisponible(LocalDate desde, LocalDate hasta) {
        if (!disponible) return false;
        for (Reserva r : reservas) {
            if (r.seSolapa(desde, hasta)) return false;
        }
        return true;
    }
}