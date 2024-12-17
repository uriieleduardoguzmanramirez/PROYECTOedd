public class NodoDoble {
    private String dato;  // Cambiado a String para almacenar el nombre o descripción
    private NodoDoble anterior;
    private NodoDoble siguiente;
    public NodoDoble(String dato) {
        this.dato = dato;
        anterior = null;
        siguiente = null;
    }
    public void setDato(String dato) {
        this.dato = dato;
    }
    public String getDato() {
        return this.dato;
    }
    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }
    public NodoDoble getAnterior() {
        return this.anterior;
    }
    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }
    public NodoDoble getSiguiente() {
        return this.siguiente;
    }
}


