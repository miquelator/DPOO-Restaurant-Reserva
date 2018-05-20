package Network;

import Controlador.ActionListener.PrincipalController;
import Model.Carta;
import Model.CartaSelection;
import Model.CartaStatus;
import Model.ConfigJson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;



/**
 * Class that allows for the communication with the server
 */
public class ComunicationServer extends Thread{


    private PrincipalController controller;
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


    /***
     * Send the request to the server to check if a user has valid credentials to login
     * @param userName Name of the user
     * @param password User's password
     * @return Boolean with the answer of the server of whether the operation was successful

     */
    public boolean autenticar(String userName, String password){
        try {
            outToServer.writeUTF("AUTHENTICATE");
            outToServer.writeUTF(userName);
            outToServer.writeUTF(password);

            return inToServer.readBoolean();

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


    /***
     * Send the request to send and order to the server
     * @param cartaSelection List of the products selected
     * @return Boolean with the answer of the server of whether the operation was successful

     */
    public boolean enviaComanda (ArrayList<CartaSelection> cartaSelection){
        try{
            outToServer.writeUTF("ORDER");
            ooStream.reset();
            ooStream.writeObject(cartaSelection);
            return inToServer.readBoolean();
        }catch (IOException e){
        }
        return false;
    }


    /***
     * Send the request to the server to retrieve the list of availible dishes
     * @param seleccio Integer that determines the type of dish that we want to check
     * @return List of products
     */
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



    /***
     * Send the request to the server to retrieve the list of orders it has
     * @return List of orders

     */
    public   ArrayList<CartaStatus> veureEstat (){

        try {
            outToServer.writeUTF("SHOW_STATUS");

           return(ArrayList<CartaStatus>) oiStream.readObject();

        }catch (IOException | NullPointerException |ClassNotFoundException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
        }

        return null;
    }

    public void setController (PrincipalController c){
        controller = c;
    }



    /***
     * Notifies the server that the order is willing to pay and leave, requesting the amount he has to pay
     * @return amount to pay

     */
    public double pagar (){

        try {
            outToServer.writeUTF("PAY");
            return inToServer.readDouble();
        }catch (IOException | NullPointerException e){
            controller.mostraError("Error a l'hora de conectar-se al servidor!", "Error");
        }
        return -1;
    }

}
