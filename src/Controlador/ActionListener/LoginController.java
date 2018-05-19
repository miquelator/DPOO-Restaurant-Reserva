package Controlador.ActionListener;

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

                //abans d'enviar la informació a la base de dades, comprovem que no estigui buida
                    if (loginView.getUserName().equals("")){
                        loginView.setLoginError("EL nom d'usuari no pot estar buit!", "ERROR");
                    }else if (loginView.getPassword().length == 0){
                        loginView.setLoginError("La contrasenya no por estar buida!", "ERROR");
                    }else {
                        //enviem informació a la base de dades i comprovem si la funció retorna true o false
                        if (parent.validateAuthentication(loginView.getUserName(), loginView.getPassword())){
                            loginView.mostraInformacioServidor("Autenticació correcta.", "INFORMACIO");
                            loginView.setVisible(false);
                            parent.setViewEnabled(true);
                        }else {
                            loginView.setLoginError("Error a l'hora de validar la informacio. Revisa que els camps siguin correctes així com el dia de reserva, o que no estiguis ja conectat.", "ERROR");
                        }

                    }
                break;
        }
    }
}
