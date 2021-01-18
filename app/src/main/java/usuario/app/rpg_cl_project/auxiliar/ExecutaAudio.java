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

    public boolean executaAudioThreadUI(String strUri, Boolean repetir) {
        try {
            Uri uri = Uri.parse(strUri);
            this.mediaPlayer.reset();
            this.mediaPlayer.setLooping(repetir);
            this.mediaPlayer.setDataSource(this.contexto, uri);
            this.mediaPlayer.prepare();
            this.mediaPlayer.start();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public boolean executaAudioThreadUICondicional(String strUri, Boolean repetir, Boolean condicao) {
        try {
            if (!condicao)
                return true;
            Uri uri = Uri.parse(strUri);
            this.mediaPlayer.reset();
            this.mediaPlayer.setLooping(repetir);
            this.mediaPlayer.setDataSource(this.contexto, uri);
            this.mediaPlayer.prepare();
            this.mediaPlayer.start();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public boolean executaAudioAsync(String strUri, Boolean repetir)  {
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
            this.mediaPlayer.setLooping(repetir);
            this.mediaPlayer.setDataSource(this.contexto, uri);
            this.mediaPlayer.setOnPreparedListener(preparedListener);
            this.mediaPlayer.setOnErrorListener(errorListener);
            this.mediaPlayer.prepareAsync();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public boolean executaAudioAsyncCondicional(String strUri, Boolean repetir, Boolean condicao)  {
        try {
            if (!condicao)
                return true;

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

            Uri uri = Uri.parse(strUri);
            this.mediaPlayer.reset();
            this.mediaPlayer.setLooping(repetir);
            this.mediaPlayer.setDataSource(this.contexto, uri);
            this.mediaPlayer.setOnPreparedListener(preparedListener);
            this.mediaPlayer.setOnErrorListener(errorListener);
            this.mediaPlayer.prepareAsync();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public void pausaAudio()  {
        this.mediaPlayer.pause();
    }

    public void paraExecucao()  {this.mediaPlayer.stop();}

        public void liberaRecursos(){
        this.mediaPlayer.release();
    }

}
