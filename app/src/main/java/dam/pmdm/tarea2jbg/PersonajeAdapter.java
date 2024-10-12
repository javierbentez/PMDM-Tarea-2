package dam.pmdm.tarea2jbg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Adapter para el RecyclerView que maneja la lista de personajes.
 */
public class PersonajeAdapter extends RecyclerView.Adapter<PersonajeAdapter.PersonajeViewHolder> {

    private List<Personaje> personajeList;
    private OnItemClickListener onItemClickListener;

    /**
     * Interfaz para manejar el evento de clic en un personaje.
     */
    public interface OnItemClickListener {
        /**
         * Método que se ejecuta cuando un personaje es clicado.
         *
         * @param personaje El personaje que fue seleccionado.
         */
        void onItemClick(Personaje personaje);
    }

    /**
     * Constructor para inicializar el adaptador con la lista de personajes y un listener para los clics.
     *
     * @param personajeList       Lista de objetos de tipo Personaje.
     * @param onItemClickListener Listener que maneja los eventos de clic en los personajes.
     */
    public PersonajeAdapter(List<Personaje> personajeList, OnItemClickListener onItemClickListener) {
        this.personajeList = personajeList;
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * Crea una nueva instancia del ViewHolder con el layout de cada item.
     *
     * @param parent   El ViewGroup al que pertenece el View.
     * @param viewType El tipo de vista.
     * @return Una nueva instancia de PersonajeViewHolder.
     */
    @NonNull
    @Override
    public PersonajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new PersonajeViewHolder(view);
    }

    /**
     * Vincula los datos del personaje con el ViewHolder.
     *
     * @param holder   El ViewHolder que contiene la vista.
     * @param position La posición del personaje en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull PersonajeViewHolder holder, int position) {
        Personaje personaje = personajeList.get(position);
        holder.imageView.setImageResource(personaje.getImageID());
        holder.textViewNombre.setText(personaje.getNombre());

        // Manejamos el clic de cada tarjeta
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(personaje));
    }

    /**
     * Devuelve el tamaño de la lista de personajes.
     *
     * @return El número de elementos en la lista.
     */
    @Override
    public int getItemCount() {
        return personajeList.size();
    }

    /**
     * Clase ViewHolder que representa cada elemento en el RecyclerView.
     */
    static class PersonajeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewNombre;

        /**
         * Constructor que inicializa los componentes gráficos del ViewHolder.
         *
         * @param itemView La vista correspondiente al item en el RecyclerView.
         */
        public PersonajeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
        }
    }
}
