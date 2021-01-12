package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao_app);

        switchConfigSomBotoes = (Switch) findViewById(R.id.swt_som_botoes);
        ConstraintLayout header = (ConstraintLayout) findViewById(
                                                                R.id.complete_header_out_play);
        TextView headerTitle = (TextView) header.getViewById(R.id.txt_titulo_cabecalho);
        headerTitle.setText("CONFIGURAÇÕES");
        Button buttonHelp = (Button) header.getViewById(R.id.bt_ajuda_cabecalho);

        buttonHelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder helpAlert = new AlertDialog.Builder(ConfiguracaoAppActivity.this);
                helpAlert.setMessage("Essa tela permite que você configure diversos parâmetros " +
                        "do GRG I como quiser! Todas as configurações daqui são aplicadas " +
                        "em todo o app.");
                helpAlert.setTitle("A Ajuda Chegou! :)");
                helpAlert.show();
            }
        });

        activityAtual = this;
        new Thread(new Runnable() {
            public void run() {
                try {
                    estabeleceConexaoBD();
                    instanciaRepositorio();

                    activityAtual.runOnUiThread(new Runnable() {
                        public void run() {
                            mostraMensagemToast("Conexão com BD estabelicidaaa!", Toast.LENGTH_SHORT);
                        }
                    });

                    configuracaoApp = new ConfiguracaoApp();
                    configuracaoApp.setConfiguracoesGerais(repositorioTbConfigApp.buscarTodasTuplas());
                    //Coloquei esse if enquanto não se povoa a tabela de configurações do app:
                    if (!configuracaoApp.getConfiguracoesGerais().isEmpty()){
                        ConfiguracaoGeral configuracaoGeral = configuracaoApp.getConfiguracoesGerais().get(0);
                        if (configuracaoGeral.getValor() == 0) {
                            activityAtual.runOnUiThread(new Runnable() {
                                public void run() {
                                    switchConfigSomBotoes.setChecked(false);
                                }
                            });

                        }else {
                            activityAtual.runOnUiThread(new Runnable() {
                                public void run() {
                                    switchConfigSomBotoes.setChecked(true);
                                }
                            });
                        }
                    }

                    dadosOpenHelper.fechaConexao();
                }catch(android.database.SQLException e){
                    //Há dois SQLException e temos q usar o android.database, pois é o q pertence ao pacote do SQLite
                    mostraMensagemToast(e.getMessage(), Toast.LENGTH_SHORT);
                }finally {
                    dadosOpenHelper.fechaConexao();
                }
            }
        }).start();

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

    private void mostraMensagemToast(String mensagem, int duracao){
        Toast toast = Toast.makeText(this, mensagem, duracao);
        toast.show();
    }
}