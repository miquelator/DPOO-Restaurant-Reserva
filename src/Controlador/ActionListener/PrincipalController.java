package Controlador.ActionListener;

import Controlador.ChangeListener.PlatsChangeController;
import Controlador.WindowAdapter.CartaWindowClosing;
import Controlador.WindowAdapter.ReservaWindowClosing;
import Controlador.WindowAdapter.StatusWindowClosing;
import Model.Carta;
import Network.ComunicationServer;
import Vista.LoginView;
import Vista.StatusView;
import Vista.VistaPlats;
import Vista.VistaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class that controlls the main view and the appereance of the secondary ones
 */
public class PrincipalController implements ActionListener {

    private VistaPrincipal vistaPrincipal;
    private ComunicationServer comunicacio;
    private ReservaWindowClosing reservaWindowClosing;

    /***
     * Constructor of the class
     * @param v Reference to the main view that this controller controls
     * @param c Reference to the network class in order to comunicate with the server

     */
    public PrincipalController(VistaPrincipal v, ComunicationServer c) {
        vistaPrincipal = v;
        vistaPrincipal.linkejaController(this);
        comunicacio = c;
        c.setController(this);

        reservaWindowClosing = new ReservaWindowClosing(v);
        v.setWindowClosing(reservaWindowClosing);

    }
    /***
     * Method that manages actions performed by the view it's listening
     * @param event Event that is triggered
     */
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
                statusView.insertaComandes(comunicacio.veureEstat());
                StatusController statusController = new StatusController(statusView, comunicacio);
                StatusWindowClosing statusWindowClosing = new StatusWindowClosing(statusView, vistaPrincipal);
                statusView.setController(statusWindowClosing, statusController);
                vistaPrincipal.setVisible(false);
                statusView.setVisible(true);
                break;

            case VistaPrincipal.PAY_EXIT:
                double totalPagar = comunicacio.pagar();
                if (totalPagar == -1){
                    vistaPrincipal.mostraErrorServidor("Error a l'hora de calcular el preu total a pagar!", "ERROR");
                }else {
                    vistaPrincipal.mostraPreuTotal(totalPagar);
                    comunicacio.desconecta();
                    System.exit(1);
                }
                break;

        }

    }


    /***
     * This method allows for a personalized message to be sent in case of an error
     * @param errorMessage Body of the message
     * @param title Title of the message
     */

    public void mostraError(String errorMessage, String title) {
        vistaPrincipal.mostraErrorServidor(errorMessage, title);
        System.exit(1);
    }


    /***
     * This method orders the network to check in the server if a new user has his credentials in good order and modifies the other windows accordingly
     * @param userName Name of the user
     * @param password Password introduced by the user
     * @return This method returns a true or false boolean

     */

    public boolean validateAuthentication(String userName, char[] password) {
        boolean b = comunicacio.autenticar(userName, String.valueOf(password));
        reservaWindowClosing.setAutenticat(b);
        vistaPrincipal.setEnabledBotons(b);

        return b;
    }


    /***
     * Setter for the main page's visibility
     * @param b Boolean to determine whether to hide or show the window
     */
    public void setViewEnabled(boolean b) {
        vistaPrincipal.setVisible(b);
    }
}