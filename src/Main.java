import Controlador.VistaPrincipalController;
import Model.GestioDades;
import Vista.VistaPrincipal;

public class Main {

    public static void main(String[] args) {

        GestioDades gestioDades = new GestioDades();

        VistaPrincipal vistaPrincipal = new VistaPrincipal();

        VistaPrincipalController controller = new VistaPrincipalController(gestioDades,vistaPrincipal);


        vistaPrincipal.setVisible(true);


    }
}
