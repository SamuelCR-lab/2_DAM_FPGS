package PracticaAbstractos;

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
	static ArrayList <Comun> almRevista = new ArrayList<>();
	static ArrayList <Comun> almLibro = new ArrayList<>();
	static Scanner entrada = new Scanner(System.in);
	public static void main(String[] args) {
		menu();
		
		
	}
	public static void menu() {
		int codigo;
		String titulo;
		int anio;
		boolean bandera = true;
		do {
		System.out.println("Bienvenido a la biblioteca de Samu: "
				+ "1. Introducir revista."
				+ "2. Introducir Libro."
				+ "3. Prestar."
				+ "4. Devolver." 
				+ "5. Prestado."
				+ "6. Salir.");
		int opcion = entrada.nextInt();
		switch (opcion){
		case 1:
			System.out.print("Introduce el codigo de la revista: ");
			codigo = entrada.nextInt();
			System.out.print("Introduce el Nombre de la revista: ");
			titulo = entrada.nextLine();
			entrada.nextLine();
			System.out.print("Introduce el anio de la revista: ");
			anio = entrada.nextInt();
			System.out.print("Introduce el nº de la revista: ");
			int nRevista = entrada.nextInt();
			almRevista.add(new Revista(codigo,titulo,anio,nRevista));
			break;
		case 2:
			System.out.print("Introduce el codigo de la Libro: ");
			codigo = entrada.nextInt();
			System.out.print("Introduce el Nombre de la Libro: ");
			titulo = entrada.nextLine();
			entrada.nextLine();
			System.out.print("Introduce el anio de la Libro: ");
			anio = entrada.nextInt();
			almLibro.add (new Libros(codigo,titulo,anio));
			break;
		case 3:
			Libros.prestar();
			System.out.println("");
			break;
		case 4:
			Libros.devolver();
			System.out.println("");
			break;
		case 5:
			if(Libros.prestado()== true) {
				System.out.println("El libro no esta disponible.");
			}else {
				System.out.println("El libro esta disponible.");
			}
			break;
		default:
			System.out.println("BYE BYE....");
			bandera = false;
		}
	}while(!bandera);
		}
}
