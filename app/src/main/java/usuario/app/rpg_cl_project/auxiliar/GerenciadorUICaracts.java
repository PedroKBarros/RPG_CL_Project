package usuario.app.rpg_cl_project.auxiliar;

import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import usuario.app.rpg_cl_project.R;
import usuario.app.rpg_cl_project.dominio.Caracteristica;

public class GerenciadorUICaracts {
    private String nomeCategoria;
    private ImageView uiValorCaract;
    private Spinner uiCaracts;
    private boolean ehInitSpinner;
    private TextView uiDetalhes;
    private List<Caracteristica> caracts;

    public GerenciadorUICaracts(String nomeCategoria, ImageView uiValorCaract, Spinner uiCaracts,
                                                                            TextView uiDetalhes){
        this.nomeCategoria = nomeCategoria;
        this.uiValorCaract = uiValorCaract;
        this.uiCaracts = uiCaracts;
        this.uiDetalhes = uiDetalhes;
        this.ehInitSpinner = true;
        this.caracts = new ArrayList<Caracteristica>();
    }


    public Caracteristica retornaCaractSelecionada(){
        String itemSelecionado = (String) this.uiCaracts.getSelectedItem();

        for(Caracteristica c : this.caracts){
            if (c.getNome().compareTo(itemSelecionado) == 0){
                return c;
            }
        }
        return null;
    }

    public Caracteristica retornaCaractSelecionada(int indiceCaractLista){

        if (indiceCaractLista < 0 || indiceCaractLista >= this.caracts.size())
            return null;

        return this.caracts.get(indiceCaractLista);
    }

    public Caracteristica retornaCaractSelecionada(String nomeCaract, boolean diferenciarMaiscMinusc){

        for(Caracteristica c : this.caracts){
            if (diferenciarMaiscMinusc){
                if (c.getNome().compareToIgnoreCase(nomeCaract) == 0){
                    return c;
                }
            }else{
                if (c.getNome().compareTo(nomeCaract) == 0){
                    return c;
                }
            }
        }
        return null;
    }

    public void selecionaCaractUiSpinner(int posicao){
        this.uiCaracts.setSelection(posicao);
    }

    public void formataUiPorCaractSelecionada(Caracteristica caract){
        this.alteraImagemPorNivelCaract(caract.getNivel());
        this.formataDetalhePorCaract(caract);
    }

    private void formataDetalhePorCaract(Caracteristica caract){
        String descricaoCategoria = caract.getCategoria().getDescricao();
        String descricaoCaract = caract.getDescricao();

        Formatador formatador = new Formatador(
                "INFLUENCIA EM:"
                            + "\n" +
                            descricaoCategoria
                            + "\n\n" +
                            "DESCRIÇÃO:"
                            + "\n" +
                            descricaoCaract
        );

        //Colocando Subtítulos com cor de fonte preta:
        formatador.formataPorExpressao("INFLUENCIA EM:", 0, 1,
                Formatador.estiloCorFonte(Formatador.C_PRETO), Formatador.F_EXCLUSIVE_EXCLUSIVE);
        formatador.formataPorExpressao("DESCRIÇÃO:", 0, 1,
                Formatador.estiloCorFonte(Formatador.C_PRETO), Formatador.F_EXCLUSIVE_EXCLUSIVE);

        //Colocando Subtítulos sublinhado:
        formatador.formataPorExpressao("INFLUENCIA EM", 0, 1,
                Formatador.estiloTextoSublinhado(), Formatador.F_EXCLUSIVE_EXCLUSIVE);
        formatador.formataPorExpressao("DESCRIÇÃO", 0, 1,
                Formatador.estiloTextoSublinhado(), Formatador.F_EXCLUSIVE_EXCLUSIVE);

        //Colocando descricao da categoria com cor laranja e em negrito:
        formatador.formataPorExpressao(descricaoCategoria, 0, 1,
                Formatador.estiloCorFonte("#E07E54"), Formatador.F_EXCLUSIVE_EXCLUSIVE);
        formatador.formataPorExpressao(descricaoCategoria, 0, 1,
                Formatador.estiloTextoNegrito(), Formatador.F_EXCLUSIVE_EXCLUSIVE);

        //Colocando descricao da caract com cor azul e em negrito:
        formatador.formataPorExpressao(descricaoCaract, 0, 1,
                Formatador.estiloCorFonte("#548CE0"), Formatador.F_EXCLUSIVE_EXCLUSIVE);
        formatador.formataPorExpressao(descricaoCaract, 0, 1,
                Formatador.estiloTextoNegrito(), Formatador.F_EXCLUSIVE_EXCLUSIVE);


        this.uiDetalhes.setText(formatador.getSpannableStringBuilder());

    }

    private void alteraImagemPorNivelCaract(int nivel){
        switch (nivel){
            case 10:
                this.uiValorCaract.setImageResource(R.drawable.caracteristica_valor10);
                break;
            case 8:
                this.uiValorCaract.setImageResource(R.drawable.caracteristica_valor8);
                break;
            case 6:
                this.uiValorCaract.setImageResource(R.drawable.caracteristica_valor6);
                break;
            case 4:
                this.uiValorCaract.setImageResource(R.drawable.caracteristica_valor4);
                break;
            default:
                this.uiValorCaract.setImageResource(R.drawable.caracteristica_valor2);
                break;
        }
    }

    public boolean ehSpinnerInicializando(){
        return this.ehInitSpinner;
    }

    public ImageView getUiValorCaract() {
        return uiValorCaract;
    }

    public void setUiValorCaract(ImageView uiValorCaract) {
        this.uiValorCaract = uiValorCaract;
    }

    public Spinner getUiCaracts() {
        return uiCaracts;
    }

    public void setUiCaracts(Spinner uiCaracts) {
        this.uiCaracts = uiCaracts;
    }

    public boolean getEhInitSpinner() {
        return ehInitSpinner;
    }

    public void setEhInitSpinner(boolean ehInitSpinner) {
        this.ehInitSpinner = ehInitSpinner;
    }

    public TextView getUiDetalhes() {
        return uiDetalhes;
    }

    public void setUiDetalhes(TextView uiDetalhes) {
        this.uiDetalhes = uiDetalhes;
    }

    public List<Caracteristica> getCaracts() {
        return caracts;
    }

    public void setCaracts(List<Caracteristica> caracts) {
        this.caracts = caracts;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

}
