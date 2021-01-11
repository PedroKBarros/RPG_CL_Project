package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import usuario.app.rpg_cl_project.database.ddl.DadosOpenHelper;

public class MenuAcitivity extends AppCompatActivity {

    private Button buttonSettings;
    private Button buttonQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonSettings = (Button) findViewById(R.id.bt_settings);
        buttonQuit = (Button) findViewById(R.id.bt_quit);

        buttonSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getBaseContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        buttonQuit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finishAffinity(); //Fecha a activity atual e todas as que estão abaixo na pilha (abertas anteriormente)
            }
        });
    }
}