package usuario.app.rpg_cl_project.auxiliar;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;

public class Formatador {

    private SpannableStringBuilder spannableStringBuilder;
    private String textoInicial;

    //Constantes de cor:
    public static final int C_PRETO = Color.BLACK;
    public static final int C_AZUL = Color.BLUE;
    public static final int C_CINZA = Color.GRAY;
    public static final int C_VERDE = Color.GREEN;
    public static final int C_MAGENTA = Color.MAGENTA;
    public static final int C_VERMELHO = Color.RED;
    public static final int C_TRANSPARENTE = Color.TRANSPARENT;
    public static final int C_BRANCO = Color.WHITE;
    public static final int C_AMARELO = Color.YELLOW;

    //Constantes para as flags:
    public static final int F_INCLUSIVE_INCLUSIVE = Spanned.SPAN_INCLUSIVE_INCLUSIVE;
    public static final int F_EXCLUSIVE_EXCLUSIVE = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
    public static final int F_INCLUSIVE_EXCLUSIVE = Spanned.SPAN_INCLUSIVE_EXCLUSIVE;
    public static final int F_EXCLUSIVE_INCLUSIVE = Spanned.SPAN_EXCLUSIVE_INCLUSIVE;

    //Constante para configuracao do espacamento do bullet para o texto:
    public static final int E_ESPACAMENTO_BALA_PADRAO = 10;
    public static final int E_ESPACAMENTO_BALA_LARGO = 15;
    public static final int E_ESPACAMENTO_BALA_CURTO = 5;
    public static final int E_ESPACAMENTO_BALA_EXTRA_LARGO = 20;

    //Constantes para alinhamento de imagem:
    public static final int A_ALINHAMENTO_IMAGEM_LINHA_BASE = ImageSpan.ALIGN_BASELINE;
    public static final int A_ALINHAMENTO_IMAGEM_INFERIOR = ImageSpan.ALIGN_BOTTOM;
    public static final int A_ALINHAMENTO_IMAGEM_CENTRO = ImageSpan.ALIGN_CENTER;

    //Constantes para alinhamento horizontal do texto:
    public static final Layout.Alignment A_ALINHAMENTO_TEXTO_NORMAL = Layout.Alignment.ALIGN_NORMAL;
    public static final Layout.Alignment A_ALINHAMENTO_TEXTO_CENTRO = Layout.Alignment.ALIGN_CENTER;
    public static final Layout.Alignment A_ALINHAMENTO_TEXTO_OPOSTO = Layout.Alignment.ALIGN_OPPOSITE;


    //Constantes para tipos de estilo da formatacao por simbolos:
    public static final int T_ESTILO_COR_FONTE = 0;
    public static final int T_ESTILO_COR_FUNDO = 1;
    public static final int T_ESTILO_TEXTO_NEGRITO = 2;
    public static final int T_ESTILO_TEXTO_ITALICO = 3;
    public static final int T_ESTILO_TEXTO_NEGRITO_ITALICO = 4;
    public static final int T_ESTILO_TEXTO_NORMAL = 5;
    public static final int T_ESTILO_BALA_LISTA = 6;
    public static final int T_ESTILO_SUBLINHADO = 7;
    public static final int T_ESTILO_RISCADO = 8;

    //Construtores:
    public Formatador(String textoInicial){
        this.textoInicial = textoInicial;
        this.spannableStringBuilder = new SpannableStringBuilder(this.textoInicial);
    }

    public Formatador(){
        this.textoInicial = "";
        this.spannableStringBuilder = new SpannableStringBuilder();
    }

    public static RelativeSizeSpan estiloTamanhoFonteRelativo(float valor){
        return new RelativeSizeSpan(valor);
    }

    public static AbsoluteSizeSpan estiloTamanhoFonteAbsoluto(int valorDP){
        return new AbsoluteSizeSpan(valorDP);
    }

    public static AlignmentSpan estiloAlinhamentoHorizontal(Layout.Alignment alinhamento){
        return new AlignmentSpan.Standard(alinhamento);
    }

    //Outros métodos:
    public static ForegroundColorSpan estiloCorFonte(int cor){
        try{
            return new ForegroundColorSpan(cor);
        }catch (Exception e){
            return null;
        }
    }

    public static ForegroundColorSpan estiloCorFonte(int red, int green, int blue){
        try{
            return new ForegroundColorSpan(Color.rgb(red, green, blue));
        }catch (Exception e){
            return null;
        }
    }

