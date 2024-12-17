
class Lista {
    private Arista primero;
    private Arista ultimo;
    private int tamaño;  // Guardamos el tamaño para optimizar el uso de size()
    public Lista() {
        this.primero = null;
        this.ultimo = null;
        this.tamaño = 0;
    }
    public boolean estaVacia() {
        return this.primero == null;
    }
    public void agregarAdyacencia(Object destino) {
        Arista nodo = new Arista(destino);
        if (estaVacia()) {
            this.primero = nodo;
            this.ultimo = nodo;
        } else {
            this.ultimo.setSiguiente(nodo);
            this.ultimo = nodo;
        }
        tamaño++;
    }
    public int size() {
        return tamaño;
    }
    public Object obtenerElemento(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Arista actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDestino();
    }
    @Override
    public String toString() {
        String cadena = "";
        Arista actual = primero;
        while (actual != null) {
            cadena += actual.getDestino().toString() + ";";
            actual = actual.getSiguiente();
        }
        return cadena;
    }
}
