package Vista;



import Controlador.PlatsController;
import Model.Carta;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaPlats extends JFrame{
/*
    private JLabel user,products, llistaComandes;
    private JPanel aux, centre, panelComandes, esquerra, dreta, userPane, productsPane, sendPane;
    private JSplitPane total;
    private JScrollPane dretaScroll;
    private JTextField userText;
    private JComboBox<String> productsList;
    private JButton sendButton;

*/
    private JButton serve;
    private JLabel label;
    private JLabel user;
    private JLabel product;
    private JPanel right;
    private JSplitPane jSplitPane;

    public final static String SERVE = "Serve";

    public VistaPlats (){
/*
        user = new JLabel("Username");
        products = new JLabel("Products:");
        userText = new JTextField(14);

        sendPane = new JPanel();
        sendPane.setLayout(new BorderLayout());
        sendButton = new JButton("Send");


        sendPane.add(sendButton,BorderLayout.CENTER);


        ArrayList<String> llista;

        productsList = new JComboBox<String>();
        productsList.addItem("Cafè");
        productsList.addItem("Tallat");
        productsList.addItem("Cafè amb llet");
        productsList.addItem("Cafè amb llet de soja");
        productsList.addItem("Cigalò");


        userPane = new JPanel();
        userPane.add(user);
        userPane.add(userText);

        productsPane = new JPanel();
        productsPane.add(products);
        productsPane.add(productsList);

        centre = new JPanel(new GridLayout(3,1));
        centre.add(userPane);
        centre.add(productsPane);
        centre.add(sendPane);

        aux = new JPanel();

        esquerra = new JPanel(new GridLayout(3,1));
        esquerra.add(aux);
        esquerra.add(centre);
        esquerra.setBorder(BorderFactory.createTitledBorder("Order"));

        llistaComandes = new JLabel("");
        panelComandes = new JPanel(new BorderLayout());
        panelComandes.add(llistaComandes, BorderLayout.WEST);

        dreta = new JPanel(new BorderLayout());
        dreta.add(panelComandes, BorderLayout.NORTH);
        dreta.setBorder(BorderFactory.createTitledBorder("Waiting queue"));

        dretaScroll = new JScrollPane(dreta);
        total = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                esquerra, dretaScroll);

        total.setDividerLocation(300);



        getContentPane().add(total);
*/
        setSize(600,510);
        setTitle("DPO2-1718-PCS2-Client");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        populateView();
    }

    /**
     * Fills view with components
     */
    private void populateView() {
        JPanel left = new JPanel(new GridBagLayout());
        right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setAlignmentX(Component.LEFT_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(right);

        left.setBorder(BorderFactory.createTitledBorder("Order"));
        right.setBorder(BorderFactory.createTitledBorder("Waiting queue"));

        serve = new JButton(SERVE);
        serve.setActionCommand(SERVE);
        JLabel userName = new JLabel("User name:");
        user = new JLabel("-");
        JLabel productName = new JLabel("Product:");
        product = new JLabel("-");

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.weightx = 0.1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20,0,0,0);
        left.add(userName, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        left.add(user, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        left.add(productName, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        left.add(product, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        left.add(serve, constraints);

        label = new JLabel("Pending orders: 0");

        right.add(label);

        jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, scrollPane);
        jSplitPane.setDividerLocation(250);
        setContentPane(jSplitPane);
    }


    public void setController (PlatsController controller){
        serve.addActionListener(controller);
    }

    public void drawInfo(ArrayList<Carta> cartas) {

    }
}
