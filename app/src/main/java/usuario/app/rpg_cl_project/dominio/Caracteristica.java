package usuario.app.rpg_cl_project.dominio;

public class Caracteristica {
    private Categoria categoria;
    private String nome;
    private int nivel;
    private String descricao;

    public Caracteristica(Categoria categoria, String nome, int nivel, String descricao){
        this.categoria = categoria;
        this.nome = nome;
        this.nivel = nivel;
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
