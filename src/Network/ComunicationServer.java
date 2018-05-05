package Network;

import Model.Carta;
import Model.CartaSelection;
import Model.ConfigJson;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by miquelator on 19/3/18.
 */
public class ComunicationServer extends Thread{


    private Controlador.PrincipalController controller;
    private Socket socketServer;
    private DataOutputStream outToServer;
    private DataInputStream inToServer;
    private ObjectOutputStream ooStream;
    private ObjectInputStream oiStream;
    private ConfigJson configJson;


    public ComunicationServer (ConfigJson c){
        configJson = c;
    }
    public void run() {
        try {
            //creem el nostre socket

            socketServer = new Socket(configJson.getIp(), configJson.getPort_server());
            outToServer = new DataOutputStream(socketServer.getOutputStream());
            inToServer = new DataInputStream(socketServer.getInputStream());
            ooStream = new ObjectOutputStream(socketServer.getOutputStream());
            oiStream = new ObjectInputStream(socketServer.getInputStream());

        } catch (Exception e) {

        }
    }

    public boolean autenticar(String userName, String password){
        try {
            outToServer.writeUTF("AUTHENTICATE");
            outToServer.writeUTF(userName);
            outToServer.writeUTF(password);

            boolean b = inToServer.readBoolean();

            return  b;

        }catch (IOException | NullPointerException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
        }
        return false;
    }

    public void desconecta(){
        try{
            outToServer.writeUTF("DISCONNECT");

        }catch (IOException e){

        }

    }

    public boolean enviaComanda (ArrayList<CartaSelection> cartaSelection){
        try{
            outToServer.writeUTF("ORDER");
            ooStream.writeObject(cartaSelection);

            Boolean b = inToServer.readBoolean();
            if(b){
                return true;
            }else{
                return false;            }
        }catch (IOException e){

        }
        return false;
    }

    public ArrayList<Carta> veureCarta(int seleccio){
        try {
            outToServer.writeUTF("SHOW_MENU");
            outToServer.writeInt(seleccio);

            ArrayList<Carta> carta = (ArrayList<Carta>) oiStream.readObject();
            return carta;
        }catch (IOException | NullPointerException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
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

    public void setController (Controlador.PrincipalController c){
        controller = c;
    }



    public double pagar (){

        try {
            outToServer.writeUTF("PAY");
            double totalPagar = inToServer.readDouble();
            return totalPagar;
        }catch (IOException | NullPointerException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
        }
        return -1;
    }

}
