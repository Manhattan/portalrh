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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "CONTRATO")
@NamedQueries({
    @NamedQuery(name = "Contrato.findByCentroECpf",
            query = " SELECT DISTINCT c FROM Contrato c"
            + " WHERE (:empresaCod = c.empresaCod) "
            + " AND (:filialCod = c.filialCod) "
            + " AND (:centroCod = c.centroCod) "
            + " AND (:cpf = c.cpf) "
            + " AND (:mes = c.mes) "
            + " AND (c.codFolha = '1') "
            + " AND (c.recalcular = 'S' OR c.recalcular = '' OR c.recalcular is null OR c.recalcular = 'F' OR c.recalcular = 'C')"),
    @NamedQuery(name = "Contrato.findCpfByCentro",
            query = " SELECT DISTINCT c.cpf FROM Contrato c "
            + " WHERE (:empresaCod = c.empresaCod) "
            + " AND (:filialCod = c.filialCod) "
            + " AND (:centroCod = c.centroCod) "
            + " AND (:mes = c.mes) "
            + " AND (c.codFolha = '1') ")
})
public class Contrato implements EntityInterface<Contrato> {

    @Id
    @Column(name = "Empresa_Cod")
    private String empresaCod;
    /*
     */
    @Id
    @Column(name = "MATRICULA")
    private String matricula;
    /*
     */
    @Id
    @Column(name = "ANO")
    private String ano;
    /*
     */
    @Id
    @Column(name = "MES")
    private String mes;
    /*
     */
    @Id
    @Column(name = "CODFOLHA")
    private String codFolha;
    /*
     */
    @Id
    @Column(name = "CODFILIAL")
    private String filialCod;
    /*
     */
    @Column(name = "CODCC")
    private String centroCod;
    /*
     */
    @Column(name = "CPF")
    private String cpf;
    /*
     */
    @Column(name = "NOME")
    private String nome;
    /*
     */
    @Column(name = "RECALCULAR")
    private String recalcular;
    /*
     */
    @Column(name = "CODDEPART")
    private String departamentoCod;
    /*
     */
    @Column(name = "CODSETOR")
    private String setorCod;
    /*
     */
    @OneToOne(targetEntity = Cargo.class)
    @JoinColumns({
        @JoinColumn(name="CARGO", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name="ANO", referencedColumnName = "ANO", insertable = false, updatable = false),
        @JoinColumn(name="MES", referencedColumnName = "MES", insertable = false, updatable = false),
        @JoinColumn(name="CODFOLHA", referencedColumnName = "CODFOLHA", insertable = false, updatable = false)
    })
    private Cargo cargo;
    /*
     */
    @ManyToOne(targetEntity = CentroCusto.class)
    @JoinColumns({
        @JoinColumn(name = "Empresa_Cod", referencedColumnName = "Empresa_Cod", insertable = false, updatable = false),
        @JoinColumn(name = "CODFILIAL", referencedColumnName = "Filial_Cod", insertable = false, updatable = false),
        @JoinColumn(name = "CODCC", referencedColumnName = "Centro_Cod", insertable = false, updatable = false)
    })
    private CentroCusto centro;

    @Override
    public Serializable getId() {
        return this.empresaCod + this.matricula + this.ano + this.mes + this.codFolha + this.filialCod;
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
    public int compareTo(Contrato o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }

    public String getEmpresaCod() {
        return empresaCod;
    }

    public void setEmpresaCod(String empresaCod) {
        this.empresaCod = empresaCod;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public String getFilialCod() {
        return filialCod;
    }

    public void setFilialCod(String filialCod) {
        this.filialCod = filialCod;
    }

    public String getCentroCod() {
        return centroCod;
    }

    public void setCentroCod(String centroCod) {
        this.centroCod = centroCod;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRecalcular() {
        return recalcular;
    }

    public void setRecalcular(String recalcular) {
        this.recalcular = recalcular;
    }

    public String getDepartamentoCod() {
        return departamentoCod;
    }

    public void setDepartamentoCod(String departamentoCod) {
        this.departamentoCod = departamentoCod;
    }

    public String getSetorCod() {
        return setorCod;
    }

    public void setSetorCod(String setorCod) {
        this.setorCod = setorCod;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.empresaCod != null ? this.empresaCod.hashCode() : 0);
        hash = 47 * hash + (this.matricula != null ? this.matricula.hashCode() : 0);
        hash = 47 * hash + (this.ano != null ? this.ano.hashCode() : 0);
        hash = 47 * hash + (this.mes != null ? this.mes.hashCode() : 0);
        hash = 47 * hash + (this.codFolha != null ? this.codFolha.hashCode() : 0);
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
        final Contrato other = (Contrato) obj;
        if ((this.empresaCod == null) ? (other.empresaCod != null) : !this.empresaCod.equals(other.empresaCod)) {
            return false;
        }
        if ((this.matricula == null) ? (other.matricula != null) : !this.matricula.equals(other.matricula)) {
            return false;
        }
        if ((this.ano == null) ? (other.ano != null) : !this.ano.equals(other.ano)) {
            return false;
        }
        if ((this.mes == null) ? (other.mes != null) : !this.mes.equals(other.mes)) {
            return false;
        }
        if ((this.codFolha == null) ? (other.codFolha != null) : !this.codFolha.equals(other.codFolha)) {
            return false;
        }
        return true;
    }
    
    
}
