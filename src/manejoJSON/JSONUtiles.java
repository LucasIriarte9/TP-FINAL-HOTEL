package manejoJSON;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONUtiles {

    public static void grabar(JSONArray array, String nombre) {
        try {
            FileWriter file = new FileWriter(nombre);
            file.write(array.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void grabarObjeto(JSONObject objeto, String rutaArchivo) {
        try (FileWriter file = new FileWriter(rutaArchivo)) {
            file.write(objeto.toString(4)); // el 4 agrega sangría y formato legible
            file.flush();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


    public static JSONTokener leer(String archivo) {
        JSONTokener tokener = null;

        try {
            tokener = new JSONTokener(new FileReader(archivo));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tokener;
    }

    public static JSONObject leerObjeto(String archivo) {
        try {
            JSONTokener tokener = new JSONTokener(new FileReader(archivo));
            return new JSONObject(tokener);
        } catch (FileNotFoundException e) {
            return new JSONObject(); // devuelve uno vacío si no existe
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }
}