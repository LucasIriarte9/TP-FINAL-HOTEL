package manejoJSON;

import Clases.Empleado;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.UUID; // Importar UUID

public class ManejoJSONEmpleado {
    
    public static JSONObject toJSON(Empleado empleado) throws JSONException {
        JSONObject empleadoJson = new JSONObject();

        // --- CORREGIDO ---
        empleadoJson.put("id", empleado.getId().toString()); // Convertir UUID a String
        empleadoJson.put("nombre", empleado.getNombre());
        empleadoJson.put("apellido", empleado.getApellido());
        empleadoJson.put("email", empleado.getEmail());
        empleadoJson.put("dni", empleado.getDni());
        empleadoJson.put("telefono", empleado.getTelefono());
        empleadoJson.put("cargo", empleado.getCargo());

        return empleadoJson;
    }
    
    public static Empleado fromJSON(JSONObject json) throws JSONException {
        // --- CORREGIDO ---
        UUID id = UUID.fromString(json.getString("id")); // Leer String y convertir a UUID
        String nombre = json.getString("nombre");
        String apellido = json.getString("apellido");
        String email = json.getString("email");
        int dni = json.getInt("dni");
        String telefono = json.getString("telefono");
        String cargo = json.getString("cargo");

        Empleado empleado = new Empleado(
                telefono,
                dni,
                email,
                apellido,
                nombre,
                cargo
        );
        
        empleado.setId(id); // Asignamos el ID leído del JSON

        return empleado;
    }
}
