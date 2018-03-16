package Vista;


import Controlador.VistaPrincipalController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaPrincipal extends JFrame{

    private JPanel total;

    private JButton autentica;
    private JButton veureCarta;
    private JButton veureEstat;
    private JButton paga;

    public VistaPrincipal(){

        total = new JPanel();
        total.setLayout(new GridLayout(2,2));

        autentica = new JButton("Autenticar-se al sistema");
        veureCarta = new JButton("Carta / Realitzar comanda");
        veureEstat = new JButton("Estat de la comanda");
        paga = new JButton("Pagar i marxar");

        total.add(autentica);
        total.add(veureCarta);
        total.add(veureEstat);
        total.add(paga);

        getContentPane().add(total);

        setSize(750,510);
        setTitle("Reserva");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void linkejaController (VistaPrincipalController c){
        autentica.addActionListener(c);
        veureEstat.addActionListener(c);
        veureCarta.addActionListener(c);
        paga.addActionListener(c);
    }
}
