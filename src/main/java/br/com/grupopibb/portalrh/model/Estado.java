/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.model;

import br.com.grupopibb.portalrh.model.commons.EntityInterface;
import br.com.grupopibb.portalrh.types.EnumPais;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name="Estado")
@NamedQuery(name="Estado.findByPaisUf", 
        query=" SELECT DISTINCT e FROM Estado e "
        + " WHERE e.pais = :pais"
        + " AND e.uf = :uf ")
public class Estado implements EntityInterface<Estado>{
    
    public Estado(){}
    
    public Estado(EnumPais pais, String uf, String nome){
        this.pais = pais;
        this.uf = uf;
        this.nome = nome;
    }

    @Id 
    @Enumerated(EnumType.STRING)
    @Column(name="Pais_Cod")
    private EnumPais pais;
    /*
     */
    @Id
    @Column(name="UF_Sigla")
    private String uf;
    /*
     */
    @Column(name="UF_Nome")
    private String nome;

    @Override
    public Serializable getId() {
        return pais.toString() + uf;
    }

    @Override
    public String getLabel() {
        return uf;
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
    public int compareTo(Estado o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.pais != null ? this.pais.hashCode() : 0);
        hash = 89 * hash + (this.uf != null ? this.uf.hashCode() : 0);
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
        final Estado other = (Estado) obj;
        if (this.pais != other.pais) {
            return false;
        }
        if ((this.uf == null) ? (other.uf != null) : !this.uf.equals(other.uf)) {
            return false;
        }
        return true;
    }

    public EnumPais getPais() {
        return pais;
    }

    public void setPais(EnumPais pais) {
        this.pais = pais;
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

    @Override
    public String toString() {
        return uf;
    }
    
}
