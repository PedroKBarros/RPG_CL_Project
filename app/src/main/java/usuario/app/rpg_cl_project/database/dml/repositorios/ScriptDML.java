package usuario.app.rpg_cl_project.database.dml.repositorios;

public class ScriptDML {

    private static final String categorias[] = {"Saúde", "Agressividade", "Agilidade", "Visão",
                                                "Passada", "Forma Física", "Força"};

    private static final String descricaoCategorias[] = {"Influencia em: COMBATES e MOVIMENTAÇÕES.",
                                                        "Influencia em: COMBATES e CAPACIDADE DE SE IMPOR.",
                                                        "Influencia em: MOVIMENTAÇÃO, COMABATE e FUGAS.",
                                                        "Influencia em: CAPACIDADE DE VIZUALIZAÇÃO " +
                                                                "DE OBJETOS DOS CENÁRIOS",
                                                        "Influencia em: CAPACIDADE DE NÃO SER PERCEBIDO",
                                                        "Influencia em: INTIMIDAÇÃO DE INIMIGOS e " +
                                                                "ACESSO A PARTES DOS CENÁRIOS",
                                                        "Influencia em: COMBATES e DEMAIS SITUAÇÕES" +
                                                                " EM QUE SE PRECISE EXERCER FORÇA " +
                                                                "SOBRE ALGO"};

    private static final int niveisCaracteristicas[] = {10, 8, 6, 4, 2};

    private static  final String caractsCategoria0[] = {"ATLETA", "BOA", "MÉDIA", "RUIM", "ZUMBI"};
    private static  final String caractsCategoria1[] = {"INCONTROLÁVEL", "VALENTÃO DE COLÉGIO",
                                                        "MÉDIA", "BAIXA", "BUDA"};
    private static  final String caractsCategoria2[] = {"BANDIDO EM FUGA", "BOA", "MÉDIA", "RUIM",
                                                            "TARTARUGA"};
    private static  final String caractsCategoria3[] = {"ÁGUIA", "BOA", "MÉDIA", "RUIM", "QUASE CEGO"};
    private static  final String caractsCategoria4[] = {"MATADOR PROFISSIONAL", "QUASE SILENCIOSA",
                                                        "UM POUCO BARULHENTA", "MUITO BARULHENTA",
                                                        "SAPATEADO"};
    private static  final String caractsCategoria5[] = {"MUSCULOSO E COMPACTO", "MUSCULOSO E GRANDE",
                                                        "MÉDIA", "FLÁCIDO E COMPACTO",
                                                        "FLÁCIDO E GRANDE"};
    private static  final String caractsCategoria6[] = {"OGRO", "BOA", "MÉDIA", "RUIM", "PAPEL"};

    private static final String descricaoCaractsCategoria0[] = {"A saúde de um atleta e a tristeza " +
                                                                "de quem não pode comer chocolate!",
                                                                "Como a saúde de um adolescente e, o " +
                                                                "melhor, podendo comer chocolates!",
                                                                "Um “motor velho”, mas que ainda " +
                                                                        "aguenta algumas “ladeiras”!",
                                                                "“Morrer de rir” já não é apenas uma" +
                                                                                        " expressão!",
                                                                "Diabetes, hipertensão e crises renais. " +
                                                                        "Praticamente um morto-vivo!"};

    private static final String descricaoCaractsCategoria1[] = {"A agressividade de um imbecil vivendo " +
                                                                "em sociedade. “Pensar” é um verbo " +
                                                                "bem distante...",
                                                                "Respirar e distribuir “cuecões” são" +
                                                                      " duas atividades revigorantes!",
                                                                "Ser agressivo é necessário em alguns" +
                                                                " poucos momentos. O bom senso deve" +
                                                                                       " prevalecer.",
                                                                "Pelo direito das moscas terem velório" +
                                                                        " e caixão! Todos merecem um" +
                                                                                       " final digno!",
                                                                "Controlar a respiração é olhar para" +
                                                                " dentro, se libertando das " +
                                                                "frustrações do mundo exterior!"};

    private static final String descricaoCaractsCategoria2[] = {"Atravessar grandes avenidas, entre" +
                                                                " carros, em poucos segundos e sem" +
                                                                " morrer parece bastante ágil!",
                                                                "Tocar bateria é uma atividade bem " +
                                                                                           "“fácil”!",
                                                                "Se mexer quando for necessário, " +
                                                                "deitar-se sempre que for possível. " +
                                                                "Essa é a “chave” da longevidade!",
                                                                "Coordenar as pernas para andar não" +
                                                                " é uma tarefa tão fácil quanto " +
                                                                                    "possa parecer!",
                                                                "Parar e pensar! Ah, e parar e " +
                                                                                "pensar novamente!"};

