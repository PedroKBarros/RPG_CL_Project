package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import usuario.app.rpg_cl_project.auxiliar.Formatador;

public class SobreActivity extends AppCompatActivity {

    private TextView txtSobre;
    private TextView headerTitle;
    private Formatador formatador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        this.inicializaAtributos();

        this.formataTexto();
        this.apresentaTexto();
    }

    private void apresentaTexto(){
        this.txtSobre.setText(this.formatador.getSpannableStringBuilder());
    }

    private void formataTexto(){

        //Colocando todo o texto com mesma cor de fonte:
        this.formatador.formataTodoTexto(Formatador.estiloCorFonte("#D4D1D1"),
                                                                    Formatador.F_EXCLUSIVE_EXCLUSIVE);

        //Formatando tópicos:
        this.formatador.formataPorLinhas(1, 1, Formatador.estiloTextoNegrito(),
                                                                    Formatador.F_EXCLUSIVE_EXCLUSIVE);
        this.formatador.formataPorLinhas(1, 1,
                Formatador.estiloAlinhamentoHorizontal(Formatador.A_ALINHAMENTO_TEXTO_CENTRO),
                                                                Formatador.F_EXCLUSIVE_EXCLUSIVE);
        this.formatador.formataPorLinhas(1, 1,
                Formatador.estiloTamanhoFonteRelativo(1.20f),
                Formatador.F_EXCLUSIVE_EXCLUSIVE);

        this.formatador.formataPorExpressao("LICENÇAS DE MÍDIA:",0, 1,
                                        Formatador.estiloTextoNegrito(), Formatador.F_EXCLUSIVE_EXCLUSIVE);
        this.formatador.formataPorExpressao("LICENÇAS DE MÍDIA", 0,
                1,
                Formatador.estiloAlinhamentoHorizontal(Formatador.A_ALINHAMENTO_TEXTO_CENTRO),
                Formatador.F_EXCLUSIVE_EXCLUSIVE);
        this.formatador.formataPorExpressao("LICENÇAS DE MÍDIA",0, 1,
                Formatador.estiloTamanhoFonteRelativo(1.20f), Formatador.F_EXCLUSIVE_EXCLUSIVE);


        this.formatador.formataPorExpressao("PARA DESENVOLVEDORES:", 0, 1,
                                        Formatador.estiloTextoNegrito(), Formatador.F_EXCLUSIVE_EXCLUSIVE);
        this.formatador.formataPorExpressao("PARA DESENVOLVEDORES", 0,
                1,
                Formatador.estiloAlinhamentoHorizontal(Formatador.A_ALINHAMENTO_TEXTO_CENTRO),
                Formatador.F_EXCLUSIVE_EXCLUSIVE);
        this.formatador.formataPorExpressao("PARA DESENVOLVEDORES",0, 1,
                Formatador.estiloTamanhoFonteRelativo(1.20f), Formatador.F_EXCLUSIVE_EXCLUSIVE);

        this.formatador.formataPorExpressao("REDES E CONTATOS:", 0, 1,
                                        Formatador.estiloTextoNegrito(), Formatador.F_EXCLUSIVE_EXCLUSIVE);
        this.formatador.formataPorExpressao("REDES E CONTATOS", 0,
                1,
                Formatador.estiloAlinhamentoHorizontal(Formatador.A_ALINHAMENTO_TEXTO_CENTRO),
                Formatador.F_EXCLUSIVE_EXCLUSIVE);
        this.formatador.formataPorExpressao("REDES E CONTATOS",0, 1,
                Formatador.estiloTamanhoFonteRelativo(1.20f), Formatador.F_EXCLUSIVE_EXCLUSIVE);


        //Formatando subtópicos:
        this.formatador.formataPorExpressao("IMAGENS:",0,
                1, Formatador.estiloTextoSublinhado(), Formatador.F_EXCLUSIVE_EXCLUSIVE);

        this.formatador.formataPorExpressao("MÚSICAS:",0,
                1, Formatador.estiloTextoSublinhado(), Formatador.F_EXCLUSIVE_EXCLUSIVE);

        this.formatador.formataPorExpressao("LICENCIAMENTO:",0,
                1, Formatador.estiloTextoSublinhado(), Formatador.F_EXCLUSIVE_EXCLUSIVE);

        this.formatador.formataPorExpressao("TECNOLOGIAS UTILIZADAS:",0,
                1, Formatador.estiloTextoSublinhado(), Formatador.F_EXCLUSIVE_EXCLUSIVE);

        this.formatador.formataPorExpressao("REPOSITÓRIO:",0,
                1, Formatador.estiloTextoSublinhado(), Formatador.F_EXCLUSIVE_EXCLUSIVE);


        //Colocando links:
        this.formatador.formataPorExpressao("github.com/pedrokbarros/rpg_cl_project",
                                                                                0, 1,
                Formatador.estiloLink("https://www.github.com/pedrokbarros/rpg_cl_project"),
                                                                    Formatador.F_EXCLUSIVE_EXCLUSIVE);

        this.formatador.formataPorExpressao("pedrokatsbarros@gmail.com",
                0, 1,
                Formatador.estiloLink("pedrokatsbarros@gmail.com"),
                Formatador.F_EXCLUSIVE_EXCLUSIVE);

        this.formatador.formataPorExpressao("github.com/pedrokbarros",
                                                                1, 1,
                Formatador.estiloLink("https://www.github.com/pedrokbarros"),
                Formatador.F_EXCLUSIVE_EXCLUSIVE);

        this.formatador.formataPorExpressao("linkedin.com/in/pedrokbarros",
                0, 1,
                Formatador.estiloLink("https://www.linkedin.com/in/pedrokbarros"),
                Formatador.F_EXCLUSIVE_EXCLUSIVE);

        this.formatador.formataPorExpressao("Fykr Caverns",
                0, 1,
                Formatador.estiloLink("https://freemusicarchive.org/music/Fabian_Measures/Singles_Album/Fykr_Caverns/"),
                Formatador.F_EXCLUSIVE_EXCLUSIVE);

        this.formatador.formataPorExpressao("Fabian Measures is licensed under a",
                0, 1,
                Formatador.estiloLink("https://soundcloud.com/fogheart/"),
                Formatador.F_EXCLUSIVE_EXCLUSIVE);

        this.formatador.formataPorExpressao("Attribution-NonCommercial License.",
                0, 1,
                Formatador.estiloLink("https://creativecommons.org/licenses/by-nc/4.0/"),
                Formatador.F_EXCLUSIVE_EXCLUSIVE);

        //Formatando frase de estágio:
        this.formatador.formataPorExpressao("ESTOU DISPONÍVEL PARA ESTÁGIOS!",
                0, 1, Formatador.estiloCorFundo("#E07E54"),
                                                                Formatador.F_EXCLUSIVE_EXCLUSIVE);


        //Configurando View para que os links abram ao serem clicados:
        this.txtSobre.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void inicializaAtributos(){
        this.txtSobre = (TextView) findViewById(R.id.txt_sobre);
        headerTitle = (TextView) findViewById(R.id.txt_titulo_cabecalho);
        headerTitle.setText("SOBRE");
        this.formatador = new Formatador("MOTIVAÇÃO E HISTÓRIA\n" +
                        "\n" +
                        "GRG I é um jogo inspirado em RPGs por linha de comando e desenvolvido por " +
                        "Pedro Barros, um estudante de Ciência da Computação que, durante um momento" +
                        " de “angustia criativa”, motivado pela pandemia de Covid-19, resolveu " +
                        "desenvolver algo durante as férias de fim do enlouquecedor ano de 2020.\n" +
                        "\n" +
                        "LICENÇAS DE MÍDIA\n" +
                        "\n" +
                        "A maioria das imagens e músicas utilizadas no GRG I estão sob licenças " +
                        "Creative Commons que serão apresentadas a seguir:\n\n" +
                        "-IMAGENS:\n" +
                        "Nenhuma imagem licenciada utilizada até o presente momento.\n\n" +
                        "-MÚSICAS:\n" +
                        "Música do menu:\n" +
                        "Fykr Caverns by Fabian Measures is licensed under a " +
                        "Attribution-NonCommercial License.\n" +
                        "\n" +
                        "PARA DESENVOLVEDORES\n" +
                        "\n" +
                        "-LICENCIAMENTO:\n" +
                        "Não definido até o presente momento.\n\n" +
                        "-REPOSITÓRIO:\n" +
                        "github.com/pedrokbarros/rpg_cl_project\n" +
                        "\n" +
                        "-TECNOLOGIAS UTILIZADAS:\n" +
                        "IDE: Android Studio\n" +
                        "SGBD: SQLite 3\n" +
                        "Linguagens: Java, SQL e XML\n" +
                        "Versionamento: Git e GitHub\n" +
                        "Prototipagem de telas: Figma\n" +
                        "Documentação: Draw.io, Excel e Word\n" +
                        "\n" +
                        "REDES E CONTATOS\n" +
                        "\n" +
                        "E-mail: \npedrokatsbarros@gmail.com\n" +
                        "GitHub: \n" +
                        "github.com/pedrokbarros\n" +
                        "LinkdeIn:\n" +
                        "linkedin.com/in/pedrokbarros\n" +
                        "\n" +
                        "ESTOU DISPONÍVEL PARA ESTÁGIOS!\n"
        );
    }
}