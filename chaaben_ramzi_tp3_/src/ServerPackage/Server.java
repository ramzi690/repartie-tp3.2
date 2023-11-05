package ServerPackage;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import chaaben_ramzi_tp3_.Operation;


public class Server extends Thread{


	public static void main(String[] args) {
		new Server().start();
	}
	@Override	
	public void run() {
		
		try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Le serveur est en écoute sur le port 1234");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread clientThread = new Thread(new ClientProcess(clientSocket));
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        	}
    	}
	}

class ClientProcess extends Thread {
    Socket clientSocket;

    public ClientProcess(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        try {    		
    		System.out.println("Je suis connecté");
    		System.out.println("Nouvelle connexion entrante denus "+ clientSocket.getRemoteSocketAddress());
    		InputStream is = clientSocket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			Operation op = (Operation)ois.readObject();
			
			int p1 = op.getOp1();
			String p2 = op.getOperation();
			int p3 = op.getOp2();
			
			float nb=0;
			
			switch(p2) {
				case "+": nb = p1+p3;
						break;
				case "-": nb = p1-p3;
						break;
				case "*": nb = p1*p3;
						break;
				case "/": nb = p1/p3;
						break;
			}
			
			op.setResult(nb);
			OutputStream os = clientSocket.getOutputStream();
			ObjectOutputStream ooos = new ObjectOutputStream(os);
			ooos.writeObject(op);
			
        	clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
}