    private static final String descricaoCaractsCategoria3[] = {"Enxergar um alfinete em uma rua " +
                                                                "escura é só uma questão de treino," +
                                                                      " ou melhor, de sorte, mesmo!",
                                                                "Para muitos, diversão é ir para a " +
                                                                "“night”. Para outros, é ler placas" +
                                                                    " de carros em alta velocidade!",
                                                                "Uma visão seletiva do mundo permite" +
                                                                " que se veja apenas a conveniência" +
                                                                                    " da realidade!",
                                                                "Enxergar aonde se vai, mas não " +
                                                                           "saber de onde se volta!",
                                                                "A vida é uma tela repleta de lindos" +
                                                                " borrões. Ou seja, ela é uma me$%@" +
                                                                                          " mesmo!"};

    private static final String descricaoCaractsCategoria4[] = {"Estar foragido há trinta anos com " +
                                                                        "a certeza de que passadas " +
                                                                        "planejadas são a chave da " +
                                                                                        "impunidade!",
                                                                  "Barulhos na madrugada são apenas" +
                                                                  " estalos dos móveis de madeira! " +
                                                                "Quer dizer, na maioria das vezes...",
                                                                "Saltos altos e tamancos são ótimos" +
                                                                       " para casamentos e missões " +
                                                                                          "secretas!",
                                                                "Andar sobre plástico bolha, uma " +
                                                                "atividade relaxante que “talvez” " +
                                                                "não combine com agentes secretos!",
                                                                "O silêncio é o barulho da ausência" +
                                                                " de som. O mundo é barulhento, as " +
                                                                                    "pessoas também!"};

    private static final String descricaoCaractsCategoria5[] = {"Calistenia, a prática de aumentar a" +
                                                                " massa muscular sem gastar dinheiro" +
                                                                                      " com academia!",
                                                                "Fisiculturismo, a prática de " +
                                                                "aumentar a massa muscular até não " +
                                                                    "conseguir mais coçar as costas!",
                                                                   "Comer, dormir e correr em dias " +
                                                                                            "pares!",
                                                                   "Comer, dormir e comer e dormir!",
                                                                         "Comer! Para que dormir?!"};

    private static final String descricaoCaractsCategoria6[] = {"Levantar um trem de carga com cem " +
                                                                "vagões apenas para poder cruzar a " +
                                                                                      "linha férrea!",
                                                                "Armários em madeira maciça são " +
                                                                "apenas um pouco mais pesados do que" +
                                                                                " uma dúzia de ovos!",
                                                                "Empurrar uma cama de casal requer" +
                                                                   " esforço, principalmente se o " +
                                                                       "casal estiver deitado nela!",
                                                                "Puxar o freio de mão de alguns " +
                                                                "carros é algo que requer bastante" +
                                                                                           " força!",
                                                                "A cabeça é a parte mais pesada do " +
                                                                                            "corpo!"};




    public static String retornaConsultaTodasConfiguracoesGeraisTbConfigApp(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT chave, valor, valor_min, valor_max FROM TB_CONFIG_APP");

        return sql.toString();
    }
    public static String retornaInsercaoConfigSomBotoesTuplaTbConfigApp(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CONFIG_APP(chave, valor_min, valor_max, valor) VALUES('som_botoes', 0, 1, 1)");

        return sql.toString();
    }

    public static String retornaInsercaoConfigSomMusicasTuplaTbConfigApp(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CONFIG_APP(chave, valor_min, valor_max, valor) VALUES('som_musicas', 0, 1, 1)");

        return sql.toString();
    }

