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
@Table(name = "PARAMETROS")
@NamedQuery(name = "Parametros.find",
        query = " SELECT DISTINCT p FROM Parametros p "
        + " WHERE (p.mes = :mes) "
        + " AND (p.ano = :ano) "
        + " AND (p.codFolha = :codFolha) ")
public class Parametros implements EntityInterface<Parametros> {

    @Id
    @Column(name = "PARAMETROS_MES")
    private String mes;
    /*
     */
    @Id
    @Column(name = "PARAMETROS_ANO")
    private String ano;
    /*
     */
    @Id
    @Column(name = "PARAMETROS_CODFOLHA")
    private String codFolha;
    /*
     */
    @Column(name = "PARAMETROS_DIAENCERRA")
    private Integer diaEncerramento;

    @Override
    public Serializable getId() {
        return mes + ano + codFolha;
    }

    @Override
    public String getLabel() {
        return mes + "." + ano + "." + codFolha + "." + diaEncerramento.toString();
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
    public int compareTo(Parametros o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCodFolha() {
        return codFolha;
    }

    public void setCodFolha(String codFolha) {
        this.codFolha = codFolha;
    }

    public Integer getDiaEncerramento() {
        return diaEncerramento;
    }

    public void setDiaEncerramento(Integer diaEncerramento) {
        this.diaEncerramento = diaEncerramento;
    }
}
