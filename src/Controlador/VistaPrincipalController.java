package Controlador;

import Model.GestioDades;
import Network.ComunicacioServer;
import Vista.VistaPlats;
import Vista.VistaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by miquelator on 16/3/18.
 */
public class VistaPrincipalController implements ActionListener {

    private GestioDades gestioDades;
    private VistaPrincipal vistaPrincipal;
    private ComunicacioServer comunicacio;

    public VistaPrincipalController(GestioDades g, VistaPrincipal v, ComunicacioServer c) {
        gestioDades = g;
        vistaPrincipal = v;
        vistaPrincipal.linkejaController(this);
        comunicacio = c;
        c.setController(this);

    }

    public void actionPerformed(ActionEvent event) {

        //Mirem quin botó ha estat apretat
        String quinBoto = event.getActionCommand();

        switch (quinBoto) {
            case VistaPrincipal.AUTHENTICATE:
                comunicacio.autenticar();
                break;
            case VistaPrincipal.MENU:
                VistaPlats vistaPlats = new VistaPlats();
                VistaPlatsController vistaPrincipalController = new VistaPlatsController(vistaPlats);
                vistaPlats.setController(vistaPrincipalController);
                vistaPlats.drawInfo(comunicacio.veureCarta());
                vistaPrincipal.setVisible(false);
                vistaPlats.setVisible(true);

                break;

            case VistaPrincipal.ORDER_STATUS:
                comunicacio.veureEstat();
                break;

            case VistaPrincipal.PAY_EXIT:
                comunicacio.pagar();
                break;
            case VistaPrincipal.EXIT:
                System.exit(1);
                break;
        }

    }

    public void mostraError(String errorMessage, String title) {
        vistaPrincipal.mostraErrorServidor(errorMessage, title);
    }
}