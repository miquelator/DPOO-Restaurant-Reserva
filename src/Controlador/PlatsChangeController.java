package Controlador;

import Network.ComunicationServer;
import Vista.VistaPlats;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlatsChangeController  implements ChangeListener {
    private VistaPlats vistaPlats;
    private ComunicationServer comunicacio;

    public PlatsChangeController(VistaPlats vistaPlats, ComunicationServer comunicacio) {
        this.vistaPlats = vistaPlats;
        this.comunicacio = comunicacio;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        switch (vistaPlats.getSelectedTab()){
            case 0:
                vistaPlats.drawInfo(comunicacio.veureCarta(1), 0);
                break;
            case 1:
                vistaPlats.drawInfo(comunicacio.veureCarta(2), 1);
                break;
            case 2:
                vistaPlats.drawInfo(comunicacio.veureCarta(3), 2);
                break;
            case 3:
                vistaPlats.drawInfo(comunicacio.veureCarta(4), 3);
                break;
        }
    }
}
