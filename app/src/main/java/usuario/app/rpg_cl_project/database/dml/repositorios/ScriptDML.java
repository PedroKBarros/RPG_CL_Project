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

    public static String retornaInsercaoCategoriaSaudeTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome) VALUES('Saúde')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaAgressividadeTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome) VALUES('Agressividade')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaAgilidadeTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome) VALUES('Agilidade')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaVisaoTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome) VALUES('Visão')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaPassadaTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome) VALUES('Passada')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaFormaFisicaTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome) VALUES('Forma Física')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaForcaTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome) VALUES('Força')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaExperienciaTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome) VALUES('Experiência')");

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
