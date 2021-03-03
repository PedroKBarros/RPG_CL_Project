package usuario.app.rpg_cl_project.dominio;

public class PersonagemJogador extends Personagem {
    private int totalObjetivosAlcancados;

    public PersonagemJogador(String nome){
        super(nome);
        this.totalObjetivosAlcancados = 0;
    }

    public PersonagemJogador(String nome, int totalObjetivosAlcancados){
        super(nome);
        this.totalObjetivosAlcancados = totalObjetivosAlcancados;
    }

    public int getTotalObjetivosAlcancados() {
        return totalObjetivosAlcancados;
    }

    public void setTotalObjetivosAlcancados(int totalObjetivosAlcancados) {
        this.totalObjetivosAlcancados = totalObjetivosAlcancados;
    }
}
