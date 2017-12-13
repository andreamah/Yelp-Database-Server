package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class YelpDBServer {

	public static final int YELPDB_PORT = 4949;
	private ServerSocket serverSocket;
	private YelpDB yelpDB;
	
	public YelpDBServer(int port, YelpDB yelpDB) throws IOException {
		this.serverSocket = new ServerSocket(port);
		this.yelpDB = yelpDB;
	}
	
	public void serve() throws IOException {
		while(true) {
			
			Socket socket = serverSocket.accept();
			try {
				handle(socket);
			} catch(IOException ioe) {
				ioe.printStackTrace();
			} finally {
				socket.close();
			}
			
		}
	}
	
	private void handle(Socket socket) throws IOException {
		System.err.println("client connected");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		
		try {
			
			for(String line = in.readLine(); line != null; line = in.readLine()) {
				System.err.println("request; " + line);
				
				try {
					
				}
			}
		}
	}
	
	private void GETRESTAURANT(String business_id) {
	}
	
	
	
	
}
