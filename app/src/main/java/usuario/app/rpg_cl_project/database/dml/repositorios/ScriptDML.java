package usuario.app.rpg_cl_project.database.dml.repositorios;

public class ScriptQuery {
    public static String getQueryAppSettingAllTuples(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM TB_CONFIG_APP");

        return sql.toString();
    }
}
