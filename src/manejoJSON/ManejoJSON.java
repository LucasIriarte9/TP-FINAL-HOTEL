package manejoJSON;

import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ManejoJSON {

    private final Scanner sc = new Scanner(System.in);
    /*
     "data" es el json principal donde se guarda
     tiene tres arrays: personas, habitaciones y reservas.
    */
    private JSONObject data;
    private static final String ARCHIVO = "hotel.json"; // nombre del archivo donde se guarda el json

    public void iniciar() throws JSONException {
        // Acá intento leer el archivo JSON si ya existe. Si no está, se crea uno vacío.
        data = JSONUtiles.leerObjeto(ARCHIVO);

        // Si no había archivo o estaba vacío, inicializo las estructuras
        if (data.length() == 0) {
            System.out.println("📄 No se encontró archivo previo. Se creará uno nuevo.");
            data.put("personas", new JSONArray());
            data.put("habitaciones", new JSONArray());
            data.put("reservas", new JSONArray());
        } else {
            // Si el archivo existía, muestro cuántos datos cargó
            JSONArray personas = data.optJSONArray("personas");
            JSONArray habitaciones = data.optJSONArray("habitaciones");
            JSONArray reservas = data.optJSONArray("reservas");

            int cantPersonas = (personas != null) ? personas.length() : 0;
            int cantHabitaciones = (habitaciones != null) ? habitaciones.length() : 0;
            int cantReservas = (reservas != null) ? reservas.length() : 0;

            System.out.println("✅ Datos cargados correctamente desde " + ARCHIVO);
            System.out.println("   Personas: " + cantPersonas);
            System.out.println("   Habitaciones: " + cantHabitaciones);
            System.out.println("   Reservas: " + cantReservas);
        }

        // Menú principal del sistema (acá arranca la interacción)
        System.out.println("\n=== SISTEMA DE HOTEL ===");

        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1 -> menuPersonas();       // CRUD de personas
                case 2 -> menuHabitaciones();   // CRUD de habitaciones
                case 3 -> menuReservas();       // CRUD de reservas
                case 4 -> mostrarTodo();        // muestra el contenido actual del json
                case 5 -> guardarArchivo();     // guarda los cambios manualmente
                case 0 -> {
                    guardarArchivo(); // antes de salir, guarda todo automáticamente
                    System.out.println("👋 Fin del programa.");
                    salir = true;
                }
                default -> System.out.println("⚠️ Opción inválida.");
            }
        }
    }

    // Este método imprime el menú principal con todas las opciones disponibles
    private void mostrarMenuPrincipal() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1) Personas");
        System.out.println("2) Habitaciones");
        System.out.println("3) Reservas");
        System.out.println("4) Mostrar todo");
        System.out.println("5) Guardar JSON");
        System.out.println("0) Salir");
    }

    // Menú secundario de personas (agregar, editar, eliminar, listar)
    private void menuPersonas() throws JSONException {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENÚ PERSONAS ---");
            System.out.println("1) Agregar");
            System.out.println("2) Editar");
            System.out.println("3) Eliminar");
            System.out.println("4) Mostrar todas");
            System.out.println("0) Volver");
            int op = leerEntero("Opción: ");
            switch (op) {
                case 1 -> agregarPersona();
                case 2 -> editarElemento(data.getJSONArray("personas"), "persona");
                case 3 -> eliminarElemento(data.getJSONArray("personas"), "persona");
                case 4 -> mostrarArray(data.getJSONArray("personas"));
                case 0 -> volver = true;
                default -> System.out.println("⚠️ Opción inválida.");
            }
        }
    }

    // Lo mismo para habitaciones
    private void menuHabitaciones() throws JSONException {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENÚ HABITACIONES ---");
            System.out.println("1) Agregar");
            System.out.println("2) Editar");
            System.out.println("3) Eliminar");
            System.out.println("4) Mostrar todas");
            System.out.println("0) Volver");
            int op = leerEntero("Opción: ");
            switch (op) {
                case 1 -> agregarHabitacion();
                case 2 -> editarElemento(data.getJSONArray("habitaciones"), "habitación");
                case 3 -> eliminarElemento(data.getJSONArray("habitaciones"), "habitación");
                case 4 -> mostrarArray(data.getJSONArray("habitaciones"));
                case 0 -> volver = true;
                default -> System.out.println("⚠️ Opción inválida.");
            }
        }
    }

    // Y otro menú igual para reservas
    private void menuReservas() throws JSONException {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENÚ RESERVAS ---");
            System.out.println("1) Agregar");
            System.out.println("2) Editar");
            System.out.println("3) Eliminar");
            System.out.println("4) Mostrar todas");
            System.out.println("0) Volver");
            int op = leerEntero("Opción: ");
            switch (op) {
                case 1 -> agregarReserva();
                case 2 -> editarElemento(data.getJSONArray("reservas"), "reserva");
                case 3 -> eliminarElemento(data.getJSONArray("reservas"), "reserva");
                case 4 -> mostrarArray(data.getJSONArray("reservas"));
                case 0 -> volver = true;
                default -> System.out.println("⚠️ Opción inválida.");
            }
        }
    }

    // === PERSONAS ===
    private void agregarPersona() throws JSONException {
        // Acá creo un nuevo objeto JSON con los datos de la persona que el usuario ingrese
        JSONObject p = new JSONObject();
        System.out.println("\n--- Nueva Persona ---");
        p.put("id", leerTexto("ID: "));
        p.put("nombre", leerTexto("Nombre: "));
        p.put("apellido", leerTexto("Apellido: "));
        p.put("documento", leerTexto("Documento: "));
        p.put("email", leerTexto("Email: "));
        p.put("telefono", leerTexto("Teléfono: "));

        // Lo agrego al array de personas dentro del objeto general "data"
        data.getJSONArray("personas").put(p);
        System.out.println("✅ Persona agregada correctamente.");
    }

    // === HABITACIONES ===
    private void agregarHabitacion() throws JSONException {
        JSONObject h = new JSONObject();
        System.out.println("\n--- Nueva Habitación ---");
        h.put("id", leerTexto("ID: "));
        h.put("numero", leerEntero("Número: "));
        h.put("tipoHabitacion", leerTexto("Tipo (SIMPLE/DOBLE/SUITE): "));
        h.put("estado", leerTexto("Estado (DISPONIBLE/OCUPADA/MANTENIMIENTO): "));

        // Al igual que con personas, lo agrego dentro del array de habitaciones
        data.getJSONArray("habitaciones").put(h);
        System.out.println("✅ Habitación agregada correctamente.");
    }

    // === RESERVAS ===
    private void agregarReserva() throws JSONException {
        // Antes de crear una reserva, verifico que existan personas y habitaciones registradas
        JSONArray personas = data.getJSONArray("personas");
        JSONArray habitaciones = data.getJSONArray("habitaciones");

        if (personas.length() == 0 || habitaciones.length() == 0) {
            System.out.println("⚠️ Necesitás al menos una persona y una habitación para crear una reserva.");
            return;
        }

        JSONObject r = new JSONObject();
        System.out.println("\n--- Nueva Reserva ---");
        r.put("id", leerTexto("ID reserva: "));

        // Selección de persona para la reserva
        System.out.println("Personas disponibles:");
        for (int i = 0; i < personas.length(); i++) {
            JSONObject p = personas.getJSONObject(i);
            System.out.println("- " + p.getString("id") + ": " + p.getString("nombre") + " " + p.getString("apellido"));
        }
        r.put("personaId", leerTexto("Ingrese ID persona: "));

        // Selección de habitación
        System.out.println("Habitaciones disponibles:");
        for (int i = 0; i < habitaciones.length(); i++) {
            JSONObject h = habitaciones.getJSONObject(i);
            System.out.println("- " + h.getString("id") + ": Habitación " + h.getInt("numero") + " (" + h.getString("estado") + ")");
        }
        r.put("habitacionId", leerTexto("Ingrese ID habitación: "));

        // Fechas y estado de la reserva
        r.put("fechaInicio", leerTexto("Fecha inicio (YYYY-MM-DD): "));
        r.put("fechaFin", leerTexto("Fecha fin (YYYY-MM-DD): "));
        r.put("estado", leerTexto("Estado (CONFIRMADA/PENDIENTE/CANCELADA): "));

        // Agrego la reserva al array general
        data.getJSONArray("reservas").put(r);
        System.out.println("✅ Reserva creada correctamente.");
    }

    // === EDITAR ===
    private void editarElemento(JSONArray array, String tipo) throws JSONException {
        // Busco el elemento por su ID dentro del array correspondiente
        String id = leerTexto("Ingrese ID de la " + tipo + " a editar: ");
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.getString("id").equals(id)) {
                System.out.println("Objeto actual: " + obj.toString(4));
                System.out.println("Ingrese nuevos valores (deje vacío para mantener):");

                // Recorro cada campo del objeto y permito modificarlo
                Iterator<String> keys = obj.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    Object oldVal = obj.get(key);
                    String nuevo = leerTexto(key + " (" + oldVal + "): ");
                    if (!nuevo.isEmpty()) {
                        if (oldVal instanceof Integer) {
                            try {
                                obj.put(key, Integer.parseInt(nuevo));
                            } catch (NumberFormatException e) {
                                System.out.println("⚠️ Valor inválido, se mantiene el anterior.");
                            }
                        } else {
                            obj.put(key, nuevo);
                        }
                    }
                }

                System.out.println("✅ " + tipo + " editada correctamente.");
                return;
            }
        }
        System.out.println("⚠️ No se encontró la " + tipo + " con ID: " + id);
    }

    // === ELIMINAR ===
    private void eliminarElemento(JSONArray array, String tipo) throws JSONException {
        // Busco por ID y elimino directamente el objeto del array
        String id = leerTexto("Ingrese ID de la " + tipo + " a eliminar: ");
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj.getString("id").equals(id)) {
                array.remove(i);
                System.out.println("🗑️ " + tipo + " eliminada correctamente.");
                return;
            }
        }
        System.out.println("⚠️ No se encontró la " + tipo + " con ID: " + id);
    }

    // === MOSTRAR ===
    private void mostrarTodo() throws JSONException {
        // Muestra todo el contenido del JSON en consola con formato legible
        System.out.println("\n=== PERSONAS ===");
        mostrarArray(data.getJSONArray("personas"));
        System.out.println("\n=== HABITACIONES ===");
        mostrarArray(data.getJSONArray("habitaciones"));
        System.out.println("\n=== RESERVAS ===");
        mostrarArray(data.getJSONArray("reservas"));
    }

    private void mostrarArray(JSONArray array) throws JSONException {
        // Si está vacío aviso, si no, lo muestro con sangría para que se lea bien
        if (array.length() == 0) {
            System.out.println("(sin datos)");
            return;
        }
        for (int i = 0; i < array.length(); i++) {
            System.out.println(array.getJSONObject(i).toString(4));
        }
    }

    // === GUARDAR ===
    private void guardarArchivo() throws JSONException {
        // Este método usa JSONUtiles para guardar todo el objeto principal en un archivo .json
        JSONUtiles.grabarObjeto(data, ARCHIVO);
        System.out.println("💾 Archivo guardado correctamente en " + ARCHIVO);
    }

    // === INPUT HELPERS ===
    // Métodos auxiliares para leer texto y números del teclado.
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
