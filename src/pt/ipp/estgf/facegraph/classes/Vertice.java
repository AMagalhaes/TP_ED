package pt.ipp.estgf.facegraph.classes;

/**
 * Created by antoniomagalhaes on 09/01/15.
 */
public class Vertice implements VertexInterface {
    private static int UID = -1;

    private int id = UID++;
    private String nome;
    private String cidade;


    public Vertice() {
    }

    public Vertice(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
        this.id++;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = this.getId() + 1;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertice vertice = (Vertice) o;

        if (id != vertice.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
