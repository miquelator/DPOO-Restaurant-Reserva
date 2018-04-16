package Controlador;

import Model.Carta;
import Model.CartaSelection;
import Vista.VistaPlats;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;

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
                System.out.println("borra comanda");
                break;

            case VistaPlats.DO_ORDER:
                System.out.println("fes comanda");
                break;

            case "Afegeix":
                afegeix(aux[1]);
                break;
        }
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
