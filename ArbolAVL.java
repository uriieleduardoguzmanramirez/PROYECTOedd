
  
import java.util.ArrayList;
import java.util.List;
public class ArbolAVL {
    private NodoAVL raiz;
    public ArbolAVL() {
        this.raiz = null;
    }
    public boolean estaVacio() {
        return raiz == null;
    }
    public void InsertarPorNombre(peliculas valor) {
        this.raiz = GenerarNodoPorNombre(valor, raiz);
    }
    // Método para convertir el árbol a un arreglo
    public peliculas[] toArray() {
        List<peliculas> lista = new ArrayList<>();
        inOrden(raiz, lista);
        return lista.toArray(new peliculas[0]);
    }
    // Recorrido in-order para llenar la lista
    private void inOrden(NodoAVL nodo, List<peliculas> lista) {
        if (nodo != null) {
            inOrden(nodo.getIzq(), lista); // Recursión al subárbol izquierdo
            lista.add((peliculas) nodo.getDato()); // Agregar el dato del nodo actual
            inOrden(nodo.getDer(), lista); // Recursión al subárbol derecho
        }
    }
    public NodoAVL GenerarNodoPorNombre(peliculas valor, NodoAVL nodo) {
        if (nodo == null) {
            return new NodoAVL(valor);
        }
        if (valor.compareNombre((peliculas) nodo.getDato()) < 0) {
            nodo.setIzq(GenerarNodoPorNombre(valor, nodo.getIzq()));
        }
        if (valor.compareNombre((peliculas) nodo.getDato()) > 0) {
            nodo.setDer(GenerarNodoPorNombre(valor, nodo.getDer()));
        }
        int nuevaAltura = 1 + Math.max(DetAltura(nodo.getIzq()), DetAltura(nodo.getDer()));
        nodo.setAltura(nuevaAltura);
        int balance = DetAltura(nodo.getDer()) - DetAltura(nodo.getIzq());
        if (balance >= 2) {
            if (valor.compareNombre((peliculas) nodo.getDer().getDato()) > 0) {
                nodo = RotacionIzq(nodo);
            } else {
                nodo = RotacionIzqDer(nodo);
            }
        } else if (balance == -2) {
            if (valor.compareNombre((peliculas) nodo.getIzq().getDato()) < 0) {
                nodo = RotacionDer(nodo);
            } else {
                nodo = RotacionDerIzq(nodo);
            }
        }
        return nodo;
    }
    public void InsertarPorDuracion(peliculas valor) {
        this.raiz = GenerarNodoPorDuracion(valor, raiz);
    }
    public NodoAVL GenerarNodoPorDuracion(peliculas valor, NodoAVL nodo) {
        if (nodo == null) {
            return new NodoAVL(valor);
        }
        if (valor.compareDuration((peliculas) nodo.getDato()) < 0) {
            nodo.setIzq(GenerarNodoPorDuracion(valor, nodo.getIzq()));
        }
        if (valor.compareDuration((peliculas) nodo.getDato()) > 0) {
            nodo.setDer(GenerarNodoPorDuracion(valor, nodo.getDer()));
        }
        int nuevaAltura = 1 + Math.max(DetAltura(nodo.getIzq()), DetAltura(nodo.getDer()));
        nodo.setAltura(nuevaAltura);
        int balance = DetAltura(nodo.getDer()) - DetAltura(nodo.getIzq());
        if (balance >= 2) {
            if (valor.compareDuration((peliculas) nodo.getDer().getDato()) > 0) {
                nodo = RotacionIzq(nodo);
            } else {
                nodo = RotacionIzqDer(nodo);
            }
        } else if (balance == -2) {
            if (valor.compareDuration((peliculas) nodo.getIzq().getDato()) < 0) {
                nodo = RotacionDer(nodo);
            } else {
                nodo = RotacionDerIzq(nodo);
            }
        }
        return nodo;
    }

    public NodoAVL RotacionIzq(NodoAVL x) {
        NodoAVL y = x.getDer();
        NodoAVL z = y.getIzq();
        y.setIzq(x);
        x.setDer(z);
        int Alturax = 1 + Math.max(DetAltura(x.getIzq()), DetAltura(x.getDer()));
        x.setAltura(Alturax);
        int Alturay = 1 + Math.max(DetAltura(y.getIzq()), DetAltura(y.getDer()));
        y.setAltura(Alturay);
        return y;
    }

    public NodoAVL RotacionDer(NodoAVL y) {
        NodoAVL x = y.getIzq();
        NodoAVL z = x.getDer();
        x.setDer(y);
        y.setIzq(z);
        int Alturay = 1 + Math.max(DetAltura(y.getIzq()), DetAltura(y.getDer()));
        y.setAltura(Alturay);
        int Alturax = 1 + Math.max(DetAltura(x.getIzq()), DetAltura(x.getDer()));
        x.setAltura(Alturax);
        return x;
    }

    public NodoAVL RotacionIzqDer(NodoAVL nodo) {
        nodo.setDer(RotacionDer(nodo.getDer()));
        return RotacionIzq(nodo);
    }

