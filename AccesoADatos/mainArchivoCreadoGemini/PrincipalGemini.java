package mainArchivoCreadoGemini;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import EXAMEN_PRACTICA_FINAL_FICHEROS_2025.Empleado;
import EXAMEN_PRACTICA_FINAL_FICHEROS_2025.MenuGestores;
import EXAMEN_PRACTICA_FINAL_FICHEROS_2025.plantasClass;

public class PrincipalGemini {
	static Scanner entrada = new Scanner(System.in);
	static ArrayList <plantasClass> plantas = new ArrayList<>();
	static ArrayList <plantasClass> plantasVenta = new ArrayList<>();
	public static ArrayList <Empleado> empleados = new ArrayList<>();
	
    // --- RUTAS ESTATICAS PARA MEJOR MANEJO ---
    static final String DIR_PLANTAS = "Plantas";
    static final String FILE_PLANTAS_DAT = "Plantas//plantas.dat";
    static final String FILE_PLANTAS_XML = "Plantas//plantas.xml";
    static final String FILE_PLANTAS_BAJA_DAT = "Plantas//plantasBaja.dat";

    static final String DIR_EMPLEADOS = "Empleados";
    static final String FILE_EMPLEADOS_DAT = "Empleados//empleado.dat";
    static final String FILE_EMPLEADOS_BAJA_DAT = "Empleados//empleados_baja.dat";

    static final String DIR_TICKETS = "TICKETS";
    static final String DIR_DEVOLUCIONES = "Devoluciones";

	static int idIS;
    static String nombreEmpleadoIS;
    // Cada registro de planta en .dat (int:4, float:4, int:4) = 12 bytes
	static final long TAMANIO_REGISTRO_PLANTA = 12; 

	public static int controlErroresInt() {
		boolean error = true;
		int dato =0;
		do {
            // MEJORA: Validación para que no acepte negativos si no se espera
			if(entrada.hasNextInt()) {
				dato = entrada.nextInt();
                if (dato >= 0) {
				    error = false;
                } else {
                    System.out.println("ERROR, El número no puede ser negativo.");
                }
			}else {
				System.out.println("ERROR, Escribe un número válido.");
			}
			entrada.nextLine(); // Limpiar buffer
		}while(error);
		return dato;
	}

    // MEJORA: Control de errores para cantidad (debe ser mayor que 0)
    public static int controlErroresIntPositivo() {
		boolean error = true;
		int dato =0;
		do {
			if(entrada.hasNextInt()) {
				dato = entrada.nextInt();
                if (dato > 0) {
				    error = false;
                } else {
                    System.out.println("ERROR, La cantidad debe ser mayor que 0.");
                }
			}else {
				System.out.println("ERROR, Escribe un número válido.");
			}
			entrada.nextLine(); // Limpiar buffer
		}while(error);
		return dato;
    }

	public static int comprobacionContraseña(ArrayList<Empleado>empleadosO) {
		// Rúbrica 3.4 - Relleno de ceros (Asumimos ID de 4 dígitos)
        System.out.print("Introduce su número de identificación (4 dígitos): ");
        String idStr = entrada.nextLine();
        // Rellenar con ceros a la izquierda
        while (idStr.length() < 4) {
            idStr = "0" + idStr;
        }
        // Convertir a int solo para la lógica interna si es necesario, 
        // pero la comprobación se puede hacer con el String.
        // Aquí asumimos que el ID guardado es un int, así que convertimos.
        try {
             idIS = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID no numérico.");
            return 0;
        }

		System.out.print("Introduce la contraseña: ");
		String contrasenia = entrada.nextLine();

		for(Empleado empleado:empleadosO) {
			if((empleado.getIdentificacion() == idIS) && (empleado.getPassword().equals(contrasenia))){
				if(empleado.getCargo().equalsIgnoreCase("gestor")) { // Usar equalsIgnoreCase
					nombreEmpleadoIS = empleado.getNombre();
					return 1; // Es gestor
				}else {
					nombreEmpleadoIS = empleado.getNombre();
					return 2; // Es vendedor
				}
			}
		}

        // Rúbrica 3.3 - Control de errores
        System.out.println("Error: Usuario, contraseña o cargo incorrectos.");
		return 0; // No encontrado
	}
	
