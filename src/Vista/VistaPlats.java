package Vista;



import Controlador.PlatsChangeController;
import Controlador.PlatsController;
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
    public final static String DO_ORDER = "Fes comandesTable";
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


    public void drawInfo(ArrayList<Carta> cartes, int tab) {
        try{
            JPanel left = new JPanel(new GridLayout(cartes.size(),1));
            arrayButtons = new ArrayList<>();
            for (Carta carta :cartes){
                left.add(createMenuRow(carta));
            }
            carta.setComponentAt(tab, left);
            setContentPane(jSplitPane);
            updateControllers();
        }catch (NullPointerException ignored){
        }
    }

    private JPanel createMenuRow(Carta carta) {
        JPanel menuRow = new JPanel();
        JLabel itemName = new JLabel(carta.getNomPlat());
        JPanel rightSideMenuRow = new JPanel(new GridLayout(1,2));
        JPanel quantitatMenuRow = new JPanel(new BorderLayout());
        JButton addButton = new JButton(ADD);
        addButton.setActionCommand(ADD + "#" + carta.getNomPlat());
        JLabel price = new JLabel(String.valueOf(carta.getPreu()) + " €");
        JLabel quantitat = new JLabel(String.valueOf("Disponibles: "+carta.getQuantitat()));
        rightSideMenuRow.add(price);
        quantitatMenuRow.add(quantitat, BorderLayout.CENTER);

        rightSideMenuRow.add(addButton);
        menuRow.add(itemName);
        menuRow.add(rightSideMenuRow);
        menuRow.add(quantitatMenuRow);

        arrayButtons.add(addButton);
        return menuRow;
    }

    public int getSelectedTab() {
        return carta.getSelectedIndex();
    }

    private void updateControllers(){
        for (JButton buttons: arrayButtons){
            buttons.addActionListener(controller);
        }
    }

    public void setController(PlatsController controller, PlatsChangeController platsChangeController){
        this.controller = controller;
        delete.addActionListener(controller);
        doOrder.addActionListener(controller);
        carta.addChangeListener(platsChangeController);
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
