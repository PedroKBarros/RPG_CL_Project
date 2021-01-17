package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import usuario.app.rpg_cl_project.auxiliar.ExecutaMensagem;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoApp;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoGeral;

public class MenuAcitivity extends AppCompatActivity {

    private Button botaoConfig;
    private Button botaoSair;
    private ExecutaAudio execSomCliqueBotoes;
    private ExecutaAudio execMusicaFundo;
    private ExecutaMensagem execMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.inicializaAtributos();

        this.defineEventoClickBotaoConfig();
        this.defineEventoClickBotaoSair();
    }

    private void defineEventoClickBotaoSair(){
        botaoSair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                execSomCliqueBotoes.executaAudioThreadUI(ExecutaAudio.URI_SOM_CLIQUE_BOTAO, false);
                execMensagem.criaAlertDialogComTitulo("Sair", "Deseja realmente sair?");
                execMensagem.addBotaoAlertDialog(ExecutaMensagem.ALERTDLG_TIPO_BOTAO_POSITIVO,
                        "SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finishAffinity(); //Fecha a activity atual e todas as que estão abaixo na pilha (abertas anteriormente)
                            }
                        });
                execMensagem.addBotaoAlertDialog(ExecutaMensagem.ALERTDLG_TIPO_BOTAO_NEGATIVO,
                        "NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { }
                        });
                execMensagem.mostraAlertDialog();
            }
        });
    }

    private void defineEventoClickBotaoConfig(){
        botaoConfig.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                execMusicaFundo.paraExecucao();
                execSomCliqueBotoes.executaAudioThreadUI(ExecutaAudio.URI_SOM_CLIQUE_BOTAO, false);
                Intent intent = new Intent(getBaseContext(), ConfiguracaoAppActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inicializaAtributos(){
        botaoConfig = (Button) findViewById(R.id.bt_config);
        botaoSair = (Button) findViewById(R.id.bt_sair);
        execSomCliqueBotoes = new ExecutaAudio(this);
        execMusicaFundo = new ExecutaAudio(this);
        execMensagem = new ExecutaMensagem(this);
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