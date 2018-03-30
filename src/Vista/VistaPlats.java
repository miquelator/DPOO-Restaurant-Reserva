package Vista;



import Controlador.VistaPrincipalController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class VistaPlats extends JFrame{

    private JLabel user,products, llistaComandes;
    private JPanel aux, centre, panelComandes, esquerra, dreta, userPane, productsPane, sendPane;
    private JSplitPane total;
    private JScrollPane dretaScroll;
    private JTextField userText;
    private JComboBox<String> productsList;
    private JButton sendButton;

    public VistaPlats (){

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

        setSize(600,510);
        setTitle("DPO2-1718-PCS2-Client");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }


    public void setController (VistaPrincipalController controller){
        sendButton.addActionListener(controller);
    }

}
