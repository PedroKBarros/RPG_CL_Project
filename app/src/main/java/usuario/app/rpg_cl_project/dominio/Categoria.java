package usuario.app.rpg_cl_project.dominio;

public class Categoria {
    private String nome;
    private String descricao;

    public Categoria(String nome){
        this.nome = nome;
        this.descricao = "";
    }

    public Categoria(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