	public static void Catalogo() {
        // Rúbrica 1. Comprobación de directorios
		File directorio = new File(DIR_PLANTAS);
		File ficheroDAT = new File(FILE_PLANTAS_DAT);
		File ficheroXML = new File(FILE_PLANTAS_XML);
		if (!directorio.exists()) {
			directorio.mkdirs();
		}

        // Rúbrica 1.1 y 1.2
		if(!ficheroDAT.exists() || !ficheroXML.exists()){
			System.out.println("Ficheros de plantas no encontrados. Creando ficheros de ejemplo...");
			try {
				ficheroDAT.createNewFile();
                ficheroXML.createNewFile();
                // Si no existen, creamos datos de ejemplo (esta clase no la diste, pero es necesaria)
                // creacionEmpleadosYPlantas.EscribirFichero(); 
			} catch (IOException e) {
				System.out.println("Error crítico: No se pudieron crear los ficheros de plantas.");
                return; // Salir del método si no se pueden crear
			}
		}
		
        // Rúbrica 2.1 y 2.2: Carga de datos
		try {
            plantas.clear(); // Limpiamos el arraylist antes de cargar
			DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = docBF.newDocumentBuilder();
			Document doc = docB.parse(ficheroXML);
			doc.getDocumentElement().normalize();
			NodeList lista = doc.getElementsByTagName("planta");
			
            // Rúbrica 2.1: Lectura de fichero de acceso directo (plantas.dat)
			RandomAccessFile plantasDat = new RandomAccessFile (ficheroDAT,"r");
            
            if (plantasDat.length() == 0) {
                 System.out.println("El fichero plantas.dat está vacío.");
                 plantasDat.close();
                 return;
            }

            // Leemos el XML (nombres, desc) y el DAT (codigo, precio, stock)
			for (int i =0; i < lista.getLength(); i++) {
				Node nodo = lista.item(i);
				
                // Posicionamos el puntero del DAT
                plantasDat.seek(i * TAMANIO_REGISTRO_PLANTA);

				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element plantaElement = (Element)nodo;
                    
                    // Leemos del DAT
					int codigo = plantasDat.readInt();
					float precio = plantasDat.readFloat();
					int stock = plantasDat.readInt();

                    // Leemos del XML
					String nombre = plantaElement.getElementsByTagName("nombre").item(0).getTextContent();
					String foto = plantaElement.getElementsByTagName("foto").item(0).getTextContent();
					String descripcion = plantaElement.getElementsByTagName("descripcion").item(0).getTextContent();
					
                    // Rúbrica 2.2: Carga en ArrayList
                    // MEJORA: Solo añadimos plantas activas (con precio > 0)
                    if (precio > 0) {
					    plantas.add(new plantasClass(codigo,nombre,foto,descripcion,precio,stock));
                    }
				}
			}
			plantasDat.close();
		} catch(Exception e){
            // Rúbrica 2.5: Control de errores
            System.out.println("Error al cargar los datos de las plantas: " + e.getMessage());
            e.getStackTrace();
        }
	}
		

	public static void lecturaEmpleados() {
        // Rúbrica 1.4
		File directorio = new File(DIR_EMPLEADOS);
		if (!directorio.exists()) {
			directorio.mkdirs();
		}
        
        // Rúbrica 1.3
        File fichero = new File(FILE_EMPLEADOS_DAT);
        if (!fichero.exists()) {
            System.out.println("No se encuentra el fichero " + FILE_EMPLEADOS_DAT);
            // Aquí deberías tener un método para crear empleados por defecto o salir
            return;
        }

        // Rúbrica 2.3 y 2.4
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_EMPLEADOS_DAT))) {
			ArrayList<Empleado> listaEmpleados = (ArrayList<Empleado>) ois.readObject();
			empleados = listaEmpleados;
        } catch (EOFException ei) {
        	// Fin de fichero, no es un error
        } catch (Exception e) {
            // Rúbrica 2.5: Control de errores
            System.out.println("Error al cargar los datos de empleados: " + e.getMessage());
            e.printStackTrace();
        }
	}
		
	public static void mostrarCatalogo(ArrayList <plantasClass> plantasO){
        // Rúbrica 4.1: Listar datos (Unión de XML y DAT)
        // Esto ya se hizo en el método Catalogo() y se cargó en el ArrayList 'plantas'
        System.out.println("\n--- 🌿 CATÁLOGO DE PLANTAS 🌿 ---");
		for(plantasClass mostrarPlantas:plantasO) {
            // Mostramos solo las que tienen stock y precio (las de baja no se muestran)
            if (mostrarPlantas.getStock() > 0 && mostrarPlantas.getPrecio() > 0) {
			    System.out.println(mostrarPlantas.toString());
            }
		}
        System.out.println("------------------------------------");
	}
	
	public static plantasClass buscarPlantaPorCodigo(int codigo) {
        for (plantasClass p : plantas) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null; // No encontrada
    }

	public static void buscadorPlantaVenta(int codigoPlantaVenta, int cantidadPlantaVenta){
		plantasClass plantaEncontrada = buscarPlantaPorCodigo(codigoPlantaVenta);

        if (plantaEncontrada != null) {
            // Rúbrica 5.2: Comprobar el stock
            if(cantidadPlantaVenta <= plantaEncontrada.getStock()) {
                // Añadimos a la cesta temporal
                plantasVenta.add(new plantasClass(
                    plantaEncontrada.getCodigo(),
                    plantaEncontrada.getNombre(),
                    plantaEncontrada.getFoto(),
                    plantaEncontrada.getDescripcion(),
                    plantaEncontrada.getPrecio(),
                    cantidadPlantaVenta // <-- Usamos la cantidad de la venta
                ));
                
                // Actualizamos el stock en el ArrayList principal
                plantaEncontrada.setStock(plantaEncontrada.getStock() - cantidadPlantaVenta);
                
                System.out.println("✅ Añadido al carrito: " + cantidadPlantaVenta + "x " + plantaEncontrada.getNombre());

            } else {
                System.out.println("Error: No hay suficiente Stock. Stock actual: " + plantaEncontrada.getStock());
            }
        } else {
            System.out.println("Error: No se ha encontrado una planta con ese código.");
        }
	}

    // --- MEJORA: MÉTODO PARA ACTUALIZAR EL STOCK EN EL .DAT ---
    // Rúbrica 5.4.1: Modificación del campo de fichero de acceso directo
    public static void actualizarStockPlantaDAT(int codigoPlanta, int nuevoStock) {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PLANTAS_DAT, "rw")) {
            long posicion = 0;
            // Buscamos la posición del registro
            for (int i = 0; i < raf.length() / TAMANIO_REGISTRO_PLANTA; i++) {
                raf.seek(i * TAMANIO_REGISTRO_PLANTA);
                int codigoActual = raf.readInt();
                if (codigoActual == codigoPlanta) {
                    posicion = (i * TAMANIO_REGISTRO_PLANTA);
                    break;
                }
            }

            // Nos posicionamos en el campo de stock (código: 4 bytes, precio: 4 bytes)
            raf.seek(posicion + 4 + 4); 
            raf.writeInt(nuevoStock);
            
        } catch (IOException e) {
            System.out.println("Error al actualizar el stock en plantas.dat: " + e.getMessage());
        }
    }


	public static void generarVentas() {
		boolean seguirComprando = true;
        plantasVenta.clear(); // Limpiamos la cesta anterior

		System.out.println("--- 🛒 Nueva Venta ---");
		
        // Rúbrica 5.0: Permitir realizar la acción varias veces
        do {
            // Rúbrica 4.2: Redirigir desde el catálogo (aquí se pide el código)
            System.out.println("Introduce el Código de la planta (o 0 para finalizar):");
            int codigoPlantaVenta = controlErroresInt();

            if (codigoPlantaVenta == 0) {
                seguirComprando = false;
            } else {
                // Rúbrica 4.3 y 5.1: Introducir datos válidos
                System.out.println("Cantidad de la planta que quieres vender:");
                int cantidadPlantaVenta = controlErroresIntPositivo();
                
                buscadorPlantaVenta(codigoPlantaVenta, cantidadPlantaVenta);
            }
		}while(seguirComprando);

        if (plantasVenta.isEmpty()) {
            System.out.println("Venta cancelada. No hay productos en el carrito.");
            return;
        }

        // Rúbrica 5.3: Mostrar resumen compra
        System.out.println("\n--- 🧾 Resumen de la Compra ---");
        float totalPrevisualizacion = 0;
        for (plantasClass p : plantasVenta) {
            System.out.printf("  %dx %s (%.2f €/u) = %.2f €\n", 
                p.getStock(), p.getNombre(), p.getPrecio(), p.getStock() * p.getPrecio());
            totalPrevisualizacion += p.getStock() * p.getPrecio();
        }
        System.out.printf("  TOTAL: %.2f €\n", totalPrevisualizacion);
        System.out.println("---------------------------------");
        System.out.println("¿Confirmar venta? (1: Sí / 2: No)");
        int confirmar = controlErroresInt();

        if (confirmar == 1) {
            // Rúbrica 5.4: Aceptar venta y actualizar stock
            for (plantasClass pVendida : plantasVenta) {
                // El stock en el ArrayList 'plantas' YA se actualizó en buscadorPlantaVenta
                // Ahora actualizamos el fichero .dat
                plantasClass plantaOriginal = buscarPlantaPorCodigo(pVendida.getCodigo());
                if (plantaOriginal != null) {
                    // Rúbrica 5.4.1: Modificación del fichero de acceso directo
                    actualizarStockPlantaDAT(plantaOriginal.getCodigo(), plantaOriginal.getStock());
                }
            }
            
            // Rúbrica 5.5: Generar ticket
            System.out.println("Generando Ticket... ");
		    generarTicket(plantasVenta);
            System.out.println("Venta finalizada con éxito.");
        } else {
            System.out.println("Venta cancelada. Revirtiendo cambios de stock...");
            // Si cancela, hay que revertir el stock en el ArrayList
            for (plantasClass pVendida : plantasVenta) {
                plantasClass plantaOriginal = buscarPlantaPorCodigo(pVendida.getCodigo());
                if (plantaOriginal != null) {
                    plantaOriginal.setStock(plantaOriginal.getStock() + pVendida.getStock());
                }
            }
            System.out.println("Stock revertido.");
        }
        plantasVenta.clear();
	}

	public static int IDticketsGenerados() {
        // Rúbrica 5.5.1: Tomar el nombre del ticket secuencial
		int numTicketsCreados = 0;
		File directorio = new File(DIR_TICKETS);
        if (!directorio.exists()) {
            directorio.mkdirs();
            return 1; // Si no existe el directorio, este es el ticket 1
        }

		File[] ticketsCreados = directorio.listFiles();
		if(ticketsCreados == null || ticketsCreados.length == 0) {
			return 1;
		}

		Pattern patron = Pattern.compile("Tickets(\\d+)\\.txt");
        for (File archivo : ticketsCreados) {
            if (archivo.isFile()) {
                Matcher matcher = patron.matcher(archivo.getName());
                if (matcher.find()) {
                    try {
                        int id = Integer.parseInt(matcher.group(1));
                        if (id > numTicketsCreados) {
                        	numTicketsCreados = id;
                        }
                    } catch (Exception e) {
                        // Ignorar ficheros mal nombrados
                    }
                }
            }
        }
	  	return numTicketsCreados + 1;
	}

	public static void generarTicket(ArrayList<plantasClass> Venta){
		int iDTickets = IDticketsGenerados();
	  	float total=0;
	  	File directorio = new File(DIR_TICKETS);
	  	File ficheroTickets= new File(DIR_TICKETS + "//Tickets"+iDTickets+".txt");
	  	
	  	if(!directorio.exists()) {
		  	directorio.mkdirs();
	  	}
	  	
        // Rúbrica 5.5.2: Escribir el ticket en un fichero de caracteres
		try (BufferedWriter buffer = new BufferedWriter(new FileWriter(ficheroTickets))) {
            buffer.write("TICKET ID: "+iDTickets + "\n");
			buffer.write("----------------------//----------------------\n");
            // Rúbrica 5.5.2: Incluir datos del empleado
			buffer.write("Id de empleado: " + String.format("%04d", idIS) + "\n"); // Relleno de ceros
			buffer.write("Nombre del empleado: " + nombreEmpleadoIS + "\n\n");
			buffer.write("Codigo | Cantidad | PrecioUnitario | Subtotal\n");
            buffer.write("----------------------------------------------\n");

			for (plantasClass plantasVendidas : Venta) {
                int codigoProd = plantasVendidas.getCodigo();
				int cantidadProd = plantasVendidas.getStock(); // Usamos stock como cantidad
				float precio = plantasVendidas.getPrecio();
                float subtotal = cantidadProd * precio;
				
                buffer.write(String.format("%-6d | %-8d | %-14.2f | %.2f\n",
                     codigoProd, cantidadProd, precio, subtotal));
				
                // Rúbrica 5.4.2: Calcular el total
                total += subtotal;	
			}	             
			buffer.write("----------------------------------------------\n");
			buffer.write("TOTAL A PAGAR: " + String.format("%.2f", total) + " €\n\n");
	
		} catch (IOException i) {
		    System.out.println("Error al generar el ticket: " + i.getMessage());
		    i.printStackTrace();
		}
	}
	
    // --- FUNCIONALIDAD FALTANTE: DEVOLUCIÓN ---
    // Rúbrica 6: Devolución
	public static void generarDevolucion() {
        // Rúbrica 6.1: Buscar el ticket
        System.out.println("Escribe el Id del ticket a devolver: ");
        int idTicket = controlErroresInt();
        String nombreFichero = "Tickets" + idTicket + ".txt";
        File ficheroOriginal = new File(DIR_TICKETS, nombreFichero);
        
        if (!ficheroOriginal.exists()) {
            System.out.println("Error: El ticket " + nombreFichero + " no se encuentra en la carpeta " + DIR_TICKETS);
            return;
        }

        System.out.println("Procesando devolución para el ticket: " + nombreFichero);
        float totalDevolucion = 0;
        ArrayList<plantasClass> plantasDevueltas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ficheroOriginal))) {
            String linea;
            boolean esDevolucion = false;
            
            // Patrón para capturar las líneas de producto
            // Ejemplo: "1      | 2        | 15.50          | 31.00"
            Pattern pProductos = Pattern.compile("^(\\d+)\\s*\\|\\s*(\\d+)\\s*\\|");
            // Patrón para capturar el total
            Pattern pTotal = Pattern.compile("^TOTAL A PAGAR: ([-]?\\d+[.,]\\d{2})");

            while ((linea = reader.readLine()) != null) {
                if (linea.contains("-- DEVOLUCIÓN --")) {
                    esDevolucion = true;
                }

                Matcher mProd = pProductos.matcher(linea);
                if (mProd.find()) {
                    int codigo = Integer.parseInt(mProd.group(1).trim());
                    int cantidad = Integer.parseInt(mProd.group(2).trim());
                    plantasDevueltas.add(new plantasClass(codigo, "", "", "", 0, cantidad));
                }

                Matcher mTotal = pTotal.matcher(linea);
                if (mTotal.find()) {
                    totalDevolucion = Float.parseFloat(mTotal.group(1).replace(",", "."));
                }
            }

            if (esDevolucion) {
                System.out.println("Error: Este ticket ya ha sido devuelto.");
                return;
            }

            if (plantasDevueltas.isEmpty()) {
                System.out.println("Error: No se pudieron leer productos del ticket.");
                return;
            }

            // Rúbrica 6.4: Modificar el stock
            System.out.println("Actualizando stock...");
            for (plantasClass pDev : plantasDevueltas) {
                plantasClass plantaOriginal = buscarPlantaPorCodigo(pDev.getCodigo());
                if (plantaOriginal != null) {
                    int stockAntiguo = plantaOriginal.getStock();
                    int nuevoStock = stockAntiguo + pDev.getStock();
                    plantaOriginal.setStock(nuevoStock);
                    
                    // Guardamos en el .dat
                    actualizarStockPlantaDAT(plantaOriginal.getCodigo(), nuevoStock);
                    System.out.println("  Stock de " + plantaOriginal.getNombre() + " actualizado a " + nuevoStock);
                }
            }

            // Rúbrica 6.2 y 6.3: Escribir en el fichero
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheroOriginal, true))) {
                writer.newLine();
                writer.write("----------------------------------------------\n");
                writer.write("-- DEVOLUCIÓN --\n");
                writer.write("TOTAL DEVUELTO: " + String.format("%.2f", (totalDevolucion * -1)) + " €\n");
            }

            // Rúbrica 6.4 (Mover el ticket)
            File dirDestino = new File(DIR_DEVOLUCIONES);
            if (!dirDestino.exists()) {
                dirDestino.mkdirs();
            }
            Path rutaOrigen = ficheroOriginal.toPath();
            Path rutaDestino = Paths.get(dirDestino.getAbsolutePath(), ficheroOriginal.getName());
            Files.move(rutaOrigen, rutaDestino, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("Devolución completada. Ticket movido a " + DIR_DEVOLUCIONES);

        } catch (IOException e) {
            System.out.println("Error procesando la devolución: " + e.getMessage());
        }
	}
	
	public static void menuVendedores() {
		boolean bandera = false;
		System.out.println("\n\t\tBienvenido al menú de Vendedores.");
		// Catalogo(); // Se carga una sola vez al inicio del main
		do {
			System.out.println("\n1. Visualizar Catalogo\n"
							+ "2. Generar Venta\n"
							+ "3. Generar Devolución\n"
							+ "4. Salir.\n");
			System.out.print("Seleccione una opción: ");
			int eleccion = controlErroresInt();
			switch (eleccion) {
			case 1:
                // Rúbrica 4.1
				mostrarCatalogo(plantas);
				break;
			case 2:
                // Rúbrica 5
				generarVentas();
				break;
			case 3:
                // Rúbrica 6
				generarDevolucion();
				break;
			case 4:
				bandera = true;
				break;
			default:
				System.out.println("Opción no válida.");	
			}
		}while(!bandera);
	}
	
	public static void main(String[] args) {
		boolean bandera = true;
		System.out.println("============== Bienvenido al vivero Carías Ramos ==============\n");

        // Rúbrica 1 y 2: Carga inicial de todos los datos
		lecturaEmpleados();
        Catalogo(); 
        // Cargar también los ficheros de bajas
        //MenuGestores.cargarEmpleadosBaja();
        // (La carga de plantas de baja se puede hacer dentro del método de rescatar)

        if (empleados.isEmpty() || plantas.isEmpty()) {
            System.out.println("Error: No se han podido cargar los datos iniciales (empleados o plantas).");
            System.out.println("El programa no puede continuar.");
            return;
        }

		do{
            // Rúbrica 3: Identificación
			int tipoUsuario = 0;
            while (tipoUsuario == 0) {
                tipoUsuario = comprobacionContraseña(empleados);
            }

            // Rúbrica 3.2: Mostrar menú según el cargo
			switch (tipoUsuario) {
			case 1: // Gestor
                // Rúbrica 8
				MenuGestores.menuGestores(); // Pasamos los ArrayLists
				break;
			case 2: // Vendedor
                // Rúbrica 4
				menuVendedores();
				break;
			}

            System.out.println("\n¿Desea iniciar sesión con otro usuario o salir?");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir del programa");
            int salir = controlErroresInt();
            if (salir == 2) {
                bandera = false; // Termina el bucle principal
            }
			
		}while (bandera);

        // El guardado ya no se hace al final, se hace en cada operación.
		System.out.println("Gracias por usar el sistema. ¡Adiós!");
	}
}
