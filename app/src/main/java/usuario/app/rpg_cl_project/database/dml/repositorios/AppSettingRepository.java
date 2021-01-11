package usuario.app.rpg_cl_project.database.dml.repositorios;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AppConfigurationRepository {
    private SQLiteDatabase conexao;

    public AppConfigurationRepository(SQLiteDatabase conexao){ this.conexao = conexao; }

    public List<Calculo> queryAllTuples(){
        List<Calculo> calculos = new ArrayList<Calculo>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT NOME, EXPRESSAO, DATA, DESCRICAO ");
        sql.append("FROM CALCULO");

        Cursor resultado = conexao.rawQuery(sql.toString(), null); //Método para escrever consulta SQL, passando a String da consulta e parâmetros da consulta em um vetor de Strings
        //Verificando se algum registro foi retornado:                         //O primeiro parâmetro é a quary e o outro são os argumentos. Porém, como não temos argumentos, pois não usamos a clausula where, coloquei como null.
        if (resultado.getCount() > 0){                                           //Ele retorna um objeto Cursor, que contém as tuplas de resultado. Vamos converter para List<Cliente>.
            //Garantindo q a gnt vai ter os resultados a
            // partir do  primeiro registro:
            resultado.moveToFirst();
            //Transformando o resultado em List<Cliente>:
            do{
                //Convertendo os valores da tabela em atributos da classe Calculo:
                Calculo calculo = new Calculo();
                calculo.nome = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
                calculo.expressao = resultado.getString(resultado.getColumnIndexOrThrow("EXPRESSAO"));
                calculo.data = resultado.getString(resultado.getColumnIndexOrThrow("DATA"));
                calculo.descricao = resultado.getString(resultado.getColumnIndexOrThrow("DESCRICAO"));

                calculos.add(calculo);
            }while(resultado.moveToNext());//Enquanto houver registro,
            // vamos mover o resultado para o próximo registro. Esse método retorna true, caso tenha conseguido mover.
        }


        return calculos;
    }
}