    public static String retornaInsercaoCategoria0TbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('");
        sql.append(categorias[0]);
        sql.append("', ");
        sql.append("'");
        sql.append(descricaoCategorias[0]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoria1TbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('");
        sql.append(categorias[1]);
        sql.append("', ");
        sql.append("'");
        sql.append(descricaoCategorias[1]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoria2TbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('");
        sql.append(categorias[2]);
        sql.append("', ");
        sql.append("'");
        sql.append(descricaoCategorias[2]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoria3TbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('");
        sql.append(categorias[3]);
        sql.append("', ");
        sql.append("'");
        sql.append(descricaoCategorias[3]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoria4TbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('");
        sql.append(categorias[4]);
        sql.append("', ");
        sql.append("'");
        sql.append(descricaoCategorias[4]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoria5TbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('");
        sql.append(categorias[5]);
        sql.append("', ");
        sql.append("'");
        sql.append(descricaoCategorias[5]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoria6TbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('");
        sql.append(categorias[6]);
        sql.append("', ");
        sql.append("'");
        sql.append(descricaoCategorias[6]);
        sql.append("')");

        return sql.toString();
    }

    //Inserções das características da categoria 0:

    public static String retornaInsercaoCaract0Categoria0TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria0[0]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[0]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria0[0]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[0]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract1Categoria0TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria0[1]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[1]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria0[1]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[0]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract2Categoria0TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria0[2]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[2]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria0[2]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[0]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract3Categoria0TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria0[3]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[3]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria0[3]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[0]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract4Categoria0TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria0[4]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[4]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria0[4]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[0]);
        sql.append("')");

        return sql.toString();
    }

    //------------------------------------------------------------------------------------------

    //Inserções das características da categoria 1:

    public static String retornaInsercaoCaract0Categoria1TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria1[0]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[0]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria1[0]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[1]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract1Categoria1TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria1[1]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[1]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria1[1]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[1]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract2Categoria1TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria1[2]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[2]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria1[2]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[1]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract3Categoria1TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria1[3]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[3]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria1[3]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[1]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract4Categoria1TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria1[4]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[4]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria1[4]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[1]);
        sql.append("')");

        return sql.toString();
    }

    //-------------------------------------------------------------------------------

    //Inserções das características da categoria 2:

    public static String retornaInsercaoCaract0Categoria2TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria2[0]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[0]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria2[0]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[2]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract1Categoria2TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria2[1]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[1]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria2[1]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[2]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract2Categoria2TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria2[2]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[2]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria2[2]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[2]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract3Categoria2TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria2[3]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[3]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria2[3]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[2]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract4Categoria2TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria2[4]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[4]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria2[4]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[2]);
        sql.append("')");

        return sql.toString();
    }

    //-------------------------------------------------------------------------------

    //Inserções das características da categoria 3:

    public static String retornaInsercaoCaract0Categoria3TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria3[0]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[0]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria3[0]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[3]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract1Categoria3TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria3[1]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[1]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria3[1]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[3]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract2Categoria3TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria3[2]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[2]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria3[2]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[3]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract3Categoria3TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria3[3]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[3]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria3[3]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[3]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract4Categoria3TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria3[4]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[4]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria3[4]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[3]);
        sql.append("')");

        return sql.toString();
    }

    //-------------------------------------------------------------------------------

    //Inserções das características da categoria 4:

    public static String retornaInsercaoCaract0Categoria4TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria4[0]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[0]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria4[0]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[4]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract1Categoria4TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria4[1]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[1]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria4[1]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[4]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract2Categoria4TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria4[2]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[2]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria4[2]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[4]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract3Categoria4TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria4[3]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[3]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria4[3]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[4]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract4Categoria4TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria4[4]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[4]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria4[4]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[4]);
        sql.append("')");

        return sql.toString();
    }

    //-------------------------------------------------------------------------------

    //Inserções das características da categoria 5:

    public static String retornaInsercaoCaract0Categoria5TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria5[0]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[0]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria5[0]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[5]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract1Categoria5TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria5[1]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[1]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria5[1]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[5]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract2Categoria5TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria5[2]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[2]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria5[2]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[5]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract3Categoria5TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria5[3]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[3]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria5[3]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[5]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract4Categoria5TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria5[4]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[4]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria5[4]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[5]);
        sql.append("')");

        return sql.toString();
    }

    //-------------------------------------------------------------------------------

    //Inserções das características da categoria 6:

    public static String retornaInsercaoCaract0Categoria6TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria6[0]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[0]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria6[0]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[6]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract1Categoria6TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria6[1]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[1]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria6[1]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[6]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract2Categoria6TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria6[2]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[2]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria6[2]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[6]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract3Categoria6TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria6[3]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[3]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria6[3]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[6]);
        sql.append("')");

        return sql.toString();
    }

    public static String retornaInsercaoCaract4Categoria6TbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_TIPO_CARACT(nome, nivel, descricao, nome_categoria_tipo) ");
        sql.append("VALUES('");
        sql.append(caractsCategoria6[4]);
        sql.append("', ");
        sql.append(niveisCaracteristicas[4]);
        sql.append(", ");
        sql.append("'");
        sql.append(descricaoCaractsCategoria6[4]);
        sql.append("', ");
        sql.append("'");
        sql.append(categorias[6]);
        sql.append("')");

        return sql.toString();
    }

    //-------------------------------------------------------------------------------------

    public static String retornaConsultaValorConfiguracaoEspecificaTbConfigApp(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT valor FROM TB_CONFIG_APP ");
        sql.append("WHERE ");
        sql.append("id = ?");

        return sql.toString();
    }
}
