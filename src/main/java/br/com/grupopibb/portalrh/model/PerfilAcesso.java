/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.grupopibb.portalrh.model.commons.EntityInterface;
import br.com.grupopibb.portalrh.types.EnumHabilitado;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 *
 * @author administrator
 */
@Entity
@Table(name = "PO_Perfil_Acesso")
public class PerfilAcesso implements EntityInterface<PerfilAcesso> {

    @Id
    @Column(name = "Perfil_Cod")
    private Integer codigo;
    /*
     */
    @Column(name = "Perfil_Nome", nullable = false)
    private String nome;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_Solicitacao")
    private EnumHabilitado incluiSolicitacao;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_Solicitacao")
    private EnumHabilitado alteraSolicitacao;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_Solicitacao")
    private EnumHabilitado removeSolicitacao;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Conclui_Solicitacao")
    private EnumHabilitado concluiSolicitacao;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Autoriza_Solicitacao")
    private EnumHabilitado autorizaSolicitacao;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_Entrada_Material")
    private EnumHabilitado incluiEntradaMaterial;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_Entrada_Material")
    private EnumHabilitado alteraEntradaMaterial;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_Entrada_Material")
    private EnumHabilitado removeEntradaMaterial;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_Saida_Material")
    private EnumHabilitado incluiSaidaMaterial;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_Saida_Material")
    private EnumHabilitado alteraSaidaMaterial;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_Saida_Material")
    private EnumHabilitado removeSaidaMaterial;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_AR")
    private EnumHabilitado incluiAR;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_AR")
    private EnumHabilitado alteraAR;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_AR")
    private EnumHabilitado removeAR;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_Imagem_AR")
    private EnumHabilitado removeImagemAR;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_Fundo_Fixo")
    private EnumHabilitado incluiFundoFixo;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_Fundo_Fixo")
    private EnumHabilitado alteraFundoFixo;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_Fundo_Fixo")
    private EnumHabilitado removeFundoFixo;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Inclui_Usuario")
    private EnumHabilitado incluiUsuario;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Altera_Usuario")
    private EnumHabilitado alteraUsuario;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Remove_Usuario")
    private EnumHabilitado removeUsuario;
    /*
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name="Inclui_RH_CadGer")
    private EnumHabilitado incluiRhCadGer;
    /*
     */
    @Column(name="Altera_RH_CadGer")
    @Enumerated(EnumType.ORDINAL)
    private EnumHabilitado alteraRhCadGer;
    /*
     */
    @Column(name="Exclui_RH_CadGer")
    @Enumerated(EnumType.ORDINAL)
    private EnumHabilitado excluiRhCadGer;
    /*
     */
    @Column(name="Inclui_RH_Ocorrencia")
    @Enumerated(EnumType.ORDINAL)
    private EnumHabilitado incluiRhOcorrencia;
    /*
     */
    @Column(name="Altera_RH_Ocorrencia")
    @Enumerated(EnumType.ORDINAL)
    private EnumHabilitado alteraRhOcorrencia;
    /*
     */
    @Column(name="Exclui_RH_Ocorrencia")
    @Enumerated(EnumType.ORDINAL)
    private EnumHabilitado excluiRhOcorrencia;

    /* ========================= GETTERS E SETTERS ============================ */
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

    public EnumHabilitado getIncluiSolicitacao() {
        return incluiSolicitacao;
    }

    public void setIncluiSolicitacao(EnumHabilitado incluiSolicitacao) {
        this.incluiSolicitacao = incluiSolicitacao;
    }

    public EnumHabilitado getAlteraSolicitacao() {
        return alteraSolicitacao;
    }

    public void setAlteraSolicitacao(EnumHabilitado alteraSolicitacao) {
        this.alteraSolicitacao = alteraSolicitacao;
    }

    public EnumHabilitado getRemoveSolicitacao() {
        return removeSolicitacao;
    }

    public void setRemoveSolicitacao(EnumHabilitado removeSolicitacao) {
        this.removeSolicitacao = removeSolicitacao;
    }

    public EnumHabilitado getConcluiSolicitacao() {
        return concluiSolicitacao;
    }

    public void setConcluiSolicitacao(EnumHabilitado concluiSolicitacao) {
        this.concluiSolicitacao = concluiSolicitacao;
    }

    public EnumHabilitado getAutorizaSolicitacao() {
        return autorizaSolicitacao;
    }

    public void setAutorizaSolicitacao(EnumHabilitado autorizaSolicitacao) {
        this.autorizaSolicitacao = autorizaSolicitacao;
    }

    public EnumHabilitado getIncluiEntradaMaterial() {
        return incluiEntradaMaterial;
    }

    public void setIncluiEntradaMaterial(EnumHabilitado incluiEntradaMaterial) {
        this.incluiEntradaMaterial = incluiEntradaMaterial;
    }

    public EnumHabilitado getAlteraEntradaMaterial() {
        return alteraEntradaMaterial;
    }

    public void setAlteraEntradaMaterial(EnumHabilitado alteraEntradaMaterial) {
        this.alteraEntradaMaterial = alteraEntradaMaterial;
    }

    public EnumHabilitado getRemoveEntradaMaterial() {
        return removeEntradaMaterial;
    }

