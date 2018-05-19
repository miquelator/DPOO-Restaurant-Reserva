package Controlador.ChangeListener;

import Controlador.ActionListener.PlatsController;
import Network.ComunicationServer;
import Vista.VistaPlats;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class PlatsChangeController  implements ChangeListener {
    private VistaPlats vistaPlats;
    private ComunicationServer comunicacio;
    private PlatsController platsController;

    public PlatsChangeController(VistaPlats vistaPlats, ComunicationServer comunicacio, PlatsController platsController) {
        this.vistaPlats = vistaPlats;
        this.comunicacio = comunicacio;
        this.platsController = platsController;
    }


    /***
     * This method changes the current dish panel depending on which tab has been pressed
     * @param e Change event
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        switch (vistaPlats.getSelectedTab()){
            case 0:
                ArrayList<Model.Carta> carta = comunicacio.veureCarta(1);
                vistaPlats.drawInfo(carta, 0);
                platsController.setCurrentCarta(carta);
                break;
            case 1:
                carta = comunicacio.veureCarta(2);
                vistaPlats.drawInfo(carta, 1);
                platsController.setCurrentCarta(carta);
                break;
            case 2:
                carta = comunicacio.veureCarta(3);
                vistaPlats.drawInfo(carta, 2);
                platsController.setCurrentCarta(carta);
                break;
            case 3:
                carta = comunicacio.veureCarta(4);
                vistaPlats.drawInfo(carta, 3);
                platsController.setCurrentCarta(carta);
                break;
        }
    }
}
