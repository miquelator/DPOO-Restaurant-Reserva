package Controlador.WindowAdapter;

import Vista.VistaPlats;
import Vista.VistaPrincipal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReservaWindowClosing extends WindowAdapter {
    private boolean autenticat;
    private VistaPrincipal vistaPrincipal;

    public ReservaWindowClosing(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        autenticat = false;
    }

    public void setAutenticat(boolean autenticat) {
        this.autenticat = autenticat;
        System.out.print("Setejo autenticat a"+autenticat);
    }

    @Override
    public void windowClosing(WindowEvent e) {


        if(!autenticat){
            System.exit(0);

        }else{
            vistaPrincipal.setVisible(true);
        }



    }
}
