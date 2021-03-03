package usuario.app.rpg_cl_project.auxiliar;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoGerenciadorUICaracts {
    private List<GerenciadorUICaracts> gerenciadores;
    private TextView uiTotal;

    private static final int MAXIMO_NIVEIS_10 = 5;
    private static final int MINIMO_TOTAL = 14;
    private static final int MAXIMO_TOTAL = 64;

    public ConjuntoGerenciadorUICaracts(TextView uiTotal){
        this.uiTotal = uiTotal;
        this.gerenciadores = new ArrayList<GerenciadorUICaracts>();
    }

    public boolean qtdNiveis10Permitida(){
        int qtdNiveis10 = 0;
        for(GerenciadorUICaracts g : this.gerenciadores){
            if(g.retornaCaractSelecionada().getNivel() == 10)
                qtdNiveis10++;
        }

        return qtdNiveis10 <= MAXIMO_NIVEIS_10;
    }

    public boolean ehTotalIntervaloValido(){
        int total = calculaTotal();

        return total >= MINIMO_TOTAL && total <= MAXIMO_TOTAL;
    }

    public boolean ehTudoValido(){
        return qtdNiveis10Permitida() && ehTotalIntervaloValido();
    }

    public void add(GerenciadorUICaracts gerenciadorUICaracts){
        this.gerenciadores.add(gerenciadorUICaracts);
    }

    public void addVarios(GerenciadorUICaracts... gerenciadores){
        for(GerenciadorUICaracts g : gerenciadores){
            this.gerenciadores.add(g);
        }
    }

    public int calculaTotal(){
        int total;

        total = 0;
        for(GerenciadorUICaracts g : gerenciadores){
            Log.i("CGUIC", "\n\nNOME = " + g.retornaCaractSelecionada().getNome() + "\nENCREMENTO = "
                    + Integer.toString(g.retornaCaractSelecionada().getNivel()));

            total += g.retornaCaractSelecionada().getNivel();
        }

        return total;
    }

    public void atualizaUiTotal(int total){
        this.uiTotal.setText(" " + Integer.toString(total) + " ");
    }

    public static int getMaximoNiveis10() {
        return MAXIMO_NIVEIS_10;
    }

    public static int getMinimoTotal() {
        return MINIMO_TOTAL;
    }

    public static int getMaximoTotal() {
        return MAXIMO_TOTAL;
    }
}
