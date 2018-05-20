package Controlador.WindowAdapter;

import Vista.StatusView;
import Vista.VistaPrincipal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class to control what to do when the order status window closes
 */
public class StatusWindowClosing extends WindowAdapter {
    private VistaPrincipal parent;
    private StatusView statusView;

    public StatusWindowClosing(StatusView statusView, VistaPrincipal parent) {
        this.statusView = statusView;
        this.parent = parent;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        statusView.setVisible(false);
        parent.setVisible(true);
    }
}
