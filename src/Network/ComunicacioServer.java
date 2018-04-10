package Network;

import Controlador.VistaPrincipalController;
import Vista.VistaPrincipal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by miquelator on 19/3/18.
 */
public class ComunicacioServer extends Thread{


    private VistaPrincipalController controller;
    private Socket socketServer;
    private   DataOutputStream outToServer;

//bullshit

    public void run() {
        try {
            //creem el nostre socket
            InetAddress iAddress = InetAddress.getLocalHost();
            String IP = iAddress.getHostAddress();
            socketServer = new Socket(String.valueOf(IP), 33334);
            outToServer = new DataOutputStream(socketServer.getOutputStream());


        } catch (Exception e) {

        }
    }

    public   void autenticar (){

        try {
            outToServer.writeUTF("autenticar");

        }catch (IOException | NullPointerException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
        }

    }

    public   void veureCarta (){

        try {
            outToServer.writeUTF("veure carta");

        }catch (IOException | NullPointerException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
            //TODO: AMAGAR LA VISTA DE LA CARTA SI NO HI HA CONEXIO AL SERVIDOR?
        }

    }
    public   void veureEstat (){

        try {
            outToServer.writeUTF("veure estat");

        }catch (IOException | NullPointerException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
        }

    }

    public void setController (VistaPrincipalController c){
        controller = c;
    }



    public   void pagar (){

        try {
            outToServer.writeUTF("pagar");

        }catch (IOException | NullPointerException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
        }

    }
}
