
public class ColaCircularPeliculas {
    private int primero;
    private int ultimo;
    private int tamaño;
    private peliculas[] arreglo;
    public ColaCircularPeliculas(int tamaño) {
        this.tamaño = tamaño;
        primero = -1;
        ultimo = -1;
        arreglo = new peliculas[tamaño];
    }
    public boolean EstaLlena() {
        return (ultimo + 1) % tamaño == primero;
    }
    public boolean EstaVacia() {
        return primero == -1;
    }
    // Agrega una película a la cola
    public boolean agregar(peliculas pelicula) {
        if (EstaLlena()) {
            return false;
        }

        if (EstaVacia()) {
            primero++;
            ultimo++;
        } else {
            ultimo = (ultimo + 1) % tamaño;
        }
        arreglo[ultimo] = pelicula;
        return true;
    }
    // Elimina y devuelve la primera película en la cola
    public peliculas eliminar() throws Exception {
        if (EstaVacia()) {
             throw new Exception("Cola circular vacía");
        }

        peliculas pelicula = arreglo[primero];

        if (primero == ultimo) {
            primero = -1;
            ultimo = -1;
        } else {
            primero = (primero + 1) % tamaño;
        }
        return pelicula;
    }
    public peliculas peek() throws Exception {
        if (EstaVacia()) {
            throw new Exception("Cola circular vacía");
        }
        return arreglo[primero];
    }
    public void imprimirCola() {
        if (EstaVacia()) {
            System.out.println("La cola está vacía.");
            return;
        }
        int i = primero;
        while (i != ultimo) {
            System.out.println(arreglo[i]);
            i = (i + 1) % tamaño;
        }
        System.out.println(arreglo[ultimo]); 
    }
}
