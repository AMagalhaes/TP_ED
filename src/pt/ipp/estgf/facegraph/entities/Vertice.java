package pt.ipp.estgf.facegraph.entities;

import pt.ipp.estgf.facegraph.Interfaces.UnorderedList;
import pt.ipp.estgf.facegraph.Interfaces.VertexInterface;
import pt.ipp.estgf.facegraph.lists.ArrayUnorderedList;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

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

    public Vertice(int id, String nome, String cidade) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
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
    public String toString() {
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
