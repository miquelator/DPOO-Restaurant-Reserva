package Vista;


import Controlador.ActionListener.PrincipalController;
import Controlador.WindowAdapter.ReservaWindowClosing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;



/**
 * Window of the main menu section
 */

public class VistaPrincipal extends JFrame{

    private JButton authenticate;
    private JButton menu;
    private JButton order_status;
    private JButton pay_exit;

    public final static String AUTHENTICATE = "Autenticar-se";
    public final static String MENU = "Carta / Realitzar comanda";
    public final static String ORDER_STATUS = "Estat de la comanda";
    public final static String PAY_EXIT = "Pagar i marxar";

    public VistaPrincipal(){
        populateView();
        setSize(700, 700);
        setTitle("Administrator view");
        setLocationRelativeTo(null);
        Dimension dimension = new Dimension();
        dimension.height = 600;
        dimension.width = 600;
        setMinimumSize(dimension);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


    }

    /**
     * Draws the view
     */
    private void populateView() {
        JPanel principal = new JPanel(new GridLayout(0,1,10,10));
        principal.setBorder(new EmptyBorder(20,30,20,30));

        JLabel title = new JLabel("Reserves del restaurant");
        title.setFont(title.getFont().deriveFont(45.0f));
        title.setHorizontalAlignment(JLabel.CENTER);

        authenticate = new JButton(AUTHENTICATE);
        menu = new JButton(MENU);
        menu.setEnabled(false);
        order_status = new JButton(ORDER_STATUS);
        pay_exit = new JButton(PAY_EXIT);


        menu.setEnabled(false);
        pay_exit.setEnabled(false);
        order_status.setEnabled(false);


        authenticate.setFont(new Font("Arial", Font.PLAIN, 30));
        menu.setFont(new Font("Arial", Font.PLAIN, 30));
        order_status.setFont(new Font("Arial", Font.PLAIN, 30));
        pay_exit.setFont(new Font("Arial", Font.PLAIN, 30));

        principal.add(title);
        principal.add(authenticate);
        principal.add(menu);
        principal.add(order_status);
        principal.add(pay_exit);

        this.add(principal);
        this.pack();
    }

    /**
     * Displays error message on view
     * @param message Error message to be displayed
     * @param title Error title to be displayed
     */
    public void mostraErrorServidor(String message, String title) {
        String[] options = { "OK" };
        JOptionPane.showOptionDialog(this, message,
                title, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                null, options, options[0]);
    }



    /***
     * Sets the window controller that determines whether the window closes
     * @param reservaWindowClosing Controller
     */
    public void setWindowClosing(ReservaWindowClosing reservaWindowClosing){
        this.addWindowListener(reservaWindowClosing);

    }

    /***
     * Sets actions listener to buttons
     * @param c Controller
     */
    public void linkejaController (PrincipalController c){
        authenticate.addActionListener(c);
        order_status.addActionListener(c);
        menu.addActionListener(c);
        pay_exit.addActionListener(c);
    }

    /***
     * Depending on whether the user has authenticated, this method will disable some buttons and enable others
     * @param b boolean depending the authentication
     */
    public void setEnabledBotons (boolean b){
        authenticate.setEnabled(!b);
        menu.setEnabled(b);
        pay_exit.setEnabled(b);
        order_status.setEnabled(b);
    }

    /***
     * Generate message to show the price to pay when leaving
     * @param totalPagar price
     */
    public void mostraPreuTotal(double totalPagar) {
        String[] options = { "Pagar i marxar" };
        JOptionPane.showOptionDialog(this, "Total a pagar: " + totalPagar,
                "Factura", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }
}
