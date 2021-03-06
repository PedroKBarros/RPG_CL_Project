package usuario.app.rpg_cl_project.database.dml.repositorios;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import usuario.app.rpg_cl_project.dominio.Caracteristica;
import usuario.app.rpg_cl_project.dominio.Categoria;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoGeral;

public class RepositorioTbTipoCaract {
    private SQLiteDatabase conexao;
    private static final  String NOME_TABELA = "TB_TIPO_CARACT";

    public RepositorioTbTipoCaract(SQLiteDatabase conexao){ this.conexao = conexao; }

    public List<Caracteristica> buscaCaractsPorCategoria(String nomeCategoria){
        Categoria categoria;
        String nome;
        int nivel;
        String descricao;
        String[] parametros;
        List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();

        RepositorioTbCategoriaTipo repositorioTbCategoriaTipo = new
                                                            RepositorioTbCategoriaTipo(this.conexao);

        categoria = repositorioTbCategoriaTipo.buscaCategoria(nomeCategoria);

        if (categoria == null)
            return null;

        parametros = new String[1];
        parametros[0] = nomeCategoria;
        Cursor cursor = conexao.rawQuery(
                ScriptDML.retornaConsultaCaractsCategoriaTbTipoCaractTbCategoriaTipo(), parametros);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                nome = cursor.getString(cursor.getColumnIndex("nome"));
                nivel = cursor.getInt(cursor.getColumnIndex("nivel"));
                descricao = cursor.getString(cursor.getColumnIndex("descricao"));

                caracteristicas.add(new Caracteristica(categoria, nome, nivel, descricao));
            }while(cursor.moveToNext());
        }
        return caracteristicas;
    }

    public List<String> buscaNomeCaractsPorCategoria(String nomeCategoria){
        String nome;
        String[] parametros;
        List<String> nomesCaracts = new ArrayList<String>();

        parametros = new String[1];
        parametros[0] = nomeCategoria;
        Cursor cursor = conexao.rawQuery(
                ScriptDML.retornaConsultaCaractsCategoriaTbTipoCaractTbCategoriaTipo(), parametros);
        Log.i("RTTC", "CHEGOU0!");
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Log.i("RTTC", "CHEGOU!");
                nome = cursor.getString(cursor.getColumnIndex(NOME_TABELA + ".nome"));
                Log.i("RTTC", NOME_TABELA + ".nome");

                nomesCaracts.add(nome);
            }while(cursor.moveToNext());
        }
        return nomesCaracts;
    }

}
