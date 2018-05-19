import Controlador.ActionListener.PrincipalController;
import Model.ConfigJson;
import Model.GestioDades;
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


            GestioDades gestioDades = new GestioDades();

        VistaPrincipal vistaPrincipal = new VistaPrincipal();


        ComunicationServer comunicacioServer = new ComunicationServer(configJson);
        comunicacioServer.start();


        PrincipalController controller = new PrincipalController(gestioDades,vistaPrincipal, comunicacioServer);

        vistaPrincipal.setVisible(true);

        } catch (FileNotFoundException e) {
            System.err.println("El fichero \"config.json\" no ha sido encontrado.");
        }

    }
}
