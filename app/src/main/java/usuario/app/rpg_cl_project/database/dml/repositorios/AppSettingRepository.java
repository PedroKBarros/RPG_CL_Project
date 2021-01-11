package usuario.app.rpg_cl_project.database.dml.repositorios;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import usuario.app.rpg_cl_project.dominio.AppSetting;
import usuario.app.rpg_cl_project.dominio.GeneralSetting;

public class AppSettingRepository {
    private SQLiteDatabase conexao;

    public AppSettingRepository(SQLiteDatabase conexao){ this.conexao = conexao; }

    public AppSetting queryAllTuples(){
        List<GeneralSetting> generalSettings = new ArrayList<GeneralSetting>();
        AppSetting appSetting = new AppSetting();
        String chave;
        int valor, valorMin, valorMax;

        Cursor cursor = conexao.rawQuery(ScriptDML.getQueryAppSettingAllTuples(), null); //Método para escrever consulta SQL, passando a String da consulta e parâmetros da consulta em um vetor de Strings
        //Verificando se algum registro foi retornado:                         //O primeiro parâmetro é a quary e o outro são os argumentos. Porém, como não temos argumentos, pois não usamos a clausula where, coloquei como null.
        if (cursor.getCount() > 0){                                           //Ele retorna um objeto Cursor, que contém as tuplas de resultado. Vamos converter para List<Cliente>.
            //Garantindo q a gnt vai ter os resultados a
            // partir do  primeiro registro:
            cursor.moveToFirst();
            //Transformando o resultado em List<GeneralSetting>:
            do{
                //Convertendo os valores da tabela em atributos da classe GeneralSetting
                chave = cursor.getString(cursor.getColumnIndex("chave"));
                valor = cursor.getInt(cursor.getColumnIndex("valor"));
                valorMin = cursor.getInt(cursor.getColumnIndex("valor_min"));
                valorMax = cursor.getInt(cursor.getColumnIndex("valor_max"));
                GeneralSetting generalSetting = new GeneralSetting(chave, valor, valorMin, valorMax);

                generalSettings.add(generalSetting);
            }while(cursor.moveToNext());//Enquanto houver registro,
            // vamos mover o resultado para o próximo registro. Esse método retorna true, caso tenha conseguido mover.
        }

        appSetting.setGeneralSettings(generalSettings);
        return appSetting;
    }
}
