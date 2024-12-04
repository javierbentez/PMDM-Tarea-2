package dam.pmdm.tarea2jbg;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * MainActivity es la actividad principal de la aplicación que maneja la
 * interfaz de usuario
 * incluyendo un RecyclerView para mostrar una lista de personajes y un
 * Navigation Drawer.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PersonajeAdapter personajeAdapter;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private androidx.appcompat.app.ActionBarDrawerToggle mDrawerToggle;

    /**
     * Método que se llama cuando se crea la actividad.
     * 
     * @param savedInstanceState Si la actividad se está recreando desde un estado
     *                           previamente guardado,
     *                           este es el estado.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuramos el RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lista de personajes
        List<Personaje> personajesLista = new ArrayList<>();
        personajesLista.add(new Personaje(getString(R.string.mario), R.drawable.mario,
                getString(R.string.mario_detalles), getString(R.string.mario_habilidades)));
        personajesLista.add(new Personaje(getString(R.string.luigi), R.drawable.luigi,
                getString(R.string.luigi_detalles), getString(R.string.luigi_habilidades)));
        personajesLista.add(new Personaje(getString(R.string.peach), R.drawable.peach,
                getString(R.string.peach_detalles), getString(R.string.peach_habilidades)));
        personajesLista.add(new Personaje(getString(R.string.toad), R.drawable.toad, getString(R.string.toad_detalles),
                getString(R.string.toad_habilidades)));

        // Configuramos el Adapter con la lista de personajes y manejamos el clic en un
        // personaje
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

        // Configuramos el Navigation Drawer
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);

        setupToolbar();

        DataModel[] drawerItem = new DataModel[2];
        String home = getString(R.string.inicio);
        String language = getString(R.string.toggleLanguage);
        drawerItem[0] = new DataModel(R.drawable.baseline_home_24, home);
        drawerItem[1] = new DataModel(R.drawable.baseline_flag_24, language);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

        // Añadimos un mensaje de Snackbar al cargar la lista de elementos que diga
        // "Bienvenidos al mundo de Mario".
        Snackbar.make(findViewById(R.id.drawer_layout), R.string.bienvenidos_mario, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Clase interna que maneja los clics en los elementos del Navigation Drawer.
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * Maneja la selección de un elemento del Navigation Drawer.
     * 
     * @param position La posición del elemento seleccionado.
     */
    private void selectItem(int position) {
        switch (position) {
            case 1:
                // Cambiar idioma
                SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
                String currentLang = prefs.getString("app_language", "es"); // Por defecto en español
                if (currentLang.equals("en")) {
                    setLocale("es");
                } else {
                    setLocale("en");
                }
                break;
            default:
                break;
        }

        mDrawerLayout.closeDrawer(mDrawerList);
    }

    /**
     * Establece el título de la actividad.
     * 
     * @param title El nuevo título.
     */
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * Método que se llama después de que la actividad ha sido creada.
     * 
     * @param savedInstanceState Si la actividad se está recreando desde un estado
     *                           previamente guardado,
     *                           este es el estado.
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    /**
     * Configura la Toolbar.
     */
    void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * Configura el Drawer Toggle.
     */
    void setupDrawerToggle() {
        mDrawerToggle = new androidx.appcompat.app.ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    /**
     * Infla el menú de opciones.
     * 
     * @param menu El menú de opciones.
     * @return true si el menú se ha inflado correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Maneja la selección de un elemento del menú de opciones.
     * 
     * @param item El elemento seleccionado.
     * @return true si el elemento se ha manejado correctamente.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            showAboutDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Muestra un diálogo "Acerca de".
     */
    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.acerca_de))
                .setMessage(R.string.acerca_completo)
                .setIcon(R.drawable.info_48)
                .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Cambia el idioma de la aplicación.
     * 
     * @param lang El código del idioma (por ejemplo, "es" para español, "en" para
     *             inglés).
     */
    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        // Guardamos el idioma seleccionado en SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences("app_prefs", MODE_PRIVATE).edit();
        editor.putString("app_language", lang);
        editor.apply();

        // Reiniciamos la actividad para aplicar los cambios
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}