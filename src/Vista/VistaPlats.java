package Vista;



import Controlador.PlatsController;
import Model.Carta;
import Model.JTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaPlats extends JFrame{
    private JButton delete;
    private JButton doOrder;
    private JLabel product;
    private JPanel right;
    private JSplitPane jSplitPane;
    private JTable comanda;
    private JTabbedPane carta;

    public final static String DELETE = "Esborra";
    public final static String DO_ORDER = "Fes comanda";

    public VistaPlats (){
        setSize(800,500);
        setTitle("Carta");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        populateView();
    }

    /**
     * Fills view with components
     */
    private void populateView() {
        JPanel east = new JPanel(new BorderLayout());
        comanda = new JTable(new JTableModel());
        comanda.getTableHeader().setReorderingAllowed(false);
        delete = new JButton(DELETE);
        doOrder = new JButton(DO_ORDER);
        east.add(comanda, BorderLayout.CENTER);
        JPanel southEast = new JPanel(new GridLayout(1,2));
        JPanel deleteAux = new JPanel();
        JPanel doOrderAux = new JPanel();
        deleteAux.add(delete);
        doOrderAux.add(doOrder);
        southEast.add(deleteAux);
        southEast.add(doOrderAux);
        east.add(southEast, BorderLayout.SOUTH);

        carta = new JTabbedPane();

        jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, populateCarta(), east);
        jSplitPane.setDividerLocation(500);
        setContentPane(jSplitPane);
    }

    private JTabbedPane populateCarta() {
        ImageIcon icon = new ImageIcon("java-swing-tutorial.JPG");

        carta.addTab("Primer", icon, generateTabView(), "Tab 1");
        carta.addTab("Segon", icon, generateTabView(), "Tab 2");
        carta.addTab("Postre", icon, generateTabView(), "Tab 3");
        carta.addTab("Begudes", icon, generateTabView(), "Tab 4");

        return carta;
    }

    private JPanel generateTabView() {
        return null;
    }


    public void setController (PlatsController controller){
        delete.addActionListener(controller);
        doOrder.addActionListener(controller);
    }

    public void drawInfo(ArrayList<Carta> cartas) {

    }
}
