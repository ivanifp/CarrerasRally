package es.ivan.psp;

import java.util.HashMap;
import java.util.Map.Entry;

public class Test {
	
	private HashMap<String, HashMap<String, String[]>> registroCoches = new HashMap<>();
	private static HashMap<String, String[]> regCoche = new HashMap<>();
    private static String[] datosCoche;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Test t = new Test();
		
		datosCoche = new String[] {"0","23223","aaa"};
		t.setRegCoche(datosCoche);
		
		datosCoche = new String[] {"0","XXX","aaa"};
		t.setRegCoche(datosCoche);
		
		t.getAll();
		
		datosCoche = new String[] {"111","23223","aaabbb"};
		t.setRegCoche(datosCoche);
		
		datosCoche = new String[] {"111","XXX","aaabbb"};
		t.setRegCoche(datosCoche);
		
		t.getAll();
		
		

	}
	



	public synchronized void setRegCoche(String[] datosCoche) {
		
		//obtengo el hasMap del server
		
		
		
		
		//tengo que comprobar si existe la clave en el diccionario, 
		if(registroCoches.containsKey(datosCoche[0])) {
			//si ya existe, agrego otro diccionario (con los datos del coche)
			//regCoche.put(datosCoche[1], datosCoche);
			System.out.println("PC-----");
			regCoche = registroCoches.get(datosCoche[0]);
			
			regCoche.put(datosCoche[1], datosCoche);
			
			registroCoches.put(datosCoche[0], regCoche);
			
			
		}else {
			System.out.println("Creo un nuevo PC-----");
			//si no existe, lo inyecto con un nuevo diccionario(con los datos del coche)
			
			//regCoche.put(datosCoche[1], datosCoche);
			//registroCoches.put(datosCoche[0], regCoche);
			registroCoches.put(datosCoche[0], new HashMap<String, String[]>(){{put(datosCoche[1], datosCoche);}});
			
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
