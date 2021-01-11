package usuario.app.rpg_cl_project.domain;

public class GeneralSetting {
    private String chave;
    private int valor;
    private int valorMin;
    private int valorMax;

    public GeneralSetting(String chave, int valor, int valorMin, int valorMax){
        this.chave = chave;
        this.valor = valor;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
    }


}
