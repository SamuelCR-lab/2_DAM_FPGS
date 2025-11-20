package TraductorEnClases;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Operaciones {
	
	public static String traducirAesp (String palabraIntroducida) {
		String web = "https://www.spanishdict.com/translate/"+palabraIntroducida;
		Document documento;
		String resultado ="No existe";
		try {
			documento = Jsoup.connect(web).get();
			Elements palabraTraducida = documento.select("div#quickdef1-en a.tCur1iYh");
			if (palabraTraducida.isEmpty()) {
				palabraTraducida.add(traducirAIngles(palabraIntroducida));
				Element elementoPalabraTraducida=palabraTraducida.getLast();
				
				resultado=elementoPalabraTraducida.html().toUpperCase();
				System.out.println(resultado);
			}else {
				Element elementoPalabraTraducida=palabraTraducida.first();
				resultado=elementoPalabraTraducida.html().toUpperCase();
				System.out.println(resultado);
			}
			/*if (resultado.indent(0)==resultado.length()) {
				resultado=traducirAIngles(palabraIntroducida);
			}*/
		} catch (IOException e)  {
			e.printStackTrace();
			
		}
		
		return resultado;
		
	}
	public static Element traducirAIngles (String palabraIntroducida) {
		String web = "https://www.spanishdict.com/translate/"+palabraIntroducida;
		Document documento;
		Element resultado = null ;
		try {
			documento = Jsoup.connect(web).get();
			Element palabraTraducida = documento.select("div#quickdef1-es a.tCur1iYh").get(0);
			resultado=palabraTraducida;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
		
	}

}