    public static BackgroundColorSpan estiloCorFundo(int cor){
        try{
            return new BackgroundColorSpan(cor);
        }catch (Exception e){
            return null;
        }
    }

    public static BackgroundColorSpan estiloCorFundo(int red, int green, int blue){
        try{
            return new BackgroundColorSpan(Color.rgb(red, green, blue));
        }catch (Exception e){
            return null;
        }
    }

    public static BackgroundColorSpan estiloCorFundo(String corHexadecimal){

        try{
            return new BackgroundColorSpan(corHexParaRGB(corHexadecimal));
        }catch (Exception e){
            return null;
        }
    }

    public static ForegroundColorSpan estiloCorFonte(String corHexadecimal){
        try{
            return new ForegroundColorSpan(corHexParaRGB(corHexadecimal));
        }catch (Exception e){
            return null;
        }
    }

    public static int corHexParaRGB(String corHexadecimal) {

        try {
            int red, green, blue;

            red = Integer.valueOf(corHexadecimal.substring(1, 3), 16);
            green = Integer.valueOf(corHexadecimal.substring(3, 5), 16);
            blue = Integer.valueOf(corHexadecimal.substring(5, 7), 16);

            return Color.rgb(red, green, blue);

        }catch (Exception e){
            return -1;
        }
    }

    public static ImageSpan estiloImagem(Drawable imagem){
        try {
            return new ImageSpan(imagem, ImageSpan.ALIGN_BASELINE);
        }catch (Exception e ){
            return null;
        }
    }

    public static URLSpan estiloLink(String url){
        try {
            return new URLSpan(url);
        }catch (Exception e){
            return null;
        }
    }

    public static StyleSpan estiloTextoNegrito(){
        return new StyleSpan(Typeface.BOLD);
    }

    public static StyleSpan estiloTextoItalico(){
        return new StyleSpan(Typeface.ITALIC);
    }

    public static StyleSpan estiloTextoNegritoItalico(){
        return new StyleSpan(Typeface.BOLD_ITALIC);
    }

    public static StyleSpan estiloTextoNormal(){
        return new StyleSpan(Typeface.NORMAL);
    }

    public static BulletSpan estiloBalaLista(){
        return new BulletSpan(E_ESPACAMENTO_BALA_PADRAO);
    }

    public static BulletSpan estiloBalaLista(int espacamento){
        try {
            return new BulletSpan(espacamento);
        }catch (Exception e){
            return null;
        }
    }

    public static UnderlineSpan estiloTextoSublinhado(){
        return new UnderlineSpan();
    }

    public static StrikethroughSpan estiloTextoRiscado(){
        return new StrikethroughSpan();
    }


