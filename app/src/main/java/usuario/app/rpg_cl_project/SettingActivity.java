package usuario.app.rpg_cl_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_setting);

        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(
                                                                R.id.complete_header_out_play);
        TextView headerTitle = (TextView) constraintLayout.getViewById(R.id.txt_header_title);
        headerTitle.setText("CONFIGURAÇÕES");

    }
}