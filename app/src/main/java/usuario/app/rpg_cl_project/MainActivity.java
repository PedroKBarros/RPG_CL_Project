package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private static int tempoFechamentoTela = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash screen com um timer.
             */
            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity menu
                Intent intent = new Intent(MainActivity.this,
                        MenuAcitivity.class);
                startActivity(intent);
                // Fecha esta activity
                finish();
            }
        }, tempoFechamentoTela);


    }


}