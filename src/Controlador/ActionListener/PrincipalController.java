package Controlador.ActionListener;

import Controlador.ChangeListener.PlatsChangeController;
import Controlador.WindowAdapter.CartaWindowClosing;
import Controlador.WindowAdapter.StatusWindowClosing;
import Model.Carta;
import Model.GestioDades;
import Network.ComunicationServer;
import Vista.LoginView;
import Vista.StatusView;
import Vista.VistaPlats;
import Vista.VistaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by miquelator on 16/3/18.
 */
public class PrincipalController implements ActionListener {

    private GestioDades gestioDades;
    private VistaPrincipal vistaPrincipal;
    private ComunicationServer comunicacio;

    public PrincipalController(GestioDades g, VistaPrincipal v, ComunicationServer c) {
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
                LoginView loginView = new LoginView();
                LoginController loginController = new LoginController(this , loginView);
                loginView.registerController(loginController);
                vistaPrincipal.setVisible(false);
                loginView.setVisible(true);
                break;

            case VistaPrincipal.MENU:
                ArrayList<Carta> carta = comunicacio.veureCarta(1);
                VistaPlats vistaPlats = new VistaPlats();

                CartaWindowClosing cartaWindowClosing = new CartaWindowClosing(vistaPrincipal, vistaPlats);
                PlatsController platsController = new PlatsController(vistaPlats, comunicacio);
                platsController.setCurrentCarta(carta);
                PlatsChangeController platsChangeController = new PlatsChangeController(vistaPlats, comunicacio, platsController);

                vistaPlats.setController(platsController, platsChangeController, cartaWindowClosing);
                vistaPlats.drawInfo(carta, 0);
                vistaPrincipal.setVisible(false);
                vistaPlats.setVisible(true);

                break;

            case VistaPrincipal.ORDER_STATUS:
                StatusView statusView = new StatusView();
                statusView.fillComandes(comunicacio.veureEstat());
                StatusController statusController = new StatusController(statusView, comunicacio);
                statusView.registerController(statusController);
                StatusWindowClosing statusWindowClosing = new StatusWindowClosing(statusView, vistaPrincipal);
                statusView.setController(statusWindowClosing);
                vistaPrincipal.setVisible(false);
                statusView.setVisible(true);
                break;

            case VistaPrincipal.PAY_EXIT:
                double totalPagar = comunicacio.pagar();
                if (totalPagar == -1){
                    vistaPrincipal.mostraErrorServidor("Error a l'hora de calcular el preu total a pagar!", "ERROR");
                }else {
                    vistaPrincipal.mostraPreuTotal(totalPagar);
                }
                break;
            case VistaPrincipal.EXIT:
                comunicacio.desconecta();
                System.exit(1);
                break;
        }

    }

    public void mostraError(String errorMessage, String title) {
        vistaPrincipal.mostraErrorServidor(errorMessage, title);
        System.exit(1);
    }

    public boolean validateAuthentication(String userName, char[] password) {
        boolean b = comunicacio.autenticar(userName, String.valueOf(password));
        vistaPrincipal.setEnabledBotons(b);

        return b;
    }

    public void setViewEnabled(boolean b) {
        vistaPrincipal.setVisible(b);
    }
}