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

    }

    public void actionPerformed(ActionEvent event) {

        //Mirem quin botó ha estat apretat
        String quinBoto = ((JButton) event.getSource()).getText();

        switch (quinBoto) {
            case "Autenticar-se al sistema":
                comunicacio.autenticar();
                break;
            case "Carta / Realitzar comanda":
                vistaPlats.setVisible(true);
                comunicacio.veureCarta();
                break;

            case "Estat de la comanda":
                comunicacio.veureEstat();
                break;

            case "Pagar i marxar":
                comunicacio.pagar();
                break;

        }

    }
}