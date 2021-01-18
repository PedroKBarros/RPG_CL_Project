package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import usuario.app.rpg_cl_project.auxiliar.ExecutaAudio;
import usuario.app.rpg_cl_project.auxiliar.ExecutaMensagem;
import usuario.app.rpg_cl_project.database.ddl.DadosOpenHelper;
import usuario.app.rpg_cl_project.database.dml.repositorios.RepositorioTbConfigApp;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoApp;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoGeral;

public class MenuAcitivity extends AppCompatActivity {

    MenuAcitivity activityAtual;
    private Button botaoConfig;
    private Button botaoSair;
    private ExecutaAudio execSomCliqueBotoes;
    private ExecutaAudio execMusicaFundo;
    private ExecutaMensagem execMensagem;
    private boolean temSomBotoes;
    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private RepositorioTbConfigApp repositorioTbConfigApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.inicializaAtributos();

        this.estabeleceConexaoBD();
        this.instanciaRepositorio();

        this.defineEventoClickBotaoConfig();
        this.defineEventoClickBotaoSair();
    }

    private void defineThreadBDConfigSomBotoes(){
        new Thread(new Runnable() {
            public void run() {
                try {
                       activityAtual.runOnUiThread(new Runnable() {
                        public void run() {
                            execMensagem.criaToast("Conexão estabelecida com BD, Porra! :p",
                                    ExecutaMensagem.TOAST_DURACAO_CURTA);
                            execMensagem.mostraToast();
                        }
                    });
                    temSomBotoes = repositorioTbConfigApp.retornaValorTuplaComoBoolean(1);
                }catch(SQLException e){
                    //Há dois SQLException e temos q usar o android.database, pois é o q pertence ao pacote do SQLite
                    encerraRecursosBD();
                    execMensagem.criaAlertDialogComTitulo("Ops! :/",
                            "Infelizmente algo deu errado. O aplicativo será encerrado.\n" +
                                    "Se quiser relatar o problema, entre em contato através " +
                                    "do e-mail: pedrokatsbarros@gmail.com");
                    execMensagem.addBotaoAlertDialog(ExecutaMensagem.ALERTDLG_TIPO_BOTAO_POSITIVO,
                            "OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finishAffinity();
                                }
                            });
                    execMensagem.addEventoCancelAlertDialog(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            finishAffinity();
                        }
                    });
                    execMensagem.mostraAlertDialog();
                }
            }
        }).start();
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
                execSomCliqueBotoes.executaAudioThreadUICondicional(ExecutaAudio.URI_SOM_CLIQUE_BOTAO, false, temSomBotoes);
                Intent intent = new Intent(getBaseContext(), ConfiguracaoAppActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inicializaAtributos(){
        activityAtual = this;
        botaoConfig = (Button) findViewById(R.id.bt_config);
        botaoSair = (Button) findViewById(R.id.bt_sair);
        execSomCliqueBotoes = new ExecutaAudio(this);
        execMusicaFundo = new ExecutaAudio(this);
        execMensagem = new ExecutaMensagem(this);
    }

    @Override
    protected void onResume(){
        super.onResume();

        this.defineThreadBDConfigSomBotoes();
        execMusicaFundo.executaAudioAsync("android.resource://usuario.app.rpg_cl_project/raw/menu_music", true);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        this.liberaRecursos();
    }

    private void instanciaRepositorio(){
        this.repositorioTbConfigApp = new RepositorioTbConfigApp(this.conexao);
    }

    private void estabeleceConexaoBD() throws android.database.SQLException{
        //Método que vai criar a conexão com o BD em si. Esse método não cria nenhuma estrutura, apenas conecta com o BD
        dadosOpenHelper = new DadosOpenHelper(this); //Lembre-se que nosso contrutor pede o contexto, ou seja, a Activity em questão
        //Criando conexão
        conexao = dadosOpenHelper.estabeleceConexao(); //O writable permite leitura e escrita, enquanto q o redtable, só leitura
        //Fecha a activity atual e todas as que estão abaixo na pilha (abertas anteriormente);
    }

    private void fechaConexao(){
        dadosOpenHelper.fechaConexao();
    }

    private void fechaBD(){
        dadosOpenHelper.fechaBD();
    }

    private void encerraRecursosBD(){
        fechaConexao();
        fechaBD();
    }

    private void liberaRecursos(){
        execSomCliqueBotoes.liberaRecursos();
        execMusicaFundo.liberaRecursos();
        encerraRecursosBD();
    }

}