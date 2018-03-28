import Controlador.VistaPrincipalController;
import Model.GestioDades;
import Network.ComunicacioServer;
import Vista.VistaPlats;
import Vista.VistaPrincipal;

public class Main {

    public static void main(String[] args) {

        GestioDades gestioDades = new GestioDades();

        VistaPrincipal vistaPrincipal = new VistaPrincipal();

        VistaPlats vistaPlats = new VistaPlats();




        ComunicacioServer comunicacioServer = new ComunicacioServer();
        comunicacioServer.start();


        VistaPrincipalController controller = new VistaPrincipalController(gestioDades,vistaPrincipal, vistaPlats, comunicacioServer);

        vistaPrincipal.setVisible(true);



    }
}
