package manejoJSON;

import Clases.Usuario;
import Enums.Rol;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class ManejoJSONUsuario {
    public static JSONObject toJSON(Usuario u) throws JSONException {
        JSONObject usuarioJson = new JSONObject();
        usuarioJson.put("id", u.getId().toString());
        usuarioJson.put("username", u.getUsername());
        usuarioJson.put("passwordHash", u.getPasswordHash());
        usuarioJson.put("activo", u.isActivo());

        //convertimos el enum a String usuando su nombre
        usuarioJson.put("rol", u.getRol().name());

        return usuarioJson;
    }

    public static  Usuario fromJSON(JSONObject usuarioJson) throws JSONException {
        String idString = usuarioJson.getString("id");
        String username  = usuarioJson.getString("username");
        String passwordHash = usuarioJson.getString("passwordHash");
        boolean activo = usuarioJson.getBoolean("activo");

        //lee el rol como string y lo convierte en enum
        String rolString = usuarioJson.getString("rol");
        Rol rol = Rol.valueOf(rolString);

        //creamos el obj Usuario usando el contructor
        Usuario u = new Usuario(
                username,
                passwordHash,
                rol,
                activo
        );
        //asigna ID
        u.setId(UUID.fromString(idString));

        return u;
    }
}
