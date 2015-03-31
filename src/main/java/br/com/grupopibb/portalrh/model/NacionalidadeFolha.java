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
@Table(name="Nacio_Folha")
@NamedQuery(name="NacionalidadeFolha.selectAll", 
        query=" SELECT DISTINCT n FROM NacionalidadeFolha n")
public class NacionalidadeFolha implements EntityInterface<NacionalidadeFolha>{

    public NacionalidadeFolha() {
    }

    public NacionalidadeFolha(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    
    @Id
    @Column(name="CodNacio_Folha")
    private String codigo;
    /*
     */
    @Column(name="NomeNacio_Folha")
    private String nome;

    @Override
    public Serializable getId() {
        return this.codigo;
    }

    @Override
    public String getLabel() {
        return this.nome;
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
    public int compareTo(NacionalidadeFolha o) {
        return this.getId().toString().compareTo(o.getId().toString());
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final NacionalidadeFolha other = (NacionalidadeFolha) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

} 
