package Controlador.ActionListener;

import Network.ComunicationServer;
import Vista.StatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusController implements ActionListener {

    private StatusView statusView;
    private ComunicationServer comunication;

    public StatusController(StatusView statusView, ComunicationServer comunication) {
        this.statusView = statusView;
        this.comunication = comunication;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(StatusView.REFRESH)){
            statusView.insertaComandes(comunication.veureEstat());

        }
    }
}
