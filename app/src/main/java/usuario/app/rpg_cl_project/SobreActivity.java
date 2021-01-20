package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

public class SobreActivity extends AppCompatActivity {

    private TextView txtSobre;
    private SpannableString textoSobreFormatado;
    private TextView headerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        this.inicializaAtributos();

        this.formataTexto();
        this.apresentaTexto();
    }

    private void apresentaTexto(){
        this.txtSobre.setText(this.textoSobreFormatado);
    }

    private void formataTexto(){
        //Atribuindo links de licença:
        //  Música Menu:
        textoSobreFormatado.setSpan(
                new URLSpan("https://freemusicarchive.org/music/Fabian_Measures/Singles_Album/Fykr_Caverns/"),
                554, 567,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textoSobreFormatado.setSpan(
                new URLSpan("https://soundcloud.com/fogheart/"),
                571, 606,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textoSobreFormatado.setSpan(
                new URLSpan("https://creativecommons.org/licenses/by-nc/4.0/"),
                606, 640,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //Configurando View para que os links abram ao serem clicados:
        this.txtSobre.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void inicializaAtributos(){
        this.txtSobre = (TextView) findViewById(R.id.txt_sobre);
        headerTitle = (TextView) findViewById(R.id.txt_titulo_cabecalho);
        headerTitle.setText("SOBRE");
        this.textoSobreFormatado = new SpannableString(
                "MOTIVAÇÃO E HISTÓRIA:\n" +
                        "\n" +
                        "GRG I é um jogo inspirado em RPGs por linha de comando e desenvolvido por " +
                        "Pedro Barros, um estudante de Ciência da Computação que, durante um momento" +
                        " de “angustia criativa”, motivado pela pandemia de Covid-19, resolveu " +
                        "desenvolver algo durante as férias de fim do enlouquecedor ano de 2020.\n" +
                        "\n" +
                        "LICENÇAS DE MÍDIA:\n" +
                        "\n" +
                        "A maioria das imagens e músicas utilizadas no GRG I estão sob licenças " +
                        "Creative Commons que serão apresentadas a seguir:\n" +
                        "-IMAGENS:\n" +
                        "\tNenhuma imagem licenciada utilizada até o presente momento.\n" +
                        "-MÚSICAS:\n" +
                        "\tMúsica do menu:\n" +
                        "\tFykr Caverns by Fabian Measures is licensed under a " +
                        "Attribution-NonCommercial License.\n" +
                        "\n" +
                        "PARA DESENVOLVEDORES:\n" +
                        "\n" +
                        "-LICENCIAMENTO:\n" +
                        "\tNão definido até o presente momento.\n" +
                        "\n" +
                        "-TECNOLOGIAS UTILIZADAS:\n" +
                        "\tIDE: Android Studio\n" +
                        "\tSGBD: SQLite 3\n" +
                        "\tLinguagens: Java, SQL e XML\n" +
                        "\tVersionamento: Git e GitHub\n" +
                        "\tPrototipagem de telas: Figma\n" +
                        "\tDocumentação: Draw.io, Excel e Word\n" +
                        "\n" +
                        "REDES E CONTATOS:\n" +
                        "\n" +
                        "\tE-mail: pedrokatsbarros@gmail.com\n" +
                        "\tGitHub: \n" +
                        "\tgithub/pedrokbarros\n" +
                        "\tLinkdeIn:\n" +
                        "\thttps://www.linkedin.com/in/pedrokbarros\n" +
                        "\n" +
                        "ESTOU DISPONÍVEL PARA ESTÁGIOS!\n"
        );
    }
}