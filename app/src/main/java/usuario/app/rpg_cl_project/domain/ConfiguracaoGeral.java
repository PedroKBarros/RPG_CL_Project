package usuario.app.rpg_cl_project.domain;

public class ConfiguracaoGeral {
    private String chave;
    private int valor;
    private int valorMin;
    private int valorMax;

    public ConfiguracaoGeral(String chave, int valor, int valorMin, int valorMax){
        this.chave = chave;
        this.valor = valor;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValorMin() {
        return valorMin;
    }

    public void setValorMin(int valorMin) {
        this.valorMin = valorMin;
    }

    public int getValorMax() {
        return valorMax;
    }

    public void setValorMax(int valorMax) {
        this.valorMax = valorMax;
    }
}