    public NodoAVL RotacionDerIzq(NodoAVL nodo) {
        nodo.setIzq(RotacionIzq(nodo.getIzq()));
        return RotacionDer(nodo);
    }

    public int DetAltura(NodoAVL nodo) {
        if (nodo == null)
            return 0;
        return nodo.getAltura();
    }

    public boolean BuscarPeliculaPorDuracion(long valor) {
        return BuscarValorD(valor, raiz);
    }

    private boolean BuscarValorD(long valor, NodoAVL nodo) {
        if (nodo == null) {
            return false;
        }
        double duracionPelicula = ((peliculas) nodo.getDato()).getDuracion();
        if (duracionPelicula == valor) {
            return true;
        }
        if (valor < duracionPelicula) {
            return BuscarValorD(valor, nodo.getIzq());
        }
        return BuscarValorD(valor, nodo.getDer());
    }

    public boolean BuscarPeliculaPorNombre(String valor) {
        int contador = 0;
        return BuscarValorN(valor, raiz, contador);
    }

    private boolean BuscarValorN(String valor, NodoAVL nodo, int contador) {
        if (nodo == null) {
            return false;
        }
        String nombrePelicula = ((peliculas) nodo.getDato()).getNombre();
        if (compararNombrePelicula(valor, nombrePelicula)) {
            return true;
        }
        if (valor.compareTo(nombrePelicula) < 0) {
            return BuscarValorN(valor, nodo.getIzq(), contador);
        }
        return BuscarValorN(valor, nodo.getDer(), contador);
    }

