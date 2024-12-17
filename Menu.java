import java.util.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Menu {
        private static Pila pilaEliminadas = new Pila(100);
        private static ListaDobleLigada historialEliminaciones = new ListaDobleLigada();
        private static Grafo grafoPeliculas = new Grafo();
        private static ColaCircularPeliculas colaPeliculas = new ColaCircularPeliculas(100);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolAVL arbolPorNombre = new ArbolAVL();
        ArbolAVL arbolPorDuracion = new ArbolAVL();
                
        leerArchivoCSV("peliculas_distribuidas2.0.csv", arbolPorNombre, arbolPorDuracion);

        int opc = 0;
        while (opc != -1) {
            System.out.println("Menu de opciones \n 1.-Buscar por nombre \n 2.-Buscar por duración " +
                    "\n 3.-Ordenar por nombre \n 4.-Ordenar por duración \n 5.-Agregar película "+
                    "\n 6.-Eliminar película \n 7.-Deshacer ultima eliminacion \n 8.-historial de eliminaciones "+
                    "\n 9.-Odenar Nombre Peliculas(quicksort) \n 10.-Relacionar película con género \n 11.-ConsultarPeliculasPorGenero \n 12.-Salir");
            opc = scanner.nextInt();
            switch (opc) {
                case 1:
                    buscarPeliculaPorNombre(arbolPorNombre, scanner);
                    break;
                case 2:
                    buscarPeliculaPorDuracion(arbolPorDuracion, scanner);
                    break;
                case 3:
                    arbolPorNombre.ImprimirEnOrden();
                    break;
                case 4:
                    arbolPorDuracion.ImprimirEnOrden();
                    break;
                case 5:
                    agregarPelicula(arbolPorNombre, arbolPorDuracion, scanner);
                    break;
                case 6:
                    eliminarPelicula(arbolPorNombre, arbolPorDuracion, scanner);
                    break;
                case 7:
                    deshacerUltimaEliminacion(arbolPorNombre, arbolPorDuracion);
                    break;
                case 8:
                    System.out.println(historialEliminaciones.imprimir());
                    break;
                case 9:
                    ordenarNombresPeliculas(arbolPorNombre);
                    break;
                case 10:
                    relacionarPeliculaConGenero(scanner);  
                    break;
                case 11:
                    consultarPeliculasPorGenero(scanner);
                    break;
                case 12:
                    opc = -1;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void buscarPeliculaPorNombre(ArbolAVL arbol, Scanner scanner) {
        if (arbol.estaVacio()) {
            System.out.println("Árbol vacío");
            return;
        }
        scanner.nextLine();
        System.out.println("Ingresa el nombre de la película:");
        String nombre = scanner.nextLine();
        if (arbol.BuscarPeliculaPorNombre(nombre)) {
            long tiempoInicial = System.nanoTime();
            long tiempoFinal = System.nanoTime();
            long duracion = tiempoFinal - tiempoInicial;
            System.out.println("Película encontrada.tiempo de búsqueda: " + duracion + " ns");
        } else {
            System.out.println("No se encontró la película.");
        }
    }

    private static void buscarPeliculaPorDuracion(ArbolAVL arbol, Scanner scanner) {
        if (arbol.estaVacio()) {
            System.out.println("Árbol vacío");
            return;
        }
        System.out.println("Ingresa la duración de la película :");
        long duracion = scanner.nextLong();
        scanner.nextLine();
        if (arbol.BuscarPeliculaPorDuracion(duracion)) {
            long tiempoInicial = System.nanoTime();
            long tiempoFinal = System.nanoTime();
            long duracionBusqueda = tiempoFinal - tiempoInicial;
            System.out.println("Película encontrada. Tiempo de búsqueda fue: " + duracionBusqueda + " ns");
        } else {
            System.out.println("No se encontró la película.");
        }
    }

    private static void agregarPelicula(ArbolAVL arbolPorNombre, ArbolAVL arbolPorDuracion, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Ingresa el nombre de la película:");
        String nombre = scanner.nextLine().trim();
        System.out.println("Ingresa el director de la película:");
        String director = scanner.nextLine();
        System.out.println("Ingresa el género de la película:");
        String genero = scanner.nextLine();
        System.out.println("Ingresa el pais de la película:");
        String pais = scanner.nextLine();
        long duracion = leerDuracion(scanner);

        peliculas nuevaPelicula = new peliculas(nombre, director, genero, pais, duracion);
        arbolPorNombre.InsertarPorNombre(nuevaPelicula);
        arbolPorDuracion.InsertarPorDuracion(nuevaPelicula);
        System.out.println("Película creada: " + nuevaPelicula.getNombre() + " DIRIGIDA POR: " + nuevaPelicula.getDirector() +
                            " , GENERO: " + nuevaPelicula.getGenero() + ", DURACION: " + nuevaPelicula.getDuracion() + " ms " +",pais "+ nuevaPelicula.getPais() );
    }

    private static long leerDuracion(Scanner scanner) {
        System.out.println("Duración de la película en milisegundos:");
        if (scanner.hasNextLong()) {
            long duracion = scanner.nextLong();
            scanner.nextLine();  
            return duracion;
        } else {
            System.out.println("Ingresa un número válido");
            scanner.next(); 
            return leerDuracion(scanner);  
        }
    }

    private static void eliminarPelicula(ArbolAVL arbolPorNombre, ArbolAVL arbolPorDuracion, Scanner scanner) {
        System.out.println("¿Cómo eliminar película?");
        System.out.println("1. Por nombre");
        System.out.println("2. Por duración");
        int opcionEliminar = scanner.nextInt();
        scanner.nextLine();

        if (opcionEliminar == 1) {
            System.out.println("Ingresa el nombre de la película a eliminar:");
            String nombrePelicula = scanner.nextLine();
            peliculas pelicula = new peliculas(nombrePelicula, "", "", "", 0);

            arbolPorNombre.EliminarPorNombre(pelicula);
            arbolPorDuracion.EliminarPorNombre(pelicula);
             // Almacenar en la cola circular
          if (!colaPeliculas.EstaLlena()) {
                colaPeliculas.agregar(pelicula);
            }
            // Almacenar en la pila de eliminaciones (ahora almacenamos el objeto completo)
          pilaEliminadas.push(pelicula);
            // Agregar al historial de eliminaciones
            historialEliminaciones.agregarFin(pelicula.getNombre());
            System.out.println("La película " + nombrePelicula + " fue eliminada");
        
        } else if (opcionEliminar == 2) {
            System.out.println("Ingresa la duración de la película que deseas eliminar :");
            long duracionPelicula = scanner.nextLong();
            peliculas pelicula = new peliculas("", "", "", "" ,duracionPelicula);
  
            arbolPorNombre.EliminarPorDuracion(pelicula);
            arbolPorDuracion.EliminarPorDuracion(pelicula);
            // Almacenar en la cola circular
          if (!colaPeliculas.EstaLlena()) {
            colaPeliculas.agregar(pelicula);
          }
          // Almacenar en la pila de eliminaciones (ahora almacenamos el objeto completo)
            pilaEliminadas.push(pelicula);

            // Agregar al historial de eliminaciones
            historialEliminaciones.agregarFin("Duración: " + duracionPelicula + " ms");
            System.out.println("La película con duración " + duracionPelicula + " fue eliminada");
        } else {
            System.out.println("Opción inválida.");
        }
    }

        private static void deshacerUltimaEliminacion(ArbolAVL arbolPorNombre, ArbolAVL arbolPorDuracion) {
        try {
        // Recuperamos el objeto completo de la pila de eliminaciones
        peliculas ultimaPelicula = pilaEliminadas.Pop();
        System.out.println("Se restauró la película: " + ultimaPelicula.getNombre() + " con duración: " + ultimaPelicula.getDuracion() + " ms");

        // Restaurar la película en los árboles
        arbolPorNombre.InsertarPorNombre(ultimaPelicula);  // Asegúrate de que este método exista en tu árbol AVL
        arbolPorDuracion.InsertarPorDuracion(ultimaPelicula);

        // Si es necesario, también puedes agregarla de nuevo a la cola si corresponde
        if (!colaPeliculas.EstaLlena()) {
            colaPeliculas.agregar(ultimaPelicula);
        }

        } catch (Exception e) {
            System.out.println("No hay películas para deshacer: " + e.getMessage());
      }
    }
        
        private static void ordenarNombresPeliculas(ArbolAVL arbolPorNombre) {
            if (arbolPorNombre.estaVacio()) {
                System.out.println("No hay películas para ordenar.");
                return;
        }
        // Convertir el árbol a un arreglo
        peliculas[] peliculasArray = arbolPorNombre.toArray();
            // Extraer los nombres de las películas
        String[] nombresPeliculas = new String[peliculasArray.length];
        for (int i = 0; i < peliculasArray.length; i++) {
            nombresPeliculas[i] = peliculasArray[i].getNombre();
        }
        // Ordenar los nombres con Quicksort
        QuicksortStrings.ordenar(nombresPeliculas);
        // Imprimir los nombres ordenados
        System.out.println("Nombres de películas ordenados:");
        for (String nombre : nombresPeliculas) {
            System.out.println(nombre);
        }
    }
  
    private static void relacionarPeliculaConGenero(Scanner scanner) {
        scanner.nextLine();  // Limpiar buffer
        System.out.println("Ingresa el nombre de la película:");
        String nombrePelicula = scanner.nextLine().trim();
        System.out.println("Ingresa el género:");
        String genero = scanner.nextLine().trim();

        // Relacionar la película con el género
        grafoPeliculas.agregarRelacion(nombrePelicula, genero);
        System.out.println("Película " + nombrePelicula + " relacionada con el género: " + genero);
        }
    
        private static void mostrarRelacionesGrafo() {
            grafoPeliculas.imprimirRelaciones();
    }
    // Método estático para consultar las películas por género
    private static void consultarPeliculasPorGenero(Scanner scanner) {
        scanner.nextLine();  // Limpiar buffer
        System.out.println("Ingresa el género:");
        String genero = scanner.nextLine().trim();
        grafoPeliculas.consultarPeliculasPorGenero(genero);
    }
    // Ejemplo para imprimir todas las películas
    private static void imprimirTodasLasPeliculas(ArbolAVL arbolPorNombre) {
        if (arbolPorNombre.estaVacio()) {
            System.out.println("No hay películas en el sistema.");
            return;
        }
        peliculas[] peliculasArray = arbolPorNombre.toArray();
        for (peliculas p : peliculasArray) {
            System.out.println(p);
        }
    }
    
    private static void leerArchivoCSV(String archivo, ArbolAVL arbolPorNombre, ArbolAVL arbolPorDuracion) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", -1);
                 if (partes.length != 5) {
                     System.out.println("Línea inválida: " + linea);
                    continue;
                }
                String nombre = partes[0].replace("\"", "");
                String genero = partes[1].replace("\"", "");
                String director = partes[2].replace("\"", "");
                String pais = partes[3].replace("\"", "");
                long duracion = Long.parseLong(partes[4]);
                peliculas pelicula = new peliculas(nombre, genero, director, pais, duracion);
                arbolPorNombre.InsertarPorNombre(pelicula);
                arbolPorDuracion.InsertarPorDuracion(pelicula);
                 grafoPeliculas.agregarRelacion(nombre, genero);
            }
            System.out.println("Datos insertados.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir la duración: " + e.getMessage());
         }
     }
}
