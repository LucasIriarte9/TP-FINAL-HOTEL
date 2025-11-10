package Clases;

import java.time.LocalDate;
import java.util.UUID;

public class Reserva {
    private UUID id;
    private String nombreReservante;
    private String documento;
    private LocalDate desde;
    private LocalDate hasta;
    private UUID habitacionId;
    private boolean cancelada;

    public Reserva() { this.id = UUID.randomUUID(); }

    public Reserva(String nombreReservante, String documento, LocalDate desde, LocalDate hasta, UUID habitacionId) {
        this();
        this.nombreReservante = nombreReservante;
        this.documento = documento;
        this.desde = desde;
        this.hasta = hasta;
        this.habitacionId = habitacionId;
        this.cancelada = false;
    }

    public boolean seSolapa(LocalDate dDesde, LocalDate dHasta) {
        // solapan si no (uno termina antes que empiece el otro)
        return !(this.hasta.isBefore(dDesde) || this.desde.isAfter(dHasta));
    }

    // getters/setters...

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombreReservante() {
        return nombreReservante;
    }

    public void setNombreReservante(String nombreReservante) {
        this.nombreReservante = nombreReservante;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public LocalDate getDesde() {
        return desde;
    }

    public void setDesde(LocalDate desde) {
        this.desde = desde;
    }

    public LocalDate getHasta() {
        return hasta;
    }

    public void setHasta(LocalDate hasta) {
        this.hasta = hasta;
    }

    public UUID getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(UUID habitacionId) {
        this.habitacionId = habitacionId;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }
}
