package Vista;

import Controlador.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    public final static String LOG_IN = "Entrar";

    private JTextField userName;
    private JPasswordField password;

    private JButton logIn;

    /**
     * Builds a new Login screen
     */
    public LoginView(){
        setSize(420,500);
        setTitle("Restaurant - Login");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jpGrid = new JPanel (new GridLayout(4,1));

        JLabel jlGameTitle = new JLabel("Restaurant", SwingConstants.CENTER);
        jlGameTitle.setFont(new Font("TituloTM", Font.BOLD, 40));
        jpGrid.add(jlGameTitle);

        JPanel jpAux = new JPanel(new FlowLayout());
        JLabel jlUserName = new JLabel("Nom d'usuari: ");
        userName = new JTextField();
        userName.setEditable(true);
        userName.setPreferredSize(new Dimension(200,24));

        jpAux.add(jlUserName);
        jpAux.add(userName);
        jpGrid.add(jpAux);

        jpAux = new JPanel (new FlowLayout());
        JLabel jlPassword = new JLabel("Contrasenya: ");
        password = new JPasswordField();
        password.setEditable(true);
        password.setPreferredSize(new Dimension(200,24));

        jpAux.add(jlPassword);
        jpAux.add(password);

        jpGrid.add(jpAux);

        JPanel jpLogIn = new JPanel ();
        logIn = new JButton(LOG_IN);

        jpLogIn.add(logIn);
        jpGrid.add(jpLogIn);

        getContentPane().add(jpGrid, BorderLayout.CENTER);
    }

    /**
     * @return The entered user name
     */
    public String getUserName(){
        return userName.getText();
    }

    /**
     * @return The entered user password
     */
    public char[] getPassword(){
        return password.getPassword();
    }

    /**
     * Sets the login error message below the credential fields.
     */
    public void setLoginError(String errorMessage, String title){
        String[] options = { "OK" };
        JOptionPane.showOptionDialog(this, errorMessage,
                title, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                null, options, options[0]);
    }

    /**
     * Displays information message on view
     * @param message Information message to be displayed
     * @param title Information title to be displayed
     */
    public void mostraInformacioServidor(String message, String title) {
        String[] options = { "OK" };
        JOptionPane.showOptionDialog(this, message,
                title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }

    /**
     * Links the login window buttons to the corresponding logic controller
     * @param controller an instance of LoginController
     */
    public void registerController(LoginController controller){
        logIn.addActionListener(controller);
    }
}
