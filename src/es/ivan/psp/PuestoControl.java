package es.ivan.psp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.spi.RegisterableService;

public class PuestoControl {
	
	//el puesto de control es el cliente, que va ha ir informando de los datos al server
	// idControl, 
	//en los argumentos del main tengo el idControl (tendré que aumentarlo cada vez que se ejecute apra que sea unico) y la ip de server
	//registramos, datos que enviamos al server, IDCONTROL + IDPARTICIPANTE + FECHAYHORA.
	
	private static String idControl;
	private static String idParticipante;
	private static Date fechaHora;
	
	public static void main(String[] args) {
		
		System.out.println("*** Bienvenido ***\n¿Que desea hacer?\n1) Registrar participante\n2) Salir");
		Scanner sc = new Scanner(System.in);
		String op = sc.nextLine();
		
		
		do {
			
			switch (op) {
			case "1":
				
				Boolean recuestServer = registrar(args);
				
				if(recuestServer) {
				
					System.out.println("** ** Registro realizado correctamente. ** **");
					
				
				}else {
				
					System.out.println("## No se ha podido registrar ##");
				}
				
				break;
			
			case "2":
				System.out.println("* Adios.\nCerrando aplicacion...");
				Socket socket = null;
				
				try {
					
					socket = new Socket("localhost", Integer.parseInt(args[1]));
					InputStreamReader isr;
					OutputStreamWriter osw;
					//isr = new InputStreamReader(socket.getInputStream());
					//BufferedReader br = new BufferedReader(isr);
					
					//conecto el canal de envio de informacion
					osw = new OutputStreamWriter(socket.getOutputStream());
					BufferedWriter bw = new BufferedWriter(osw);
					//con true hace el auto flus (para liberar ekl buffer)
					PrintWriter pw = new PrintWriter(bw, true);
					pw.println("BYE");
					
					op = "2";
					
				}catch (NumberFormatException e) {
					
					System.out.println("fallo al convertir el numero de puerto");
					e.printStackTrace();
					
				} catch (UnknownHostException e) {
					
					System.out.println("Host no encontrado");
					e.printStackTrace();
					
				} catch (IOException e) {
					System.out.println("Error de conexion al servidor");
					e.printStackTrace();
				}
				
				break;

			default:
				System.out.println("# Elija una opcion coreccta, gracias.");
				
				break;
		}//fin switch
			if (!op.equals("2")) {
				System.out.println(" ¿Que desea hacer?\n1) Registrar participante\n2) Salir");
				op = sc.nextLine();
				
			}
			
			
		}while(!op.equalsIgnoreCase("2"));
		
		System.out.println("Puesto de control cerrado.");
		sc.close();

	}//fin main
	
	
	
	public static boolean registrar(String[] args) {
		
		//numero de idControl en args[0] = 0
				//numero de puerto en args[1] = 7777
				
				//obtengo la fecha actual con el objeto de la clase Date
				fechaHora = new Date();
				//declaro el objeto socket
				Socket socket = null;
				
				try {
					//nombre, el localhost y el puerto para conectarse, como argumeto al main
					socket = new Socket("localhost", Integer.parseInt(args[1]));
					
				} catch (NumberFormatException e) {
					
					System.out.println("fallo al convertir el numero de puerto");
					e.printStackTrace();
					
				} catch (UnknownHostException e) {
					
					System.out.println("Host no encontrado");
					e.printStackTrace();
					
				} catch (IOException e) {
					System.out.println("Error de conexion al servidor");
					e.printStackTrace();
				}
				
				System.out.println("Se ha establecido la conexion con el servidor.....");
				
				//conecto el canal de recepcion
				InputStreamReader isr;
				OutputStreamWriter osw;
				
				//conecto el canal de recepcion de informacion
				try {
					
					isr = new InputStreamReader(socket.getInputStream());
					BufferedReader br = new BufferedReader(isr);
					
					//conecto el canal de envio de informacion
					osw = new OutputStreamWriter(socket.getOutputStream());
					BufferedWriter bw = new BufferedWriter(osw);
					//con true hace el auto flus (para liberar ekl buffer)
					PrintWriter pw = new PrintWriter(bw, true);
					
					
					Scanner sc = new Scanner(System.in);
					String textoCliente = "";
					String textoServidor = "";
					
					
					//tengo que leer primero del servidor
					System.out.println(br.readLine());
					
					do {
						//pido el numero de participante
						System.out.println("Registre el numero de participante: ");
						idParticipante = sc.nextLine();
						
						//monto el string a enviar al servidor como texto cliente
						idControl = args[0];
						textoCliente = idControl + " " + idParticipante + " " + fechaHora;
						
						//lo envio al server
						pw.println(textoCliente);
						//espero la respuesta del server, me tiene que dar el  ACK
						//y lo comprobare con un IF para seaber la respuesta
						textoServidor = br.readLine();
						
						System.out.println("RESPUESTA SERVER: "+textoServidor);
						
						if(textoServidor.equalsIgnoreCase("OK")) {
							return true;
						}else {
							//return false;
						}
						
						
					}while(!textoCliente.equals("BYE"));
					
					sc.close();
					isr.close();
					osw.close();
					socket.close();
					
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		
		
		
		
		
		
		return false;
		
	}

}
