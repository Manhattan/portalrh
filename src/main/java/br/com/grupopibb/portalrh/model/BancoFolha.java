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
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "CADBANCOS")
public class BancoFolha implements EntityInterface<BancoFolha> {

    public BancoFolha() {
    }

    public BancoFolha(String numero, String nome, String sigla) {
        this.numero = numero;
        this.nome = nome;
        this.sigla = sigla;
    }

    @Id
    @Column(name = "NUMBANCO")
    private String numero;
    /*
     */
    @Column(name = "NOMEBANCO")
    private String nome;
    /*
     */
    @Column(name = "SIGLA")
    private String sigla;

    @Override
    public Serializable getId() {
        return numero;
    }

    @Override
    public String getLabel() {
        return numero + " - " + nome;
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
    public int compareTo(BancoFolha o) {
        return this.getNumero().compareTo(o.getNumero());
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return numero;
    }
    
}
