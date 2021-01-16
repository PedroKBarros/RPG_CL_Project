package usuario.app.rpg_cl_project.auxiliar;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.lang.reflect.Method;

import usuario.app.rpg_cl_project.ConfiguracaoAppActivity;

public class ExecutaMensagem {

    private Context contexto;
    private Toast ultimoToastInstanciado;
    private AlertDialog ultimoAlertDlgInstanciado;

    public static final int TOAST_DURACAO_CURTA = Toast.LENGTH_SHORT;
    public static final int TOAST_DURACAO_LONGA = Toast.LENGTH_LONG;
    public static final int ALERTDLG_TIPO_BOTAO_POSITIVO = AlertDialog.BUTTON_POSITIVE;
    public static final int ALERTDLG_TIPO_BOTAO_NEUTRO = AlertDialog.BUTTON_NEUTRAL;
    public static final int ALERTDLG_TIPO_BOTAO_NEGATIVO = AlertDialog.BUTTON_NEGATIVE;

    public ExecutaMensagem(Context contexto){
        this.contexto = contexto;
    }

    public Toast criaToast(String mensagem, int duracao){
        try {
            Toast toast = Toast.makeText(this.contexto, mensagem, duracao);
            this.ultimoToastInstanciado = toast;
            return toast;
        }catch (Exception e){
            return null;
        }
    }

    public Toast mostraToast(){
        try {
            ultimoToastInstanciado.show();
            return ultimoToastInstanciado;
        }catch (Exception e){
            return null;
        }
    }

    public Toast cancelaToast(){
        try {
            ultimoToastInstanciado.cancel();
            return ultimoToastInstanciado;
        }catch (Exception e){
            return null;
        }
    }

    public AlertDialog criaAlertDialogSemTitulo(String mensagem){
        try {
            AlertDialog alertDialog = new AlertDialog.Builder(this.contexto).create();
            alertDialog.setMessage(mensagem);
            this.ultimoAlertDlgInstanciado = alertDialog;
            return alertDialog;
        }catch (Exception e){
            return null;
        }
    }

    public AlertDialog criaAlertDialogComTitulo(String titulo, String mensagem){
        try {
            AlertDialog alertDialog = new AlertDialog.Builder(this.contexto).create();
            alertDialog.setTitle(titulo);
            alertDialog.setMessage(mensagem);
            this.ultimoAlertDlgInstanciado = alertDialog;
            return alertDialog;
        }catch (Exception e){
            return null;
        }
    }

    public AlertDialog addBotaoAlertDialog(int tipoBotao, String textoBotao,
                                                  DialogInterface.OnClickListener eventoClique){
        try{
            ultimoAlertDlgInstanciado.setButton(tipoBotao, textoBotao, eventoClique);
            return ultimoAlertDlgInstanciado;
        }catch (Exception e){
            return null;
        }
    }

    public AlertDialog addEventoCancelAlertDialog(DialogInterface.OnCancelListener eventoCancel){
        try{
            ultimoAlertDlgInstanciado.setOnCancelListener(eventoCancel);
            return ultimoAlertDlgInstanciado;
        }catch (Exception e){
            return null;
        }
    }

    public AlertDialog mostraAlertDialog(){
        try {
            ultimoAlertDlgInstanciado.show();
            return ultimoAlertDlgInstanciado;
        }catch (Exception e){
            return null;
        }
    }

    public AlertDialog cancelaAlertDialog(){
        try {
            ultimoAlertDlgInstanciado.cancel();
            return ultimoAlertDlgInstanciado;
        }catch (Exception e){
            return null;
        }
    }

    public Toast getUltimoToastInstanciado() {
        return ultimoToastInstanciado;
    }

    public AlertDialog getUltimoAlertDlgInstanciado() {
        return ultimoAlertDlgInstanciado;
    }
}
