/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.model;

import br.com.grupopibb.portalrh.model.commons.EntityInterface;
import br.com.grupopibb.portalrh.utils.DateUtils;
import br.com.grupopibb.portalrh.utils.NumberUtils;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "CADFUNCOCORRENCIAS")
@NamedQueries({
    @NamedQuery(name = "FuncionarioOcorrencia.selectRange",
            query = " SELECT DISTINCT fo FROM FuncionarioOcorrencia fo"
            + " WHERE (:codigo2 = 'todos' OR fo.codigo = :codigo ) "
            + " AND (:codOcor2 = 'todos' OR fo.ocorrencia.codigo = :codOcor) "
            + " AND (:descOcor2 = 'todos' OR fo.ocorrencia.descricao LIKE :descOcor) "
            + " AND (:dtLancto2 = 'todos' OR fo.data = :dtLancto) "
            + " AND (:dtIni2 = 'todos' OR fo.dataIni = :dtIni) "
            + " AND (:dtFim2 = 'todos' OR fo.dataFim = :dtFim) "
            + " AND (:detalha2 = 'todos' OR fo.detalhamento LIKE :detalha) "
            + " AND (:cpf2 = 'todos' OR fo.cadastroGeral.cpf LIKE :cpf) "
            + " AND (:nome2 = 'todos' OR fo.cadastroGeral.nome LIKE :nome) "
            + " ORDER BY fo.codigo DESC"),
    @NamedQuery(name = "FuncionarioOcorrencia.countRange",
            query = " SELECT COUNT(DISTINCT fo) FROM FuncionarioOcorrencia fo"
            + " WHERE (:codigo2 = 'todos' OR fo.codigo = :codigo ) "
            + " AND (:codOcor2 = 'todos' OR fo.ocorrencia.codigo = :codOcor) "
            + " AND (:descOcor2 = 'todos' OR fo.ocorrencia.descricao LIKE :descOcor) "
            + " AND (:dtLancto2 = 'todos' OR fo.data = :dtLancto) "
            + " AND (:dtIni2 = 'todos' OR fo.dataIni = :dtIni) "
            + " AND (:dtFim2 = 'todos' OR fo.dataFim = :dtFim) "
            + " AND (:detalha2 = 'todos' OR fo.detalhamento LIKE :detalha) "
            + " AND (:cpf2 = 'todos' OR fo.cadastroGeral.cpf LIKE :cpf) "
            + " AND (:nome2 = 'todos' OR fo.cadastroGeral.nome LIKE :nome) "),
    @NamedQuery(name = "FuncionarioOcorrencia.findFalta",
            query = "SELECT DISTINCT fo FROM FuncionarioOcorrencia fo"
            + " WHERE (:data = fo.dataIni) "
            + " AND (:cpf = fo.cpf) "),
    @NamedQuery(name = "FuncionarioOcorrencia.findOcorrencia",
            query = "SELECT DISTINCT fo FROM FuncionarioOcorrencia fo"
            + " WHERE (:data BETWEEN fo.dataIni AND fo.dataFim) "
            + " AND (:cpf = fo.cpf) ")
})
public class FuncionarioOcorrencia implements EntityInterface<FuncionarioOcorrencia> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Integer codigo;
    /**/
    @ManyToOne(targetEntity = Ocorrencia.class)
    @JoinColumn(name = "CODOCORRENCIA")
    private Ocorrencia ocorrencia;
    /**/
    @Column(name = "DETALHAMENTO")
    private String detalhamento;
    /**/
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA")
    private Date data;
    /**/
    @ManyToOne(targetEntity = CadastroGeral.class)
    @JoinColumn(name = "CPFFUNC", referencedColumnName = "CPF")
    private CadastroGeral cadastroGeral;
    /**/
    @Column(name = "CPFFUNC", insertable = false, updatable = false)
    private String cpf;
    /**/
    @Temporal(TemporalType.DATE)
    @Column(name = "DATAINI")
    private Date dataIni;
    /**/
    @Temporal(TemporalType.DATE)
    @Column(name = "DATAFIM")
    private Date dataFim;
    /**/
    @Column(name = "tipoOCORRENC")
    private Integer tipoOcorrencia;
    /**/
    @Column(name = "CHAVE")
    private String chave;
    /**/
    @ManyToOne(targetEntity = CentroCusto.class, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "EMPRESA_COD", referencedColumnName = "Empresa_Cod"),
        @JoinColumn(name = "FILIAL_COD", referencedColumnName = "Filial_Cod"),
        @JoinColumn(name = "CENTRO_COD", referencedColumnName = "Centro_Cod")
    })
    private CentroCusto centro;
    /**/
    @ManyToOne(targetEntity = CentroCusto.class, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "EMPRESA_CODN", referencedColumnName = "Empresa_Cod"),
        @JoinColumn(name = "FILIAL_CODN", referencedColumnName = "Filial_Cod"),
        @JoinColumn(name = "CENTRO_CODN", referencedColumnName = "Centro_Cod")
    })
    private CentroCusto centroNovo;
    /**/
    @Column(name = "DEPTO_COD")
    private String departamentoCod;
    /**/
    @Column(name = "DEPTO_CODN")
    private String departamentoCodNovo;
    /**/
    @Column(name = "SETOR_COD")
    private String setorCod;
    /**/
    @Column(name = "SETOR_CODN")
    private String setorCodNovo;
    /**/
    @Column(name = "MATRICULA")
    private String matricula;
    /**/
    @Column(name = "MATRICULA_N")
    private String matriculaNova;
    /**/
    @Column(name = "ANO")
    private String ano;
    /**/
    @Column(name = "MES")
    private String mes;
    /**/
    @Column(name = "CARGO")
    private String cargo;
    /**/
    @Column(name = "CARGO_N")
    private String cargoNovo;
    /**/
    @Column(name = "CARGONIVEL")
    private String cargoNivel;
    /**/
    @Column(name = "CARGONIVEL_N")
    private String cargoNivelNovo;
    /**/
    @Column(name = "codigo_tmp")
    private Integer codigoTmp;
    /**/
    @Temporal(TemporalType.DATE)
    @Column(name = "data_tmp")
    private Date dataTmp;
    /**/
    @Column(name = "USUARIO_CAD")
    private String usuarioCadastro;
    /**/
    @Transient
    private Contrato contrato;

    public boolean rebuildByContrato() {
        if (contrato != null) {
            this.centro = contrato.getCentro();
            this.centroNovo = contrato.getCentro();
            this.departamentoCod = contrato.getDepartamentoCod();
            this.departamentoCodNovo = contrato.getDepartamentoCod();
            this.setorCod = contrato.getSetorCod();
            this.setorCodNovo = contrato.getSetorCod();
            this.matricula = contrato.getMatricula();
            this.matriculaNova = contrato.getMatricula();
            this.chave = centro.getEmpresa() + centro.getFilial() + contrato.getMatricula() + NumberUtils.preencheZeroEsquerda(String.valueOf(DateUtils.getMonth(this.dataIni)), 2) + DateUtils.getYear(this.dataIni) + "1";
            return true;
        }
        return false;
    }

    @Override
    public Serializable getId() {
        return this.codigo;
    }

    @Override
    public String getLabel() {
        return this.detalhamento;
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
    public int compareTo(FuncionarioOcorrencia o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public CadastroGeral getCadastroGeral() {
        return cadastroGeral;
    }

    public void setCadastroGeral(CadastroGeral cadastroGeral) {
        this.cadastroGeral = cadastroGeral;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getTipoOcorrencia() {
        if (ocorrencia != null && ocorrencia.getTipo() != null) {
            tipoOcorrencia = ocorrencia.getTipo();
        }
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(Integer tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public CentroCusto getCentro() {
        return centro;
    }

    public void setCentro(CentroCusto centro) {
        this.centro = centro;
    }

    public CentroCusto getCentroNovo() {
        return centroNovo;
    }

    public void setCentroNovo(CentroCusto centroNovo) {
        this.centroNovo = centroNovo;
    }

    public String getDepartamentoCod() {
        return departamentoCod;
    }

    public void setDepartamentoCod(String departamentoCod) {
        this.departamentoCod = departamentoCod;
    }

    public String getDepartamentoCodNovo() {
        return departamentoCodNovo;
    }

    public void setDepartamentoCodNovo(String departamentoCodNovo) {
        this.departamentoCodNovo = departamentoCodNovo;
    }

    public String getSetorCod() {
        return setorCod;
    }

    public void setSetorCod(String setorCod) {
        this.setorCod = setorCod;
    }

    public String getSetorCodNovo() {
        return setorCodNovo;
    }

    public void setSetorCodNovo(String setorCodNovo) {
        this.setorCodNovo = setorCodNovo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatriculaNova() {
        return matriculaNova;
    }

    public void setMatriculaNova(String matriculaNova) {
        this.matriculaNova = matriculaNova;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargoNovo() {
        return cargoNovo;
    }

    public void setCargoNovo(String cargoNovo) {
        this.cargoNovo = cargoNovo;
    }

    public String getCargoNivel() {
        return cargoNivel;
    }

    public void setCargoNivel(String cargoNivel) {
        this.cargoNivel = cargoNivel;
    }

    public String getCargoNivelNovo() {
        return cargoNivelNovo;
    }

    public void setCargoNivelNovo(String cargoNivelNovo) {
        this.cargoNivelNovo = cargoNivelNovo;
    }

    public Integer getCodigoTmp() {
        return codigoTmp;
    }

    public void setCodigoTmp(Integer codigoTmp) {
        this.codigoTmp = codigoTmp;
    }

    public Date getDataTmp() {
        return dataTmp;
    }

    public void setDataTmp(Date dataTmp) {
        this.dataTmp = dataTmp;
    }

    public String getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(String usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final FuncionarioOcorrencia other = (FuncionarioOcorrencia) obj;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.detalhamento;
    }
}
