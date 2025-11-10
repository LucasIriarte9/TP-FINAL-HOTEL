package manejoJSON;

import Clases.Reserva;
import Clases.Habitacion;
import org.json.*;

import java.util.UUID;

public class ManejoJSONHabitacion {

    public static JSONObject toJSON(Habitacion h) throws JSONException {
        JSONObject habitacionJson = new JSONObject();
        habitacionJson.put("id", h.getId().toString());
        habitacionJson.put("numero", h.getNumero());
        habitacionJson.put("tipo", h.getTipo());
        habitacionJson.put("disponible", h.isDisponible());

        // se guarda solo los IDs de las reservas
        JSONArray reservasID = new JSONArray();
        for (Reserva r : h.getReservas()) {
            reservasID.put(r.getId().toString());
        }
        habitacionJson.put("reservasIds", reservasID);
        return habitacionJson;

    }

    public static Habitacion fromJSON(JSONObject habitacionJson) throws JSONException {
        String idString = habitacionJson.getString("id");
        int numero  = habitacionJson.getInt("numero");
        String tipo = habitacionJson.getString("tipo");
        boolean disponible = habitacionJson.getBoolean("disponible");

        // creamos el Objeto habitacion usando el constructor
        Habitacion h = new Habitacion(numero, tipo);

        // asigamos los valores restantes usando los setters
        h.setId(UUID.fromString(idString));
        h.setDisponible(disponible);

        return h;
    }

}
