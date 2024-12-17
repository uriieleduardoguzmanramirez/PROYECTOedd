
public class NodoAVL {
    private Object dato;
    private int altura;
    private NodoAVL izq;
    private NodoAVL der;
    peliculas pelicula;
    public NodoAVL(peliculas dato) {
        this.dato = dato;
        this.izq=null;
        this.der=null;
        this.altura=1;                
    }
    public Object getDato() {
        return dato;
    }
    public int getAltura() {
        return altura;
    }
    public NodoAVL getIzq() {
        return izq;
    }
    public NodoAVL getDer() {
        return der;
    }
    public void setDato(peliculas dato) {
        this.dato = dato;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }
    public void setIzq(NodoAVL izq) {
        this.izq = izq;
    }
    public void setDer(NodoAVL der) {
        this.der = der;
    }
}