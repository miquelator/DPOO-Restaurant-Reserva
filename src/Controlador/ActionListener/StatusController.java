package Controlador.ActionListener;

import Network.ComunicationServer;
import Vista.StatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class to control the status order window
 */

public class StatusController implements ActionListener {

    private StatusView statusView;
    private ComunicationServer comunication;


    /***
     * Constructor of the class
     * @param statusView Reference to the window
     * @param comunication Reference to the network package in order to communicate with the server

     */
    public StatusController(StatusView statusView, ComunicationServer comunication) {
        this.statusView = statusView;
        this.comunication = comunication;
    }
    /***
     * Method that manages actions performed by the view it's listening
     * @param e Event that is triggered
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(StatusView.REFRESH)){
            statusView.insertaComandes(comunication.veureEstat());

        }
    }
}
