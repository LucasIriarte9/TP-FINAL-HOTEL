import Controladores.Sistema;
import manejoJSON.Consola;
import org.json.JSONException;

public class Main {
    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        try {
            // 1. Cargamos el sistema.
            sistema.cargarSistema();

            // 2. Creamos la consola y le pasamos el sistema.
            Consola consola = new Consola(sistema);

            // 3. Iniciamos la consola. Ahora la llamada está DENTRO del try.
            consola.iniciar();

        } catch (JSONException e) {
            // Si ocurre un error en cargarSistema() O en iniciar(), será capturado aquí.
            System.err.println("Error fatal durante la ejecución: " + e.getMessage());
            e.printStackTrace();
        }
    }
}