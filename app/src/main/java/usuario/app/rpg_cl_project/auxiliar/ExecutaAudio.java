package usuario.app.rpg_cl_project.auxiliar;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;

import usuario.app.rpg_cl_project.R;

public class ExecutaAudio {

    private Context contexto;
    private MediaPlayer mediaPlayer;
    public final static String URI_SOM_CLIQUE_BOTAO = "android.resource://usuario.app.rpg_cl_project/raw/som_clique_botao";

    public ExecutaAudio(Context contexto){
        this.contexto = contexto;
        this.mediaPlayer = new MediaPlayer();
    }

    public boolean executaAudioThreadUI(String strUri) {
        try {
            Uri uri = Uri.parse(strUri);
            this.mediaPlayer.reset();
            this.mediaPlayer.setDataSource(this.contexto, uri);
            this.mediaPlayer.prepare();
            this.mediaPlayer.start();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public boolean executaAudioAsync(String strUri)  {
        /*Observação: A thread em que será executada esse método deverá ser a thread diferente da de UI,
        implementada em quem chama esse método.*/
        new Thread(new Runnable() {
            public void run() {

            }
        }).start();
        MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        };

        MediaPlayer.OnErrorListener errorListener = new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                liberaRecursos();
                return false;
            }
        };

        try {
            Uri uri = Uri.parse(strUri);
            this.mediaPlayer.reset();
            this.mediaPlayer.setDataSource(this.contexto, uri);
            this.mediaPlayer.setOnPreparedListener(preparedListener);
            this.mediaPlayer.setOnErrorListener(errorListener);
            this.mediaPlayer.prepareAsync();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public void executaAudioClickBotaoThreadUI(){

    }

    public void pausaAudio()  {
        this.mediaPlayer.pause();
    }

    public void liberaRecursos(){
        this.mediaPlayer.release();
    }

}
