package usuario.app.rpg_cl_project.database.ddl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DadosOpenHelper extends SQLiteOpenHelper {
    //Construtor
    public DadosOpenHelper(@Nullable Context context) {
        super(context, "BD_RPG_CL_PROJECT", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*A primeira vez que o método getWritableDataBase() for chamado,
        o onCreate, onUpgrade() e o onOpen(), esse último de implementação opcional,
        serão chamados*/
        db.execSQL(ScriptDDL.getCreateTableTbPersonagemJog());
        db.execSQL(ScriptDDL.getCreateTableTbCategoriaTipo());
        db.execSQL(ScriptDDL.getCreateTableTbCenario());
        db.execSQL(ScriptDDL.getCreateTableTbNarrador());
        db.execSQL(ScriptDDL.getCreateTableTbTipoCaract());
        db.execSQL(ScriptDDL.getCreateTableTbCaract());
        db.execSQL(ScriptDDL.getCreateTableTbAventura());
        db.execSQL(ScriptDDL.getCreateTableTbAbertura());
        db.execSQL(ScriptDDL.getCreateTableTbJogo());
        db.execSQL(ScriptDDL.getCreateTableTbConfig());
        db.execSQL(ScriptDDL.getCreateTableTbOrdemJogo());
        db.execSQL(ScriptDDL.getCreateTableTbTrecho());
        db.execSQL(ScriptDDL.getCreateTableTbEvento());
        db.execSQL(ScriptDDL.getCreateTableTbAcao());
        db.execSQL(ScriptDDL.getCreateTableTbCaractAcao());
        db.execSQL(ScriptDDL.getCreateTableTbCaractEvento());
        db.execSQL(ScriptDDL.getCreateTableTbMomento());
        db.execSQL(ScriptDDL.getCreateTableTbConfigApp());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Método para realizar qualquer atualização no BD, como incluir um campo numa tabela, por exemplo

    }

    public SQLiteDatabase estabeleceConexao() {
        return this.getWritableDatabase();
    }

    public void fechaConexao(){
        this.getWritableDatabase().close();
    }
}
