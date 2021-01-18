package usuario.app.rpg_cl_project.database.dml.repositorios;

public class ScriptDML {
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

    public static String retornaConsultaValorConfiguracaoEspecificaTbConfigApp(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT valor FROM TB_CONFIG_APP ");
        sql.append("WHERE ");
        sql.append("id = ?");

        return sql.toString();
    }
}
