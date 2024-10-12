package dam.pmdm.tarea2jbg;

/**
 * Clase que representa un personaje del juego.
 */
public class Personaje {
    private String nombre;    // Nombre del personaje
    private int imagenID;     // Recurso de imagen del personaje
    private String detalles;   // Descripción detallada del personaje
    private String habilidades; // Habilidades del personaje

    /**
     * Constructor de la clase Personaje.
     *
     * @param nombre      El nombre del personaje.
     * @param imagenID    El recurso de imagen del personaje.
     * @param detalles    Detalles sobre el personaje.
     * @param habilidades Habilidades que posee el personaje.
     */
    public Personaje(String nombre, int imagenID, String detalles, String habilidades) {
        this.nombre = nombre;
        this.imagenID = imagenID;
        this.detalles = detalles;
        this.habilidades = habilidades;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return Nombre del personaje.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el recurso de imagen del personaje.
     *
     * @return ID del recurso de imagen.
     */
    public int getImageID() {
        return imagenID;
    }

    /**
     * Obtiene los detalles del personaje.
     *
     * @return Detalles del personaje.
     */
    public String getDetalles() {
        return detalles;
    }

    /**
     * Obtiene las habilidades del personaje.
     *
     * @return Habilidades del personaje.
     */
    public String getHabilidades() {
        return habilidades;
    }
}
