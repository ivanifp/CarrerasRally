package es.ivan.psp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class AlmacenDatos {
	
	private HashMap<String, HashMap<String, String[]>> registroCoches = new HashMap<>();
	private static HashMap<String, String[]> regCoche = new HashMap<>();
    private static String[] datosCoche;
    
    public AlmacenDatos() {}
    
    public AlmacenDatos(HashMap<String, HashMap<String, String[]>> registroCoches ) {
    	
    		this.registroCoches = registroCoches;
		
    	
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//HashMap<String, HashMap<String, String[]>> registroCoches = new HashMap<>();
		//HashMap<String, String[]> regCoche = new HashMap<>();
	    //String[] datosCoche;

	}
	
	
	
	
	public synchronized void setRegCoche(String[] datosCoche) {
		
		//obtengo el hasMap del server
		
		
		//tengo que comprobar si existe la clave en el diccionario, 
		if(registroCoches.containsKey(datosCoche[0])) {
			//si ya existe, agrego otro diccionario (con los datos del coche)
			//regCoche.put(datosCoche[1], datosCoche);
			
			regCoche = registroCoches.get(datosCoche[0]);
			
			regCoche.put(datosCoche[1], datosCoche);
			
			registroCoches.put(datosCoche[0], regCoche);
			
		}else {
			
			//si no existe, lo inyecto con un nuevo diccionario(con los datos del coche)
			//regCoche.put(datosCoche[1], datosCoche);
			//registroCoches.put(datosCoche[0], regCoche);
			HashMap<String, String[]> regCoche = new HashMap<>();
			regCoche.put(datosCoche[1], datosCoche);
			registroCoches.put(datosCoche[0], regCoche);
			
			//registroCoches.put(datosCoche[0], new HashMap<String, String[]>(){{put(datosCoche[1], datosCoche);}});

			
		}
		
		
		
	}
	
	public synchronized void borrarAll() {
		
		registroCoches.clear();
		
	}
	
	public synchronized void getPcDisponibles() {
		
		System.out.println("Puede consultar los siguientes Puesto de control \nque hay registrados hasta el mometo: ");
		
		// Imprimimos el Map con un Iterador
		Iterator it = registroCoches.keySet().iterator();
		while(it.hasNext()){
		  String key = (String) it.next();
		  System.out.println("Puesto Control nº: " + key);
		}
		
		
	}
	
	public synchronized void getAll() {
		
		System.out.println("Datos del HashMap");
		
		if (registroCoches.isEmpty()) {
			System.out.println("Registro de coches vacio");
		}else {
			System.out.println("Registro de coches ****");
			
			for (Entry<String, HashMap<String, String[]>> coche : registroCoches.entrySet()){
				String clave = coche.getKey();
				HashMap<String, String[]> hm = coche.getValue();
				
				for(Entry<String, String[]> participante : hm.entrySet()) {
					String cla = participante.getKey();
					String[] val = participante.getValue();
					
					for(int i = 0; i<val.length;i++) {
						
						System.out.println("Puesto de control: "+clave+"  ->  "+" Participante: "+cla+" "+ datos(i) +" "+val[i]);
					}//fin for
					
				}//fin for each 2
			
			}//fin for each 1
			
		}//fin else
		
	}//fin getAll
	
	public synchronized void getParticipante(String idParticipante) {
		
		System.out.println("Datos del HashMap");
		
		if (registroCoches.isEmpty()) {
			System.out.println("Registro de coches vacio");
		}else {
			System.out.println("Registro de coches ****");
			//clave Puestos de control
			for (Entry<String, HashMap<String, String[]>> coche : registroCoches.entrySet()){
				String clave = coche.getKey();
				HashMap<String, String[]> hm = coche.getValue();
				
				//clave idParticipante
				for(Entry<String, String[]> participante : hm.entrySet()) {
					
					
					String cla = participante.getKey();
					if(cla.equalsIgnoreCase(idParticipante)) {
						String[] val = participante.getValue();
						
						for(int i = 0; i<val.length;i++) {
							
							System.out.println("Puesto de control: "+clave+"  ->  "+" Participante: "+cla+" "+ datos(i) +" "+val[i]);
						}//fin for
						
					}
					
					
				}//fin for each 2
			
			}//fin for each 1
			
		}//fin else
		
	}//fin getAll
	
	
	public synchronized void getPuestoControl(String idPuestoControl) {
		
		System.out.println("Datos de los Puestos de Control");
		
		if (registroCoches.isEmpty()) {
			System.out.println("Registro de coches vacio");
		}else {
			System.out.println("Registro de coches por un puesto de control ****");
			
			for (Entry<String, HashMap<String, String[]>> coche : registroCoches.entrySet()){
				String clave = coche.getKey();
				if(clave.equalsIgnoreCase(idPuestoControl)) {
					
					HashMap<String, String[]> hm = coche.getValue();
					
					for(Entry<String, String[]> participante : hm.entrySet()) {
						String cla = participante.getKey();
						String[] val = participante.getValue();
						
						for(int i = 0; i<val.length;i++) {
							
							System.out.println("Puesto de control: "+clave+"  ->  "+" Participante: "+cla+" "+ datos(i) +" "+val[i]);
						}//fin for
						
					}//fin for each 2
					
				}//fin if, PC
			
			}//fin for each 1
			
		}//fin else
		
	}//fin getAll
	
	
	public static String datos(int i) {
	
		switch (i) {
		case 0:
			return "registrado por Puesto Control nº ";
		case 1:
			return "id Participante:";
		case 2:
			return "Dia:";
		case 3:
			return "Mes:";
		case 4:
			return "Dia:";
		case 5:
			return "Hora:";
		case 6:
			return "tipo horario:";
		case 7:
			return "Año:";
			
		default:
			return "";
		}
	}//fin datos

}
