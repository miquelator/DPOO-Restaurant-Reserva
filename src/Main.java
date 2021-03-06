import Controlador.ActionListener.PrincipalController;
import Model.ConfigJson;
import Network.ComunicationServer;
import Vista.VistaPrincipal;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {

        ConfigJson configJson = null;

        try {
            File configFile = new File("resources/config.json");
            configJson = new Gson().fromJson(new FileReader(configFile), ConfigJson.class);

            VistaPrincipal vistaPrincipal = new VistaPrincipal();

            ComunicationServer comunicacioServer = new ComunicationServer(configJson);
            comunicacioServer.start();

            PrincipalController controller = new PrincipalController(vistaPrincipal, comunicacioServer);
            vistaPrincipal.setVisible(true);

        } catch (FileNotFoundException e) {
            System.err.println("El fichero \"config.json\" no ha sido encontrado.");
        }

    }
}
