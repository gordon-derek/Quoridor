/**
 * 
 */
package quoridorClient;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @author Cody Hamilton
 * 
 */
public class ServerPlayer implements Player {
	/**
	 * 
	 * @param ip
	 */
	private Socket playerSocket;
	private PrintStream outToServer;
	private Scanner inFromServer;
	private String aiID;

	public ServerPlayer(String ip) throws Exception {
		// parse ip string to split into ip and port parts(seperated by ":")
		String ipAddress = "";
		String port = "";

		int i;
		for (i = 0; ip.charAt(i) != ':'; i++)
			ipAddress += ip.charAt(i);

		i++;

		for (;i < ip.length();i++)
			port += ip.charAt(i);
		

		int p = Integer.parseInt(port);
		playerSocket = new Socket(ipAddress, p);
		outToServer = new PrintStream(playerSocket.getOutputStream());
		inFromServer = new Scanner(playerSocket.getInputStream());
		aiID = "";
	}

	/* 
	 * twoWayMessage sends messages to the server that requires
	 * a reply.
	 */
	public String twoWayMessage(String message){
		outToServer.println(message);
		return inFromServer.nextLine();
	}

	/*
	 * oneWayMessage sends a message to the server that doesn't
	 * require a reply.
	 */
	public void oneWayMessage(String message){
		outToServer.println(message);
	}
	
	public String getMessage(){
		return inFromServer.nextLine();
	}
	
	public String getID(){
		return aiID;
	}
	
	public void setID(String id){
		aiID = id;
	}
}
