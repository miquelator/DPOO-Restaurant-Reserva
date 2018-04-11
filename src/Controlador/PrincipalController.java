package Controlador;

import Model.GestioDades;
import Network.ComunicationServer;
import Vista.LoginView;
import Vista.VistaPlats;
import Vista.VistaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                VistaPlats vistaPlats = new VistaPlats();
                PlatsController vistaPrincipalController = new PlatsController(vistaPlats);
                vistaPlats.setController(vistaPrincipalController);
                vistaPlats.drawInfo(comunicacio.veureCarta(1));
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

    public boolean validateAuthentication(String userName, char[] password) {
        return comunicacio.autenticar(userName, String.valueOf(password));
    }
}