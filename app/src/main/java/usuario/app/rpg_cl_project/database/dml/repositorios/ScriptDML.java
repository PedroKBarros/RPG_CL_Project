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
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('Saúde', ");
        sql.append("'Influencia em: COMBATES e MOVIMENTAÇÕES')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaAgressividadeTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('Agressividade', ");
        sql.append("'Influencia em: COMBATES e CAPACIDADE DE SE IMPOR')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaAgilidadeTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome,  descricao) VALUES('Agilidade', ");
        sql.append("'Influencia em: MOVIMENTAÇÃO, COMABATE e FUGAS')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaVisaoTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('Visão', ");
        sql.append("'Influencia em: CAPACIDADE DE VIZUALIZAÇÃO DE OBJETOS DOS CENÁRIOS')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaPassadaTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('Passada', ");
        sql.append("'Influencia em: CAPACIDADE DE NÃO SER PERCEBIDO')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaFormaFisicaTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('Forma Física', ");
        sql.append("'Influencia em: INTIMIDAÇÃO DE INIMIGOS e ACESSO A PARTES DOS CENÁRIOS')");

        return sql.toString();
    }

    public static String retornaInsercaoCategoriaForcaTbCategoriaTipo(){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO TB_CATEGORIA_TIPO(nome, descricao) VALUES('Força', ");
        sql.append("'Influencia em: COMBATES e DEMAIS SITUAÇÕES EM QUE SE PRECISE EXERCER FORÇA SOBRE ALGO')");

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
