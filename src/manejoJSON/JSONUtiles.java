package manejoJSON;
import java.io.*;
import java.nio.file.*;

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

    /**
     * Lee un archivo JSON que contiene un objeto y lo devuelve como un JSONObject.
     * retorna Un JSONObject con el contenido, o uno vacío si el archivo no existe o falla la lectura.
     */
    public static JSONObject leerObjeto(String archivo) {
        try{
            String constent = new String(Files.readAllBytes(Paths.get(archivo)));
            if(constent.isEmpty()){
                return new JSONObject();
            }
            return new JSONObject(constent);
        } catch (NullPointerException e) {
            // El archivo no existe, es una situación normal la primera vez que se ejecuta.
            return new JSONObject();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new JSONObject(); // Devuelve vacío en caso de error.
        }
    }
}
