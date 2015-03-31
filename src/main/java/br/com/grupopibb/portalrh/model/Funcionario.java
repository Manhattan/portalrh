/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.model;

import br.com.grupopibb.portalrh.model.commons.EntityInterface;
import br.com.grupopibb.portalrh.types.EnumHabilitado;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "PO_Funcionario")
@NamedQuery(name = "Funcionario.findByLogin",
        query = " SELECT DISTINCT f FROM Funcionario f "
        + " WHERE f.login = :login")
public class Funcionario implements EntityInterface<Funcionario> {

    public Funcionario() {
    }

    public Funcionario(List<CentroCusto> centros) {
        this.centros = centros;
    }
    @Id
    @Column(name = "Fun_Cod")
    private Integer codigo;
    /*
     */
    @Column(name = "Fun_Nome")
    private String nome;
    /*
     */
    @Column(name = "Fun_Sobrenome")
    private String sobrenome;
    /*
     */
    @Column(name = "Empresa_Cod")
    private String empresa;
    /*
     */
    @Column(name = "Fun_Login")
    private String login;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Fun_Ativo")
    private EnumHabilitado ativo;
    /*
     */
    @Transient
    private String senha;
    /*
     */
    @ManyToMany(targetEntity = CentroCusto.class, mappedBy = "funcionarios", fetch = FetchType.EAGER)
    private List<CentroCusto> centros;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAtivo() {
        if (this.ativo == EnumHabilitado.S) {
            return true;
        }
        return false;
    }

    public void setAtivo(boolean ativo) {
        if (ativo) {
            this.ativo = EnumHabilitado.S;
        } else {
            this.ativo = EnumHabilitado.N;
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<CentroCusto> getCentros() {
        return centros;
    }

    public void setCentros(List<CentroCusto> centros) {
        this.centros = centros;
    }

    @Override
    public Serializable getId() {
        return nome;
    }

    @Override
    public String getLabel() {
        return sobrenome;
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
    public int compareTo(Funcionario o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }
}
