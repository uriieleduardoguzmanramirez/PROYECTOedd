public class ListaDobleLigada {
    private NodoDoble primero;
    private NodoDoble ultimo;
    public ListaDobleLigada() {
        primero = null;
        ultimo = null;
    }
    public boolean estaVacia() {
        return primero == null && ultimo == null;
    }
    public void agregarFin(String dato) {
        NodoDoble nuevo = new NodoDoble(dato);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(this.ultimo);
            this.ultimo = nuevo;
        }
    }
    public String imprimir() {
        NodoDoble actual = primero;
        String mostrar = "";
        while (actual != null) {
            mostrar += actual.getDato() + "\n"; // Cada dato en una nueva línea
            actual = actual.getSiguiente();
        }
        return mostrar;
    }
}

