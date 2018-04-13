package Controlador;

import Network.ComunicationServer;
import Vista.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private PrincipalController parent;
    private LoginView loginView;

    public LoginController(PrincipalController parent, LoginView loginView) {
        this.parent = parent;
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case LoginView.LOG_IN:
                    if (loginView.getUserName().equals("")){
                        loginView.setLoginError("EL nom d'usuari no pot estar buit!", "ERROR");
                    }else if (loginView.getPassword().length == 0){
                        loginView.setLoginError("La contrasenya no por estar buida!", "ERROR");
                    }else {
                        if (parent.validateAuthentication(loginView.getUserName(), loginView.getPassword())){
                            loginView.mostraInformacioServidor("Autenticació correcta.", "INFORMACIO");
                            loginView.setVisible(false);
                            parent.setViewEnabled(true);
                            //TODO: PERMETRE VEURE LA CARTA SI LA VALIDACIO ES CORRECTA
                        }else {
                            loginView.setLoginError("Error a l'hora de validar la informacio. Revisa que els camps siguin correctes.", "ERROR");
                        }

                    }
                break;
        }
    }
}
