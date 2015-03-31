/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.model;

import br.com.grupopibb.portalrh.model.commons.EntityInterface;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "DEPENDENTES")
public class Dependente implements EntityInterface<Dependente> {

    public Dependente() {
    }

    public Dependente(CadastroGeral cadastroGeral, Integer sequencial, String cpf) {
        this.cadastroGeral = cadastroGeral;
        this.sequencial = sequencial;
        this.cpf = cpf;
        if (cadastroGeral != null) {
            this.cpfCadGeral = cadastroGeral.getCpf();
        }
    }

    public Dependente(CadastroGeral cadastroGeral, String cpfCadGeral, Integer sequencial, String cpf, String nome, String cidadeNascimento, String ufNascimento, String endereco, String bairro, String cep, String cidade, String uf, String fone, String sexo, String grauInstrucao, String rg, String rgOrgaoEmissor, String ctps, String pisPasep, String tituloEleitor, String contaFgts, String contaBanco, String observacao, String parentesco, String depositaIRRF, String tmpCodigoFilial, String tmpMatricula, String tmpCartorio, String tmpNumeroRegistroCartao, String tmpNumeroLivro, String tmpNumeroFolha, Date tmpDataEntrada, String grauParentesco, String cartorio, String numeroRegistroCartao, String numeroLivro, String numeroFolha, Date dataEntrada, Date dataNascimento, Date dataBaixa, Date rgDataEmissao, String tmpEmpresa, String tmpMat, String tmpSalarioFamilia, Date dataEntregaCertidao, Date dataEntregaVascinacao, Date dataProxEntregaVascinacao, Date dataEntregaDeclaracao, Date dataProxEntregaDeclaracao, String deficiencia) {
        this.cadastroGeral = cadastroGeral;
        this.cpfCadGeral = cpfCadGeral;
        this.sequencial = sequencial;
        this.cpf = cpf;
        this.nome = nome;
        this.cidadeNascimento = cidadeNascimento;
        this.ufNascimento = ufNascimento;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.fone = fone;
        this.sexo = sexo;
        this.grauInstrucao = grauInstrucao;
        this.rg = rg;
        this.rgOrgaoEmissor = rgOrgaoEmissor;
        this.ctps = ctps;
        this.pisPasep = pisPasep;
        this.tituloEleitor = tituloEleitor;
        this.contaFgts = contaFgts;
        this.contaBanco = contaBanco;
        this.observacao = observacao;
        this.parentesco = parentesco;
        this.depositaIRRF = depositaIRRF;
        this.tmpCodigoFilial = tmpCodigoFilial;
        this.tmpMatricula = tmpMatricula;
        this.tmpCartorio = tmpCartorio;
        this.tmpNumeroRegistroCartao = tmpNumeroRegistroCartao;
        this.tmpNumeroLivro = tmpNumeroLivro;
        this.tmpNumeroFolha = tmpNumeroFolha;
        this.tmpDataEntrada = tmpDataEntrada;
        this.grauParentesco = grauParentesco;
        this.cartorio = cartorio;
        this.numeroRegistroCartao = numeroRegistroCartao;
        this.numeroLivro = numeroLivro;
        this.numeroFolha = numeroFolha;
        this.dataEntrada = dataEntrada;
        this.dataNascimento = dataNascimento;
        this.dataBaixa = dataBaixa;
        this.rgDataEmissao = rgDataEmissao;
        this.tmpEmpresa = tmpEmpresa;
        this.tmpMat = tmpMat;
        this.tmpSalarioFamilia = tmpSalarioFamilia;
        this.dataEntregaCertidao = dataEntregaCertidao;
        this.dataEntregaVascinacao = dataEntregaVascinacao;
        this.dataProxEntregaVascinacao = dataProxEntregaVascinacao;
        this.dataEntregaDeclaracao = dataEntregaDeclaracao;
        this.dataProxEntregaDeclaracao = dataProxEntregaDeclaracao;
        this.deficiencia = deficiencia;
    }

