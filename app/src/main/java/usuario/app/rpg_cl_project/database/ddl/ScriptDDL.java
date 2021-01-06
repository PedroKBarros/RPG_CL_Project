package usuario.app.rpg_cl_project.database.ddl;

public class ScriptDDL {
    public static String getCreateTableTbPersonagemJog(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_PERSONAGEM_JOG(nome VARCHAR(30) NOT NULL, ");
        sql.append("tot_objs_alcancados INT NOT NULL DEFAULT 0, ");
        sql.append("eh_auto INT NOT NULL DEFAULT 0, ");
        sql.append("CONSTRAINT pk_tb_personagem_jog PRIMARY KEY(nome), ");
        sql.append("CONSTRAINT ch_tb_personagem_jog_tot_objs_alcancados CHECK(tot_objs_alcancados >= 0), ");
        sql.append("CONSTRAINT ch_tb_personagem_jog_eh_auto CHECK(eh_auto = 0 OR eh_auto = 1))");

        return sql.toString();
    }

    public static String getCreateTableTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_CATEGORIA_TIPO(nome VARCHAR(20) NOT NULL, ");
        sql.append("CONSTRAINT pk_tb_categoria_tipo PRIMARY KEY(nome))");

        return sql.toString();
    }

    public static String getCreateTableTbCenario(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_CENARIO(nome VARCHAR(30) NOT NULL, ");
        sql.append("CONSTRAINT pk_tb_cenario PRIMARY KEY(nome))");

        return sql.toString();
    }

    public static String getCreateTableTbNarrador(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_NARRADOR(nome VARCHAR(30) NOT NULL, ");
        sql.append("velocidade_narracao INT NOT NULL, ");
        sql.append("CONSTRAINT pk_tb_narrador PRIMARY KEY(nome))");

        return sql.toString();
    }

    public static String getCreateTableTbTipoCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_TIPO_CARACT(nome VARCHAR(20) NOT NULL, ");
        sql.append("nivel INT NOT NULL, ");
        sql.append("descricao VARCHAR(100) NOT NULL, ");
        sql.append("nome_categoria_tipo VARCHAR(20) NOT NULL, ");
        sql.append("CONSTRAINT pk_tb_tipo_caract PRIMARY KEY(nome), ");
        sql.append("CONSTRAINT fk_tb_tipo_caract_tb_categoria_tipo FOREIGN KEY(nome_categoria_tipo) ");
        sql.append("REFERENCES TB_CATEGORIA_TIPO(nome), ");
        sql.append("CONSTRAINT ch_tb_tipo_caract_nivel CHECK(nivel = 10 OR nivel = 8 OR nivel = 6 ");
        sql.append("OR nivel = 4 OR nivel = 2 OR nivel = 0))");

        return sql.toString();
    }

    public static String getCreateTableTbCaract(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_CARACT(nome_personagem_jog VARCHAR(30) NOT NULL, ");
        sql.append("nome_tipo_caract VARCHAR(20) NOT NULL, ");
        sql.append("CONSTRAINT pk_tb_caract PRIMARY KEY(nome_personagem_jog, nome_tipo_caract), ");
        sql.append("CONSTRAINT fk_tb_caract_tb_personagem_jog FOREIGN KEY(nome_personagem_jog) ");
        sql.append("REFERENCES TB_PERSONAGEM_JOG(nome), ");
        sql.append("CONSTRAINT fk_tb_caract_tb_tipo_caract FOREIGN KEY(nome_tipo_caract) ");
        sql.append("REFERENCES TB_TIPO_CARACT(nome))");

        return sql.toString();
    }

    public static String getCreateTableTbAventura(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_AVENTURA(titulo VARCHAR(50) NOT NULL, ");
        sql.append("descricao VARCHAR(300) NOT NULL, ");
        sql.append("objetivo VARCHAR(300) NOT NULL, ");
        sql.append("nome_narrador VARCHAR(30) NOT NULL, ");
        sql.append("caminho_banner VARCHAR(300) NOT NULL, ");
        sql.append("CONSTRAINT pk_tb_aventura PRIMARY KEY(titulo), ");
        sql.append("CONSTRAINT fk_tb_aventura_tb_narrador FOREIGN KEY(nome_narrador) ");
        sql.append("REFERENCES TB_NARRADOR(nome))");

        return sql.toString();
    }

    public static String getCreateTableTbAbertura(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_ABERTURA(id INT NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("texto VARCHAR(1000) NOT NULL, ");
        sql.append("eh_individual INT NOT NULL, ");
        sql.append("caminho_imagem VARCHAR(300), ");
        sql.append("caminho_musica VARCHAR(300), ");
        sql.append("titulo_aventura VARCHAR(50) NOT NULL, ");
        sql.append("CONSTRAINT fk_tb_abertura_tb_aventura FOREIGN KEY(titulo_aventura) ");
        sql.append("REFERENCES tb_aventura(titulo), ");
        sql.append("CONSTRAINT ch_tb_abertura_eh_individual CHECK(eh_individual = 0 OR ");
        sql.append("eh_individual = 1))");

        return sql.toString();
    }

    public static String getCreateTableTbJogo(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_JOGO(nome VARCHAR(40) NOT NULL, ");
        sql.append("data_criacao VARCHAR(10) NOT NULL, ");
        sql.append("ordem_jog_atual INT NOT NULL DEFAULT 1, ");
        sql.append("nome_jog_obj VARCHAR(30), ");
        sql.append("titulo_aventura VARCHAR(50) NOT NULL, ");
        sql.append("CONSTRAINT pk_tb_jogo PRIMARY KEY(nome), ");
        sql.append("CONSTRAINT fk_tb_jogo_tb_aventura FOREIGN KEY(titulo_aventura) ");
        sql.append("REFERENCES tb_aventura(titulo), ");
        sql.append("CONSTRAINT ch_tb_jogo_ordem_jog_atual CHECK(ordem_jog_atual >= 1))");

        return sql.toString();
    }

    public static String getCreateTableTbConfig(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_CONFIG(nome_jogo VARCHAR(40) NOT NULL, ");
        sql.append("tocar_musica INT NOT NULL DEFAULT 1, ");
        sql.append("qtd_trechos_personagem INT NOT NULL DEFAULT 3, ");
        sql.append("qtd_jogadores INT NOT NULL DEFAULT 1, ");
        sql.append("CONSTRAINT pk_tb_config PRIMARY KEY(nome_jogo), ");
        sql.append("CONSTRAINT fk_tb_config_tb_jogo FOREIGN KEY(nome_jogo) REFERENCES tb_jogo(nome), ");
        sql.append("CONSTRAINT ch_tb_config_tocar_musica CHECK(tocar_musica = 0 OR tocar_musica = 1), ");
        sql.append("CONSTRAINT ch_tb_config_qtd_trechos_personagem ");
        sql.append("CHECK(qtd_trechos_personagem >= 1 AND qtd_trechos_personagem <= 10), ");
        sql.append("CONSTRAINT ch_tb_config_qtd_jogadores ");
        sql.append("CHECK(qtd_jogadores >= 1 AND qtd_jogadores <= 4))");

        return sql.toString();
    }

    public static String getCreateTableTbOrdemJogo(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_ORDEM_JOGO(nome_jogo VARCHAR(40) NOT NULL, ");
        sql.append("nome_personagem VARCHAR(30) NOT NULL, ");
        sql.append("ordem INT NOT NULL, ");
        sql.append("CONSTRAINT pk_ordem_jogo PRIMARY KEY(nome_jogo, nome_personagem), ");
        sql.append("CONSTRAINT fk_tb_ordem_jogo_tb_jogo FOREIGN KEY(nome_jogo) ");
        sql.append("REFERENCES tb_jogo(nome), ");
        sql.append("CONSTRAINT fk_tb_ordem_jogo_tb_personagem_jog FOREIGN KEY(nome_personagem) ");
        sql.append("REFERENCES tb_personagem_jog(nome), ");
        sql.append("CONSTRAINT ch_tb_ordem_jogo_ordem CHECK(ordem >= 1))");

        return sql.toString();
    }

    public static String getCreateTableTbTrecho(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_TRECHO(id INT PRIMARY KEY NOT NULL AUTOINCREMENT, ");
        sql.append("texto VARCHAR(1000) NOT NULL, ");
        sql.append("caminho_imagem VARCHAR(300), ");
        sql.append("caminho_musica VARCHAR(300), ");
        sql.append("eh_final INT NOT NULL, ");
        sql.append("eh_trecho_evento INT NOT NULL, ");
        sql.append("titulo_aventura VARCHAR(50) NOT NULL, ");
        sql.append("nome_cenario VARCHAR(30) NOT NULL, ");
        sql.append("CONSTRAINT fk_tb_trecho_tb_aventura FOREIGN KEY(titulo_aventura) ");
        sql.append("REFERENCES tb_aventura(titulo), ");
        sql.append("CONSTRAINT fk_tb_trecho_tb_cenario FOREIGN KEY(nome_cenario) ");
        sql.append("REFERENCES tb_cenario(nome), ");
        sql.append("CONSTRAINT ch_tb_trecho_eh_final CHECK(eh_final = 0 OR eh_final = 1), ");
        sql.append(" CONSTRAINT ch_tb_trecho_eh_trecho_evento ");
        sql.append("CHECK(eh_trecho_evento = 0 OR eh_trecho_evento = 1))");

        return sql.toString();
    }

    public static String getCreateTableTbEvento(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_EVENTO(id INT PRIMARY KEY NOT NULL AUTOINCREMENT, ");
        sql.append("texto VARCHAR(1000) NOT NULL, ");
        sql.append("id_trecho INT NOT NULL, ");
        sql.append("CONSTRAINT fk_tb_evento_tb_trecho FOREIGN KEY(id_trecho) ");
        sql.append("REFERENCES tb_trecho(id))");

        return sql.toString();
    }

    public static String getCreateTableTbAcao(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_ACAO(id INT NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("nome VARCHAR(30) NOT NULL, ");
        sql.append("id_trecho_inicial INT NOT NULL, ");
        sql.append("id_trecho_seguinte INT NOT NULL, ");
        sql.append("id_evento INT NOT NULL, ");
        sql.append("CONSTRAINT fk_tb_acao_tb_trecho1 FOREIGN KEY(id_trecho_inicial) ");
        sql.append("REFERENCES tb_trecho(id), ");
        sql.append("CONSTRAINT fk_tb_acao_tb_trecho2 FOREIGN KEY(id_trecho_seguinte) ");
        sql.append("REFERENCES tb_trecho(id), ");
        sql.append("CONSTRAINT fk_tb_acao_tb_evento FOREIGN KEY(id_evento) ");
        sql.append("REFERENCES tb_evento(id))");

        return sql.toString();
    }

    public static String getCreateTableTbCaractAcao(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_CARACT_ACAO(id_acao INT NOT NULL, ");
        sql.append("nome_tipo_caract VARCHAR(20) NOT NULL, ");
        sql.append("CONSTRAINT pk_tb_caract_acao PRIMARY KEY(id_acao, nome_tipo_caract), ");
        sql.append("CONSTRAINT fk_tb_caract_acao_tb_acao FOREIGN KEY(id_acao) ");
        sql.append("REFERENCES tb_acao(id), ");
        sql.append("CONSTRAINT fk_tb_caract_acao_tb_tipo_caract FOREIGN KEY(nome_tipo_caract) ");
        sql.append("REFERENCES tb_tipo_caract(nome))");

        return sql.toString();
    }

    public static String getCreateTableTbCaractEvento(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_CARACT_EVENTO(id_evento INT NOT NULL, ");
        sql.append("nome_tipo_caract VARCHAR(20) NOT NULL, ");
        sql.append("CONSTRAINT pk_tb_caract_evento PRIMARY KEY(id_evento, nome_tipo_caract), ");
        sql.append("CONSTRAINT fk_tb_caract_evento_tb_evento FOREIGN KEY(id_evento) ");
        sql.append("REFERENCES tb_evento(id), ");
        sql.append("CONSTRAINT fk_tb_caract_evento_tb_tipo_caract FOREIGN KEY(nome_tipo_caract) ");
        sql.append("REFERENCES tb_tipo_caract(nome))");

        return sql.toString();
    }

    public static String getCreateTableTbMomento(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_MOMENTO(nome_jogo VARCHAR(40) NOT NULL, ");
        sql.append("nome_personagem VARCHAR(30) NOT NULL, ");
        sql.append("id_trecho INT NOT NULL, ");
        sql.append("CONSTRAINT pk_tb_momento PRIMARY KEY(nome_jogo, nome_personagem), ");
        sql.append("CONSTRAINT fk_tb_momento_tb_jogo FOREIGN KEY(nome_jogo) ");
        sql.append("REFERENCES tb_jogo(nome), ");
        sql.append("CONSTRAINT fk_tb_momento_tb_personagem_jog FOREIGN KEY(nome_personagem) ");
        sql.append("REFERENCES tb_personagem_jog(nome), ");
        sql.append("CONSTRAINT fk_tb_momento_tb_trecho FOREIGN KEY(id_trecho) ");
        sql.append("REFERENCES tb_trecho(id))");

        return sql.toString();
    }

    public static String getCreateTableTbConfigApp(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS TB_CONFIG_APP(id INT PRIMARY KEY ");
        sql.append("NOT NULL AUTOINCREMENT, ");
        sql.append("chave VARCHAR(40) NOT NULL, ");
        sql.append("valor_min INT NOT NULL, ");
        sql.append("valor_max INT NOT NULL, ");
        sql.append("valor INT NOT NULL, ");
        sql.append("CONSTRAINT ch_tb_config_app_valor ");
        sql.append("CHECK(valor_min <= valor AND valor <= valor_max))");

        return sql.toString();
    }
}
