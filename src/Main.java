import manejoJSON.ManejoJSON;
import org.json.JSONException;

public class Main {
    public static void main(String[] args) {
        ManejoJSON app = new ManejoJSON();
        try {
            app.iniciar();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}