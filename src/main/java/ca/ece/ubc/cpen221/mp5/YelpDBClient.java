package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class YelpDBClient {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	public YelpDBClient(String hostname, int port) throws IOException {
		socket = new Socket(hostname, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	
	public void sendRequest(String x) throws IOException {
		out.print(x + "\n");
		out.flush();
	}
	
	public String getReply() throws IOException {
		String reply = in.readLine();
		if(reply == null) {
			throw new IOException("connection terminated unexpectedly");
		}
		
		return reply;
	}
	
	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}
	
	public static void main(String[] args) {
		try {
			YelpDBClient client = new YelpDBClient("localhost", YelpDBServer.YELPDB_PORT);
			
			String request = "GETRESTAURANT h_we4E3zofRTf4G0JTEF0A";
			
			client.sendRequest(request);
			System.out.println("request: " + request);
			
			String reply = client.getReply();
			System.out.println("reply: " + reply);
			
			client.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
