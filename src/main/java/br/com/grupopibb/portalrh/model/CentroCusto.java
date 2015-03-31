/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.model;

import br.com.grupopibb.portalrh.model.commons.EntityInterface;
import br.com.grupopibb.portalrh.types.EnumEspecieCentroCusto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "Centro_de_Custo")
@NamedQuery(name = "CentroCusto.findByFilial",
        query = "SELECT DISTINCT c FROM CentroCusto c "
        + " WHERE c.empresa = :empresaCod "
        + " AND c.filial = :filialCod ")
public class CentroCusto implements EntityInterface<CentroCusto> {

    public CentroCusto() {
    }

    public CentroCusto(String codigo, String empresa, String filial) {
        this.codigo = codigo;
        this.empresa = empresa;
        this.filial = filial;
    }

    public CentroCusto(String codigo, String empresa, String filial, String nucleo, String nome, EnumEspecieCentroCusto especie) {
        this.codigo = codigo;
        this.empresa = empresa;
        this.filial = filial;
        this.nucleo = nucleo;
        this.nome = nome;
        this.especie = especie;
    }
    /*
     */
    @Id
    @NotNull
    @Column(name = "Centro_Cod")
    private String codigo;
    /*
     */
    @Id
    @NotNull
    @Column(name = "Empresa_Cod")
    private String empresa;
    /*
     */
    @Id
    @NotNull
    @Column(name = "Filial_Cod")
    private String filial;
    /*
     */
    @Size(min = 2, max = 2)
    @Column(name = "Nucleo_Cod", length = 2)
    private String nucleo;
    /*
     */
    @Size(min = 1, max = 100)
    @Column(name = "Centro_Nome", length = 100)
    private String nome;
    /*
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "EspCentro_Cod")
    private EnumEspecieCentroCusto especie;
    /*
     */
    @ManyToOne(targetEntity = Estado.class)
    @JoinColumns({
        @JoinColumn(name = "UF_Sigla", referencedColumnName = "UF_Sigla"),
        @JoinColumn(name = "Pais_Cod", referencedColumnName = "Pais_Cod")
    })
    private Estado estado;
    /*
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "PO_Funcionario_Centro",
            joinColumns = {
        @JoinColumn(name = "Centro_Cod", referencedColumnName = "Centro_Cod"),
        @JoinColumn(name = "Empresa_Cod", referencedColumnName = "Empresa_Cod"),
        @JoinColumn(name = "Filial_Cod", referencedColumnName = "Filial_Cod")},
            inverseJoinColumns = {
        @JoinColumn(name = "Fun_Cod", referencedColumnName = "Fun_Cod")
    })
    private List<Funcionario> funcionarios;

    public String getNomeCompleto() {
        return empresa + "." + filial + "." + codigo + " - " + nome;
    }

    @Override
    public Serializable getId() {
        return this.empresa + this.filial + this.codigo;
    }

    @Override
    public String getLabel() {
        return this.getCodigo() + " - " + this.getNome();
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
    public int compareTo(CentroCusto o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        hash = 79 * hash + (this.empresa != null ? this.empresa.hashCode() : 0);
        hash = 79 * hash + (this.filial != null ? this.filial.hashCode() : 0);
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
        final CentroCusto other = (CentroCusto) obj;
        if ((this.codigo == null) ? (other.codigo != null) : !this.codigo.equals(other.codigo)) {
            return false;
        }
        if ((this.empresa == null) ? (other.empresa != null) : !this.empresa.equals(other.empresa)) {
            return false;
        }
        if ((this.filial == null) ? (other.filial != null) : !this.filial.equals(other.filial)) {
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getNucleo() {
        return nucleo;
    }

    public void setNucleo(String nucleo) {
        this.nucleo = nucleo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnumEspecieCentroCusto getEspecie() {
        return especie;
    }

    public void setEspecie(EnumEspecieCentroCusto especie) {
        this.especie = especie;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
