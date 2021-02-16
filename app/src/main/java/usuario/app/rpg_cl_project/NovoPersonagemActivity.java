package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import usuario.app.rpg_cl_project.auxiliar.ExecutaMensagem;
import usuario.app.rpg_cl_project.auxiliar.GerenciadorUICaracts;
import usuario.app.rpg_cl_project.auxiliar.OperacaoCaract;
import usuario.app.rpg_cl_project.database.ddl.DadosOpenHelper;
import usuario.app.rpg_cl_project.database.dml.repositorios.RepositorioTbConfigApp;
import usuario.app.rpg_cl_project.database.dml.repositorios.RepositorioTbPersonagemJog;
import usuario.app.rpg_cl_project.database.dml.repositorios.RepositorioTbTipoCaract;
import usuario.app.rpg_cl_project.dominio.Caracteristica;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoApp;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoGeral;

public class NovoPersonagemActivity extends AppCompatActivity {

    NovoPersonagemActivity activityAtual;
    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private RepositorioTbTipoCaract repositorioTbTipoCaract;
    private TextView headerTitle;
    private ExecutaMensagem executaMensagem;
    private GerenciadorUICaracts gerencUiCaractsSaude;
    private GerenciadorUICaracts gerencUiCaractsAgressividade;
    private GerenciadorUICaracts gerencUiCaractsAgilidade;
    private GerenciadorUICaracts gerencUiCaractsVisao;
    private GerenciadorUICaracts gerencUiCaractsPassada;
    private GerenciadorUICaracts gerencUiCaractsFormaFisica;
    private GerenciadorUICaracts gerencUiCaractsForca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_personagem);

        this.inicializaAtributos();
        this.configuraTituloCabecalho();

        estabeleceConexaoBD();
        instanciaRepositorios();

        this.defineThreadBDArmazenaCaractsSpinners();
        this.defineEventoItemSelecionadoSpinnerCaractsSaude();
        this.defineEventoItemSelecionadoSpinnerCaractsAgressividade();
        this.defineEventoItemSelecionadoSpinnerCaractsAgilidade();
        this.defineEventoItemSelecionadoSpinnerCaractsVisao();
        this.defineEventoItemSelecionadoSpinnerCaractsPassada();
        this.defineEventoItemSelecionadoSpinnerCaractsFormaFisica();
        this.defineEventoItemSelecionadoSpinnerCaractsForca();

    }

    private void defineEventoItemSelecionadoSpinnerCaractsSaude(){
        new Thread(new Runnable() {
            public void run() {
                gerencUiCaractsSaude.getUiCaracts().setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                                       int position, long id) {
                                Caracteristica caract = gerencUiCaractsSaude.retornaCaractSelecionada();

                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsSaude.formataUiPorCaractSelecionada(caract);
                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // your code here
                            }

                        });
            }
        }).start();

    }

    private void defineEventoItemSelecionadoSpinnerCaractsAgressividade(){
        new Thread(new Runnable() {
            public void run() {

                gerencUiCaractsAgressividade.getUiCaracts().setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                                       int position, long id) {

                                if (gerencUiCaractsAgressividade.ehSpinnerInicializando()){
                                    gerencUiCaractsAgressividade.setEhInitSpinner(false);
                                    //Carregando Spinner com o segundo elemento:
                                    gerencUiCaractsAgressividade.selecionaCaractUiSpinner(1);
                                }

                                Caracteristica caract = gerencUiCaractsAgressividade.
                                                                        retornaCaractSelecionada();
                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsAgressividade.
                                                            formataUiPorCaractSelecionada(caract);
                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // your code here
                            }

                        });
            }
        }).start();
    }

    private void defineEventoItemSelecionadoSpinnerCaractsAgilidade(){
        new Thread(new Runnable() {
            public void run() {

                gerencUiCaractsAgilidade.getUiCaracts().setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                                       int position, long id) {

                                if (gerencUiCaractsAgilidade.ehSpinnerInicializando()){
                                    gerencUiCaractsAgilidade.setEhInitSpinner(false);
                                    //Carregando Spinner com o terceiro elemento:
                                    gerencUiCaractsAgilidade.selecionaCaractUiSpinner(2);
                                }

                                Caracteristica caract = gerencUiCaractsAgilidade.
                                                                        retornaCaractSelecionada();
                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsAgilidade.
                                                formataUiPorCaractSelecionada(caract);
                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // your code here
                            }

                        });
            }
        }).start();
    }

    private void defineEventoItemSelecionadoSpinnerCaractsVisao(){
        new Thread(new Runnable() {
            public void run() {

                gerencUiCaractsVisao.getUiCaracts().setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                                       int position, long id) {

                                if (gerencUiCaractsVisao.ehSpinnerInicializando()){
                                    gerencUiCaractsVisao.setEhInitSpinner(false);
                                    //Carregando Spinner com o quarto elemento:
                                    gerencUiCaractsVisao.selecionaCaractUiSpinner(3);
                                }

                                Caracteristica caract = gerencUiCaractsVisao.
                                        retornaCaractSelecionada();
                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsVisao.
                                                formataUiPorCaractSelecionada(caract);
                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // your code here
                            }

                        });
            }
        }).start();
    }

    private void defineEventoItemSelecionadoSpinnerCaractsPassada(){
        new Thread(new Runnable() {
            public void run() {

                gerencUiCaractsPassada.getUiCaracts().setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                                       int position, long id) {

                                if (gerencUiCaractsPassada.ehSpinnerInicializando()){
                                    gerencUiCaractsPassada.setEhInitSpinner(false);
                                    //Carregando Spinner com o quinto elemento:
                                    gerencUiCaractsPassada.selecionaCaractUiSpinner(4);
                                }

                                Caracteristica caract = gerencUiCaractsPassada.
                                        retornaCaractSelecionada();
                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsPassada.
                                                formataUiPorCaractSelecionada(caract);
                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // your code here
                            }

                        });
            }
        }).start();
    }

    private void defineEventoItemSelecionadoSpinnerCaractsFormaFisica(){
        new Thread(new Runnable() {
            public void run() {

                gerencUiCaractsFormaFisica.getUiCaracts().setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                                       int position, long id) {

                                Caracteristica caract =
                                            gerencUiCaractsFormaFisica.retornaCaractSelecionada();

                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsFormaFisica.
                                                            formataUiPorCaractSelecionada(caract);
                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // your code here
                            }

                        });
            }
        }).start();
    }

    private void defineEventoItemSelecionadoSpinnerCaractsForca(){
        new Thread(new Runnable() {
            public void run() {

                gerencUiCaractsForca.getUiCaracts().setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                                       int position, long id) {

                                if (gerencUiCaractsForca.ehSpinnerInicializando()){
                                    gerencUiCaractsForca.setEhInitSpinner(false);
                                    //Carregando Spinner com o segundo elemento:
                                    gerencUiCaractsForca.selecionaCaractUiSpinner(1);
                                }

                                Caracteristica caract = gerencUiCaractsForca.
                                        retornaCaractSelecionada();
                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsForca.
                                                formataUiPorCaractSelecionada(caract);
                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // your code here
                            }

                        });
            }
        }).start();
    }


    private void carregaListasCaracteristicas(){
        //Saúde:
        this.gerencUiCaractsSaude.setCaracts(repositorioTbTipoCaract.buscaCaractsPorCategoria(
                                                        this.gerencUiCaractsSaude.getNomeCategoria()));
        //Agressividade:
        this.gerencUiCaractsAgressividade.setCaracts(
                repositorioTbTipoCaract.buscaCaractsPorCategoria(
                                            this.gerencUiCaractsAgressividade.getNomeCategoria()));

        //Agilidade:
        this.gerencUiCaractsAgilidade.setCaracts(
                repositorioTbTipoCaract.buscaCaractsPorCategoria(
                        this.gerencUiCaractsAgilidade.getNomeCategoria()));

        //Visão:
        this.gerencUiCaractsVisao.setCaracts(
                repositorioTbTipoCaract.buscaCaractsPorCategoria(
                        this.gerencUiCaractsVisao.getNomeCategoria()));

        //Passada:
        this.gerencUiCaractsPassada.setCaracts(
                repositorioTbTipoCaract.buscaCaractsPorCategoria(
                        this.gerencUiCaractsPassada.getNomeCategoria()));

        //Forma Física:
        this.gerencUiCaractsFormaFisica.setCaracts(
                repositorioTbTipoCaract.buscaCaractsPorCategoria(
                        this.gerencUiCaractsFormaFisica.getNomeCategoria()));

        //Forca:
        this.gerencUiCaractsForca.setCaracts(
                repositorioTbTipoCaract.buscaCaractsPorCategoria(
                        this.gerencUiCaractsForca.getNomeCategoria()));
    }

    private void defineThreadBDArmazenaCaractsSpinners(){
        new Thread(new Runnable() {
            public void run() {
                try {
                    activityAtual.runOnUiThread(new Runnable() {
                        public void run() {
                            executaMensagem.criaToast("Conexão estabelecida com BD! :p",
                                    ExecutaMensagem.TOAST_DURACAO_CURTA);
                            executaMensagem.mostraToast();
                        }
                    });

                    //Carregando do BD as características:
                    carregaListasCaracteristicas();

                    //Obtendo listas de string com os nomes de todas as
                    // características de cada categoria:
                    List<String> nomesCaractsSaude;
                    nomesCaractsSaude = OperacaoCaract.retornaNomesCaractsLista(
                                                                gerencUiCaractsSaude.getCaracts());

                    List<String> nomesCaractsAgressividade = OperacaoCaract.retornaNomesCaractsLista(
                                                    gerencUiCaractsAgressividade.getCaracts());

                    List<String> nomesCaractsAgilidade = OperacaoCaract.retornaNomesCaractsLista(
                            gerencUiCaractsAgilidade.getCaracts());

                    List<String> nomesCaractsVisao = OperacaoCaract.retornaNomesCaractsLista(
                            gerencUiCaractsVisao.getCaracts());

                    List<String> nomesCaractsPassada = OperacaoCaract.retornaNomesCaractsLista(
                            gerencUiCaractsPassada.getCaracts());

                    List<String> nomesCaractsFormaFisica = OperacaoCaract.retornaNomesCaractsLista(
                            gerencUiCaractsFormaFisica.getCaracts());

                    List<String> nomesCaractsForca = OperacaoCaract.retornaNomesCaractsLista(
                            gerencUiCaractsForca.getCaracts());

                    //Utilizando o ArrayAdapter para povoar os spinners:
                    activityAtual.runOnUiThread(new Runnable() {
                        public void run() {
                            //Saúde:
                            ArrayAdapter<String> spinnerAdapterCaractsSaude = new ArrayAdapter<String>(
                                    activityAtual, R.layout.spinner_item_customizado,
                                                                                    nomesCaractsSaude);
                            spinnerAdapterCaractsSaude.setDropDownViewResource(
                                                    android.R.layout.simple_spinner_dropdown_item);
                            gerencUiCaractsSaude.getUiCaracts().setAdapter(spinnerAdapterCaractsSaude);

                            //Agressividade:
                            ArrayAdapter<String> spinnerAdapterCaractsAgressividade = new ArrayAdapter<String>(
                                    activityAtual, R.layout.spinner_item_customizado,
                                    nomesCaractsAgressividade);
                            spinnerAdapterCaractsAgressividade.setDropDownViewResource(
                                    android.R.layout.simple_spinner_dropdown_item);
                            gerencUiCaractsAgressividade.getUiCaracts().setAdapter(
                                                                spinnerAdapterCaractsAgressividade);

                            //Agilidade:
                            ArrayAdapter<String> spinnerAdapterCaractsAgilidade = new ArrayAdapter<String>(
                                    activityAtual, R.layout.spinner_item_customizado,
                                    nomesCaractsAgilidade);
                            spinnerAdapterCaractsAgilidade.setDropDownViewResource(
                                    android.R.layout.simple_spinner_dropdown_item);
                            gerencUiCaractsAgilidade.getUiCaracts().setAdapter(
                                    spinnerAdapterCaractsAgilidade);

                            //Visão:
                            ArrayAdapter<String> spinnerAdapterCaractsVisao = new ArrayAdapter<String>(
                                    activityAtual, R.layout.spinner_item_customizado,
                                    nomesCaractsVisao);
                            spinnerAdapterCaractsVisao.setDropDownViewResource(
                                    android.R.layout.simple_spinner_dropdown_item);
                            gerencUiCaractsVisao.getUiCaracts().setAdapter(
                                    spinnerAdapterCaractsVisao);

                            //Passada:
                            ArrayAdapter<String> spinnerAdapterCaractsPassada = new ArrayAdapter<String>(
                                    activityAtual, R.layout.spinner_item_customizado,
                                    nomesCaractsPassada);
                            spinnerAdapterCaractsPassada.setDropDownViewResource(
                                    android.R.layout.simple_spinner_dropdown_item);
                            gerencUiCaractsPassada.getUiCaracts().setAdapter(
                                    spinnerAdapterCaractsPassada);

                            //Forma Física:
                            ArrayAdapter<String> spinnerAdapterCaractsFormaFisica = new
                                                                                ArrayAdapter<String>(
                                    activityAtual, R.layout.spinner_item_customizado,
                                    nomesCaractsFormaFisica);
                            spinnerAdapterCaractsFormaFisica.setDropDownViewResource(
                                    android.R.layout.simple_spinner_dropdown_item);
                            gerencUiCaractsFormaFisica.getUiCaracts().setAdapter(
                                    spinnerAdapterCaractsFormaFisica);

                            //Força:
                            ArrayAdapter<String> spinnerAdapterCaractsForca = new
                                    ArrayAdapter<String>(
                                    activityAtual, R.layout.spinner_item_customizado,
                                    nomesCaractsForca);
                            spinnerAdapterCaractsForca.setDropDownViewResource(
                                    android.R.layout.simple_spinner_dropdown_item);
                            gerencUiCaractsForca.getUiCaracts().setAdapter(
                                    spinnerAdapterCaractsForca);


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

    private void configuraTituloCabecalho(){
        this.headerTitle.setText("NOVO PERSONAGEM");
    }

    private void inicializaAtributos(){
        this.activityAtual = this;
        this.headerTitle = (TextView) findViewById(R.id.txt_titulo_cabecalho);
        this.executaMensagem = new ExecutaMensagem(this);

        //Saúde:
        this.gerencUiCaractsSaude = new GerenciadorUICaracts(
                getString(R.string.categoria_saude),
                (ImageView) findViewById(R.id.img_valor_saude),
                (Spinner) findViewById(R.id.spn_caract_saude),
                (TextView) findViewById(R.id.txt_detalhes_caract_saude));
        //Agressividade:
        this.gerencUiCaractsAgressividade = new GerenciadorUICaracts(
                "Agressividade",
                (ImageView) findViewById(R.id.img_valor_agressividade),
                (Spinner) findViewById(R.id.spn_caract_agressividade),
                (TextView) findViewById(R.id.txt_detalhes_caract_agressividade));
        //Agilidade:
        this.gerencUiCaractsAgilidade = new GerenciadorUICaracts(
                "Agilidade",
                (ImageView) findViewById(R.id.img_valor_agilidade),
                (Spinner) findViewById(R.id.spn_caract_agilidade),
                (TextView) findViewById(R.id.txt_detalhes_caract_agilidade));
        //Visão:
        this.gerencUiCaractsVisao = new GerenciadorUICaracts(
                "Visão",
                (ImageView) findViewById(R.id.img_valor_visao),
                (Spinner) findViewById(R.id.spn_caract_visao),
                (TextView) findViewById(R.id.txt_detalhes_caract_visao));
        //Passada:
        this.gerencUiCaractsPassada = new GerenciadorUICaracts(
                "Passada",
                (ImageView) findViewById(R.id.img_valor_passada),
                (Spinner) findViewById(R.id.spn_caract_passada),
                (TextView) findViewById(R.id.txt_detalhes_caract_passada));

        //Forma Física:
        this.gerencUiCaractsFormaFisica = new GerenciadorUICaracts(
                "Forma Física",
                (ImageView) findViewById(R.id.img_valor_forma_fisica),
                (Spinner) findViewById(R.id.spn_caract_forma_fisica),
                (TextView) findViewById(R.id.txt_detalhes_caract_forma_fisica));

        //Força:
        this.gerencUiCaractsForca = new GerenciadorUICaracts(
                "Força",
                (ImageView) findViewById(R.id.img_valor_forca),
                (Spinner) findViewById(R.id.spn_caract_forca),
                (TextView) findViewById(R.id.txt_detalhes_caract_forca));

    }

    private void instanciaRepositorios(){
        this.repositorioTbTipoCaract = new RepositorioTbTipoCaract(conexao);
    }

    private void estabeleceConexaoBD() throws android.database.SQLException{
        //Método que vai criar a conexão com o BD em si. Esse método não cria nenhuma estrutura, apenas conecta com o BD
        dadosOpenHelper = new DadosOpenHelper(this); //Lembre-se que nosso contrutor pede o contexto, ou seja, a Activity em questão
        //Criando conexão
        conexao = dadosOpenHelper.estabeleceConexao(); //O writable permite leitura e escrita, enquanto q o redtable, só leitura
        //Fecha a activity atual e todas as que estão abaixo na pilha (abertas anteriormente);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        this.liberaRecursos();
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