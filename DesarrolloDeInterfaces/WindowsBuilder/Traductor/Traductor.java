package Traductor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class Traductor {

	public static void main(String[] args) throws MalformedURLException {
		URL direccion = new URL("https://www.spanishdict.com/translate/take");
		String palabraTraducida = "";
		String html = obtenerHTML(direccion);
		System.out.println(""+html);
		palabraTraducida=cortarHTML(html);
		
		
		
		
		
		
		
 	}

	private static String obtenerHTML(URL direccion) {
		String inputLine, codigo="";
		try(BufferedReader in = new BufferedReader(new InputStreamReader(direccion.openStream()))){
			
			while((inputLine=in.readLine())!=null) {
				codigo+=inputLine;
			}
			in.close();

		}catch(IOException i) {i.getStackTrace();
		}
		return codigo;
	}
	private static String cortarHTML(String html) {
		int inicio,puntofinal1,puntofinal2;
		inicio=html.indexOf("?langFrom=es\" class=\"tCur1iYh\">");//
		
				//Opcion1
				//Creamos una sub cadena y buscar desde ahi y 34 caracteres porque la referencia string
				String trozo = html.substring(inicio);
				puntofinal1 = trozo.indexOf("</a>");
				//Opcion2 
				//Buscar en la misma cadena
				puntofinal2= html.indexOf("</a>", inicio+31);//Este lee todo el archivo y guarda
				
		String resultado1= html.substring(inicio+31,inicio+puntofinal1);
		String resultado2= html.substring(inicio+31,puntofinal2);
		/*Pattern cadenaBuscada = Pattern.compile("?langFrom=es\" class=\"tCur1iYh\">");
		for (String palabraEnc: html) {
			
		}*/
				return resultado1;
	}

}
