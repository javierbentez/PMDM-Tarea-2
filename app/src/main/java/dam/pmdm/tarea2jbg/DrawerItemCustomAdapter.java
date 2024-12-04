package dam.pmdm.tarea2jbg;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * DrawerItemCustomAdapter es un adaptador personalizado para los elementos del Navigation Drawer.
 */
public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {

    Context mContext;
    int layoutResourceId;
    DataModel data[] = null;

    /**
     * Constructor del adaptador personalizado.
     *
     * @param mContext El contexto de la actividad.
     * @param layoutResourceId El ID del recurso de diseño para cada elemento.
     * @param data Los datos a mostrar en el adaptador.
     */
    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DataModel[] data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    /**
     * Método que se llama para obtener la vista de un elemento en una posición específica.
     *
     * @param position La posición del elemento en el adaptador.
     * @param convertView La vista reutilizable.
     * @param parent El ViewGroup padre al que esta vista eventualmente se adjuntará.
     * @return La vista para el elemento en la posición especificada.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;

        // Inflamos el diseño del elemento de la lista
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        // Obtenemos las referencias a los elementos de la vista
        ImageView imageViewIcon = listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = listItem.findViewById(R.id.textViewName);

        // Obtenemos el elemento de datos en la posición actual
        DataModel folder = data[position];

        // Establecemos el icono y el nombre en los elementos de la vista
        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);

        return listItem;
    }
}