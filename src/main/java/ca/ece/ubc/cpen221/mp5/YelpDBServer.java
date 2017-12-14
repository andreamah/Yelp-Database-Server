package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class YelpDBServer<T> {

	public static final int YELPDB_PORT = 4949;
	public static final int REQUEST_HANDLE = 0;
	public static final int REQUEST_INFO = 1;
	private ServerSocket serverSocket;
	private YelpDB<T> yelpDB;
	
	public YelpDBServer(int port, YelpDB<T> yelpDB) throws IOException {
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
				System.err.println("request: " + line);
				
				String[] request = line.split(" ", 2);
				
				String message;
				
				if(request[REQUEST_HANDLE].equals("GETRESTAURANT")) {
					message = GETRESTAURANT(request[REQUEST_INFO]);
				}
				else if(request[REQUEST_HANDLE].equals("ADDUSER")) {
					message = ADDUSER(request[REQUEST_INFO]);
				}
				else if(request[REQUEST_HANDLE].equals("ADDRESTAURANT")) {
					message = ADDRESTAURANT(request[REQUEST_INFO]);
				}
				else if(request[REQUEST_HANDLE].equals("ADDREVIEW")) {
					message = ADDREVIEW(request[REQUEST_INFO]);
				}
				else {
					message = "ERR: ILLEGAL_REQUEST";
				}
				
				System.err.println("reply: " + message);
				out.println(message);
				
				out.flush();
				
			}
		} finally {
			out.close();
			in.close();
		}
	}
	
	private String GETRESTAURANT(String business_id) {
		return null;
	}
	
	private String ADDUSER(String user_information) {
		return null;
	}
	
	private String ADDRESTAURANT(String restaurant_information) {
		return null;
	}
	
	private String ADDREVIEW(String review_information) {
		return null;
	}
	
	
	
}
