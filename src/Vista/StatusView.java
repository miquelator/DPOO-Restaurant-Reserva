package Vista;

import Model.CartaSelection;
import Model.CartaStatus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatusView extends JFrame{

    private JScrollPane panel;
    private JPanel panelInterior;
    private JPanel panelSuperior;


    public StatusView (ArrayList<CartaStatus> comanda){

        panelSuperior = new JPanel(new GridLayout(1,2));

        JLabel aux1 = new JLabel("Nom Plat");
        aux1.setFont(new Font("Courier", Font.BOLD,12));


        JLabel aux2 = new JLabel("Servit");
        aux2.setFont(new Font("Courier", Font.BOLD,12));

        panelSuperior.add(aux1);
        panelSuperior.add(aux2);

        panelInterior = new JPanel(new GridLayout(comanda.size(),1));


        for (CartaStatus cartaStatus : comanda){
            JPanel casella = new JPanel(new GridLayout(1,2));

            JLabel nomPlat = new JLabel(cartaStatus.getNomPlat());
            JLabel servit;
            if (cartaStatus.isServit()){
                servit = new JLabel("Sí");
            }else{
                servit = new JLabel("No");
            }
            casella.add(nomPlat);
            casella.add(servit);


            casella.setBorder(BorderFactory.createLineBorder(Color.black,1));


            panelInterior.add(casella);
        }

        panel = new JScrollPane(panelInterior);

        setSize(250,500);
        setTitle("Status");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().add(panelSuperior,BorderLayout.NORTH);
        getContentPane().add(panel,BorderLayout.CENTER);




    }

}
