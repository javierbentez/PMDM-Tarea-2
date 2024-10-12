package dam.pmdm.tarea2jbg;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal de la aplicación que muestra la lista de personajes en un RecyclerView.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PersonajeAdapter personajeAdapter;

    /**
     * Método que se ejecuta al crear la actividad.
     *
     * @param savedInstanceState Estado de la instancia guardado previamente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lista de personajes
        List<Personaje> personajesLista = new ArrayList<>();
        personajesLista.add(new Personaje(getString(R.string.mario), R.drawable.mario, getString(R.string.mario_detalles), getString(R.string.mario_habilidades)));
        personajesLista.add(new Personaje(getString(R.string.luigi), R.drawable.luigi, getString(R.string.luigi_detalles), getString(R.string.luigi_habilidades)));
        personajesLista.add(new Personaje(getString(R.string.peach), R.drawable.peach, getString(R.string.peach_detalles), getString(R.string.peach_habilidades)));
        personajesLista.add(new Personaje(getString(R.string.toad), R.drawable.toad, getString(R.string.toad_detalles), getString(R.string.toad_habilidades)));

        // Configuramos el Adapter con la lista de personajes y manejamos el clic en un personaje
        personajeAdapter = new PersonajeAdapter(personajesLista, personaje -> {
            // Redirigimos a la pantalla de detalles al seleccionar un personaje
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("personaje_nombre", personaje.getNombre());
            intent.putExtra("personaje_imagen", personaje.getImageID());
            intent.putExtra("personaje_detalles", personaje.getDetalles());
            intent.putExtra("personaje_habilidades", personaje.getHabilidades());
            startActivity(intent);
        });

        recyclerView.setAdapter(personajeAdapter);
    }

    /**
     * Método para inflar el menú de opciones en la actividad.
     *
     * @param menu El menú donde se agregan los ítems.
     * @return true si el menú se infló correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Maneja la selección de ítems en el menú de opciones.
     *
     * @param item El ítem seleccionado.
     * @return true si el ítem fue manejado correctamente.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            // Mostramos el AlertDialog con la información de "Acerca de..."
            showAboutDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Muestra un diálogo "Acerca de..." con información sobre la aplicación.
     */
    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.acerca_de))
                .setMessage(R.string.acerca_completo)
                .setIcon(R.drawable.info_48)
                .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();  // Cerramos el diálogo al hacer clic en Aceptar
                    }
                });

        // Creamos y mostramos el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
