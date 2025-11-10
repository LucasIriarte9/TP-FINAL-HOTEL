package manejoJSON;

import Clases.*;
import Controladores.Sistema;
import Enums.Rol;
import org.json.JSONException;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Consola {

    private final Sistema sistema;
    private final Scanner sc = new Scanner(System.in);

    public Consola(Sistema sistema) {
        this.sistema = sistema;
    }

    public void iniciar() throws JSONException {
        System.out.println("=== SISTEMA DE HOTEL (Consola) ===");

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> agregarHabitacion();
                case 3 -> agregarReserva();
                case 4 -> mostrarTodo();
                case 0 -> {
                    sistema.guardarSistema();
                    System.out.println("👋 Fin del programa.");
                    salir = true;
                }
                default -> System.out.println("⚠️ Opción inválida.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n1) Agregar Cliente");
        System.out.println("2) Agregar Habitación");
        System.out.println("3) Agregar Reserva");
        System.out.println("4) Mostrar Todo");
        System.out.println("0) Salir y Guardar");
    }

    private void agregarCliente() {
        System.out.println("\n--- Nuevo Cliente ---");
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        int dni = leerEntero("DNI: ");
        String email = leerTexto("Email: ");
        String telefono = leerTexto("Teléfono: ");
        String nacionalidad = leerTexto("Nacionalidad: ");

        Cliente nuevoCliente = new Cliente(telefono, dni, email, apellido, nombre, nacionalidad, false);
        sistema.agregarCliente(nuevoCliente);
        System.out.println("✅ Cliente agregado correctamente.");
    }

    private void agregarHabitacion() {
        System.out.println("\n--- Nueva Habitación ---");
        String numero = leerTexto("Número de Habitación: ");
        String tipo = leerTexto("Tipo (SIMPLE/DOBLE/SUITE): ");

        Habitacion nuevaHabitacion = new Habitacion(numero, tipo);
        sistema.agregarHabitacion(nuevaHabitacion);
        System.out.println("✅ Habitación agregada correctamente.");
    }

    private void agregarReserva() {
        System.out.println("\n--- Nueva Reserva ---");
        if (sistema.getClientes().isEmpty() || sistema.getHabitaciones().isEmpty()) {
            System.out.println("⚠️ Necesitás al menos un cliente y una habitación para crear una reserva.");
            return;
        }

        System.out.println("Clientes disponibles:");
        sistema.getClientes().forEach(c -> System.out.println("- " + c.getId() + ": " + c.getNombreCompleto()));
        // --- CORREGIDO ---
        UUID clienteId = UUID.fromString(leerTexto("Ingrese ID del Cliente: "));

        System.out.println("Habitaciones disponibles:");
        sistema.getHabitaciones().forEach(h -> System.out.println("- " + h.getId() + ": Habitación " + h.getNumero()));
        UUID habitacionId = UUID.fromString(leerTexto("Ingrese ID de la Habitación: "));

        LocalDate desde = LocalDate.parse(leerTexto("Fecha inicio (YYYY-MM-DD): "));
        LocalDate hasta = LocalDate.parse(leerTexto("Fecha fin (YYYY-MM-DD): "));

        String nombreCliente = sistema.buscarClientePorId(clienteId).map(Cliente::getNombreCompleto).orElse("N/A");
        String docCliente = sistema.buscarClientePorId(clienteId).map(c -> String.valueOf(c.getDni())).orElse("N/A");

        Reserva nuevaReserva = new Reserva(nombreCliente, docCliente, desde, hasta, habitacionId);
        sistema.agregarReserva(nuevaReserva);
        System.out.println("✅ Reserva creada correctamente.");
    }

    private void mostrarTodo() {
        System.out.println("\n=== CLIENTES ===");
        if(sistema.getClientes().isEmpty()) System.out.println("(sin datos)");
        else sistema.getClientes().forEach(System.out::println);

        System.out.println("\n=== HABITACIONES ===");
        if(sistema.getHabitaciones().isEmpty()) System.out.println("(sin datos)");
        else sistema.getHabitaciones().forEach(System.out::println);

        System.out.println("\n=== RESERVAS ===");
        if(sistema.getReservas().isEmpty()) System.out.println("(sin datos)");
        else sistema.getReservas().forEach(System.out::println);
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Ingrese un número válido.");
            }
        }
    }
}
