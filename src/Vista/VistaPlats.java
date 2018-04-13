package Vista;



import Controlador.PlatsChangeController;
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
    public final static String SERVE = "Serve";

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

        jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createTabbedPane(), east);
        jSplitPane.setDividerLocation(500);
        setContentPane(jSplitPane);
    }

    private JTabbedPane createTabbedPane() {
        ImageIcon icon = new ImageIcon("java-swing-tutorial.JPG");

        carta.addTab("Primer", icon, null, "Tab 1");
        carta.addTab("Segon", icon, null, "Tab 2");
        carta.addTab("Postre", icon, null, "Tab 3");
        carta.addTab("Begudes", icon, null, "Tab 4");

        return carta;
    }

    public void setController(PlatsController controller, PlatsChangeController platsChangeController){
        delete.addActionListener(controller);
        doOrder.addActionListener(controller);
        carta.addChangeListener(platsChangeController);
    }

    public void drawInfo(ArrayList<Carta> cartes, int tab) {
        try{
            JPanel left = new JPanel(new GridLayout(cartes.size(),1));
            for (Carta carta :cartes){
                System.out.println(carta.toString());
                left.add(createMenuRow(carta));

            }
            carta.setComponentAt(tab, left);
            setContentPane(jSplitPane);
        }catch (NullPointerException ignored){

        }
    }

    private JPanel createMenuRow(Carta carta) {
        JPanel menuRow = new JPanel();
        JLabel itemName = new JLabel(carta.getNomPlat());
        JPanel rightSideMenuRow = new JPanel(new GridLayout(1,2));
        JButton add = new JButton("Afegeix");
        JLabel price = new JLabel(String.valueOf(carta.getPreu()));
        rightSideMenuRow.add(price);
        rightSideMenuRow.add(add);
        menuRow.add(itemName);
        menuRow.add(rightSideMenuRow);
        return menuRow;
    }

    public int getSelectedTab() {
        return carta.getSelectedIndex();
    }
}
