/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.model;

import br.com.grupopibb.portalrh.model.commons.EntityInterface;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "MUNI_FOLHA")
@NamedQueries({
    @NamedQuery(name = "MunicipioFolha.selectAll",
            query = " SELECT DISTINCT m FROM MunicipioFolha m"),
    @NamedQuery(name = "MunicipioFolha.findByUf",
            query = " SELECT DISTINCT m FROM MunicipioFolha m"
            + " WHERE m.uf = :uf "),
    @NamedQuery(name= "MunicipioFolha.selectAllUf",
        query=" SELECT DISTINCT m.uf FROM MunicipioFolha m")
})
public class MunicipioFolha implements EntityInterface<MunicipioFolha> {

    public MunicipioFolha() {
    }

    public MunicipioFolha(String codigo, String uf, String nome) {
        this.codigo = codigo;
        this.uf = uf;
        this.nome = nome;
    }
    
    @Id
    @Column(name = "CODNAC")
    private String codigo;
    /*
     */
    @Column(name = "UF")
    private String uf;
    /*
     */
    @Column(name = "NOME")
    private String nome;

    @Override
    public Serializable getId() {
        return codigo;
    }

    @Override
    public String getLabel() {
        return codigo + " - " + uf + " - " + nome;
    }

    @Override
    public boolean verificarId() {
        return false;
    }

    @Override
    public boolean isMarcado() {
        return false;
    }

    @Override
    public int compareTo(MunicipioFolha o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MunicipioFolha other = (MunicipioFolha) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
