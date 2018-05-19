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
    }

    /***
     * This method decides whether to set the view as visible or stop depending depending whether the user is authenticated or not
     * @param e WindowEvent
     */
    @Override
    public void windowClosing(WindowEvent e) {


        if(!autenticat){
            System.exit(0);

        }else{
            vistaPrincipal.setVisible(true);
        }



    }
}
