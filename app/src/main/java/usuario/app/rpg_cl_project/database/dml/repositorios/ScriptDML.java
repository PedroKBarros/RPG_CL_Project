package usuario.app.rpg_cl_project.database.dml.repositorios;

public class ScriptDML {
    public static String retornaConsultaTodasConfiguracoesGerais(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT chave, valor, valor_min, valor_max FROM TB_CONFIG_APP");

        return sql.toString();
    }
}
