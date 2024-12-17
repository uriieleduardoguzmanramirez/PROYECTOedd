public class Pila {
    private peliculas[] pila;
    private int tope;
    public Pila(int capacidad) {
        pila = new peliculas[capacidad]; 
        tope = -1;
    }
    public boolean estaVacia() {
        return tope == -1;
    }
    public boolean estaLlena() {
        return tope == pila.length - 1;
    }
    public void push(peliculas pelicula) {
        if (estaLlena()) {
            System.out.println("La pila está llena. No se puede agregar más elementos.");
        } else {
            pila[++tope] = pelicula; // Almacena el objeto pelicula
        }
    }
    public peliculas Pop() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía.");
        } else {
            peliculas pelicula = pila[tope];
            pila[tope--] = null; // Eliminamos el elemento y disminuimos el tope
            return pelicula;
        }
    }
}


