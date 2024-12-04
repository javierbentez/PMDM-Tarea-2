package dam.pmdm.tarea2jbg;

/**
 * DataModel es una clase que representa un elemento con un icono y un nombre.
 */
public class DataModel {

    public int icon;
    public String name;

    /**
     * Constructor de la clase DataModel.
     *
     * @param icon El recurso del icono.
     * @param name El nombre del elemento.
     */
    public DataModel(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }
}