    public Dependente(Dependente dependente) {
        this.cadastroGeral = dependente.cadastroGeral;
        this.cpfCadGeral = dependente.cpfCadGeral;
        this.sequencial = dependente.sequencial;
        this.cpf = dependente.cpf;
        this.nome = dependente.nome;
        this.cidadeNascimento = dependente.cidadeNascimento;
        this.ufNascimento = dependente.ufNascimento;
        this.endereco = dependente.endereco;
        this.bairro = dependente.bairro;
        this.cep = dependente.cep;
        this.cidade = dependente.cidade;
        this.uf = dependente.uf;
        this.fone = dependente.fone;
        this.sexo = dependente.sexo;
        this.grauInstrucao = dependente.grauInstrucao;
        this.rg = dependente.rg;
        this.rgOrgaoEmissor = dependente.rgOrgaoEmissor;
        this.ctps = dependente.ctps;
        this.pisPasep = dependente.pisPasep;
        this.tituloEleitor = dependente.tituloEleitor;
        this.contaFgts = dependente.contaFgts;
        this.contaBanco = dependente.contaBanco;
        this.observacao = dependente.observacao;
        this.parentesco = dependente.parentesco;
        this.depositaIRRF = dependente.depositaIRRF;
        this.tmpCodigoFilial = dependente.tmpCodigoFilial;
        this.tmpMatricula = dependente.tmpMatricula;
        this.tmpCartorio = dependente.tmpCartorio;
        this.tmpNumeroRegistroCartao = dependente.tmpNumeroRegistroCartao;
        this.tmpNumeroLivro = dependente.tmpNumeroLivro;
        this.tmpNumeroFolha = dependente.tmpNumeroFolha;
        this.tmpDataEntrada = dependente.tmpDataEntrada;
        this.grauParentesco = dependente.grauParentesco;
        this.cartorio = dependente.cartorio;
        this.numeroRegistroCartao = dependente.numeroRegistroCartao;
        this.numeroLivro = dependente.numeroLivro;
        this.numeroFolha = dependente.numeroFolha;
        this.dataEntrada = dependente.dataEntrada;
        this.dataNascimento = dependente.dataNascimento;
        this.dataBaixa = dependente.dataBaixa;
        this.rgDataEmissao = dependente.rgDataEmissao;
        this.tmpEmpresa = dependente.tmpEmpresa;
        this.tmpMat = dependente.tmpMat;
        this.tmpSalarioFamilia = dependente.tmpSalarioFamilia;
        this.dataEntregaCertidao = dependente.dataEntregaCertidao;
        this.dataEntregaVascinacao = dependente.dataEntregaVascinacao;
        this.dataProxEntregaVascinacao = dependente.dataProxEntregaVascinacao;
        this.dataEntregaDeclaracao = dependente.dataEntregaDeclaracao;
        this.dataProxEntregaDeclaracao = dependente.dataProxEntregaDeclaracao;
        this.deficiencia = dependente.deficiencia;
    }
    @ManyToOne(targetEntity = CadastroGeral.class)
    @JoinColumn(name = "CPF")
    private CadastroGeral cadastroGeral;
    /*
     */
    @Id
    @NotNull
    @Column(name = "CPF", insertable = false, updatable = false)
    private String cpfCadGeral;
    /*
     */
    @Id
    @NotNull
    @Column(name = "SEQ")
    private Integer sequencial;
    /*
     */
    @Column(name = "CPFDEP")
    private String cpf;
    /*
     */
    @Column(name = "NOME")
    private String nome;
    /*
     */
    @Column(name = "CIDNASC")
    private String cidadeNascimento;
    /*
     */
    @Column(name = "UFNASC")
    private String ufNascimento;
    /*
     */
    @Column(name = "ENDERECO")
    private String endereco;
    /*
     */
    @Column(name = "BAIRRO")
    private String bairro;
    /*
     */
    @Column(name = "CEP")
    private String cep;
    /*
     */
    @Column(name = "CIDADE")
    private String cidade;
    /*
     */
    @Column(name = "UF")
    private String uf;
    /*
     */
    @Column(name = "FONE")
    private String fone;
    /*
     */
    @Column(name = "SEXO")
    private String sexo;
    /*
     */
    @Column(name = "GRINSTR")
    private String grauInstrucao;
    /*
     */
    @Column(name = "IDENTIDADE")
    private String rg;
    /*
     */
    @Column(name = "ORGAOIDENT")
    private String rgOrgaoEmissor;
    /*
     */
    @Column(name = "CARTPROF")
    private String ctps;
    /*
     */
    @Column(name = "PISPASEP")
    private String pisPasep;
    /*
     */
    @Column(name = "TITELEITOR")
    private String tituloEleitor;
    /*
     */
    @Column(name = "CONTAFGTS")
    private String contaFgts;
    /*
     */
    @Column(name = "CONTABANCO")
    private String contaBanco;
    /*
     */
    @Column(name = "OBS")
    private String observacao;
    /*
     */
    @Column(name = "PARENTESCO")
    private String parentesco;
    /*
     */
    @Column(name = "DEPIRRF")
    private String depositaIRRF;
    /*
     */
    @Column(name = "TMP_CODFILIAL")
    private String tmpCodigoFilial;
    /*
     */
    @Column(name = "TMP_MATRICULA")
    private String tmpMatricula;
    /*
     */
    @Column(name = "TMP_CARTORIO")
    private String tmpCartorio;
    /*
     */
    @Column(name = "TMP_NREGCART")
    private String tmpNumeroRegistroCartao;
    /*
     */
    @Column(name = "TMP_NUMLIVRO")
    private String tmpNumeroLivro;
    /*
     */
    @Column(name = "TMP_NUMFOLHA")
    private String tmpNumeroFolha;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "TMP_DTENTRA")
    private Date tmpDataEntrada;
    /*
     */
    @Column(name = "TMP_GRAUPAR")
    private String grauParentesco;
    /*
     */
    @Column(name = "CARTORIO")
    private String cartorio;
    /*
     */
    @Column(name = "NREGCART")
    private String numeroRegistroCartao;
    /*
     */
    @Column(name = "NUMLIVRO")
    private String numeroLivro;
    /*
     */
    @Column(name = "NUMFOLHA")
    private String numeroFolha;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "DTENTRA")
    private Date dataEntrada;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "DTNASC")
    private Date dataNascimento;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "DTBAIXA")
    private Date dataBaixa;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "dtident")
    private Date rgDataEmissao;
    /*
     */
    @Column(name = "TMP_EMP")
    private String tmpEmpresa;
    /*
     */
    @Column(name = "TMP_MAT")
    private String tmpMat;
    /*
     */
    @Column(name = "TMP_SALFAM")
    private String tmpSalarioFamilia;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "CERT_DTENTREGA")
    private Date dataEntregaCertidao;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "VASC_DTENTREGA")
    private Date dataEntregaVascinacao;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "VASC_PROXDTENTREGA")
    private Date dataProxEntregaVascinacao;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "DECLAR_DTENTREGA")
    private Date dataEntregaDeclaracao;
    /*
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "DECLAR_PROXDTENTREGA")
    private Date dataProxEntregaDeclaracao;
    /*
     */
    @Column(name = "deficiencia")
    private String deficiencia;

    @Override
    public Serializable getId() {
        return cpfCadGeral + sequencial;
    }

    @Override
    public String getLabel() {
        return nome;
    }

    @Override
    public boolean verificarId() {
        return true;
    }

    @Override
    public boolean isMarcado() {
        return false;
    }

    @Override
    public int compareTo(Dependente o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.cpfCadGeral != null ? this.cpfCadGeral.hashCode() : 0);
        hash = 53 * hash + (this.sequencial != null ? this.sequencial.hashCode() : 0);
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
        final Dependente other = (Dependente) obj;
        if ((this.cpfCadGeral == null) ? (other.cpfCadGeral != null) : !this.cpfCadGeral.equals(other.cpfCadGeral)) {
            return false;
        }
        if (this.sequencial != other.sequencial && (this.sequencial == null || !this.sequencial.equals(other.sequencial))) {
            return false;
        }
        return true;
    }

    public CadastroGeral getCadastroGeral() {
        return cadastroGeral;
    }

    public void setCadastroGeral(CadastroGeral cadastroGeral) {
        if (cadastroGeral != null) {
            this.cpfCadGeral = cadastroGeral.getCpf();
        }
        this.cadastroGeral = cadastroGeral;
    }

    public String getCpfCadGeral() {
        return cpfCadGeral;
    }

    public void setCpfCadGeral(String cpfCadGeral) {
        this.cpfCadGeral = cpfCadGeral;
    }

    public Integer getSequencial() {
        return sequencial;
    }

    public void setSequencial(Integer sequencial) {
        this.sequencial = sequencial;
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

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getUfNascimento() {
        return ufNascimento;
    }

    public void setUfNascimento(String ufNascimento) {
        this.ufNascimento = ufNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getGrauInstrucao() {
        return grauInstrucao;
    }

    public void setGrauInstrucao(String grauInstrucao) {
        this.grauInstrucao = grauInstrucao;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRgOrgaoEmissor() {
        return rgOrgaoEmissor;
    }

    public void setRgOrgaoEmissor(String rgOrgaoEmissor) {
        this.rgOrgaoEmissor = rgOrgaoEmissor;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public String getPisPasep() {
        return pisPasep;
    }

    public void setPisPasep(String pisPasep) {
        this.pisPasep = pisPasep;
    }

    public String getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(String tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public String getContaFgts() {
        return contaFgts;
    }

    public void setContaFgts(String contaFgts) {
        this.contaFgts = contaFgts;
    }

    public String getContaBanco() {
        return contaBanco;
    }

    public void setContaBanco(String contaBanco) {
        this.contaBanco = contaBanco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getDepositaIRRF() {
        return depositaIRRF;
    }

    public void setDepositaIRRF(String depositaIRRF) {
        this.depositaIRRF = depositaIRRF;
    }

    public String getTmpCodigoFilial() {
        return tmpCodigoFilial;
    }

    public void setTmpCodigoFilial(String tmpCodigoFilial) {
        this.tmpCodigoFilial = tmpCodigoFilial;
    }

    public String getTmpMatricula() {
        return tmpMatricula;
    }

    public void setTmpMatricula(String tmpMatricula) {
        this.tmpMatricula = tmpMatricula;
    }

    public String getTmpCartorio() {
        return tmpCartorio;
    }

    public void setTmpCartorio(String tmpCartorio) {
        this.tmpCartorio = tmpCartorio;
    }

    public String getTmpNumeroRegistroCartao() {
        return tmpNumeroRegistroCartao;
    }

    public void setTmpNumeroRegistroCartao(String tmpNumeroRegistroCartao) {
        this.tmpNumeroRegistroCartao = tmpNumeroRegistroCartao;
    }

    public String getTmpNumeroLivro() {
        return tmpNumeroLivro;
    }

    public void setTmpNumeroLivro(String tmpNumeroLivro) {
        this.tmpNumeroLivro = tmpNumeroLivro;
    }

    public String getTmpNumeroFolha() {
        return tmpNumeroFolha;
    }

    public void setTmpNumeroFolha(String tmpNumeroFolha) {
        this.tmpNumeroFolha = tmpNumeroFolha;
    }

    public Date getTmpDataEntrada() {
        return tmpDataEntrada;
    }

    public void setTmpDataEntrada(Date tmpDataEntrada) {
        this.tmpDataEntrada = tmpDataEntrada;
    }

    public String getGrauParentesco() {
        return grauParentesco;
    }

    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    public String getCartorio() {
        return cartorio;
    }

    public void setCartorio(String cartorio) {
        this.cartorio = cartorio;
    }

    public String getNumeroRegistroCartao() {
        return numeroRegistroCartao;
    }

    public void setNumeroRegistroCartao(String numeroRegistroCartao) {
        this.numeroRegistroCartao = numeroRegistroCartao;
    }

    public String getNumeroLivro() {
        return numeroLivro;
    }

    public void setNumeroLivro(String numeroLivro) {
        this.numeroLivro = numeroLivro;
    }

    public String getNumeroFolha() {
        return numeroFolha;
    }

    public void setNumeroFolha(String numeroFolha) {
        this.numeroFolha = numeroFolha;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public Date getRgDataEmissao() {
        return rgDataEmissao;
    }

    public void setRgDataEmissao(Date rgDataEmissao) {
        this.rgDataEmissao = rgDataEmissao;
    }

    public String getTmpEmpresa() {
        return tmpEmpresa;
    }

    public void setTmpEmpresa(String tmpEmpresa) {
        this.tmpEmpresa = tmpEmpresa;
    }

    public String getTmpMat() {
        return tmpMat;
    }

    public void setTmpMat(String tmpMat) {
        this.tmpMat = tmpMat;
    }

    public String getTmpSalarioFamilia() {
        return tmpSalarioFamilia;
    }

    public void setTmpSalarioFamilia(String tmpSalarioFamilia) {
        this.tmpSalarioFamilia = tmpSalarioFamilia;
    }

    public Date getDataEntregaCertidao() {
        return dataEntregaCertidao;
    }

    public void setDataEntregaCertidao(Date dataEntregaCertidao) {
        this.dataEntregaCertidao = dataEntregaCertidao;
    }

    public Date getDataEntregaVascinacao() {
        return dataEntregaVascinacao;
    }

    public void setDataEntregaVascinacao(Date dataEntregaVascinacao) {
        this.dataEntregaVascinacao = dataEntregaVascinacao;
    }

    public Date getDataProxEntregaVascinacao() {
        return dataProxEntregaVascinacao;
    }

    public void setDataProxEntregaVascinacao(Date dataProxEntregaVascinacao) {
        this.dataProxEntregaVascinacao = dataProxEntregaVascinacao;
    }

    public Date getDataEntregaDeclaracao() {
        return dataEntregaDeclaracao;
    }

    public void setDataEntregaDeclaracao(Date dataEntregaDeclaracao) {
        this.dataEntregaDeclaracao = dataEntregaDeclaracao;
    }

    public Date getDataProxEntregaDeclaracao() {
        return dataProxEntregaDeclaracao;
    }

    public void setDataProxEntregaDeclaracao(Date dataProxEntregaDeclaracao) {
        this.dataProxEntregaDeclaracao = dataProxEntregaDeclaracao;
    }

    public String getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }
}
