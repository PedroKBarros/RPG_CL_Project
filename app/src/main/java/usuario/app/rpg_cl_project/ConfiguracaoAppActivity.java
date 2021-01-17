package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import usuario.app.rpg_cl_project.auxiliar.ExecutaMensagem;
import usuario.app.rpg_cl_project.database.ddl.DadosOpenHelper;
import usuario.app.rpg_cl_project.database.dml.repositorios.RepositorioTbConfigApp;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoApp;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoGeral;

public class ConfiguracaoAppActivity extends AppCompatActivity {

    ConfiguracaoAppActivity activityAtual;
    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private RepositorioTbConfigApp repositorioTbConfigApp;
    private ConfiguracaoApp configuracaoApp;
    private Switch switchConfigSomBotoes;
    private TextView headerTitle;
    private TextView txtHelp;
    private ExecutaMensagem executaMensagem;
    private ArrayList valoresConfigOnCreate;
    private ArrayList valoresConfigOnDestroy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao_app);

        this.inicializaAtributos();

        this.defineThreadBDApresentaConfigs();
        this.defineEventoCliqueTextViewHelp();
    }

    private void armazenaValoresConfigOnCreate(){
        valoresConfigOnCreate.add(switchConfigSomBotoes.isChecked());
    }

    private void armazenaValoresConfigOnDestroy(){
        valoresConfigOnDestroy.add(switchConfigSomBotoes.isChecked());
    }

    private void defineThreadBDAtualizaValorConfigs(){
       new Thread(new Runnable() {
            public void run() {
                try {
                    boolean valorOnCreate, valorOnDestroy;

                    valorOnCreate = (boolean) valoresConfigOnCreate.get(0);
                    valorOnDestroy = (boolean) valoresConfigOnDestroy.get(0);
                    if (valorOnCreate != valorOnDestroy){
                        repositorioTbConfigApp.alteraValorTupla(1, valorOnDestroy == true ? 1 : 0);
                    }

                    liberaRecursos();

                }catch(SQLException e){
                    encerraRecursosBD();
                    //Há dois SQLException e temos q usar o android.database, pois é o q pertence ao pacote do SQLite
                    executaMensagem.criaAlertDialogComTitulo("Ops! :/",
                            "Infelizmente algo deu errado. O aplicativo será encerrado.\n" +
                                    "Se quiser relatar o problema, entre em contato através " +
                                    "do e-mail: pedrokatsbarros@gmail.com");
                    executaMensagem.addBotaoAlertDialog(ExecutaMensagem.ALERTDLG_TIPO_BOTAO_POSITIVO,
                            "OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finishAffinity();
                                }
                            });
                    executaMensagem.addEventoCancelAlertDialog(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            finishAffinity();
                        }
                    });
                    executaMensagem.mostraAlertDialog();
                }
            }
        }).start();

    }

    private void defineThreadBDApresentaConfigs(){
        new Thread(new Runnable() {
            public void run() {
                try {
                    estabeleceConexaoBD();
                    instanciaRepositorio();

                    activityAtual.runOnUiThread(new Runnable() {
                        public void run() {
                            executaMensagem.criaToast("Conexão estabelecida com BD! :)",
                                    ExecutaMensagem.TOAST_DURACAO_CURTA);
                            executaMensagem.mostraToast();
                        }
                    });

                    configuracaoApp = new ConfiguracaoApp();
                    configuracaoApp.setConfiguracoesGerais(repositorioTbConfigApp.buscarTodasTuplas());
                    ConfiguracaoGeral configuracaoGeral = configuracaoApp.getConfiguracoesGerais().get(0);
                    activityAtual.runOnUiThread(new Runnable() {
                        public void run() {
                            if (configuracaoGeral.getValor() == 0) {
                                switchConfigSomBotoes.setChecked(false);
                                armazenaValoresConfigOnCreate();
                        }else{
                                switchConfigSomBotoes.setChecked(true);
                                armazenaValoresConfigOnCreate();

                            }
                        }
                    });

                }catch(SQLException e){
                    //Há dois SQLException e temos q usar o android.database, pois é o q pertence ao pacote do SQLite
                    encerraRecursosBD();
                    executaMensagem.criaAlertDialogComTitulo("Ops! :/",
                            "Infelizmente algo deu errado. O aplicativo será encerrado.\n" +
                                    "Se quiser relatar o problema, entre em contato através " +
                                    "do e-mail: pedrokatsbarros@gmail.com");
                    executaMensagem.addBotaoAlertDialog(ExecutaMensagem.ALERTDLG_TIPO_BOTAO_POSITIVO,
                            "OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finishAffinity();
                                }
                            });
                    executaMensagem.addEventoCancelAlertDialog(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            finishAffinity();
                        }
                    });
                    executaMensagem.mostraAlertDialog();
                }
            }
        }).start();
    }

    private void defineEventoCliqueTextViewHelp(){
        txtHelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                executaMensagem.criaAlertDialogComTitulo("A Ajuda Chegou! :)", "Essa " +
                        "tela permite que você configure diversos parâmetros do GRG I como quiser! " +
                        "Todas as configurações daqui são aplicadas em todo o app.");
                executaMensagem.mostraAlertDialog();
            }
        });
    }

    private void inicializaAtributos(){
        activityAtual = this;
        switchConfigSomBotoes = (Switch) findViewById(R.id.swt_som_botoes);
        headerTitle = (TextView) findViewById(R.id.txt_titulo_cabecalho);
        headerTitle.setText("CONFIGURAÇÕES");
        txtHelp = (TextView) findViewById(R.id.txt_ajuda_cabecalho);
        executaMensagem = new ExecutaMensagem(this);
        valoresConfigOnCreate = new ArrayList();
        valoresConfigOnDestroy = new ArrayList();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        this.armazenaValoresConfigOnDestroy();
        this.defineThreadBDAtualizaValorConfigs();
        //liberaRecursos(); //Está dentro da thread acima (atualizaValorConfigs)
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
        encerraRecursosBD();
    }
}