    public void setRemoveEntradaMaterial(EnumHabilitado removeEntradaMaterial) {
        this.removeEntradaMaterial = removeEntradaMaterial;
    }

    public EnumHabilitado getIncluiSaidaMaterial() {
        return incluiSaidaMaterial;
    }

    public void setIncluiSaidaMaterial(EnumHabilitado incluiSaidaMaterial) {
        this.incluiSaidaMaterial = incluiSaidaMaterial;
    }

    public EnumHabilitado getAlteraSaidaMaterial() {
        return alteraSaidaMaterial;
    }

    public void setAlteraSaidaMaterial(EnumHabilitado alteraSaidaMaterial) {
        this.alteraSaidaMaterial = alteraSaidaMaterial;
    }

    public EnumHabilitado getRemoveSaidaMaterial() {
        return removeSaidaMaterial;
    }

    public void setRemoveSaidaMaterial(EnumHabilitado removeSaidaMaterial) {
        this.removeSaidaMaterial = removeSaidaMaterial;
    }

    public EnumHabilitado getIncluiAR() {
        return incluiAR;
    }

    public void setIncluiAR(EnumHabilitado incluiAR) {
        this.incluiAR = incluiAR;
    }

    public EnumHabilitado getAlteraAR() {
        return alteraAR;
    }

    public void setAlteraAR(EnumHabilitado alteraAR) {
        this.alteraAR = alteraAR;
    }

    public EnumHabilitado getRemoveAR() {
        return removeAR;
    }

    public void setRemoveAR(EnumHabilitado removeAR) {
        this.removeAR = removeAR;
    }

    public EnumHabilitado getRemoveImagemAR() {
        return removeImagemAR;
    }

    public void setRemoveImagemAR(EnumHabilitado removeImagemAR) {
        this.removeImagemAR = removeImagemAR;
    }

    public EnumHabilitado getIncluiFundoFixo() {
        return incluiFundoFixo;
    }

    public void setIncluiFundoFixo(EnumHabilitado incluiFundoFixo) {
        this.incluiFundoFixo = incluiFundoFixo;
    }

    public EnumHabilitado getAlteraFundoFixo() {
        return alteraFundoFixo;
    }

    public void setAlteraFundoFixo(EnumHabilitado alteraFundoFixo) {
        this.alteraFundoFixo = alteraFundoFixo;
    }

    public EnumHabilitado getRemoveFundoFixo() {
        return removeFundoFixo;
    }

    public void setRemoveFundoFixo(EnumHabilitado removeFundoFixo) {
        this.removeFundoFixo = removeFundoFixo;
    }

    public EnumHabilitado getIncluiUsuario() {
        return incluiUsuario;
    }

    public void setIncluiUsuario(EnumHabilitado incluiUsuario) {
        this.incluiUsuario = incluiUsuario;
    }

    public EnumHabilitado getAlteraUsuario() {
        return alteraUsuario;
    }

    public void setAlteraUsuario(EnumHabilitado alteraUsuario) {
        this.alteraUsuario = alteraUsuario;
    }

    public EnumHabilitado getRemoveUsuario() {
        return removeUsuario;
    }

    public void setRemoveUsuario(EnumHabilitado removeUsuario) {
        this.removeUsuario = removeUsuario;
    }

    public EnumHabilitado getIncluiRhCadGer() {
        return incluiRhCadGer;
    }

    public void setIncluiRhCadGer(EnumHabilitado incluiRhCadGer) {
        this.incluiRhCadGer = incluiRhCadGer;
    }

    public EnumHabilitado getAlteraRhCadGer() {
        return alteraRhCadGer;
    }

    public void setAlteraRhCadGer(EnumHabilitado alteraRhCadGer) {
        this.alteraRhCadGer = alteraRhCadGer;
    }

    public EnumHabilitado getExcluiRhCadGer() {
        return excluiRhCadGer;
    }

    public void setExcluiRhCadGer(EnumHabilitado excluiRhCadGer) {
        this.excluiRhCadGer = excluiRhCadGer;
    }

    public EnumHabilitado getIncluiRhOcorrencia() {
        return incluiRhOcorrencia;
    }

    public void setIncluiRhOcorrencia(EnumHabilitado incluiRhOcorrencia) {
        this.incluiRhOcorrencia = incluiRhOcorrencia;
    }

    public EnumHabilitado getAlteraRhOcorrencia() {
        return alteraRhOcorrencia;
    }

    public void setAlteraRhOcorrencia(EnumHabilitado alteraRhOcorrencia) {
        this.alteraRhOcorrencia = alteraRhOcorrencia;
    }

    public EnumHabilitado getExcluiRhOcorrencia() {
        return excluiRhOcorrencia;
    }

    public void setExcluiRhOcorrencia(EnumHabilitado excluiRhOcorrencia) {
        this.excluiRhOcorrencia = excluiRhOcorrencia;
    }
    
    /* ============================================================================ */
    @Override
    public Serializable getId() {
        return codigo;
    }

    @Override
    public String getLabel() {
        return nome;
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
    public int compareTo(PerfilAcesso o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.codigo);
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
        final PerfilAcesso other = (PerfilAcesso) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
}
