package manejoJSON;

import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ManejoJSON {

    private final Scanner sc = new Scanner(System.in);
    private final JSONObject data = new JSONObject();
    private static final String ARCHIVO = "hotel.json";

    public void iniciar() throws JSONException {
        // inicializa arrays vacíos
        data.put("personas", new JSONArray());
        data.put("habitaciones", new JSONArray());
        data.put("reservas", new JSONArray());

        System.out.println("=== SISTEMA DE HOTEL ===");

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1 -> agregarPersona();
                case 2 -> agregarHabitacion();
                case 3 -> agregarReserva();
                case 4 -> mostrarTodo();
                case 5 -> guardarArchivo();
                case 0 -> {
                    guardarArchivo();
                    System.out.println("👋 Fin del programa.");
                    salir = true;
                }
                default -> System.out.println("⚠️ Opción inválida.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n1) Agregar persona");
        System.out.println("2) Agregar habitación");
        System.out.println("3) Agregar reserva");
        System.out.println("4) Mostrar todo");
        System.out.println("5) Guardar archivo JSON");
        System.out.println("0) Salir");
    }

    // === PERSONAS ===
    private void agregarPersona() throws JSONException {
        System.out.println("\n--- Nueva Persona ---");
        JSONObject p = new JSONObject();
        p.put("id", leerTexto("ID: "));
        p.put("nombre", leerTexto("Nombre: "));
        p.put("apellido", leerTexto("Apellido: "));
        p.put("documento", leerTexto("Documento: "));
        p.put("email", leerTexto("Email: "));
        p.put("telefono", leerTexto("Teléfono: "));
        data.getJSONArray("personas").put(p);
        System.out.println("✅ Persona agregada correctamente.");
    }

    // === HABITACIONES ===
    private void agregarHabitacion() throws JSONException {
        System.out.println("\n--- Nueva Habitación ---");
        JSONObject h = new JSONObject();
        h.put("id", leerTexto("ID: "));
        h.put("numero", leerEntero("Número: "));
        h.put("tipoHabitacion", leerTexto("Tipo (SIMPLE/DOBLE/SUITE): "));
        h.put("estado", leerTexto("Estado (DISPONIBLE/OCUPADA/MANTENIMIENTO): "));
        data.getJSONArray("habitaciones").put(h);
        System.out.println("✅ Habitación agregada correctamente.");
    }

    // === RESERVAS ===
    private void agregarReserva() throws JSONException {
        System.out.println("\n--- Nueva Reserva ---");

        JSONArray personas = data.getJSONArray("personas");
        JSONArray habitaciones = data.getJSONArray("habitaciones");

        if (personas.length() == 0 || habitaciones.length() == 0) {
            System.out.println("⚠️ Necesitás al menos una persona y una habitación para crear una reserva.");
            return;
        }

        JSONObject r = new JSONObject();
        r.put("id", leerTexto("ID reserva: "));

        System.out.println("Personas disponibles:");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject p = personas.getJSONObject(i);
            System.out.println("- " + p.getString("id") + ": " + p.getString("nombre") + " " + p.getString("apellido"));
        }
        r.put("personaId", leerTexto("Ingrese ID persona: "));

        System.out.println("Habitaciones disponibles:");
        for (int i = 0; i < habitaciones.length(); i++) {
            JSONObject h = habitaciones.getJSONObject(i);
            System.out.println("- " + h.getString("id") + ": Habitación " + h.getInt("numero") + " (" + h.getString("estado") + ")");
        }
        r.put("habitacionId", leerTexto("Ingrese ID habitación: "));

        r.put("fechaInicio", leerTexto("Fecha inicio (YYYY-MM-DD): "));
        r.put("fechaFin", leerTexto("Fecha fin (YYYY-MM-DD): "));
        r.put("estado", leerTexto("Estado (CONFIRMADA/PENDIENTE/CANCELADA): "));

        data.getJSONArray("reservas").put(r);
        System.out.println("✅ Reserva creada correctamente.");
    }

    // === MOSTRAR ===
    private void mostrarTodo() throws JSONException {
        System.out.println("\n=== PERSONAS ===");
        mostrarArray(data.getJSONArray("personas"));
        System.out.println("\n=== HABITACIONES ===");
        mostrarArray(data.getJSONArray("habitaciones"));
        System.out.println("\n=== RESERVAS ===");
        mostrarArray(data.getJSONArray("reservas"));
    }

    private void mostrarArray(JSONArray array) throws JSONException {
        if (array.length() == 0) {
            System.out.println("(sin datos)");
            return;
        }
        for (int i = 0; i < array.length(); i++) {
            System.out.println(array.getJSONObject(i).toString(4));
        }
    }

    // === GUARDAR ===
    private void guardarArchivo() {
        JSONUtiles.grabarObjeto(data, ARCHIVO);
        System.out.println("💾 Archivo guardado correctamente en " + ARCHIVO);
    }

    // === INPUT HELPERS ===
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
