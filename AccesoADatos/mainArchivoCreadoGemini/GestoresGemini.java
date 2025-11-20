package mainArchivoCreadoGemini;




	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Comparator;
	import java.util.HashMap;
	import java.util.LinkedHashMap;
	import java.util.Map;
	import java.util.Scanner;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.io.RandomAccessFile;
	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.transform.Transformer;
	import javax.xml.transform.TransformerFactory;
	import javax.xml.transform.dom.DOMSource;
	import javax.xml.transform.stream.StreamResult;
	import org.w3c.dom.Document;
	import org.w3c.dom.Element;
	import org.w3c.dom.Node;

import EXAMEN_PRACTICA_FINAL_FICHEROS_2025.Empleado;
import EXAMEN_PRACTICA_FINAL_FICHEROS_2025.plantasClass;

	public class GestoresGemini {

	    // Usamos las listas de Principal para que los cambios se reflejen en todo el programa
	    private static ArrayList<plantasClass> catalogoPlantas;
	    private static ArrayList<Empleado> listaEmpleados;
	    private static ArrayList<Empleado> empleadosDeBaja = new ArrayList<>();
	    
	    private static Scanner entrada = new Scanner(System.in);

	    // Rutas (las mismas que en Principal, idealmente serían una clase de Configuración)
	    private static final String FILE_EMPLEADOS_DAT = "Empleados//empleado.dat";
	    private static final String FILE_EMPLEADOS_BAJA_DAT = "Empleados//empleados_baja.dat";
	    private static final String FILE_PLANTAS_DAT = "Plantas//plantas.dat";
	    private static final String FILE_PLANTAS_XML = "Plantas//plantas.xml";
	    private static final String FILE_PLANTAS_BAJA_DAT = "Plantas//plantasBaja.dat";
	    // private static final String FILE_PLANTAS_BAJA_XML = "Plantas//plantasBaja.xml"; // Rúbrica menciona bajas, pero el código original era confuso. Usaremos solo .dat para bajas de plantas.
	    private static final long TAMANIO_REGISTRO_PLANTA = 12;

	    
	    // --- MÉTODOS DE ESCRITURA EN FICHEROS ---

	    // Rúbrica 8.2.1.3 / 8.2.2.3: Sobrescribir fichero empleado.dat
	    private static void guardarEmpleadosActivos() {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_EMPLEADOS_DAT))) {
	            oos.writeObject(listaEmpleados);
	        } catch (IOException e) {
	            System.err.println("Error al guardar empleados activos: " + e.getMessage());
	        }
	    }

	    // Rúbrica 8.2.2.2: Escribir el arrayList bajaEmpleado en el fichero
	    private static void guardarEmpleadosBaja() {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_EMPLEADOS_BAJA_DAT))) {
	            oos.writeObject(empleadosDeBaja);
	        } catch (IOException e) {
	            System.err.println("Error al guardar empleados de baja: " + e.getMessage());
	        }
	    }

	    // Rúbrica 8.2.3.1: Leer fichero de bajas empleado
	    public static void cargarEmpleadosBaja() {
	        File f = new File(FILE_EMPLEADOS_BAJA_DAT);
	        if (!f.exists()) return; // Si no existe, no hay nada que cargar

	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
	            empleadosDeBaja = (ArrayList<Empleado>) ois.readObject();
	        } catch (Exception e) {
	            System.err.println("Error al cargar empleados de baja: " + e.getMessage());
	        }
	    }

	    // Rúbrica 8.1.1.2: Escribir el fichero de acceso directo (y XML)
	    private static void aniadirPlantaFicheros(plantasClass nuevaPlanta) {
	        // 1. Añadir a plantas.dat (Acceso Directo)
	        try (RandomAccessFile raf = new RandomAccessFile(FILE_PLANTAS_DAT, "rw")) {
	            raf.seek(raf.length()); // Ir al final
	            raf.writeInt(nuevaPlanta.getCodigo());
	            raf.writeFloat(nuevaPlanta.getPrecio());
	            raf.writeInt(nuevaPlanta.getStock());
	        } catch (IOException e) {
	            System.err.println("Error al añadir planta en .dat: " + e.getMessage());
	        }

	        // 2. Añadir a plantas.xml (DOM)
	        try {
	            File fXml = new File(FILE_PLANTAS_XML);
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc;
	            Element rootElement;

	            if (fXml.exists() && fXml.length() > 0) {
	                 doc = db.parse(fXml);
	                 rootElement = doc.getDocumentElement();
	            } else {
	                 doc = db.newDocument();
	                 rootElement = doc.createElement("plantas");
	                 doc.appendChild(rootElement);
	            }

	            // Crear el nuevo nodo planta
	            Element planta = doc.createElement("planta");
	            rootElement.appendChild(planta);

	            // Añadir hijos (nombre, foto, descripcion)
	            Element nombre = doc.createElement("nombre");
	            nombre.appendChild(doc.createTextNode(nuevaPlanta.getNombre()));
	            planta.appendChild(nombre);

	            Element foto = doc.createElement("foto");
	            foto.appendChild(doc.createTextNode(nuevaPlanta.getFoto()));
	            planta.appendChild(foto);

	            Element descripcion = doc.createElement("descripcion");
	            descripcion.appendChild(doc.createTextNode(nuevaPlanta.getDescripcion()));
	            planta.appendChild(descripcion);

	            // Sobrescribir el XML
	            TransformerFactory tf = TransformerFactory.newInstance();
	            Transformer t = tf.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(fXml);
	            t.transform(source, result);

	        } catch (Exception e) {
	            System.err.println("Error al añadir planta en .xml: " + e.getMessage());
	        }
	    }

	    // Rúbrica 8.1.2.1: Modificar stock/precio en fichero de acceso directo
	    private static void actualizarPlantaDAT(int codigo, float nuevoPrecio, int nuevoStock) {
	         try (RandomAccessFile raf = new RandomAccessFile(FILE_PLANTAS_DAT, "rw")) {
	            long posicion = -1;
	            for (int i = 0; i < raf.length() / TAMANIO_REGISTRO_PLANTA; i++) {
	                raf.seek(i * TAMANIO_REGISTRO_PLANTA);
	                if (raf.readInt() == codigo) {
	                    posicion = (i * TAMANIO_REGISTRO_PLANTA);
	                    break;
	                }
	            }
	            
	            if (posicion != -1) {
	                raf.seek(posicion + 4); // Saltar el código (4 bytes)
	                raf.writeFloat(nuevoPrecio);
	                raf.writeInt(nuevoStock);
	            }
	        } catch (IOException e) {
	            System.out.println("Error al actualizar (dar de baja) la planta en .dat: " + e.getMessage());
	        }
	    }

	    // Rúbrica 8.1.2.2: Escribir en fichero de bajas
	    private static void aniadirPlantaBaja(plantasClass plantaBaja) {
	        // Usaremos un RandomAccessFile para el .dat de bajas
	        try (RandomAccessFile rafBaja = new RandomAccessFile(FILE_PLANTAS_BAJA_DAT, "rw")) {
	            rafBaja.seek(rafBaja.length()); // Ir al final
	            rafBaja.writeInt(plantaBaja.getCodigo());
	            rafBaja.writeFloat(plantaBaja.getPrecio()); // Guardamos el precio original
	        } catch (IOException e) {
	             System.err.println("Error al escribir en plantasBaja.dat: " + e.getMessage());
	        }
	    }

	    // Rúbrica 8.1.3.2 y 8.1.3.4: Leer y modificar fichero de bajas
	    private static float rescatarPrecioYBorrarDeBaja(int codigo) {
	        File fOriginal = new File(FILE_PLANTAS_BAJA_DAT);
	        File fTemp = new File(FILE_PLANTAS_BAJA_DAT + ".tmp");
	        float precioRescatado = -1;

	        try (RandomAccessFile rafOrig = new RandomAccessFile(fOriginal, "r");
	             RandomAccessFile rafTemp = new RandomAccessFile(fTemp, "rw")) {

	            while (rafOrig.getFilePointer() < rafOrig.length()) {
	                int codActual = rafOrig.readInt();
	                float precioActual = rafOrig.readFloat();
	                
	                if (codActual == codigo) {
	                    precioRescatado = precioActual; // Lo encontramos
	                } else {
	                    rafTemp.writeInt(codActual); // Lo copiamos al temporal
	                    rafTemp.writeFloat(precioActual);
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error leyendo fichero de bajas: " + e.getMessage());
	        }

	        // Reemplazamos el fichero original por el temporal
	        fOriginal.delete();
	        fTemp.renameTo(fOriginal);

	        return precioRescatado;
	    }


	    // --- GESTIÓN DE PLANTAS ---

	    // Rúbrica 8.1.1: Dar de alta planta
	    public static void altaPlanta() {
	        System.out.println("\n--- Alta Nueva Planta ---");
	        // Asumimos que el código es autoincremental basado en el tamaño
	        int codigo = catalogoPlantas.size() + 1; 
	        
	        System.out.print("Introduce el nombre: ");
	        String nombre = entrada.nextLine();
	        System.out.print("Introduce la URL de la foto: ");
	        String foto = entrada.nextLine();
	        System.out.print("Introduce la descripción: ");
	        String descripcion = entrada.nextLine();
	        System.out.print("Introduce el precio: ");
	        float precio = (float) PrincipalGemini.controlErroresIntPositivo();
	        System.out.print("Introduce el stock inicial: ");
	        int stock = PrincipalGemini.controlErroresIntPositivo();
	        
	        plantasClass nuevaPlanta = new plantasClass(codigo, nombre, foto, descripcion, precio, stock);

	        // Rúbrica 8.1.1.1: Añadir elemento al array
	        catalogoPlantas.add(nuevaPlanta);
	        
	        // Rúbrica 8.1.1.2: Escribir en ficheros (Persistencia)
	        aniadirPlantaFicheros(nuevaPlanta);

	        System.out.println("✅ Planta " + nombre + " (Cód: " + codigo + ") dada de alta.");
	    }

	    // Rúbrica 8.1.2: Dar de baja a la planta
	    public static void bajaPlanta() {
	        System.out.println("\n--- Baja de Planta ---");
	        PrincipalGemini.mostrarCatalogo(catalogoPlantas);
	        System.out.print("Ingrese código de la planta a dar de baja: ");
	        int codigoBaja = PrincipalGemini.controlErroresInt();

	        plantasClass plantaABajar = null;
	        for (plantasClass p : catalogoPlantas) {
	            if (p.getCodigo() == codigoBaja) {
	                plantaABajar = p;
	                break;
	            }
	        }

	        if (plantaABajar == null) {
	            System.out.println("Error: Planta no encontrada.");
	            return;
	        }

	        if (plantaABajar.getStock() > 0) {
	            System.out.println("Error: No se puede dar de baja una planta con stock (" + plantaABajar.getStock() + ").");
	            System.out.println("Realice una venta o ajuste el stock a 0 primero.");
	            return;
	        }

	        // Rúbrica 8.1.2.2: Escribir en fichero de bajas (guardamos precio original)
	        aniadirPlantaBaja(plantaABajar);

	        // Rúbrica 8.1.2.1: Modificar a precio=0, stock=0
	        actualizarPlantaDAT(plantaABajar.getCodigo(), 0.0f, 0);
	        
	        // Actualizamos también el ArrayList en memoria
	        plantaABajar.setPrecio(0.0f);
	        plantaABajar.setStock(0);

	        System.out.println("✅ Planta " + plantaABajar.getNombre() + " dada de baja (precio y stock a 0).");
	    }

	    // Rúbrica 8.1.3: Rescatar planta
	    public static void rescatarPlanta() {
	        System.out.println("\n--- Rescatar Planta ---");
	        System.out.print("Ingrese código de la planta a rescatar (dada de baja): ");
	        int codigoRescate = PrincipalGemini.controlErroresInt();

	        // Rúbrica 8.1.3.2: Leer fichero bajas y obtener precio
	        float precioOriginal = rescatarPrecioYBorrarDeBaja(codigoRescate);

	        if (precioOriginal == -1) {
	            System.out.println("Error: La planta no se encuentra en el fichero de bajas.");
	            return;
	        }

	        plantasClass plantaARescatar = null;
	        for (plantasClass p : catalogoPlantas) {
	            if (p.getCodigo() == codigoRescate) {
	                plantaARescatar = p;
	                break;
	            }
	        }

	        if (plantaARescatar == null) {
	            System.out.println("Error: La planta existe en bajas pero no en el catálogo principal (Error de sincronización).");
	            return;
	        }

	        System.out.print("Introduce el nuevo stock para la planta: ");
	        int nuevoStock = PrincipalGemini.controlErroresIntPositivo();

	        // Rúbrica 8.1.3.3: Modificar fichero de acceso directo
	        actualizarPlantaDAT(codigoRescate, precioOriginal, nuevoStock);

	        // Actualizamos el ArrayList en memoria
	        plantaARescatar.setPrecio(precioOriginal);
	        plantaARescatar.setStock(nuevoStock);

	        System.out.println("✅ Planta " + plantaARescatar.getNombre() + " rescatada con precio " + precioOriginal + " y stock " + nuevoStock + ".");
	    }

	    // --- GESTIÓN DE EMPLEADOS ---

	    // Rúbrica 8.2.1: Dar de alta empleado
	    public static void darAltaEmpleado() {
	        System.out.println("\n--- Alta Nuevo Empleado ---");
	        System.out.print("Ingrese ID (4 dígitos): ");
	        int id = PrincipalGemini.controlErroresInt(); // Asumimos que controlErroresInt valida formato
	        
	        // Rúbrica 8.2.1.2: Control de errores
	        for (Empleado e : listaEmpleados) {
	            if (e.getIdentificacion() == id) {
	                System.out.println("Error: Ya existe un empleado con ese ID.");
	                return;
	            }
	        }

	        System.out.print("Introduzca el nombre: ");
	        String nombre = entrada.nextLine();
	        System.out.print("Introduzca la contraseña: ");
	        String contrasenia = entrada.nextLine();
	        
	        String cargo = "";
	        while (!cargo.equals("gestor") && !cargo.equals("vendedor")) {
	            System.out.print("Introduzca el cargo (gestor / vendedor): ");
	            cargo = entrada.nextLine().toLowerCase();
	        }

	        // Rúbrica 8.2.1.1: Añadir al ArrayList
	        listaEmpleados.add(new Empleado(id, nombre, contrasenia, cargo));
	        
	        // Rúbrica 8.2.1.3: Escribir en fichero
	        guardarEmpleadosActivos(); 

	        System.out.println("✅ Empleado " + nombre + " dado de alta.");
	    }

	    // Rúbrica 8.2.2: Dar de baja Empleado
	    public static void darBajaEmpleado() {
	        System.out.println("\n--- Baja de Empleado ---");
	        listaEmpleados.forEach(System.out::println);
	        System.out.print("Ingrese ID del empleado a dar de baja: ");
	        int idEmpleadoBaja = PrincipalGemini.controlErroresInt();
	        
	        Empleado empleadoBaja = null;
	        // Rúbrica 8.2.2.1: Eliminarle de las altas
	        for (int i = 0; i < listaEmpleados.size(); i++) {
	            if (listaEmpleados.get(i).getIdentificacion() == idEmpleadoBaja) {
	                empleadoBaja = listaEmpleados.remove(i);
	                break;
	            }
	        }
	        
	        if (empleadoBaja != null) {
	            // Rúbrica 8.2.2.1: Escribir en ArrayList baja
	            empleadosDeBaja.add(empleadoBaja);
	            
	            // Rúbrica 8.2.2.2 y 8.2.2.3: Sobreescribir ambos ficheros
	            guardarEmpleadosActivos();
	            guardarEmpleadosBaja();
	            System.out.println("Empleado " + empleadoBaja.getNombre() + " dado de baja.");
	        } else {
	            System.out.println("Error: Empleado no encontrado.");
	        }
	    }

	    // Rúbrica 8.2.3: Rescatar empleado
	    public static void recuperarEmpleadoBaja() {
	        System.out.println("\n--- Recuperar Empleado de Baja ---");
	        if (empleadosDeBaja.isEmpty()) {
	            System.out.println("No hay empleados en la lista de baja.");
	            return;
	        }

	        // Rúbrica 8.2.3.1: Leer fichero (ya está cargado en memoria en 'empleadosDeBaja')
	        empleadosDeBaja.forEach(System.out::println);
	        System.out.print("Ingrese ID del empleado a recuperar: ");
	        int idEmpleadoRecu = PrincipalGemini.controlErroresInt();
	        
	        Empleado empleadoRecu = null;
	        // Rúbrica 8.2.3.2: Obtener y eliminar del array de bajas
	        for (int i = 0; i < empleadosDeBaja.size(); i++) {
	            if (empleadosDeBaja.get(i).getIdentificacion() == idEmpleadoRecu) {
	                empleadoRecu = empleadosDeBaja.remove(i);
	                break;
	            }
	        }
	        
	        if (empleadoRecu != null) {
	            listaEmpleados.add(empleadoRecu);
	            
	            // Rúbrica 8.2.3.3 y 8.2.3.4: Sobreescribir ambos ficheros
	            guardarEmpleadosActivos();
	            guardarEmpleadosBaja();
	            System.out.println("Empleado " + empleadoRecu.getNombre() + " ha sido recuperado.");
	        } else {
	            System.out.println("Error: Empleado no encontrado en la lista de baja.");
	        }
	    }

	    // --- FUNCIONALIDAD FALTANTE ---
	    // Rúbrica 8.3: Calcular estadisticas
	    public static void mostrarEstadisticas() {
	        System.out.println("\n--- 📊 Estadísticas de Ventas ---");
	        
	        // Rúbrica 8.3.1: Total recaudado
	        float totalRecaudado = 0;
	        Pattern pTotal = Pattern.compile("^TOTAL A PAGAR: (\\d+[.,]\\d{2})");
	        Pattern pDevolucion = Pattern.compile("^TOTAL DEVUELTO: (-\\d+[.,]\\d{2})");

	        File[] tickets = new File(PrincipalGemini.DIR_TICKETS).listFiles();
	        File[] devoluciones = new File(PrincipalGemini.DIR_DEVOLUCIONES).listFiles();

	        // Sumar ventas
	        if (tickets != null) {
	            for (File ticket : tickets) {
	                try (BufferedReader br = new BufferedReader(new FileReader(ticket))) {
	                    String linea;
	                    while ((linea = br.readLine()) != null) {
	                        Matcher m = pTotal.matcher(linea);
	                        if (m.find()) {
	                            totalRecaudado += Float.parseFloat(m.group(1).replace(",", "."));
	                        }
	                    }
	                } catch (IOException e) { /* Ignorar */ }
	            }
	        }
	        
	        // Sumar (restar) devoluciones
	         if (devoluciones != null) {
	            for (File ticket : devoluciones) {
	                try (BufferedReader br = new BufferedReader(new FileReader(ticket))) {
	                    String linea;
	                    while ((linea = br.readLine()) != null) {
	                        Matcher m = pDevolucion.matcher(linea);
	                        if (m.find()) {
	                            totalRecaudado += Float.parseFloat(m.group(1).replace(",", "."));
	                        }
	                    }
	                } catch (IOException e) { /* Ignorar */ }
	            }
	        }

	        System.out.printf("💰 Total Recaudado (Neto): %.2f €\n", totalRecaudado);

	        // Rúbrica 8.3.2: Plantas más vendidas
	        // Rúbrica 8.3.2.2: Almacenar en una estructura
	        Map<Integer, Integer> ventasPorPlanta = new HashMap<>();
	        Pattern pProductos = Pattern.compile("^(\\d+)\\s*\\|\\s*(\\d+)\\s*\\|"); // Cód | Cant | ...

	        if (tickets != null) {
	            for (File ticket : tickets) {
	                try (BufferedReader br = new BufferedReader(new FileReader(ticket))) {
	                    String linea;
	                    while ((linea = br.readLine()) != null) {
	                        Matcher m = pProductos.matcher(linea);
	                        if (m.find()) {
	                            int codigo = Integer.parseInt(m.group(1).trim());
	                            int cantidad = Integer.parseInt(m.group(2).trim());
	                            ventasPorPlanta.put(codigo, ventasPorPlanta.getOrDefault(codigo, 0) + cantidad);
	                        }
	                    }
	                } catch (IOException e) { /* Ignorar */ }
	            }
	        }

	        // Rúbrica 8.3.2.3: Ordenar por cantidad
	        LinkedHashMap<Integer, Integer> plantasOrdenadas = new LinkedHashMap<>();
	        ventasPorPlanta.entrySet()
	            .stream()
	            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
	            .forEachOrdered(x -> plantasOrdenadas.put(x.getKey(), x.getValue()));

	        System.out.println("\n🏆 Top Plantas Más Vendidas:");
	        int top = 1;
	        for (Map.Entry<Integer, Integer> entry : plantasOrdenadas.entrySet()) {
	            plantasClass p = PrincipalGemini.buscarPlantaPorCodigo(entry.getKey());
	            String nombre = (p != null) ? p.getNombre() : "Cód: " + entry.getKey();
	            System.out.printf("  %d. %s - %d unidades vendidas\n", top, nombre, entry.getValue());
	            if (top++ == 5) break; // Mostramos el Top 5
	        }
	        System.out.println("------------------------------------");
	    }

	    // --- MENÚ PRINCIPAL DEL GESTOR ---

	    public static void menuGestores(ArrayList<plantasClass> plantas, ArrayList<Empleado> empleados) {
	        // Sincronizamos las listas de esta clase con las de Principal
	        catalogoPlantas = plantas;
	        listaEmpleados = empleados;
	        
	        boolean salir = false;
	        int opcion;
	        
	        do {
	            System.out.println("\n--- 🧑‍💼 Menú de Gestores ---");
	            System.out.println("1. Gestión de Plantas (Alta)");
	            System.out.println("2. Gestión de Plantas (Baja)");
	            System.out.println("3. Gestión de Plantas (Rescatar)");
	            System.out.println("4. Gestión de Empleados (Alta)");
	            System.out.println("5. Gestión de Empleados (Baja)");
	            System.out.println("6. Gestión de Empleados (Recuperar)");
	            System.out.println("7. Ver Estadísticas de Ventas");
	            System.out.println("8. Salir al Menú Principal.");
	            System.out.print("Seleccione una opción: ");
	            
	            opcion = PrincipalGemini.controlErroresInt();
	                
	            switch (opcion) {
	                case 1: altaPlanta(); break;
	                case 2: bajaPlanta(); break;
	                case 3: rescatarPlanta(); break;
	                case 4: darAltaEmpleado(); break;
	                case 5: darBajaEmpleado(); break;
	                case 6: recuperarEmpleadoBaja(); break;
	                case 7: mostrarEstadisticas(); break;
	                case 8: 
	                    salir = true; 
	                    break;
	                default: System.out.println("Opción no válida, inténtalo de nuevo.");
	            }
	                
	        } while (!salir);
	        
	        System.out.println("Saliendo del menú de Gestores...");
	    }
	}

