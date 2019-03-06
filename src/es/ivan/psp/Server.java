package es.ivan.psp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Scanner;


public class Server {
	
	private static AlmacenDatos almacenDatos;
	private static HashMap<String, HashMap<String, String[]>> registroCoches = new HashMap<>();

	
	
	public static void main(String[] args) {
		//servidor
		ServerSocket socketServer = null;
		almacenDatos = new AlmacenDatos(registroCoches);
		
		try {
			//nos creamos el objeto de tipo serverSocket pasandole el puerto como parametro
			socketServer = new ServerSocket(7777);

		} catch (IOException e) {
			System.out.println("ERROR: El puerto esta ocupado");
			e.printStackTrace();
			System.exit(-1);
		}
		
		
		
		int contador = 1;
		try {
			
			
			
			//lanzo el menu del server, le paso el objeto de la clase almacen
			ServerMenu sm = new ServerMenu(almacenDatos);
			sm.start();
			
			while (true) {
				
				
				System.out.println("El servidor esta esperando una conexion...");
				Socket socketCliente = socketServer.accept();
				System.out.println("Conexion aceptada con: "+socketCliente);

				//String ip = socketCliente.getInetAddress().getHostAddress();
				ThreadSockectServer cliente = new ThreadSockectServer("Registro Nº "+contador, socketCliente, almacenDatos);
				cliente.start();
								
				contador++;
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			try {
				socketServer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
		
		
		
		
		
		
	}//fin main
	
	
	
	

}
