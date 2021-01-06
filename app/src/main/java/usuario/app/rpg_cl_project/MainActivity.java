package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import usuario.app.rpg_cl_project.database.ddl.DadosOpenHelper;

public class MainActivity extends AppCompatActivity {

    MainActivity activityAtual;
    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityAtual = this;
        new Thread(new Runnable() {
            public void run() {
                try {
                    establishesDBConnection();
                    activityAtual.runOnUiThread(new Runnable() {
                        public void run() {
                            mostraMensagemToast("Conexão com BD estabelicida!", Toast.LENGTH_SHORT);
                        }
                    });
                    dadosOpenHelper.fechaConexao();
                }catch(android.database.SQLException e){
                    //Há dois SQLException e temos q usar o android.database, pois é o q pertence ao pacote do SQLite
                    mostraMensagemToast(e.getMessage(), Toast.LENGTH_SHORT);
                }finally {
                    dadosOpenHelper.fechaConexao();
                }
            }
        }).start();
    }

    private void establishesDBConnection() throws android.database.SQLException{
        //Método que vai criar a conexão com o BD em si. Esse método não cria nenhuma estrutura, apenas conecta com o BD
        dadosOpenHelper = new DadosOpenHelper(this); //Lembre-se que nosso contrutor pede o contexto, ou seja, a Activity em questão
        //Criando conexão
        conexao = dadosOpenHelper.estabeleceConexao(); //O writable permite leitura e escrita, enquanto q o redtable, só leitura
        //Fecha a activity atual e todas as que estão abaixo na pilha (abertas anteriormente);
    }

    private void mostraMensagemToast(String mensagem, int duracao){
        Toast toast = Toast.makeText(this, mensagem, duracao);
        toast.show();
    }
}