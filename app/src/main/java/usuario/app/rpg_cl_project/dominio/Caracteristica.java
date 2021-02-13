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
}
