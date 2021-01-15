package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import usuario.app.rpg_cl_project.auxiliar.ExecutaAudio;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoApp;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoGeral;

public class MenuAcitivity extends AppCompatActivity {

    private Button botaoConfig;
    private Button botaoSair;
    private ExecutaAudio execSomCliqueBotoes;
    private ExecutaAudio execMusicaFundo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        botaoConfig = (Button) findViewById(R.id.bt_config);
        botaoSair = (Button) findViewById(R.id.bt_sair);

        execSomCliqueBotoes = new ExecutaAudio(this);
        execMusicaFundo = new ExecutaAudio(this);

        botaoConfig.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                execMusicaFundo.paraExecucao();
                execSomCliqueBotoes.executaAudioThreadUI(ExecutaAudio.URI_SOM_CLIQUE_BOTAO, false);
                Intent intent = new Intent(getBaseContext(), ConfiguracaoAppActivity.class);
                startActivity(intent);
            }
        });
        botaoSair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                execSomCliqueBotoes.executaAudioThreadUI(ExecutaAudio.URI_SOM_CLIQUE_BOTAO, false);
                finishAffinity(); //Fecha a activity atual e todas as que estão abaixo na pilha (abertas anteriormente)
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        execMusicaFundo.executaAudioAsync("android.resource://usuario.app.rpg_cl_project/raw/menu_music", true);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        this.liberaRecursos();
    }

    private void liberaRecursos(){
        execSomCliqueBotoes.liberaRecursos();
        execMusicaFundo.liberaRecursos();
    }

}