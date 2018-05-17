package Vista;

import Controlador.WindowAdapter.StatusWindowClosing;
import Model.CartaSelection;
import Model.CartaStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StatusView extends JFrame{

    private JScrollPane panel;
    private JPanel panelInterior;
    private JPanel panelSuperior;
    private JButton jbRefresh;
    public static final String REFRESH = "Refresh";


    public StatusView (){

        panelSuperior = new JPanel(new GridLayout(1,2));

        JLabel aux1 = new JLabel("Nom Plat");
        aux1.setFont(new Font("Courier", Font.BOLD,12));


        JLabel aux2 = new JLabel("Servit");
        aux2.setFont(new Font("Courier", Font.BOLD,12));

        panelSuperior.add(aux1);
        panelSuperior.add(aux2);

        // create a empty panel
        panelInterior = new JPanel();
        panel = new JScrollPane(panelInterior);



        jbRefresh = new JButton(REFRESH);
        JPanel pRefresh = new JPanel(new GridBagLayout());
        pRefresh.add(jbRefresh);

        getContentPane().add(panelSuperior,BorderLayout.NORTH);
        getContentPane().add(pRefresh,BorderLayout.SOUTH);
        getContentPane().add(panel,BorderLayout.CENTER);

        setSize(250,500);
        setTitle("Status");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);


    }

    public void setController(StatusWindowClosing statusWindowClosing) {
        this.addWindowListener(statusWindowClosing);
    }

    public void fillComandes (ArrayList<CartaStatus> comanda){

       // panel = new JScrollPane();

        panelInterior.removeAll();
        panelInterior.setLayout(new GridLayout(comanda.size(),1));

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


        panelInterior.updateUI();


    }

    public void registerController(ActionListener al){
        jbRefresh.addActionListener(al);
        jbRefresh.setActionCommand(REFRESH);
    }

}
