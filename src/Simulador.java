package src;

import java.io.*;
import java.util.Scanner;

public class Simulador {
    private Cola cola = new Cola();
    private Pila historial = new Pila();

    /**
     * Carga los clientes desde un archivo de texto ubicado en la carpeta 'data'.
     * Reinicia la cola antes de cargar para evitar duplicados.
     */
    public void cargarClientes(String nombreArchivo) {
        // 1. REINICIAR LA COLA
        this.cola = new Cola();

        // 2. DEFINIR RUTA DEL ARCHIVO
        String rutaCompleta = "data/" + nombreArchivo;
        File archivo = new File(rutaCompleta);

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            // 3. OMITIR ENCABEZADO: evita problemas generados por encabezados en el archivo
            br.readLine();

            int contador = 0;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty())
                    continue; // Ignorar líneas vacías

                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    // .trim() limpia espacios accidentales para que el formato sea uniforme
                    Cliente c = new Cliente(datos[0].trim(), datos[1].trim(), datos[2].trim());
                    cola.encolar(c);
                    contador++;
                }
            }
            System.out.println("✅ Éxito: se cargaron " + contador + " clientes desde el archivo.");

        } catch (FileNotFoundException e) {
            System.out.println("❌ Error: No se encontró el archivo en la ruta: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo: " + e.getMessage());
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n============================");
            System.out.println("      MENÚ DE ATENCIÓN");
            System.out.println("============================");
            System.out.println("1. Cargar clientes (data/clientes.txt)");
            System.out.println("2. Agregar cliente manualmente");
            System.out.println("3. Atender siguiente cliente");
            System.out.println("4. Ver cola de espera");
            System.out.println("5. Ver historial de atenciones");
            System.out.println("6. Consultar último atendido");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer de entrada
            } catch (Exception e) {
                System.out.println("❌ Error: Ingrese un número válido.");
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    cargarClientes("clientes.txt");
                    // Mostrar inmediatamente después de cargar para confirmar que se han agregado correctamente
                    System.out.println("\n--- LISTADO DE CLIENTES EN COLA ---");
                    cola.mostrar();
                }
                case 2 -> {
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Servicio: ");
                    String servicio = sc.nextLine();
                    cola.encolar(new Cliente(id, nombre, servicio));
                    System.out.println("✅ Cliente agregado manualmente.");
                }
                case 3 -> {
                    Cliente atendido = (Cliente) cola.desencolar();
                    if (atendido != null) {
                        historial.push(atendido);
                        System.out.println("🔔 Atendiendo a: " + atendido);
                    } else {
                        System.out.println("⚠️ La cola está vacía.");
                    }
                }
                case 4 -> {
                    System.out.println("\n--- COLA DE ESPERA ---");
                    cola.mostrar();
                }
                case 5 -> {
                    System.out.println("\n--- HISTORIAL (Pila) ---");
                    historial.mostrar();
                }
                case 6 -> {
                    Object ultimo = historial.peek();
                    if (ultimo != null) {
                        System.out.println("👤 Último cliente atendido: " + ultimo);
                    } else {
                        System.out.println("ℹ️ No hay registros en el historial.");
                    }
                }
                case 7 -> System.out.println("Saliendo... ¡Feliz día!");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 7);
    }

    public static void main(String[] args) {
    
        System.setProperty("file.encoding", "UTF-8");
        new Simulador().menu();
    }
}