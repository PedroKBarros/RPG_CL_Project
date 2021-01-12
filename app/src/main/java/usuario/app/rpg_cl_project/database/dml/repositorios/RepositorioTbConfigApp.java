package usuario.app.rpg_cl_project.database.dml.repositorios;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import usuario.app.rpg_cl_project.domain.ConfiguracaoGeral;

public class RepositorioTbConfigApp {
    private SQLiteDatabase conexao;

    public RepositorioTbConfigApp(SQLiteDatabase conexao){ this.conexao = conexao; }


    public List<ConfiguracaoGeral> buscarTodasTuplas(){
        List<ConfiguracaoGeral> configuracoesGerais = new ArrayList<ConfiguracaoGeral>();
        String chave;
        int valor, valorMin, valorMax;

        Cursor cursor = conexao.rawQuery(ScriptDML.getQueryAppSettingAllTuples(), null); //Método para escrever consulta SQL, passando a String da consulta e parâmetros da consulta em um vetor de Strings
        //Verificando se algum registro foi retornado:                         //O primeiro parâmetro é a quary e o outro são os argumentos. Porém, como não temos argumentos, pois não usamos a clausula where, coloquei como null.
        if (cursor.getCount() > 0){                                           //Ele retorna um objeto Cursor, que contém as tuplas de resultado. Vamos converter para List<Cliente>.
            //Garantindo q a gnt vai ter os resultados a
            // partir do  primeiro registro:
            cursor.moveToFirst();
            //Transformando o resultado em List<ConfiguracaoGeral>:
            do{
                //Convertendo os valores da tabela em atributos da classe ConfiguracaoGeral
                chave = cursor.getString(cursor.getColumnIndex("chave"));
                valor = cursor.getInt(cursor.getColumnIndex("valor"));
                valorMin = cursor.getInt(cursor.getColumnIndex("valor_min"));
                valorMax = cursor.getInt(cursor.getColumnIndex("valor_max"));
                ConfiguracaoGeral configuracaoGeral = new ConfiguracaoGeral(chave, valor, valorMin, valorMax);

                configuracoesGerais.add(configuracaoGeral);
            }while(cursor.moveToNext());//Enquanto houver registro,
            // vamos mover o resultado para o próximo registro. Esse método retorna true, caso tenha conseguido mover.
        }

        return configuracoesGerais;
    }
}
