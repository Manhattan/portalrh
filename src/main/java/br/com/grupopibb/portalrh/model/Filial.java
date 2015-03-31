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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Filial")
@NamedQuery(name = "Filial.findByEmpresa",
        query = "SELECT DISTINCT f FROM Filial f "
        + " WHERE f.empresaCod = :empresaCod")
public class Filial implements EntityInterface<Filial> {

    @Id
    @Column(name = "Empresa_Cod")
    private String empresaCod;
    /*
     */
    @Id
    @Column(name = "Filial_Cod")
    private String codigo;
    /*
     */
    @Column(name = "Filial_Nome")
    private String nome;

    @Override
    public Serializable getId() {
        return empresaCod + codigo;
    }

    @Override
    public String getLabel() {
        return codigo + "  - " + nome;
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
    public int compareTo(Filial o) {
        return o.getId().toString().compareTo(o.getId().toString());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.empresaCod != null ? this.empresaCod.hashCode() : 0);
        hash = 97 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final Filial other = (Filial) obj;
        if ((this.empresaCod == null) ? (other.empresaCod != null) : !this.empresaCod.equals(other.empresaCod)) {
            return false;
        }
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo;
    }

    public String getEmpresaCod() {
        return empresaCod;
    }

    public void setEmpresaCod(String empresaCod) {
        this.empresaCod = empresaCod;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
