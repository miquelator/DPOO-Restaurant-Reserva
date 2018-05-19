package Vista;



import Controlador.ChangeListener.PlatsChangeController;
import Controlador.ActionListener.PlatsController;
import Controlador.WindowAdapter.CartaWindowClosing;
import Model.Carta;
import Model.CartaSelection;
import Model.JTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class VistaPlats extends JFrame{
    private PlatsController controller;

    private JButton delete;
    private JButton doOrder;
    private JSplitPane jSplitPane;
    private JTable comandesTable;
    private JTabbedPane carta;

    private ArrayList<JButton> arrayButtons;

    public final static String DELETE = "Esborra";
    public final static String DO_ORDER = "Envia comanda";
    public final static String ADD = "Afegeix";

    public VistaPlats (){
        setSize(1000,500);
        setTitle("Carta");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        populateView();
    }

    /**
     * Fills view with components
     */
    private void populateView() {
        JPanel east = new JPanel(new BorderLayout());
        comandesTable = new JTable(new JTableModel());
        comandesTable.getTableHeader().setReorderingAllowed(false);
        createEmptyTable();
        JScrollPane scrollPane = new JScrollPane(comandesTable);
        delete = new JButton(DELETE);
        doOrder = new JButton(DO_ORDER);
        east.add(scrollPane, BorderLayout.CENTER);
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

    /**
     * Creates an empty table representing starting user's order
     */
    public void createEmptyTable() {
        DefaultTableModel model = (DefaultTableModel) comandesTable.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("Nom del plat");
        model.addColumn("Nombre d'unitats");
        model.addColumn("Preu unitari");
        model.addColumn("Preu total");
    }

    private JTabbedPane createTabbedPane() {
        ImageIcon icon = new ImageIcon("java-swing-tutorial.JPG");

        carta.addTab("Primer", icon, null, "Tab 1");
        carta.addTab("Segon", icon, null, "Tab 2");
        carta.addTab("Postre", icon, null, "Tab 3");
        carta.addTab("Begudes", icon, null, "Tab 4");

        return carta;
    }


    /***
     * This method fills the tabs with the avilable dishes on the menu
     * @param cartes ArrayList of Cartes with the avilable items on the meny
     * @param tab the tab for the type of dish
     */
    public void drawInfo(ArrayList<Carta> cartes, int tab) {
        try{

            // create a gridbag layout
            JPanel left = new JPanel(new GridBagLayout());
            //set the default constraints
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 0;
            c.insets = new Insets(10,5,10,5);
            c.anchor = GridBagConstraints.WEST;

            //create a array list of buttons
            arrayButtons = new ArrayList<>();

            // for every item fill a row of info
            for (Carta carta :cartes){

                // create the specific names and items
                JLabel itemName = new JLabel(carta.getNomPlat());
                JButton addButton = new JButton(ADD);
                addButton.setActionCommand(ADD + "#" + carta.getNomPlat());
                JLabel price = new JLabel(String.valueOf(carta.getPreu()) + " €");
                JLabel quantitat = new JLabel(String.valueOf("Disponibles: "+carta.getQuantitat()));

                // add into the layout
                c.gridx = 0;
                left.add(itemName,c);
                c.gridx = 1;
                left.add(price, c);
                c.gridx = 2;
                left.add(addButton,c);
                c.gridx = 3;
                left.add(quantitat, c);
                arrayButtons.add(addButton);

                // increment in one the y value of the grid
                c.gridy += 1;
            }

            // fill the data into the view
            JScrollPane leftScroll = new JScrollPane(left);
            carta.setComponentAt(tab, leftScroll);
            setContentPane(jSplitPane);
            updateControllers();

        }catch (NullPointerException ignored){
        }
    }


    public int getSelectedTab() {
        return carta.getSelectedIndex();
    }

    private void updateControllers(){
        for (JButton buttons: arrayButtons){
            buttons.addActionListener(controller);
        }
    }

    public void setController(PlatsController controller, PlatsChangeController platsChangeController, CartaWindowClosing cartaWindowClosing){
        this.controller = controller;
        delete.addActionListener(controller);
        doOrder.addActionListener(controller);
        carta.addChangeListener(platsChangeController);
        this.addWindowListener(cartaWindowClosing);
    }

    /**
     * Updates view's table with selected dish by user
     * @param selectedItems ArrayList of user's dish selection
     */
    public void addDishToOrder(ArrayList<CartaSelection> selectedItems) {

        DefaultTableModel model = (DefaultTableModel) comandesTable.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("Nom del plat");
        model.addColumn("Nombre d'unitats");
        model.addColumn("Preu unitari");
        model.addColumn("Preu total");

        for (int i = 0; i < selectedItems.size(); i++){
            Vector<String> nomPlat = new Vector(Arrays.asList(selectedItems.get(i).getNomPlat()));
            Vector<String> unitats = new Vector(Arrays.asList(selectedItems.get(i).getUnitatsDemanades()));
            Vector<String> preuPlat = new Vector(Arrays.asList(selectedItems.get(i).getPreu()));
            Vector<String> preuTotal = new Vector(Arrays.asList(selectedItems.get(i).getPreuTotal()));

            Vector<Object> row = new Vector<Object>();
            row.addElement(nomPlat.get(0));
            row.addElement(unitats.get(0));
            row.addElement(String.valueOf(preuPlat.get(0)) + "€");
            row.addElement(String.valueOf(preuTotal.get(0)) + "€");
            model.addRow(row);
        }
    }

    public CartaSelection getSelectedOrder() {
        try {
            return new CartaSelection(comandesTable.getModel().getValueAt(comandesTable.getSelectedRow(), 0).toString(),
                    Float.parseFloat(comandesTable.getModel().getValueAt(comandesTable.getSelectedRow(), 2).toString().replace("€", "")),
                    Integer.parseInt(comandesTable.getModel().getValueAt(comandesTable.getSelectedRow(), 1).toString()),
                    Float.parseFloat(comandesTable.getModel().getValueAt(comandesTable.getSelectedRow(), 3).toString().replace("€", "")));

        }catch (ArrayIndexOutOfBoundsException ignored){

        }
        return null;
    }

    public int getSelectedOrderIndex() {
        return  comandesTable.getSelectedRow();
    }

    /**
     * Sets the specified row to selected status
     * @param selected Row to be marked as selected
     */
    public void setSelectedRow(int selected) {
        comandesTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        comandesTable.setColumnSelectionAllowed(false);
        comandesTable.setRowSelectionAllowed(true);
        comandesTable.setRowSelectionInterval(selected, selected);
    }

    public void showError(String message, String titol){
        JOptionPane.showMessageDialog(null,
                message,
                titol,
                JOptionPane.ERROR_MESSAGE);
    }

    public void informOrderDone() {
        String[] options = { "Ok" };
        JOptionPane.showOptionDialog(this, "Comanda realitzada!",
                "Notice", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }

}
