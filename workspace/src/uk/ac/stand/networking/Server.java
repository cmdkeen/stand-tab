package uk.ac.stand.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	private int port;
	private ServerSocket socket;
	
	public Server(int port){
		this.port = port;
		new Thread(this).start();
	}

	public void run (){
		socket = createServerSocket();

		while(true){			
			Socket s,r = null;
			try {
				s = socket.accept();
				
				ObjectInputStream in = new ObjectInputStream(s.getInputStream());
				
				Object command = in.readObject();
				Object params = in.readObject();
				
				r = new Socket(s.getInetAddress(),s.getPort());
				
				ObjectOutputStream out = new ObjectOutputStream(r.getOutputStream());
				
				Result result = new Result();
				
				out.writeObject(result);
				
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.exit(-1);
			}			
		}
		
	}

	private ServerSocket createServerSocket() {
		ServerSocket ss = null;
		while(ss == null){
			try {
				ss = new ServerSocket(port);
			} catch (IOException e) {
				try {
					Thread.sleep(1000);
					ss = null;
				} catch (InterruptedException e1) {	}
			}
		}
		return ss;
	}

}
