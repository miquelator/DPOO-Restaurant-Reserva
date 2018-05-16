package Controlador.WindowAdapter;

import Vista.VistaPlats;
import Vista.VistaPrincipal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CartaWindowClosing extends WindowAdapter {
    private VistaPrincipal parent;
    private VistaPlats vistaPlats;

    public CartaWindowClosing(VistaPrincipal parent, VistaPlats vistaPlats) {
        this.parent = parent;
        this.vistaPlats = vistaPlats;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        vistaPlats.setVisible(false);
        parent.setVisible(true);
    }
}
