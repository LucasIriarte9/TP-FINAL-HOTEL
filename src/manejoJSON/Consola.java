package manejoJSON;

import Clases.*;
import Controladores.Sistema;
import Enums.Rol;
import org.json.JSONException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.Optional;
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
        int numero = leerEntero("Número de Habitación: ");
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
        sistema.getClientes().forEach(c -> System.out.println("- DNI: " + c.getDni() + " | " + c.getNombreCompleto()));
        // --- CORREGIDO ---

        int dni = leerEntero("Ingrese el DNI del cliente: "); // pide el dni para realizar la reserva
        // Buscar cliente por DNI
        Optional<Cliente> clienteOpt = sistema.buscarClientePorDni(dni);//optional esuna clase de java para manejar valores que pueden ser nulos
        if (clienteOpt.isEmpty()) {
            System.out.println("❌ No se encontró un cliente con ese DNI.");
            return;
        }
        Cliente cliente = clienteOpt.get();

        /// muestra listado de habitaciones
        System.out.println("Habitaciones disponibles:");
        sistema.getHabitaciones().forEach(h -> System.out.println("Tipo de habitacion " + h.getTipo() + " | Numero " + h.getNumero()));

        /// pide numero de habitacion a reservar
        int numHabitacion = leerEntero("Ingrese el numero de habitacion: ");

        // Buscar habitación por número
        Optional<Habitacion> habitacionOpt = sistema.buscarHabitacionPorNumero(numHabitacion);
        if (habitacionOpt.isEmpty()) {
            System.out.println("❌ No se encontró una habitación con ese número.");
            return;
        }
        Habitacion habitacion = habitacionOpt.get();

        LocalDate desde = null;
        LocalDate hasta = null;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

// --- Leer fecha de inicio ---
        while (true) {
            try {
                String texto = leerTexto("Fecha inicio (YYYY-MM-DD): ");
                desde = LocalDate.parse(texto, formato);
                break; // correcta → salir del bucle
            } catch (DateTimeParseException e) {
                System.out.println("❌ Formato inválido. Ingrese la fecha con formato YYYY-MM-DD (por ejemplo, 2025-11-10).");
            }
        }

// --- Leer fecha de fin ---
        while (true) {
            try {
                String texto = leerTexto("Fecha fin (YYYY-MM-DD): ");
                hasta = LocalDate.parse(texto, formato);

                if (hasta.isBefore(desde)) {
                    System.out.println("❌ La fecha de fin no puede ser anterior a la de inicio.");
                } else {
                    break; // fecha válida → salir del bucle
                }
            } catch (DateTimeParseException e) {
                System.out.println("❌ Formato inválido. Ingrese la fecha con formato YYYY-MM-DD (por ejemplo, 2025-11-15).");
            }
        }



        Reserva nuevaReserva = new Reserva(cliente.getNombreCompleto(), String.valueOf(cliente.getDni()), desde, hasta, habitacion.getId());
        sistema.agregarReserva(nuevaReserva);
        System.out.println("✅ Reserva creada correctamente.");
    }
/// MOSTRAR
    private void mostrarTodo() {
        System.out.println("\n=== CLIENTES ===");
        if (sistema.getClientes().isEmpty()) {
            System.out.println("(sin datos)");
        } else {
            sistema.getClientes().forEach(c -> {
                System.out.println("---------------------------");
                System.out.println("Nombre completo: " + c.getNombreCompleto());
                System.out.println("DNI: " + c.getDni());
                System.out.println("Email: " + c.getEmail());
                System.out.println("Nacionalidad: " + c.getNacionalidad());
            });
        }

        System.out.println("\n=== HABITACIONES ===");
        if (sistema.getHabitaciones().isEmpty()) {
            System.out.println("(sin datos)");
        } else {
            sistema.getHabitaciones().forEach(h -> {
                System.out.println("---------------------------");
                System.out.println("Número: " + h.getNumero());
                System.out.println("Tipo: " + h.getTipo());
            });
        }

        System.out.println("\n=== RESERVAS ===");

        if (sistema.getReservas().isEmpty()) {
            System.out.println("(sin datos)");
        } else {
            sistema.getReservas().forEach(r -> {
                // Buscamos la habitación para obtener su número
                Optional<Habitacion> habOpt = sistema.buscarHabitacionPorId(r.getHabitacionId());
                String numHabitacion = habOpt.map(h -> String.valueOf(h.getNumero())).orElse("N/A");

                System.out.println("---------------------------");
                System.out.println("Cliente: " + r.getNombreReservante() + " (DNI: " + r.getDocumento() + ")");
                System.out.println("Habitación: " + numHabitacion);
                System.out.println("Desde: " + r.getDesde());
                System.out.println("Hasta: " + r.getHasta());
            });
        }
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
