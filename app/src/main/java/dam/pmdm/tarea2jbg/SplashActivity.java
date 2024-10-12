package dam.pmdm.tarea2jbg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Actividad que muestra una pantalla de inicio al abrir la aplicación.
 * Después de 3 segundos, redirige automáticamente a la actividad principal.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Método que se ejecuta al crear la actividad.
     *
     * @param savedInstanceState Estado de la instancia guardado previamente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilitamos el modo de pantalla completa
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // Ajustamos el padding para tener en cuenta la status bar y navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ocultamos la status bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Iniciamos un temporizador de 3 segundos antes de redirigir a la Main Activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Creamos un Intent para ir a la actividad MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finalizamos la Splash Screen
            }
        }, 3000);
    }
}
