package manejoJSON;

import Clases.Cliente;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.UUID; // Importar UUID

public class ManejoJSONCliente {

    public static JSONObject toJSON(Cliente cliente) throws JSONException {
        JSONObject clienteJson = new JSONObject();

        // --- CORREGIDO ---
        clienteJson.put("id", cliente.getId().toString()); // Convertir UUID a String
        clienteJson.put("nombre", cliente.getNombre());
        clienteJson.put("apellido", cliente.getApellido());
        clienteJson.put("email", cliente.getEmail());
        clienteJson.put("dni", cliente.getDni());
        clienteJson.put("telefono", cliente.getTelefono());
        clienteJson.put("nacionalidad", cliente.getNacionalidad());
        clienteJson.put("esFrecuente", cliente.isEsFrecuente());

        return clienteJson;
    }

    public static Cliente fromJSON(JSONObject json) throws JSONException {
        // --- CORREGIDO ---
        UUID id = UUID.fromString(json.getString("id")); // Leer String y convertir a UUID
        String nombre = json.getString("nombre");
        String apellido = json.getString("apellido");
        String email = json.getString("email");
        int dni = json.getInt("dni");
        String telefono = json.getString("telefono");
        String nacionalidad = json.getString("nacionalidad");
        boolean esFrecuente = json.getBoolean("esFrecuente");

        Cliente cliente = new Cliente(
                telefono,
                dni,
                email,
                apellido,
                nombre,
                nacionalidad,
                esFrecuente
        );
        
        cliente.setId(id); // Asignamos el ID leído del JSON

        return cliente;
    }
}
