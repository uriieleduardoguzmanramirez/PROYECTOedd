public class NodoGrafo {
    private Object dato;  // Película
    private Lista listaAdyacencia;  // Lista de géneros relacionados con la película
    private NodoGrafo siguiente;  // Apunta al siguiente nodo (película)
    public NodoGrafo(Object dato) {
        this.dato = dato;
        this.listaAdyacencia = new Lista();  // Inicializar la lista de géneros
        this.siguiente = null;
    }
    public Object getDato() {
        return dato;
    }
    public void setDato(Object dato) {
        this.dato = dato;
    }
    public Lista getListaAdyacencia() {
        return listaAdyacencia;
    }
    public void setListaAdyacencia(Lista listaAdyacencia) {
        this.listaAdyacencia = listaAdyacencia;
    }
    public NodoGrafo getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(NodoGrafo siguiente) {
        this.siguiente = siguiente;
    }
}

