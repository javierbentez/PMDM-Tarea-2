package dam.pmdm.tarea2jbg;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad que muestra los detalles de un personaje seleccionado de la lista.
 */
public class DetailsActivity extends AppCompatActivity {

    /**
     * Método que se ejecuta al crear la actividad.
     *
     * @param savedInstanceState Estado de la instancia guardado previamente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Referencias a los componentes gráficos
        ImageView imageViewDetail = findViewById(R.id.imageViewDetail);
        TextView textViewDetailName = findViewById(R.id.textViewDetailName);
        TextView textViewDescription = findViewById(R.id.textViewDescription);
        TextView textViewSkills = findViewById(R.id.textViewSkills);

        // Obtenemos los datos del intent (nombre, imagen y detalles)
        String nombre = getIntent().getStringExtra("personaje_nombre");
        int imagenID = getIntent().getIntExtra("personaje_imagen", 0);
        String descripcion = getIntent().getStringExtra("personaje_detalles");
        String habilidades = getIntent().getStringExtra("personaje_habilidades");

        // Establecemos los valores en la UI
        imageViewDetail.setImageResource(imagenID);
        textViewDetailName.setText(nombre);
        textViewDescription.setText(descripcion);
        textViewSkills.setText(habilidades);

        // Mostramos un mensaje Toast indicando que se han cargado los detalles del personaje
        Toast.makeText(this, getString(R.string.detalles_cargados_para) + nombre, Toast.LENGTH_SHORT).show();
    }
}
