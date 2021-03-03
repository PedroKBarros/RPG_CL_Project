package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import usuario.app.rpg_cl_project.auxiliar.ConjuntoGerenciadorUICaracts;
import usuario.app.rpg_cl_project.auxiliar.ExecutaAudio;
import usuario.app.rpg_cl_project.auxiliar.ExecutaMensagem;
import usuario.app.rpg_cl_project.auxiliar.GerenciadorUICaracts;
import usuario.app.rpg_cl_project.auxiliar.OperacaoCaract;
import usuario.app.rpg_cl_project.database.ddl.DadosOpenHelper;
import usuario.app.rpg_cl_project.database.dml.repositorios.RepositorioTbCaract;
import usuario.app.rpg_cl_project.database.dml.repositorios.RepositorioTbConfigApp;
import usuario.app.rpg_cl_project.database.dml.repositorios.RepositorioTbPersonagemJog;
import usuario.app.rpg_cl_project.database.dml.repositorios.RepositorioTbTipoCaract;
import usuario.app.rpg_cl_project.dominio.Caracteristica;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoApp;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoGeral;
import usuario.app.rpg_cl_project.dominio.PersonagemJogador;

public class NovoPersonagemActivity extends AppCompatActivity {

    NovoPersonagemActivity activityAtual;
    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private RepositorioTbTipoCaract repositorioTbTipoCaract;
    private RepositorioTbPersonagemJog repositorioTbPersonagemJog;
    private RepositorioTbCaract repositorioTbCaract;
    private TextView headerTitle;
    private Button btConfirmar;
    private EditText edtNome;
    private ExecutaMensagem executaMensagem;
    private GerenciadorUICaracts gerencUiCaractsSaude;
    private GerenciadorUICaracts gerencUiCaractsAgressividade;
    private GerenciadorUICaracts gerencUiCaractsAgilidade;
    private GerenciadorUICaracts gerencUiCaractsVisao;
    private GerenciadorUICaracts gerencUiCaractsPassada;
    private GerenciadorUICaracts gerencUiCaractsFormaFisica;
    private GerenciadorUICaracts gerencUiCaractsForca;
    private TextView txtTotal;
    private ConjuntoGerenciadorUICaracts conjuntoGerenciador;

