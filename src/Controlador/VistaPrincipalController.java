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
    private VistaPlats vistaPlats;
    private ComunicacioServer comunicacio;

    public VistaPrincipalController(GestioDades g, VistaPrincipal v, VistaPlats vp, ComunicacioServer c) {
        gestioDades = g;
        vistaPrincipal = v;
        vistaPrincipal.linkejaController(this);
        vistaPlats = vp;
        vistaPlats.setController(this);
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
                vistaPlats.setVisible(true);
                comunicacio.veureCarta();
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