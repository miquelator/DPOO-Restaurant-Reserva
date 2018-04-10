import Model.GestioDades;
import Network.ComunicationServer;
import Vista.VistaPrincipal;

public class Main {

    public static void main(String[] args) {

        GestioDades gestioDades = new GestioDades();

        VistaPrincipal vistaPrincipal = new VistaPrincipal();

        ComunicationServer comunicacioServer = new ComunicationServer();
        comunicacioServer.start();


        Controlador.PrincipalController controller = new Controlador.PrincipalController(gestioDades,vistaPrincipal, comunicacioServer);

        vistaPrincipal.setVisible(true);



    }
}
