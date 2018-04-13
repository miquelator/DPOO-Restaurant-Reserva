package Vista;


import Controlador.PrincipalController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaPrincipal extends JFrame{

    private JButton authenticate;
    private JButton menu;
    private JButton order_status;
    private JButton pay_exit;
    private JButton exit;

    public final static String AUTHENTICATE = "Autenticar-se";
    public final static String MENU = "Carta / Realitzar comanda";
    public final static String ORDER_STATUS = "Estat de la comanda";
    public final static String PAY_EXIT = "Pagar i marxar";
    public final static String EXIT = "Sortir";

    public VistaPrincipal(){
        populateView();
        setSize(700, 700);
        setTitle("Administrator view");
        setLocationRelativeTo(null);
        Dimension dimension = new Dimension();
        dimension.height = 600;
        dimension.width = 600;
        setMinimumSize(dimension);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


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
        exit = new JButton(EXIT);

        menu.setEnabled(false);
        pay_exit.setEnabled(false);
        order_status.setEnabled(false);


        authenticate.setFont(new Font("Arial", Font.PLAIN, 30));
        menu.setFont(new Font("Arial", Font.PLAIN, 30));
        order_status.setFont(new Font("Arial", Font.PLAIN, 30));
        pay_exit.setFont(new Font("Arial", Font.PLAIN, 30));
        exit.setFont(new Font("Arial", Font.PLAIN, 30));

        principal.add(title);
        principal.add(authenticate);
        principal.add(menu);
        principal.add(order_status);
        principal.add(pay_exit);
        principal.add(exit);

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

    public void linkejaController (PrincipalController c){
        authenticate.addActionListener(c);
        order_status.addActionListener(c);
        menu.addActionListener(c);
        pay_exit.addActionListener(c);
        exit.addActionListener(c);
    }

    public void setEnabledBotons (boolean b){

        menu.setEnabled(true);
        pay_exit.setEnabled(true);
        order_status.setEnabled(true);
    }
}
