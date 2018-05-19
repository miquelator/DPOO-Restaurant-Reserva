package Vista;

import Controlador.ActionListener.StatusController;
import Controlador.WindowAdapter.StatusWindowClosing;
import Model.CartaStatus;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class StatusView extends JFrame{

    private JPanel north;
    private JPanel principal;
    private JPanel south;
    private JButton jbRefresh;

    public static final String REFRESH = "Refresh";

    public StatusView (){
        populateView();
        setSize(300,500);
        setTitle("Status");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Fills view with components
     */
    private void populateView() {
        principal = new JPanel(new BorderLayout());
        north = new JPanel();
        south = new JPanel();
        jbRefresh = new JButton(REFRESH);
        south.add(jbRefresh);

        north.setLayout(new FlowLayout(FlowLayout.LEFT));
        north.setBorder(BorderFactory.createTitledBorder("Order status (nom plat --> estat)"));

        JScrollPane scrollPane = new JScrollPane(north);
        principal.add(scrollPane, BorderLayout.CENTER);
        principal.add(south, BorderLayout.SOUTH);
        setContentPane(principal);
    }

    private final Color CONNECTED = new Color(11, 159, 0);

    private Component drawColour(TableCellRenderer renderer, String text){
        Component component = drawColour(renderer, text);
        component.setForeground(CONNECTED);
        return component;
    }

    public void fillComandes (ArrayList<CartaStatus> comanda){
        int size = comanda.size();
        JLabel ordersLabel = new JLabel("", SwingConstants.CENTER);
        String text = "";
        for (int i = 0; i < size; i++){
            if (comanda.get(i).isServit()){
                text = text + "<font color=green>" + comanda.get(i).getNomPlat() + " --> " + "servit" + "</font>" + "<br/>";
            }else {
                text = text + "<font color=red>" + comanda.get(i).getNomPlat() + " --> " + "no servit" + "</font>" + "<br/>";
            }
        }
        text = "<html>" + text + "<html>";
        ordersLabel.setText(text);

        north.removeAll();
        north.add(ordersLabel);
        setContentPane(principal);

    }

    public void setController(StatusWindowClosing statusWindowClosing, StatusController statusController) {
        this.addWindowListener(statusWindowClosing);
        jbRefresh.addActionListener(statusController);
        jbRefresh.setActionCommand(REFRESH);
    }

}
