package usuario.app.rpg_cl_project.database.dml.repositorios;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import usuario.app.rpg_cl_project.dominio.Categoria;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoGeral;

public class RepositorioTbCategoriaTipo {
    private SQLiteDatabase conexao;

    public RepositorioTbCategoriaTipo(SQLiteDatabase conexao){ this.conexao = conexao; }

    public Categoria buscaCategoria(String nome){
        String[] parametros;
        String descricao;
        Categoria categoria;

        if (nome.isEmpty())
            return null;

        parametros = new String[1];
        parametros[0] = nome;
        Cursor cursor = conexao.rawQuery(ScriptDML.retornaConsultaCategoriaTbCategoriaTipo(), parametros);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            categoria = new Categoria(nome, descricao);
            return categoria;
        }
        return null;
    }
}
