package Controlador;

import Model.Carta;
import Model.CartaSelection;
import Network.ComunicationServer;
import Vista.VistaPlats;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlatsController implements ActionListener {
    private VistaPlats vistaPlats;
    private ArrayList<Carta> carta;
    private ArrayList<CartaSelection> selectedItems;
    private ComunicationServer comunicationServer;

    /**
     * Contructor for current class
     * @param vistaPlats View linked to this controller class
     * @param c communication instance for server information exchange
     */
    public PlatsController(VistaPlats vistaPlats, ComunicationServer c) {
        this.vistaPlats = vistaPlats;
        selectedItems = new ArrayList<>();
        comunicationServer = c;
    }


    /**
     * Executes depending upon selected window
     * @param e Event thrown when window is changed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] aux = e.getActionCommand().split("#");
        switch (aux[0]){
            case VistaPlats.DELETE:
                if (vistaPlats.getSelectedOrder() != null){
                    deleteOrder(vistaPlats.getSelectedOrder());
                }
                break;

            case VistaPlats.DO_ORDER:
                comunicationServer.enviaComanda(selectedItems);
                break;

            case VistaPlats.ADD:
                afegeix(aux[1]);
                break;
        }
    }

    /**
     * Deletes and updates order's table
     * @param selectedOrder Model containing all current info from customer's order
     */
    private void deleteOrder(CartaSelection selectedOrder) {
        int size = selectedItems.size();
        int selected = -1;
        boolean flag = false;
        for (int i = 0; i < size; i++){
            if (selectedItems.get(i).getNomPlat().equals(selectedOrder.getNomPlat()) && selectedItems.get(i).getUnitatsDemanades() > 1){
                selected = vistaPlats.getSelectedOrderIndex();
                selectedItems.get(i).setPreuTotal(selectedItems.get(i).getPreuTotal() - selectedItems.get(i).getPreu());
                selectedItems.get(i).setUnitatsDemanades(selectedItems.get(i).getUnitatsDemanades() - 1);
                flag = true;
            }else if (selectedItems.get(i).getNomPlat().equals(selectedOrder.getNomPlat()) && selectedItems.get(i).getUnitatsDemanades() == 1){
                selectedItems.remove(i);
                size--;
                flag = false;
            }
        }
        vistaPlats.addDishToOrder(selectedItems);
        if (flag){
            vistaPlats.setSelectedRow(selected);
        }
    }

    /**
     * Updates user's order upon addition
     * @param nomPlat Name of the dish to add
     */
    private void afegeix(String nomPlat) {
        boolean flag = false;
        for (Carta c: carta){
            if (c.getNomPlat().equals(nomPlat) && c.getQuantitat() >= 1){
                if (selectedItems.size() == 0){
                    selectedItems.add(new CartaSelection(c.getNomPlat(), c.getPreu(), 1, c.getPreu()));
                }else {
                    for (CartaSelection cs: selectedItems){
                        if (cs.getNomPlat().equals(c.getNomPlat())){
                            flag = true;
                            cs.setPreuTotal(cs.getPreuTotal() + c.getPreu());
                            cs.setUnitatsDemanades(cs.getUnitatsDemanades() + 1);
                        }
                    }
                    if (!flag){
                        selectedItems.add(new CartaSelection(c.getNomPlat(), c.getPreu(), 1, c.getPreu()));
                    }
                }
            }
        }
        vistaPlats.addDishToOrder(selectedItems);
    }

    /**
     * Specifies instance of Carta model
     * @param carta model to instance
     */
    public void setCurrentCarta(ArrayList<Carta> carta){
        this.carta = carta;
    }
}
