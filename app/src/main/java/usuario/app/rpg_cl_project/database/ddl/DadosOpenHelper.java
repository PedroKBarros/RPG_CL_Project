package usuario.app.rpg_cl_project.database.ddl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import usuario.app.rpg_cl_project.database.dml.repositorios.ScriptDML;

public class DadosOpenHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "BD_RPG_CL_PROJECT.db";
    private static int VERSAO_BD = 1;

    //Construtor
    public DadosOpenHelper(@Nullable Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*A primeira vez que o método getWritableDataBase() for chamado,
        o onCreate, onUpgrade() e o onOpen(), esse último de implementação opcional,
        serão chamados*/
        //Criacao de tabelas:
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
        db.execSQL(ScriptDDL.getCreateTableTbConfigPersonagemJogo());
        db.execSQL(ScriptDDL.getCreateTableTbTrecho());
        db.execSQL(ScriptDDL.getCreateTableTbEvento());
        db.execSQL(ScriptDDL.getCreateTableTbAcao());
        db.execSQL(ScriptDDL.getCreateTableTbCaractAcao());
        db.execSQL(ScriptDDL.getCreateTableTbCaractEvento());
        db.execSQL(ScriptDDL.getCreateTableTbMomento());
        db.execSQL(ScriptDDL.getCreateTableTbConfigApp());

        //Inserção de dados prévios:
        //  TB_CONFIG_APP:
        db.execSQL(ScriptDML.retornaInsercaoConfigSomBotoesTuplaTbConfigApp());
        db.execSQL(ScriptDML.retornaInsercaoConfigSomMusicasTuplaTbConfigApp());

        //  TB_CATEGORIA_TIPO:
        db.execSQL(ScriptDML.retornaInsercaoCategoria0TbCategoriaTipo());
        db.execSQL(ScriptDML.retornaInsercaoCategoria1TbCategoriaTipo());
        db.execSQL(ScriptDML.retornaInsercaoCategoria2TbCategoriaTipo());
        db.execSQL(ScriptDML.retornaInsercaoCategoria3TbCategoriaTipo());
        db.execSQL(ScriptDML.retornaInsercaoCategoria4TbCategoriaTipo());
        db.execSQL(ScriptDML.retornaInsercaoCategoria5TbCategoriaTipo());
        db.execSQL(ScriptDML.retornaInsercaoCategoria6TbCategoriaTipo());

        //  TB_TIPO_CARACT:
        db.execSQL(ScriptDML.retornaInsercaoCaract0Categoria0TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract1Categoria0TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract2Categoria0TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract3Categoria0TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract4Categoria0TbTipoCaract());

        db.execSQL(ScriptDML.retornaInsercaoCaract0Categoria1TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract1Categoria1TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract2Categoria1TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract3Categoria1TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract4Categoria1TbTipoCaract());

        db.execSQL(ScriptDML.retornaInsercaoCaract0Categoria2TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract1Categoria2TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract2Categoria2TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract3Categoria2TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract4Categoria2TbTipoCaract());

        db.execSQL(ScriptDML.retornaInsercaoCaract0Categoria3TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract1Categoria3TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract2Categoria3TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract3Categoria3TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract4Categoria3TbTipoCaract());

        db.execSQL(ScriptDML.retornaInsercaoCaract0Categoria4TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract1Categoria4TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract2Categoria4TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract3Categoria4TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract4Categoria4TbTipoCaract());

        db.execSQL(ScriptDML.retornaInsercaoCaract0Categoria5TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract1Categoria5TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract2Categoria5TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract3Categoria5TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract4Categoria5TbTipoCaract());

        db.execSQL(ScriptDML.retornaInsercaoCaract0Categoria6TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract1Categoria6TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract2Categoria6TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract3Categoria6TbTipoCaract());
        db.execSQL(ScriptDML.retornaInsercaoCaract4Categoria6TbTipoCaract());

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

    public void fechaBD(){
        this.close();
    }
}
