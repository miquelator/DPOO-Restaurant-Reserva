package Network;

import Controlador.VistaPrincipalController;
import Vista.VistaPrincipal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by miquelator on 19/3/18.
 */
public class ComunicacioServer extends Thread{


    private VistaPrincipalController controller;
    private Socket socketServer;
    private   DataOutputStream outToServer;



    public void run() {
        try {
            //creem el nostre socket

            socketServer = new Socket("127.0.0.1", 33334);
            outToServer = new DataOutputStream(socketServer.getOutputStream());


        } catch (Exception e) {

        }
    }

    public   void autenticar (){

        try {
            outToServer.writeUTF("autenticar");

        }catch (IOException e){

        }

    }

    public   void veureCarta (){

        try {
            outToServer.writeUTF("veure carta");

        }catch (IOException e){

        }

    }
    public   void veureEstat (){

        try {
            outToServer.writeUTF("veure estat");

        }catch (IOException e){

        }

    }

    public void setController (VistaPrincipalController c){
        controller = c;
    }



    public   void pagar (){

        try {
            outToServer.writeUTF("pagar");

        }catch (IOException e){

        }

    }
}
