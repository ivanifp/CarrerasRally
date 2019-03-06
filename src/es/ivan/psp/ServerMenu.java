package es.ivan.psp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class ServerMenu extends Thread{
	
	private static HashMap<String, HashMap> registroCoches = new HashMap<>();
	private static HashMap<String, String[]> regCoche = new HashMap<>();
    private static String[] datosCoche;
    private static AlmacenDatos almacenDatos;

    ServerSocket socketServer = null;
    
    public ServerMenu(AlmacenDatos almacenDatos) {
    	
    		this.almacenDatos = almacenDatos;
    		
    		
    		
    }
    
	

	
    @Override
	public void run() {
		menu(almacenDatos);
		System.out.println("Cerrando menu servidor...");
		//tengo que enviar la info al server para terminar con el while
		
		//pruebas
		
		
		
	}

	
	
	public static void menu(AlmacenDatos almacenDatos) {
		
		//preparo el menu para el servidor
		
		System.out.println("¿Que desea realizar?\n1) Listar un participante \n2) Listar todos los participantes por un Puesto de Control\n3) Borrar datos\n4) Lista completa\n5) Salir");
		Scanner sc = new Scanner(System.in);
		String serverOp = sc.nextLine();
		
				
		while(!serverOp.equalsIgnoreCase("5")) {
			
			System.out.println("¿Que desea realizar?\n1) Listar un participante \n2) Listar todos los participantes por un Puesto de Control\n3) Borrar datos\n4) Lista completa\n5) Salir");
			
			serverOp = sc.nextLine();
			
			switch (serverOp) {
			case "1":
				//Listar la fecha y hora de paso de un participante por cada puesto de control.
				System.out.println("Introduzca el id del Participante a buscar..");
				String idParticipante = sc.nextLine();
				almacenDatos.getParticipante(idParticipante);
				break;
				
			case "2":
				//Listar la fecha y hora de paso de todos los participantes por un puesto de control.
				//puedo mostrar los PC disponibles
				almacenDatos.getPcDisponibles();
				System.out.println("Introduzca el id del Puesto de Control a buscar..");
				String idPuestoControl = sc.nextLine();
				almacenDatos.getPuestoControl(idPuestoControl);
				
				break;
				
			case "3":
				//Borrar todos los datos almacenados.
				almacenDatos.borrarAll();
				System.out.println("datos borrados.");
				break;
				
			case "4":
				//listar todo
				almacenDatos.getAll();
				break;
			case "5":
				//Salir
				System.out.println("Saliendo del server...");
				
				serverOp ="5";
				
				break;

			default:
				
				System.out.println("Opcion no valida.");
				break;
			}//fin switch
			
			
			
		}//fin while		
				
		sc.close();
		
	}
	
}//fin class ServerMenu