    public boolean formataPorSimbolosMultiEstilos(char simbolo, int[] tiposWhat, String[] coresHex, int[] flags){
        /*
        Descrição: este método funciona de forma semelhante ao método formataPorSimbolos(...), com
        a diferença de que este método permite aplicar mais de um estilo em cada expressão delimitada
        pelo 'simbolo'.
        OBS.: este método pode ter um grande custo computacional para textos com muitas expressões
        a terem estilos aplicados.
        */

        try {
            String texto = this.spannableStringBuilder.toString();
            int tamanhoTexto = texto.length();
            int indiceSimbolo;
            int indiceInicioExpressao = 0;
            int indiceFimExpressao = 0;
            int qtdSimbolos = 0;
            int tipoWhat;
            String corHex="";
            int flag=0;
            int indiceCorHex;
            int indiceFlags;

            if (!Formatador.tiposWhatValidos(tiposWhat))
                return false;

            if (!Formatador.coresHexValidas(tiposWhat, coresHex))
                return false;

            if (!Formatador.flagsValidas(tiposWhat, flags))
                return false;

            if (coresHex.length == 1)
                corHex = coresHex[0];

            if (flags.length == 1)
                flag = flags[0];

            for (int i = 0; i < tamanhoTexto; i++) {
                indiceSimbolo = texto.indexOf(simbolo, i);
                if (indiceSimbolo != -1) {
                    if (qtdSimbolos % 2 == 0) {
                        indiceInicioExpressao = indiceSimbolo + 1;
                    } else {
                        indiceFimExpressao = indiceSimbolo;
                        indiceCorHex = 0;
                        indiceFlags = 0;
                        for(int j = 0;j < tiposWhat.length;j++){
                            tipoWhat = tiposWhat[j];
                            if (coresHex.length > 1){
                                corHex = coresHex[indiceCorHex];
                                indiceCorHex++;
                            }
                            if (flags.length > 1){
                                flag = flags[indiceFlags];
                                indiceFlags++;
                            }
                            this.aplicaEstiloFormataPorSimbolo(indiceInicioExpressao, indiceFimExpressao,
                                    tipoWhat, corHex, flag);
                        }
                    }
                    qtdSimbolos++;
                    i = indiceSimbolo + 1;
                } else {
                    retiraSimbolos(simbolo);
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            return false;
        }

    }

    private static boolean flagsValidas(int[] tiposWhat, int[] flags) throws Exception{
        if (flags.length == 0)
            return false;

        if (flags.length > 1 && flags.length < tiposWhat.length)
            return false;

        for(int i = 0;i < flags.length;i++){
            if (!Formatador.ehFlagValida(flags[i]))
                return false;
        }

        return true;
    }

    private static boolean ehFlagValida(int flag) throws Exception{
        return flag == Formatador.F_INCLUSIVE_INCLUSIVE || flag == Formatador.F_INCLUSIVE_EXCLUSIVE
                || flag == Formatador.F_EXCLUSIVE_INCLUSIVE || flag == Formatador.F_EXCLUSIVE_EXCLUSIVE;
    }

    private static boolean coresHexValidas(int[] tiposWhat, String[] coresHex) throws Exception{
        String corHex;
        int j = 0;

        if (coresHex.length == 0)
            return false;

        if(coresHex.length == 1)
            if (Formatador.corHexParaRGB(coresHex[0]) != -1)
                return true;
            else
                return false;

        for(int i = 0;i < tiposWhat.length;i++){
            if (Formatador.ehTipoWhatCor(tiposWhat[i])){
                corHex = coresHex[j];
                if (Formatador.corHexParaRGB(corHex) == -1) //Caso corHex seja null, o método retornará -1 também
                    return false;
                j++;
            }
        }
        return true;
    }

    private static boolean ehTipoWhatCor(int tipoWhat) throws Exception{
        return tipoWhat == Formatador.T_ESTILO_COR_FONTE ||
                                                        tipoWhat == Formatador.T_ESTILO_COR_FUNDO;
    }

    private static boolean tiposWhatValidos(int[] tiposWhat) throws Exception{
        for(int i = 0;i<tiposWhat.length;i++){
            if (tiposWhat[i] < Formatador.T_ESTILO_COR_FONTE ||
                                                        tiposWhat[i] > Formatador.T_ESTILO_RISCADO)
                return false;
        }
        return true;
    }



    public boolean formataPorSimbolos(char simbolo, int tipoWhat, String corHex, int flags){
        /*
        Descrição: aplica estilo em todas as expressões que estão entre o caractere 'simbolo'
        e, em seguida, retira esses caracteres 'simbolo' do texto. Ressalta-se porém, que apesar
        desse método ser uma ótima alternativa em relação aos demais métodos, para que não se tenha
        que lidar com números de linhas e índices de caracteres, ele apresenta uma limitação em
        relação aos estilos disponíveis, tendo-se em vista
        que ele não recebe uma instância de algum estilo, mas um valor inteiro ('tipoWhat') referente
        ao estilo que deverá ser aplicado. Isso ocorre, pois para cada expressão delimitada pelo
        'simbolo', deverá se criar uma nova instância de um determinado estilo, o que não ocorre
        com os demais métodos de formatação.
        Exemplo: #MOTIVAÇÃO#, em que simbolo = '#'. Um símbolo é um delimitador, semelhante ao
        conceito de tag em HTML.
        OBS: como esse método retira caracteres do texto é recomendado que, caso se utilize
        o método formataPorIndiceChars(...), se execute primeiro o formataPorSimbolos(...) e depois
        o formataPorIndiceChars(...).
        */

        try {
            String texto = this.spannableStringBuilder.toString();
            int tamanhoTexto = texto.length();
            int indiceSimbolo;
            int indiceInicioExpressao = 0;
            int indiceFimExpressao = 0;
            int qtdSimbolos = 0;
            int corConvertida;

            if (tipoWhat < Formatador.T_ESTILO_COR_FONTE || tipoWhat > Formatador.T_ESTILO_RISCADO)
                return false;

            if (tipoWhat == Formatador.T_ESTILO_COR_FONTE || tipoWhat == Formatador.T_ESTILO_COR_FUNDO) {
                corConvertida = corHexParaRGB(corHex);
                if (corConvertida == -1)
                    return false;
            }

            if (!Formatador.flagsValidas(flags))
                return false;

            for (int i = 0; i < tamanhoTexto; i++) {
                indiceSimbolo = texto.indexOf(simbolo, i);
                if (indiceSimbolo != -1) {
                    if (qtdSimbolos % 2 == 0) {
                        indiceInicioExpressao = indiceSimbolo + 1;
                    } else {
                        indiceFimExpressao = indiceSimbolo;
                        this.aplicaEstiloFormataPorSimbolo(indiceInicioExpressao, indiceFimExpressao,
                                tipoWhat, corHex, flags);
                    }
                    qtdSimbolos++;
                    i = indiceSimbolo + 1;
                } else {
                    retiraSimbolos(simbolo);
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            return false;
        }

    }

    private static boolean flagsValidas(int flags){
        return flags == F_INCLUSIVE_INCLUSIVE || flags == F_INCLUSIVE_EXCLUSIVE ||
                                    flags == F_EXCLUSIVE_INCLUSIVE || flags == F_EXCLUSIVE_EXCLUSIVE;
    }

    private void aplicaEstiloFormataPorSimbolo(int indiceInicioExpressao, int indiceFimExpressao,
                                                            int tipoWhat, String corHex, int flags)
                                                                                    throws Exception{
        switch (tipoWhat){
            case Formatador.T_ESTILO_COR_FONTE:
                this.spannableStringBuilder.setSpan(Formatador.estiloCorFonte(corHex),
                        indiceInicioExpressao, indiceFimExpressao, flags);
                break;

            case Formatador.T_ESTILO_COR_FUNDO:
                this.spannableStringBuilder.setSpan(Formatador.estiloCorFundo(corHex),
                        indiceInicioExpressao, indiceFimExpressao, flags);
                break;

            case Formatador.T_ESTILO_TEXTO_NEGRITO:
                this.spannableStringBuilder.setSpan(Formatador.estiloTextoNegrito(),
                        indiceInicioExpressao, indiceFimExpressao, flags);
                break;

            case Formatador.T_ESTILO_TEXTO_ITALICO:
                this.spannableStringBuilder.setSpan(Formatador.estiloTextoItalico(),
                        indiceInicioExpressao, indiceFimExpressao, flags);
                break;

            case Formatador.T_ESTILO_TEXTO_NEGRITO_ITALICO:
                this.spannableStringBuilder.setSpan(Formatador.estiloTextoNegritoItalico(),
                        indiceInicioExpressao, indiceFimExpressao, flags);
                break;

            case Formatador.T_ESTILO_TEXTO_NORMAL:
                this.spannableStringBuilder.setSpan(Formatador.estiloTextoNormal(),
                        indiceInicioExpressao, indiceFimExpressao, flags);
                break;

            case Formatador.T_ESTILO_BALA_LISTA:
                this.spannableStringBuilder.setSpan(Formatador.estiloBalaLista(),
                        indiceInicioExpressao, indiceFimExpressao, flags);
                break;

            case Formatador.T_ESTILO_SUBLINHADO:
                this.spannableStringBuilder.setSpan(Formatador.estiloTextoSublinhado(),
                        indiceInicioExpressao, indiceFimExpressao, flags);
                break;

            case Formatador.T_ESTILO_RISCADO:
                this.spannableStringBuilder.setSpan(Formatador.estiloTextoRiscado(),
                        indiceInicioExpressao, indiceFimExpressao, flags);
                break;
        }
    }

    private void retiraSimbolos(char simbolo) throws Exception{
        char caractereAtual;

        for(int i = 0;i < this.spannableStringBuilder.length();i++){
            caractereAtual = this.spannableStringBuilder.charAt(i);
            if (caractereAtual == simbolo){
                this.spannableStringBuilder.replace(i, i + 1, "");
            }
        }
    }

    public boolean formataTodoTexto(Object what, int flags){
        /*
        Descrição: aplica um estilo no texto inteiro.
        Obs.: caso o texto seja alterado e passe a ter uma nova quantidade de caracteres,
        esse método deverá ser executado novamente.
        */
        return formataPorIndiceChars(0,
                        this.spannableStringBuilder.toString().length() - 1, what, flags);
    }

    public boolean formataPorIndiceChars(int indiceInicial, int indiceFinal, Object what, int flags){
        /*
        Descrição: aplica um estilo em um trecho do texto baseado em um
        índice inicial e um índice final.
        Observação: é o método menos recomendado, pois utiliza índices absolutos. Caso o texto mude,
        esses indices estarão desatualizados.
        */
        try{
            int qtdCaracteres = this.spannableStringBuilder.toString().length();
            if (indiceInicial < 0 || indiceFinal >= qtdCaracteres || indiceInicial > indiceFinal)
                return false;

            this.spannableStringBuilder.setSpan(what, indiceInicial, indiceFinal + 1, flags);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public boolean formataPorLinhas(int linhaInicial, int linhaFinal, Object what, int flags){
        /*
        Descrição: aplica um estilo em uma ou mais linhas do texto. Ressalta-se, porém, que a
        definição de uma linha é baseado na existência ou não do caractere '\n'.
        */
        try{
            int indiceCharInicioLinhaInicial = retornaIndiceCharInicioLinha(linhaInicial);
            int indiceCharFimLinhaFinal = retornaIndiceCharFimLinha(linhaFinal);

            if (indiceCharInicioLinhaInicial == -1 || indiceCharFimLinhaFinal == -1)
                return false;

            this.spannableStringBuilder.setSpan(what, indiceCharInicioLinhaInicial,
                                                                    indiceCharFimLinhaFinal, flags);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean formataPorInicioLinha(int linha, Object what, int flags){
        /*
        Descrição: aplica um estilo no primeiro caractere de uma determinada linha do texto.
        Ressalta-se, porém, que a definição de uma linha é baseado na existência ou não
        do caractere '\n'.
        */
       try{
            int indiceCharInicioLinha = retornaIndiceCharInicioLinha(linha);

            if (indiceCharInicioLinha == -1)
                return false;

            this.spannableStringBuilder.setSpan(what, indiceCharInicioLinha,
                    indiceCharInicioLinha, flags);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean formataPorExpressao(String expressao, int minOcorrencias, int maxOcorrencias, Object what,
                                                                                        int flags){
        /*
        Descrição: aplica um estilo em uma ou mais ocorrências de uma expressão presente no texto.
        O parâmetro minOcorrencias indica quantas ocorrências da expressão deve existir para que
        a(s) proxima(s) ocorrência(s) (dependendo do maxOcorrencias) da expressão recebam o estilo
        definido.
        */
        try{
            int indiceCharInicioExpressao = 0;
            int indiceCharFimExpressao;
            int indiceInicial = 0;
            int qtdOcorrencias = 0;
            String texto = this.spannableStringBuilder.toString();

            if (maxOcorrencias < 1 || minOcorrencias < 0)
                return false;

            while(qtdOcorrencias <= maxOcorrencias && indiceInicial < texto.length()){

                indiceCharInicioExpressao = retornaIndiceCharInicioExpressao(expressao, indiceInicial);
                indiceCharFimExpressao = retornaIndiceCharFimExpressao(expressao, indiceInicial);

                if (indiceCharInicioExpressao != -1){
                    if (qtdOcorrencias >= minOcorrencias){
                        this.spannableStringBuilder.setSpan(what, indiceCharInicioExpressao,
                                indiceCharFimExpressao, flags);
                    }
                    indiceInicial = indiceCharInicioExpressao + expressao.length();
                    qtdOcorrencias++;
                }else{
                    break;
                }
            }

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean formataParteExpressaoPorExpressao(String expressao, int indiceCharInicial,
                                                     int indiceCharFinal, int minOcorrencias,
                                                        int maxOcorrencias, Object what, int flags){
        /*
        Descrição: aplica um estilo em uma parte de uma ou mais ocorrências de uma expressão. Os
        parâmetros indiceCharInicial e indiceCharFinal definem a parte da expressão que receberá
        o estilo. Ressalta-se, porém, que esses parâmetros independem da posição da expressão
        no texto, variando de 0 a expressao.length() - 1.
        O parâmetro minOcorrencias indica quantas ocorrências da expressão deve existir para que
        a(s) proxima(s) ocorrência(s) (dependendo do maxOcorrencias) da expressão recebam o estilo
        definido.
        */
        try{
            int indiceCharInicioExpressao = 0;
            int indiceInicial = 0;
            int qtdOcorrencias = 0;
            int tamanhoExpressao = expressao.length();
            String texto = this.spannableStringBuilder.toString();

            if (maxOcorrencias < 1 || minOcorrencias < 0)
                return false;

            if (indiceCharInicial < 0 || indiceCharFinal >= tamanhoExpressao ||
                                                                indiceCharInicial > indiceCharFinal)
                return false;

            while(qtdOcorrencias <= maxOcorrencias && indiceInicial < texto.length()){

                indiceCharInicioExpressao = retornaIndiceCharInicioExpressao(expressao, indiceInicial);

                if (indiceCharInicioExpressao != -1){
                    Log.i("FORMATADOR", "indice = " + indiceCharInicioExpressao);
                    if (qtdOcorrencias >= minOcorrencias){
                        this.spannableStringBuilder.setSpan(what, indiceCharInicioExpressao + indiceCharInicial,
                                indiceCharInicioExpressao + indiceCharFinal + 1, flags);
                    }
                    indiceInicial = indiceCharInicioExpressao + expressao.length();
                    qtdOcorrencias++;
                }else{
                    break;
                }
            }

            return true;
        }catch (Exception e){
            return false;
        }
    }

    private int retornaIndiceCharInicioExpressao(String expressao, int indiceInicial) throws Exception{
        String texto = this.spannableStringBuilder.toString();
        return texto.indexOf(expressao, indiceInicial);
    }

    private int retornaIndiceCharFimExpressao(String expressao, int indiceInicial) throws Exception{
        String texto = this.spannableStringBuilder.toString();
        return texto.indexOf(expressao, indiceInicial) + expressao.length();
    }

    private int retornaIndiceCharInicioLinha(int linha) throws Exception{
        int qtdCharQuebraLinha = 0;
        int indiceCharQuebraLinha;
        String texto = this.spannableStringBuilder.toString();

        if (linha < 1)
            return -1;
        if (linha == 1)
            return 0;

        for(int i = 0;i < texto.length();i++){
            indiceCharQuebraLinha = texto.indexOf('\n', i);
            if (indiceCharQuebraLinha != -1) {
                qtdCharQuebraLinha++;
                i = indiceCharQuebraLinha + 1;
                if (qtdCharQuebraLinha == linha - 1){
                    return i;
                }
            }
        }
        return -1;
    }

    private int retornaIndiceCharFimLinha(int linha) throws Exception{
        int qtdCharQuebraLinha = 0;
        int indiceCharQuebraLinha;
        String texto = this.spannableStringBuilder.toString();

        if (linha < 1)
            return -1;

        for(int i = 0;i < texto.length();i++){
            indiceCharQuebraLinha = texto.indexOf('\n', i);
            if (indiceCharQuebraLinha != -1) {
                qtdCharQuebraLinha++;
                i = indiceCharQuebraLinha + 1;
                if (qtdCharQuebraLinha == linha){
                    return indiceCharQuebraLinha;
                }
            }
        }
        return -1;
    }

    //Métodos padrão da classe SpannebleStringBuilder:

    public SpannableStringBuilder addTexto(String texto){
        this.spannableStringBuilder.append(texto);
        return this.spannableStringBuilder;
    }

    public SpannableStringBuilder limpaTexto(){
        this.spannableStringBuilder.clear();
        return this.spannableStringBuilder;
    }

    public SpannableStringBuilder limpaEstilos(){
        this.spannableStringBuilder.clearSpans();
        return this.spannableStringBuilder;
    }

    public SpannableStringBuilder deletaParteTexto(int indiceInicio, int indiceFinal){
        this.spannableStringBuilder.delete(indiceInicio, indiceFinal + 1);
        return this.spannableStringBuilder;
    }

    public SpannableStringBuilder retiraEstilo(Object what){
        this.spannableStringBuilder.removeSpan(what);
        return this.spannableStringBuilder;
    }

    public String retornaTexto(){
        return this.spannableStringBuilder.toString();
    }

    public int totalCaracteres(){
        return this.spannableStringBuilder.length();
    }

    //Métodos Get and Set:

    public SpannableStringBuilder getSpannableStringBuilder() {
        return spannableStringBuilder;
    }

    public void setSpannableStringBuilder(SpannableStringBuilder spannableStringBuilder) {
        this.spannableStringBuilder = spannableStringBuilder;
    }

    public String getTextoInicial() {
        return textoInicial;
    }

    public void setTextoInicial(String textoInicial) {
        this.textoInicial = textoInicial;
    }
}
