package Network;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by miquelator on 19/3/18.
 */
public class ComunicacioServer extends Thread{


    private Socket socketServer;

    public void run() {
        try {
            //creem el nostre socket

            socketServer = new Socket("127.0.0.1", 33334);
            DataOutputStream outToServer = new DataOutputStream(socketServer.getOutputStream());
            outToServer.writeUTF("helloo");


        } catch (Exception e) {

        }
    }
}
