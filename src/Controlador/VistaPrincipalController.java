package Controlador;

import Model.GestioDades;
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

    public VistaPrincipalController(GestioDades g, VistaPrincipal v) {
        gestioDades = g;
        vistaPrincipal = v;
        vistaPrincipal.linkejaController(this);

    }

    public void actionPerformed(ActionEvent event) {

        //Mirem quin botó ha estat apretat
        String quinBoto = ((JButton) event.getSource()).getText();


        switch (quinBoto) {
            case "Buy 1":

                break;
            case "Buy 5":
                break;

            case "Buy 10":
                break;

        }

    }
}