package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MenuAcitivity extends AppCompatActivity {

    private Button botaoConfig;
    private Button botaoSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        botaoConfig = (Button) findViewById(R.id.bt_config);
        botaoSair = (Button) findViewById(R.id.bt_sair);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.som_clique_botao);

        botaoConfig.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mediaPlayer.start();
                Intent intent = new Intent(getBaseContext(), ConfiguracaoAppActivity.class);
                startActivity(intent);
            }
        });
        botaoSair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mediaPlayer.start();
                finishAffinity(); //Fecha a activity atual e todas as que estão abaixo na pilha (abertas anteriormente)
            }
        });
    }
}