    private boolean compararNombrePelicula(String valor, String nombrePelicula) {
        valor = normalizarString(valor);
        nombrePelicula = normalizarString(nombrePelicula);
        if (valor.length() != nombrePelicula.length()) {
            return false;
        }
        for (int i = 0; i < valor.length(); i++) {
            if (valor.charAt(i) != nombrePelicula.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private String normalizarString(String texto) {
        return texto.trim().replaceAll("\\s+", " ").toLowerCase();
    }

    public int BuscarPeliculaPorNombreCalcularNodos(String valor) {
        return BuscarValorNCalcularNodos(valor, raiz, 0);
    }

    private int BuscarValorNCalcularNodos(String valor, NodoAVL nodo, int contador) {
        if (nodo == null) {
            return 0;
        }
        contador++;
        String nombrePelicula = ((peliculas) nodo.getDato()).getNombre();
        if (compararNombrePelicula(valor, nombrePelicula)) {
            return contador;
        }
        if (valor.compareTo(nombrePelicula) < 0) {
            return BuscarValorNCalcularNodos(valor, nodo.getIzq(), contador);
        }
        return BuscarValorNCalcularNodos(valor, nodo.getDer(), contador);
    }

    public int BuscarPeliculaPorDuracionCalcularNodos(long valor) {
        return BuscarValorDCalcularNodos(valor, raiz, 0);
    }

    private int BuscarValorDCalcularNodos(long valor, NodoAVL nodo, int contador) {
        if (nodo == null) {
            return 0;
        }
        contador++;
        double duracionPelicula = ((peliculas) nodo.getDato()).getDuracion();
        if (duracionPelicula == valor) {
            return contador;
        }
        if (valor < duracionPelicula) {
            return BuscarValorDCalcularNodos(valor, nodo.getIzq(), contador);
        }
        return BuscarValorDCalcularNodos(valor, nodo.getDer(), contador);
    }

    public void ImprimirEnOrden() {
        imprimirEnOrdenDescendenteConRetraso(raiz);
    }
    public void imprimirEnOrdenDescendenteConRetraso(NodoAVL nodo) {
        if (nodo != null) {
            imprimirEnOrdenDescendenteConRetraso(nodo.getDer()); 
            System.out.println(nodo.getDato());  
            try {
                Thread.sleep(10); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            imprimirEnOrdenDescendenteConRetraso(nodo.getIzq()); 
        }
    }
    public void EliminarPorNombre(peliculas valor) {
        this.raiz = EliminarPorNombre(valor, raiz);
    }
    public NodoAVL EliminarPorNombre(peliculas valor, NodoAVL nodo) {
        if (nodo == null) {
            return null; 
        }
        if (valor.compareNombre((peliculas) nodo.getDato()) < 0) {
            nodo.setIzq(EliminarPorNombre(valor, nodo.getIzq()));
        }
        else if (valor.compareNombre((peliculas) nodo.getDato()) > 0) {
            nodo.setDer(EliminarPorNombre(valor, nodo.getDer()));
        }
        else {
            if (nodo.getIzq() == null && nodo.getDer() == null) {
                nodo = null;  
            }
            else if (nodo.getIzq() == null) {
                nodo = nodo.getDer();  
            }
            else if (nodo.getDer() == null) {
                nodo = nodo.getIzq();  
            }
            else {
                NodoAVL nodoMinimo = EncontrarMinimo(nodo.getDer());
                nodo.setDato((peliculas)nodoMinimo.getDato());  
                nodo.setDer(EliminarPorNombre((peliculas) nodoMinimo.getDato(), nodo.getDer()));
            }
        }
        if (nodo != null) {
            int nuevaAltura = 1 + Math.max(DetAltura(nodo.getIzq()), DetAltura(nodo.getDer()));
            nodo.setAltura(nuevaAltura);
            int balance = DetAltura(nodo.getDer()) - DetAltura(nodo.getIzq());
            if (balance >= 2) {
                if (DetAltura(nodo.getDer().getDer()) >= DetAltura(nodo.getDer().getIzq())) {
                    nodo = RotacionIzq(nodo); 
                } else {
                    nodo = RotacionIzqDer(nodo); 
                }
            } else if (balance == -2) {
                if (DetAltura(nodo.getIzq().getIzq()) >= DetAltura(nodo.getIzq().getDer())) {
                    nodo = RotacionDer(nodo); 
                } else {
                    nodo = RotacionDerIzq(nodo); 
                }
            }
        }
        return nodo;  
    }
    private NodoAVL EncontrarMinimo(NodoAVL nodo) {
        while (nodo.getIzq() != null) {
            nodo = nodo.getIzq();
        }
        return nodo;
    }
    public void EliminarPorDuracion(long duracion) {
        this.raiz = EliminarNodoPorDuracion(raiz, duracion);
    }
    public NodoAVL EliminarNodoPorDuracion(NodoAVL nodo, long duracion) {
        if (nodo == null) {
            return null;  
        }
        if (duracion < ((peliculas) nodo.getDato()).getDuracion()) {
            nodo.setIzq(EliminarNodoPorDuracion(nodo.getIzq(), duracion));
        } else if (duracion > ((peliculas) nodo.getDato()).getDuracion()) {
            nodo.setDer(EliminarNodoPorDuracion(nodo.getDer(), duracion));
        } else {
            if (nodo.getIzq() == null && nodo.getDer() == null) {
                return null; 
            } else if (nodo.getIzq() == null) {
                return nodo.getDer();  
            } else if (nodo.getDer() == null) {
                return nodo.getIzq();  
            } else {
                NodoAVL nodoMinimo = EncontrarMinimo(nodo.getDer());
                nodo.setDato((peliculas)nodoMinimo.getDato());
                nodo.setDer(EliminarNodoPorDuracion(nodo.getDer(), ((peliculas) nodoMinimo.getDato()).getDuracion()));
            }
        }
        int nuevaAltura = 1 + Math.max(DetAltura(nodo.getIzq()), DetAltura(nodo.getDer()));
        nodo.setAltura(nuevaAltura);
        return balancear(nodo);
    }
    public NodoAVL balancear(NodoAVL nodo) {
        int balance = DetAltura(nodo.getDer()) - DetAltura(nodo.getIzq());
        if (balance >= 2) {
            if (DetAltura(nodo.getDer().getDer()) > DetAltura(nodo.getDer().getIzq())) {
                return RotacionIzq(nodo);  
            } else {
                return RotacionIzqDer(nodo);  
            }
        }
        if (balance <= -2) {
            if (DetAltura(nodo.getIzq().getIzq()) > DetAltura(nodo.getIzq().getDer())) {
                return RotacionDer(nodo);  
            } else {
                return RotacionDerIzq(nodo);  
            }
        }
        return nodo;  
    }
    public void EliminarPorDuracion(peliculas cancion) {
        this.raiz = EliminarPorDuracionRecursivo(cancion, raiz);
    }
    public NodoAVL EliminarPorDuracionRecursivo(peliculas pelicula, NodoAVL nodo) {
        if (nodo == null) {
            return null;
        }
        if (pelicula.getDuracion() < ((peliculas) nodo.getDato()).getDuracion()) {
            nodo.setIzq(EliminarPorDuracionRecursivo(pelicula, nodo.getIzq()));
        } else if (pelicula.getDuracion() > ((peliculas) nodo.getDato()).getDuracion()) {
            nodo.setDer(EliminarPorDuracionRecursivo(pelicula, nodo.getDer()));
        } else {
            if (nodo.getIzq() == null && nodo.getDer() == null) {
                return null;
            }
            if (nodo.getIzq() == null) {
                return nodo.getDer();
            } else if (nodo.getDer() == null) {
                return nodo.getIzq();
            }
            NodoAVL sucesor = obtenerMinimo(nodo.getDer());
            nodo.setDato((peliculas)sucesor.getDato());  
            nodo.setDer(EliminarPorDuracionRecursivo((peliculas) sucesor.getDato(), nodo.getDer()));  
        }
        int nuevaAltura = 1 + Math.max(DetAltura(nodo.getIzq()), DetAltura(nodo.getDer()));
        nodo.setAltura(nuevaAltura);
        return balancear(nodo);
    }
    public NodoAVL obtenerMinimo(NodoAVL nodo) {
        while (nodo.getIzq() != null) {
            nodo = nodo.getIzq();
        }
        return nodo;
    }  
}
