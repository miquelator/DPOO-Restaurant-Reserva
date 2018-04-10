package Controlador;

import Vista.VistaPlats;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlatsController implements ActionListener {
    private VistaPlats vistaPlats;

    public PlatsController(VistaPlats vistaPlats) {
        this.vistaPlats = vistaPlats;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case VistaPlats.SERVE:
                System.out.println("servir comanda");
                break;
        }
    }
}
