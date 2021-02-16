package usuario.app.rpg_cl_project.auxiliar;

import java.util.ArrayList;
import java.util.List;

import usuario.app.rpg_cl_project.dominio.Caracteristica;

public class OperacaoCaract {
    public static List<String> retornaNomesCaractsLista(List<Caracteristica> caracts){
        List<String> nomesCaracts = new ArrayList<String>();
        for(Caracteristica cAtual : caracts){
            nomesCaracts.add(cAtual.getNome());
        }
        return nomesCaracts;
    }
}
