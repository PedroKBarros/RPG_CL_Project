package usuario.app.rpg_cl_project.database.dml.repositorios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import usuario.app.rpg_cl_project.dominio.Categoria;
import usuario.app.rpg_cl_project.dominio.ConfiguracaoGeral;
import usuario.app.rpg_cl_project.dominio.Personagem;
import usuario.app.rpg_cl_project.dominio.PersonagemJogador;

public class RepositorioTbPersonagemJog {

    private SQLiteDatabase conexao;

    public RepositorioTbPersonagemJog(SQLiteDatabase conexao){ this.conexao = conexao; }

    public void inseriTupla(PersonagemJogador personagemJogador){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", personagemJogador.getNome());
        contentValues.put("tot_objs_alcancados", personagemJogador.getTotalObjetivosAlcancados());

        conexao.insertOrThrow("TB_PERSONAGEM_JOG", null, contentValues); //Esse método insere os dados no BD, podendo gerar uma exceção caso ocorra um erro, e se não ocorrer, retorna
        //o valor da chave primária, o q é bom, pois podemos usá-lo caso seja necessário
        //O primeiro parâmetro é o nome da tabela, o segundo é besteira e o terceiro são os valores da tupla.
    }

    public PersonagemJogador buscaPersonagemJog(String nome){
        String[] parametros;
        int totObjetivosAlcancados;
        PersonagemJogador personagemJog;

        if (nome.isEmpty())
            return null;

        parametros = new String[1];
        parametros[0] = nome;
        Cursor cursor = conexao.rawQuery(ScriptDML.retornaConsultaPersonagemJogadorTbPersonagemJog(),
                                                                                        parametros);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            totObjetivosAlcancados = cursor.getInt(cursor.getColumnIndex("tot_objs_alcancados"));
            personagemJog = new PersonagemJogador(nome, totObjetivosAlcancados);
            return personagemJog;
        }
        return null;
    }


}
