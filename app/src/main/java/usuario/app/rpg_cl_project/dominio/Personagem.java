package usuario.app.rpg_cl_project.dominio;

public class Personagem {
    private String nome;

    public Personagem(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
