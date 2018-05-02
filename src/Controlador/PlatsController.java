package Controlador;

import Model.Carta;
import Model.CartaSelection;
import Vista.VistaPlats;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlatsController implements ActionListener {
    private VistaPlats vistaPlats;
    private ArrayList<Carta> carta;
    private ArrayList<CartaSelection> selectedItems;

    public PlatsController(VistaPlats vistaPlats) {
        this.vistaPlats = vistaPlats;
        selectedItems = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] aux = e.getActionCommand().split("#");
        switch (aux[0]){
            case VistaPlats.DELETE:
                if (vistaPlats.getSelectedOrder() != null){
                    deleteOrder(vistaPlats.getSelectedOrder());
                }
                System.out.println("borra comanda");
                break;

            case VistaPlats.DO_ORDER:
                System.out.println("fes comanda");
                break;

            case VistaPlats.ADD:
                afegeix(aux[1]);
                break;
        }
    }

    private void deleteOrder(CartaSelection selectedOrder) {
        int size = selectedItems.size();
        for (int i = 0; i < size; i++){
            if (selectedItems.get(i).getNomPlat().equals(selectedOrder.getNomPlat()) && selectedItems.get(i).getUnitatsDemanades() > 1){
                selectedItems.get(i).setPreuTotal(selectedItems.get(i).getPreuTotal() - selectedItems.get(i).getPreu());
                selectedItems.get(i).setUnitatsDemanades(selectedItems.get(i).getUnitatsDemanades() - 1);
            }else if (selectedItems.get(i).getNomPlat().equals(selectedOrder.getNomPlat()) && selectedItems.get(i).getUnitatsDemanades() == 1){
                System.out.println("borro el " + selectedOrder.toString());
                selectedItems.remove(i);
                size--;
            }
        }
        vistaPlats.addDishToOrder(selectedItems);
    }

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

    public void setCurrentCarta(ArrayList<Carta> carta){
        this.carta = carta;
    }
}
