package usuario.app.rpg_cl_project.database.dml.repositorios;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import usuario.app.rpg_cl_project.dominio.Caracteristica;
import usuario.app.rpg_cl_project.dominio.PersonagemJogador;

public class RepositorioTbCaract {
    private SQLiteDatabase conexao;

    public RepositorioTbCaract(SQLiteDatabase conexao){ this.conexao = conexao; }

    public void inseriTupla(PersonagemJogador personagemJogador, Caracteristica caracteristica){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome_personagem_jog", personagemJogador.getNome());
        contentValues.put("nome_tipo_caract", caracteristica.getNome());

        conexao.insertOrThrow("TB_CARACT", null, contentValues); //Esse método insere os dados no BD, podendo gerar uma exceção caso ocorra um erro, e se não ocorrer, retorna
        //o valor da chave primária, o q é bom, pois podemos usá-lo caso seja necessário
        //O primeiro parâmetro é o nome da tabela, o segundo é besteira e o terceiro são os valores da tupla.
    }
}
