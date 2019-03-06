package es.ivan.psp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Hashtable;

public class ThreadSockectServer extends Thread{
	
	private Socket socket;
	private HashMap<String, HashMap> registroCoches = new HashMap<>();
	private HashMap<String, String[]> regCoche = new HashMap<>();
    private String[] datosCoche;
	private Server ser;
	private  AlmacenDatos almacenDatos;  
	
 
	public ThreadSockectServer(String name, Socket socket, AlmacenDatos almacenDatos) {
		super(name);
		this.socket = socket;
		this.almacenDatos = almacenDatos;
	}






	@Override
	public void run() {
		
		InputStreamReader isr;
		BufferedReader br;
		
		//Server ser = new Server();
		
		
		try {
			// Conecto el canal de recepci�n de informaci�n
			isr = new InputStreamReader(socket.getInputStream());
			br = new BufferedReader(isr);
			
			// Conecto el canal de envio de informaci�n
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			BufferedWriter bw = new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(bw,true);
			
			pw.println("SERVER: servidor esta esperando la recepcion del registro");
			String texto = "";
			do {
				System.out.println("esperando la lectura del pc");
				//recibir informacion
				texto = br.readLine();
				if(texto.equalsIgnoreCase("BYE")) {
					System.out.println("El puesto de control ha cerrado");
					texto = "BYE";
					
					
				}else {
					
					System.out.println("Peticion: " + this.getName() + ": " + texto);
					
					//tengo que registrar la info que recibo del puesto de control
					
					datosCoche = texto.split(" ", 9); 
					//llamo a funcion para fijar
					almacenDatos.setRegCoche(datosCoche);
					
					
					System.out.println("RESPUESTA SERVER: Puesto de Control Nº "+datosCoche[0]+", "+this.getName()+": vehiculo "+ datosCoche[1] +", ha pasado el dia " + datosCoche[2]+" " +datosCoche[3]+datosCoche[4]+ " a las "+datosCoche[5]+" del  "+datosCoche[7]+" registrado correctamente");
					// Escribir la respuesta para el cliente
					pw.println("OK");
					
				}
				
				
				
				
			}while(!texto.equals("BYE"));
			
			isr.close();
			osw.close();
			socket.close();
			
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}//fin run 
	
	
	
	
	
	

}
