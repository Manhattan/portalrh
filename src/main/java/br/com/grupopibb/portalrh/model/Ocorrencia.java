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
@Table(name = "CADOCORRENCIAS")
@NamedQueries({
    @NamedQuery(name = "Ocorrencia.selectRange",
            query = " SELECT DISTINCT o FROM Ocorrencia o"
            + " WHERE (:codigo2 = 'todos' OR o.codigo = :codigo) "
            + " AND (:descricao2 = 'todos' OR o.descricao LIKE :descricao) "
            + " AND (:rais2 = 'todos' OR :rais LIKE o.codRais) "
            + " AND (:sefip2 = 'todos' OR :sefip LIKE o.codSefip) "
            + " AND (o.codigo in (3,8,15,29,31,33,65,66,67,72))"),
    @NamedQuery(name = "Ocorrencia.countRange",
            query = " SELECT COUNT(DISTINCT o) FROM Ocorrencia o"
            + " WHERE (:codigo2 = 'todos' OR o.codigo = :codigo) "
            + " AND (:descricao2 = 'todos' OR o.descricao LIKE :descricao) "
            + " AND (:rais2 = 'todos' OR :rais LIKE o.codRais) "
            + " AND (:sefip2 = 'todos' OR :sefip LIKE o.codSefip) "
            + " AND (o.codigo in (3,8,15,29,31,33,65,66,67,72))")
})
public class Ocorrencia implements EntityInterface<Ocorrencia> {

    public Ocorrencia() {
    }

    public Ocorrencia(Integer codigo, String descricao, String codRais, String codSefip, Integer tipo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.codRais = codRais;
        this.codSefip = codSefip;
        this.tipo = tipo;
    }
    @Id
    @Column(name = "CODIGO")
    private Integer codigo;
    /*
     */
    @Column(name = "DESCRICAO")
    private String descricao;
    /*
     */
    @Column(name = "CODRAIS")
    private String codRais;
    /*
     */
    @Column(name = "CODSEFIP")
    private String codSefip;
    /*
     */
    @Column(name = "tipo")
    private Integer tipo;

    @Override
    public Serializable getId() {
        return this.codigo;
    }

    @Override
    public String getLabel() {
        return this.descricao;
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
    public int compareTo(Ocorrencia o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodRais() {
        return codRais;
    }

    public void setCodRais(String codRais) {
        this.codRais = codRais;
    }

    public String getCodSefip() {
        return codSefip;
    }

    public void setCodSefip(String codSefip) {
        this.codSefip = codSefip;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
