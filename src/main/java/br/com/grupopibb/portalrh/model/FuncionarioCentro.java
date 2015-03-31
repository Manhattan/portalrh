/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.model;


import br.com.grupopibb.portalrh.model.commons.EntityInterface;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author administrator
 */ 
@Entity
@Table(name="PO_Funcionario_Centro")
@NamedQuery(name="FuncionarioCentro.find", 
        query=" SELECT DISTINCT fc FROM FuncionarioCentro fc "
        + " WHERE (fc.centro = :centro) "
        + " AND (fc.funcionario = :funcionario) ")
public class FuncionarioCentro implements EntityInterface<FuncionarioCentro>{

    @Id
    @ManyToOne(targetEntity = CentroCusto.class, fetch = FetchType.EAGER)
    @JoinColumns(value = {
        @JoinColumn(name = "Centro_Cod", referencedColumnName = "Centro_Cod"),
        @JoinColumn(name = "Empresa_Cod", referencedColumnName = "Empresa_Cod"),
        @JoinColumn(name = "Filial_Cod", referencedColumnName = "Filial_Cod")})
    private CentroCusto centro;
    /*
     */
    @Id
    @OneToOne(targetEntity = Funcionario.class)
    @JoinColumn(name="Fun_Cod", nullable = false)
    private Funcionario funcionario;
    /*
     */
    @OneToOne(targetEntity = PerfilAcesso.class)
    @JoinColumn(name="Perfil_Cod", nullable = false)
    private PerfilAcesso perfil;

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public PerfilAcesso getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilAcesso perfil) {
        this.perfil = perfil;
    }

    @Override
    public Serializable getId() {
        return funcionario.getCodigo() + "" + centro.getId();
    }

    @Override
    public String getLabel() {
        return getId().toString();
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
    public int compareTo(FuncionarioCentro o) {
        return this.getLabel().compareTo(o.getLabel());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.centro);
        hash = 37 * hash + Objects.hashCode(this.funcionario);
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
        final FuncionarioCentro other = (FuncionarioCentro) obj;
        if (!Objects.equals(this.centro, other.centro)) {
            return false;
        }
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        return true;
    }
    
    
    
}
