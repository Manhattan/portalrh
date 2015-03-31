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
@Table(name = "CARGOS")
public class Cargo implements EntityInterface<Cargo>{

    @Id
    @Column(name = "CODIGO")
    private String codigo;
    /*
     */
    @Column(name = "ANO")
    private String ano;
    /*
     */
    @Column(name = "MES")
    private String mes;
    /*
     */
    @Column(name = "CODFOLHA")
    private String codFolha;
    /*
     */
    @Column(name = "CBO")
    private String CBO;
    /*
     */
    @Column(name = "DESCRICAO")
    private String descricao;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getCodFolha() {
        return codFolha;
    }

    public void setCodFolha(String codFolha) {
        this.codFolha = codFolha;
    }

    public String getCBO() {
        return CBO;
    }

    public void setCBO(String CBO) {
        this.CBO = CBO;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public Serializable getId() {
        return codigo;
    }

    @Override
    public String getLabel() {
        return descricao;
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
    public int compareTo(Cargo o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final Cargo other = (Cargo) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }
}