    private static final int MAX_TAMANHO_NOME_PERSON = 30;

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
        this.defineEventoClickBotaoConfirmar();

    }

    private void defineEventoClickBotaoConfirmar(){
        btConfirmar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                defineThreadAcoesEventoClickBotaoConfirmar();
            }
        });
    }

    private void defineThreadAcoesEventoClickBotaoConfirmar(){
        new Thread(new Runnable() {
            PersonagemJogador personagemJogador;

            public void run() {
                if(!ehNomePersonagemValido()){
                    apresentaMsgDadosInvalidos(0);
                    return;
                }
                if (!conjuntoGerenciador.ehTotalIntervaloValido()) {
                    apresentaMsgDadosInvalidos(1);
                    return;
                }
                if (!conjuntoGerenciador.qtdNiveis10Permitida()) {
                    apresentaMsgDadosInvalidos(2);
                    return;
                }
                //Verificando se já não existe personagem com o mesmo nome:
                personagemJogador =  repositorioTbPersonagemJog.buscaPersonagemJog(
                                                                    edtNome.getText().toString());
                if (personagemJogador != null){
                    apresentaMsgDadosInvalidos(3);
                    return;
                }

                //Inserindo personagem e suas características no BD:
                inseriNovoPersonagemBD(edtNome.getText().toString());
                inseriCaractsNovoPersonagemBD(edtNome.getText().toString());

                apresentaMsgPersonagemCriado();

                retornaActivityMenu();

            }
        }).start();
    }

    private void inseriNovoPersonagemBD(String nome){
        PersonagemJogador personagemJogador = new PersonagemJogador(nome);
        this.repositorioTbPersonagemJog.inseriTupla(personagemJogador);
    }

    private void inseriCaractsNovoPersonagemBD(String nomePersonagem){
        PersonagemJogador personagemJogador = new PersonagemJogador(nomePersonagem);

        //Saúde:
        this.repositorioTbCaract.inseriTupla(personagemJogador,
                                            this.gerencUiCaractsSaude.retornaCaractSelecionada());
        //Agressividade:
        this.repositorioTbCaract.inseriTupla(personagemJogador,
                this.gerencUiCaractsAgressividade.retornaCaractSelecionada());

        //Agilidade:
        this.repositorioTbCaract.inseriTupla(personagemJogador,
                this.gerencUiCaractsAgilidade.retornaCaractSelecionada());

        //Visão:
        this.repositorioTbCaract.inseriTupla(personagemJogador,
                this.gerencUiCaractsVisao.retornaCaractSelecionada());

        //Passada:
        this.repositorioTbCaract.inseriTupla(personagemJogador,
                this.gerencUiCaractsPassada.retornaCaractSelecionada());

        //Forma Física:
        this.repositorioTbCaract.inseriTupla(personagemJogador,
                this.gerencUiCaractsFormaFisica.retornaCaractSelecionada());

        //Força:
        this.repositorioTbCaract.inseriTupla(personagemJogador,
                this.gerencUiCaractsForca.retornaCaractSelecionada());


    }

    private void apresentaMsgPersonagemCriado(){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                executaMensagem.criaToast("Personagem criado com sucesso!",
                        ExecutaMensagem.TOAST_DURACAO_CURTA);
                executaMensagem.mostraToast();
            }
        });
    }

    private void apresentaMsgDadosInvalidos(int codigo){

        this.runOnUiThread(new Runnable() {
            public void run() {
                switch (codigo){
                    case 0:
                        executaMensagem.criaToast("Ops!\nO nome do novo personagem não foi preenchido.",
                                ExecutaMensagem.TOAST_DURACAO_LONGA);
                        executaMensagem.mostraToast();
                        break;
                    case 1:
                        executaMensagem.criaToast("Ops!\nO total deve variar de " +
                                        ConjuntoGerenciadorUICaracts.getMinimoTotal() + " até " +
                                        ConjuntoGerenciadorUICaracts.getMaximoTotal() + ".",
                                ExecutaMensagem.TOAST_DURACAO_LONGA);
                        executaMensagem.mostraToast();
                        break;
                    case 2:
                        executaMensagem.criaToast("Ops!\nSó é permitido no máximo " +
                                        ConjuntoGerenciadorUICaracts.getMaximoNiveis10() +
                                        " níveis 10.",
                                ExecutaMensagem.TOAST_DURACAO_LONGA);
                        executaMensagem.mostraToast();
                        break;
                    case 3:
                        executaMensagem.criaToast("Ops!\nJá existe um personagem criado com " +
                                        "esse nome.",
                                ExecutaMensagem.TOAST_DURACAO_LONGA);
                        executaMensagem.mostraToast();
                        break;
                }
            }
        });

    }

    private boolean ehNomePersonagemValido(){
        //Obs.: a qtd máxima de caracteres foi definida no XML da activity.
        return this.edtNome.getText().length() > 0;
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
                                int totalNivelCaracts = conjuntoGerenciador.calculaTotal();

                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsSaude.formataUiPorCaractSelecionada(caract);
                                        conjuntoGerenciador.atualizaUiTotal(totalNivelCaracts);
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
                                int total = conjuntoGerenciador.calculaTotal();

                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsAgressividade.
                                                            formataUiPorCaractSelecionada(caract);
                                        conjuntoGerenciador.atualizaUiTotal(total);
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
                                int total = conjuntoGerenciador.calculaTotal();

                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsAgilidade.
                                                formataUiPorCaractSelecionada(caract);
                                        conjuntoGerenciador.atualizaUiTotal(total);
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
                                int total = conjuntoGerenciador.calculaTotal();

                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsVisao.
                                                formataUiPorCaractSelecionada(caract);
                                        conjuntoGerenciador.atualizaUiTotal(total);
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
                                int total = conjuntoGerenciador.calculaTotal();

                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsPassada.
                                                formataUiPorCaractSelecionada(caract);
                                        conjuntoGerenciador.atualizaUiTotal(total);
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
                                int total = conjuntoGerenciador.calculaTotal();

                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsFormaFisica.
                                                            formataUiPorCaractSelecionada(caract);
                                        conjuntoGerenciador.atualizaUiTotal(total);
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
                                int total = conjuntoGerenciador.calculaTotal();

                                activityAtual.runOnUiThread(new Runnable() {
                                    public void run() {
                                        gerencUiCaractsForca.
                                                formataUiPorCaractSelecionada(caract);
                                        conjuntoGerenciador.atualizaUiTotal(total);
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
        this.txtTotal = (TextView) findViewById(R.id.txt_total_nivel_caracts);
        this.btConfirmar = (Button) findViewById(R.id.anp_bt_confirmar);
        this.edtNome = (EditText) findViewById(R.id.edt_nome_pers);

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

        //Conjunto gerenciador:
        this.conjuntoGerenciador = new ConjuntoGerenciadorUICaracts(this.txtTotal);
        this.conjuntoGerenciador.addVarios(
                this.gerencUiCaractsSaude,
                this.gerencUiCaractsAgressividade,
                this.gerencUiCaractsAgilidade,
                this.gerencUiCaractsVisao,
                this.gerencUiCaractsPassada,
                this.gerencUiCaractsFormaFisica,
                this.gerencUiCaractsForca);
    }

    private void instanciaRepositorios(){
        this.repositorioTbTipoCaract = new RepositorioTbTipoCaract(conexao);
        this.repositorioTbPersonagemJog = new RepositorioTbPersonagemJog(conexao);
        this.repositorioTbCaract = new RepositorioTbCaract(conexao);
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

    private void retornaActivityMenu(){
        Intent t = new Intent(this, MenuAcitivity.class);
        startActivity(t);
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