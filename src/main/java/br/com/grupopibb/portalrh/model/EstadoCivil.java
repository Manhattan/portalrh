/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.model;

import java.io.Serializable;

/**
 *
 * @author administrator
 */
public class EstadoCivil implements Comparable<EstadoCivil>, Serializable {

    private String codigo;
    private String nome;

    public EstadoCivil() {
    }

    public EstadoCivil(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
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
        hash = 79 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final EstadoCivil other = (EstadoCivil) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int compareTo(EstadoCivil o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }
}
