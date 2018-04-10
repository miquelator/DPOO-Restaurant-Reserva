package Network;

import Controlador.VistaPrincipalController;
import Model.Carta;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by miquelator on 19/3/18.
 */
public class ComunicacioServer extends Thread{


    private VistaPrincipalController controller;
    private Socket socketServer;
    private DataOutputStream outToServer;
    private ObjectOutputStream ooStream;
    private ObjectInputStream oiStream;

//bullshit

    public void run() {
        try {
            //creem el nostre socket
            InetAddress iAddress = InetAddress.getLocalHost();
            String IP = iAddress.getHostAddress();
            socketServer = new Socket(String.valueOf(IP), 33334);
            outToServer = new DataOutputStream(socketServer.getOutputStream());
            ooStream = new ObjectOutputStream(socketServer.getOutputStream());
            oiStream = new ObjectInputStream(socketServer.getInputStream());

        } catch (Exception e) {

        }
    }

    public   void autenticar (){

        try {
            outToServer.writeUTF("AUTHENTICATE");

        }catch (IOException | NullPointerException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
        }

    }

    public ArrayList<Carta> veureCarta (){

        try {
            outToServer.writeUTF("SHOW_MENU");

            ArrayList<Carta> carta = (ArrayList<Carta>) oiStream.readObject();
            return carta;
        }catch (IOException | NullPointerException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
            //TODO: AMAGAR LA VISTA DE LA CARTA SI NO HI HA CONEXIO AL SERVIDOR?
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
    public   void veureEstat (){

        try {
            outToServer.writeUTF("SHOW_STATUS");

        }catch (IOException | NullPointerException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
        }

    }

    public void setController (VistaPrincipalController c){
        controller = c;
    }



    public   void pagar (){

        try {
            outToServer.writeUTF("PAY");

        }catch (IOException | NullPointerException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
        }

    }
}
