package usuario.app.rpg_cl_project.auxiliar;

import android.content.Context;
import android.media.MediaPlayer;

import usuario.app.rpg_cl_project.R;

public class SomBotao {

    private Context contexto;
    private final static int arquivoSom = R.raw.som_clique_botao;

    public SomBotao(Context contexto){
        this.contexto = contexto;
    }

    public static void tocaSom(Context context){
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, arquivoSom);

        mediaPlayer.start();
    }
}
