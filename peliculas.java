
public class peliculas implements Comparable<peliculas> {
    public String nombre;
    public String genero;
    public String director;
    public String pais;
    public long duracion;
    public peliculas(String nombre, String genero ,String director, String pais,long duracion) {
        this.nombre = nombre;
        this.genero = genero;
        this.director = director;
        this.pais = pais;
        this.duracion = duracion;
    }
    public String getNombre() {
        return nombre;
    }
    public String getGenero() {
        return genero;
    }
    public String getDirector() {
        return director;
    }
    public String getPais(){
        return pais;
    }
    public long getDuracion() {
        return duracion;
    }
    
    @Override
    public int compareTo(peliculas otraPelicula) {
        return Long.compare(this.duracion, otraPelicula.duracion);
    }
    public int compareNombre(peliculas otraPelicula) {
        return this.nombre.compareTo(otraPelicula.nombre);
    }
    public int compareDuration(peliculas otraPelicula) {
        return Long.compare(this.duracion, otraPelicula.duracion); 
    }
    public String toCSV() {
        return String.format("\"%s\",\"%s\",\"%s\",\"%s\",%d", nombre, genero, director, pais, duracion);
    }
        @Override
    public String toString() {
        return "peliculas{" + "nombre=" + nombre + ", genero=" + genero + ", director=" + director + ", pais= " + pais +", duracion=" + duracion + " }";
    }

}
