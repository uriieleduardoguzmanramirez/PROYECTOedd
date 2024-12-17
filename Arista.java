class Arista {
    private Object destino;
    private Arista siguiente;
    public Arista(Object destino) {
        this.destino = destino;
        this.siguiente = null;
    }
    public Object getDestino() {
        return destino;
    }
    public void setSiguiente(Arista siguiente) {
        this.siguiente = siguiente;
    }
    public Arista getSiguiente() {
        return siguiente;
    }
}

