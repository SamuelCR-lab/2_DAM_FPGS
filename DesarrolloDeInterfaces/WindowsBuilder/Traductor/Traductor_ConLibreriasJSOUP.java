package Traductor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Traductor_ConLibreriasJSOUP {
	public static Scanner entrada = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		/*URL direccion = new URL("https://www.spanishdict.com/translate/take");
		String palabraTraducida = "";
		String html = obtenerHTML(direccion);
		System.out.println(""+html);
		palabraTraducida=cortarHTML(html);*/
		System.out.println("Introduce la palabra que quieres traducir de español a ingles o ingles a español: ");
		String palabraInicial = entrada.nextLine();
		String web = "https://www.spanishdict.com/translate/"+palabraInicial;
		Document documento = Jsoup.connect(web).get();
		Element palabraTraducida = documento.select("div#quickdef1-en a.tCur1iYh").get(0);
		String resultado=palabraTraducida.html().toUpperCase();
		System.out.println(resultado);
		
 	}
}

	
