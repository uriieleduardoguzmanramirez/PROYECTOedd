import java.util.*;

public class Grafo {
    private NodoGrafo primero;
    public Grafo() {
        primero = null;
    }
    // Método para agregar una relación entre una película y un género
    public void agregarRelacion(String pelicula, String genero) {
        NodoGrafo peliculaNodo = obtenerNodoPelicula(pelicula);
        if (peliculaNodo == null) {
            // Si la película no existe, crearla y agregarla
            peliculaNodo = new NodoGrafo(new peliculas(pelicula, "", "", "", 0)); // Usamos una película ficticia solo para almacenar el nombre
            agregarNodo(peliculaNodo);
        }
        // Agregar la relación de género a la lista de adyacencia de la película
        peliculaNodo.getListaAdyacencia().agregarAdyacencia(genero);
    }
    // Buscar un nodo de película por nombre
    private NodoGrafo obtenerNodoPelicula(String nombre) {
        NodoGrafo actual = primero;
        while (actual != null) {
            if (((peliculas) actual.getDato()).getNombre().equals(nombre)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    private void agregarNodo(NodoGrafo nodo) {
        nodo.setSiguiente(primero);
        primero = nodo;
    }
    public void imprimirRelaciones() {
        NodoGrafo actual = primero;
        while (actual != null) {
            peliculas pelicula = (peliculas) actual.getDato();
            System.out.println("Película: " + pelicula.getNombre() + " | Géneros: " + actual.getListaAdyacencia().toString());
            actual = actual.getSiguiente();
        }
    }
    // Consultar las películas relacionadas con un género
    public void consultarPeliculasPorGenero(String genero) {
        boolean encontrado = false;
        NodoGrafo actual = primero;
        while (actual != null) {
            Lista generos = actual.getListaAdyacencia();
            for (int i = 0; i < generos.size(); i++) {
                String generoEnLista = (String) generos.obtenerElemento(i);
                if (generoEnLista.equalsIgnoreCase(genero)) {
                    peliculas pelicula = (peliculas) actual.getDato();
                    System.out.println("Película: " + pelicula.getNombre());
                    encontrado = true;
                    break;
                }
            }
            actual = actual.getSiguiente();
        }

        if (!encontrado) {
            System.out.println("No se encontraron películas para el género: " + genero);
        }
    }
}



