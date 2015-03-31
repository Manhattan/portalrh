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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "PRH_CADGERAL")
@NamedQueries({
    @NamedQuery(name = "TmpCadastroGeral.selectRange",
            query = " SELECT DISTINCT c FROM TmpCadastroGeral c "
            + " WHERE (:cpf2 = 'todos' OR c.cpf LIKE :cpf) "
            + " AND (:nome2 = 'todos' OR c.nome LIKE :nome) "
            + " AND (:rg2 = 'todos' OR c.rg LIKE :rg) "
            + " AND (:nomeMae2 = 'todos' OR c.nomeMae LIKE :nomeMae) "
            + " AND (:situacao2 = 'todos' OR c.situacao = :situacao) "
            + " AND (:empresa2 = 'todos' OR c.empresaCod = :empresa) "
            + " AND (:filial2 = 'todos' OR c.filialCod = :filial) "
            + " AND (:centro2 = 'todos' OR c.centroCod = :centro) "
            + " AND (:funcao2 = 'todos' OR c.funcao LIKE :funcao) "
            + " AND (:matricula2 = 'todos' OR c.matricula LIKE :matricula) "
            + " AND (:ano = c.ano OR c.ano = '') "
            + " AND (:mes = c.mes OR c.mes = '') "
            + " ORDER BY c.nome "),
    @NamedQuery(name = "TmpCadastroGeral.countRange",
            query = " SELECT COUNT(DISTINCT c) FROM TmpCadastroGeral c "
            + " WHERE (:cpf2 = 'todos' OR c.cpf LIKE :cpf) "
            + " AND (:nome2 = 'todos' OR c.nome LIKE :nome) "
            + " AND (:rg2 = 'todos' OR c.rg LIKE :rg) "
            + " AND (:nomeMae2 = 'todos' OR c.nomeMae LIKE :nomeMae) "
            + " AND (:situacao2 = 'todos' OR c.situacao = :situacao) "
            + " AND (:empresa2 = 'todos' OR c.empresaCod = :empresa) "
            + " AND (:filial2 = 'todos' OR c.filialCod = :filial) "
            + " AND (:centro2 = 'todos' OR c.centroCod = :centro) "
            + " AND (:funcao2 = 'todos' OR c.funcao LIKE :funcao) "
            + " AND (:matricula2 = 'todos' OR c.matricula LIKE :matricula) "
            + " AND (:ano = c.ano OR c.ano = '') "
            + " AND (:mes = c.mes OR c.mes = '') "),
    @NamedQuery(name = "TmpCadastroGeral.findByCpf",
            query = " SELECT DISTINCT c FROM TmpCadastroGeral c "
            + " WHERE c.cpf = :cpf")
})
public class TmpCadastroGeral implements EntityInterface<TmpCadastroGeral> {

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
    @Column(name = "CODFOLHA")
    private String codFolha;
    @Id
    @Column(name = "EMPRESA_COD")
    private String empresaCod;
    /*
     */
    @Id
    @Column(name = "FILIAL_COD")
    private String filialCod;
    /*
     */
    @Column(name = "CENTRO_COD")
    private String centroCod;
    /*
     */
    @Column(name = "CENTRO_NOME")
    private String centroNome;
    /*
     */
    @Id
    @Column(name = "MATRICULA")
    private String matricula;
    /*
     */
    @Id
    @Column(name = "CPF")
    private String cpf;
    /*
     */
    @Column(name = "NOME")
    private String nome;
    /*
     */
    @Column(name = "SITUACAO")
    private String situacao;
    /*
     */
    @Column(name = "NOME_PAI")
    private String nomePai;
    /*
     */
    @Column(name = "NOME_MAE")
    private String nomeMae;
    /*
     */
    @Column(name = "CEP")
    private String cep;
    /*
     */
    @Column(name = "ENDERECO")
    private String endereco;
    /*
     */
    @Column(name = "NUMERO")
    private String numero;
    /*
     */
    @Column(name = "COMPLEMENTO")
    private String complemento;
    /*
     */
    @Column(name = "BAIRRO")
    private String bairro;
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
    @Column(name = "DATA_NASC")
    private String dataNasciemtno;
    /*
     */
    @ManyToOne(targetEntity = NacionalidadeFolha.class)
    @JoinColumn(name = "NACIONALIDADE", referencedColumnName = "CodNacio_Folha")
    private NacionalidadeFolha nacionalidade;
    /*
     */
    @Column(name = "UF_NASC")
    private String ufNascimento;
    /*
     */
    @Column(name = "CIDADE_NASC")
    private String cidadeNascimento;
    /*
     */
    @Column(name = "GRAU_INSTRUCAO")
    private String grauInstrucao;
    /*
     */
    @Column(name = "FONE")
    private String fone;
    /*
     */
    @Column(name = "CELULAR")
    private String celular;
    /*
     */
    @Column(name = "EMAIL")
    private String email;
    /*
     */
    @Column(name = "SEXO")
    private String sexo;
    /*
     */
    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;
    /*
     */
    @ManyToOne(targetEntity = RacaCor.class)
    @JoinColumn(name = "RACA_COR", referencedColumnName = "CODIGO")
    private RacaCor racaCor;
    /*
     */
    @Column(name = "DEFICIENCIA")
    private String codigoDeficiencia;
    /*
     */
    @Column(name = "OBSERVACAO")
    private String observacao;
    /*
     */
    @Column(name = "RG_NUM")
    private String rg;
    /*
     */
    @Column(name = "RG_ORG_EXP")
    private String rgOrgaoEmissor;
    /*
     */
    @Column(name = "RG_UF")
    private String rgUf;
    /*
     */
    @Column(name = "RG_DATA_EXP")
    private String rgDataEmissao;
    /*
     */
    @Column(name = "CTPS_NUM")
    private String ctps;
    /*
     */
    @Column(name = "CTPS_SERIE")
    private String ctpsSerie;
    /*
     */
    @Column(name = "CTPS_DIG")
    private String ctpsDigito;
    /*
     */
    @Column(name = "CTPS_UF")
    private String ctpsUf;
    /*
     */
    @Column(name = "CTPS_DT_EMISSAO")
    @Temporal(TemporalType.DATE)
    private Date ctpsData;
    /*
     */
    @Column(name = "RESERV_NUM")
    private String reservistaNumero;
    /*
     */
    @Column(name = "RESERV_CATEG")
    private String reservistaCategoria;
    /*
     */
    @Column(name = "RESERV_DATA_EMIS")
    @Temporal(TemporalType.DATE)
    private Date reservistaData;
    /*
     */
    @Column(name = "TITULO_NUM")
    private String tituloEleitorNumero;
    /*
     */
    @Column(name = "TITULO_ZONA")
    private String tituloZona;
    /*
     */
    @Column(name = "TITULO_SECAO")
    private String tituloSecao;
    /*
     */
    @Column(name = "PIS_PASEP")
    private String pisPasep;
    /*
     */
    @Column(name = "CNH_NUM")
    private String cnhNumero;
    /*
     */
    @Column(name = "CNH_CATEG")
    private String cnhCategoria;
    /*
     */
    @Column(name = "CNH_DT_EMIS")
    @Temporal(TemporalType.DATE)
    private Date cnhDataExpedicao;
    /*
     */
    @Column(name = "CNH_DT_VENC")
    @Temporal(TemporalType.DATE)
    private Date cnhDataVencimento;
    /*
     */
    @Column(name = "BANCO")
    private String banco;
    /*
     */
    @Column(name = "AGENCIA")
    private String agencia;
    /*
     */
    @Column(name = "CONTA")
    private String contaBanco;
    /*
     */
    @Column(name = "TIPO_CONTA")
    private String contaBancoTipo;
    /*
     */
    @Column(name = "CONJ_CPF")
    private String cpfConjuge;
    /*
     */
    @Column(name = "CONJ_NOME")
    private String nomeConjuge;
    /*
     */
    @Column(name = "CONJ_DT_NASC")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentoConjuge;
    /*
     */
    @Column(name = "CONJ_RG_NUM")
    private String rgConjuge;
    /*
     */
    @Column(name = "CONJ_RG_ORG_EXP")
    private String rgOrgEmissorConjuge;
    /*
     */
    @Column(name = "CONJ_RG_DT_EXP")
    @Temporal(TemporalType.DATE)
    private Date rgDataEmissaoConjuge;
    /*
     */
    @Column(name = "FUNCAO")
    private String funcao;

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

    public String getEmpresaCod() {
        return empresaCod;
    }

    public void setEmpresaCod(String empresaCod) {
        this.empresaCod = empresaCod;
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

    public String getCentroNome() {
        return centroNome;
    }

    public void setCentroNome(String centroNome) {
        this.centroNome = centroNome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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

    public String getDataNasciemtno() {
        return dataNasciemtno;
    }

    public void setDataNasciemtno(String dataNasciemtno) {
        this.dataNasciemtno = dataNasciemtno;
    }

    public NacionalidadeFolha getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(NacionalidadeFolha nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getUfNascimento() {
        return ufNascimento;
    }

    public void setUfNascimento(String ufNascimento) {
        this.ufNascimento = ufNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getGrauInstrucao() {
        return grauInstrucao;
    }

    public void setGrauInstrucao(String grauInstrucao) {
        this.grauInstrucao = grauInstrucao;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public RacaCor getRacaCor() {
        return racaCor;
    }

    public void setRacaCor(RacaCor racaCor) {
        this.racaCor = racaCor;
    }

    public String getCodigoDeficiencia() {
        return codigoDeficiencia;
    }

    public void setCodigoDeficiencia(String codigoDeficiencia) {
        this.codigoDeficiencia = codigoDeficiencia;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

    public String getRgUf() {
        return rgUf;
    }

    public void setRgUf(String rgUf) {
        this.rgUf = rgUf;
    }

    public String getRgDataEmissao() {
        return rgDataEmissao;
    }

    public void setRgDataEmissao(String rgDataEmissao) {
        this.rgDataEmissao = rgDataEmissao;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public String getCtpsSerie() {
        return ctpsSerie;
    }

    public void setCtpsSerie(String ctpsSerie) {
        this.ctpsSerie = ctpsSerie;
    }

    public String getCtpsDigito() {
        return ctpsDigito;
    }

    public void setCtpsDigito(String ctpsDigito) {
        this.ctpsDigito = ctpsDigito;
    }

    public String getCtpsUf() {
        return ctpsUf;
    }

    public void setCtpsUf(String ctpsUf) {
        this.ctpsUf = ctpsUf;
    }

    public Date getCtpsData() {
        return ctpsData;
    }

    public void setCtpsData(Date ctpsData) {
        this.ctpsData = ctpsData;
    }

    public String getReservistaNumero() {
        return reservistaNumero;
    }

    public void setReservistaNumero(String reservistaNumero) {
        this.reservistaNumero = reservistaNumero;
    }

    public String getReservistaCategoria() {
        return reservistaCategoria;
    }

    public void setReservistaCategoria(String reservistaCategoria) {
        this.reservistaCategoria = reservistaCategoria;
    }

    public Date getReservistaData() {
        return reservistaData;
    }

    public void setReservistaData(Date reservistaData) {
        this.reservistaData = reservistaData;
    }

    public String getTituloEleitorNumero() {
        return tituloEleitorNumero;
    }

    public void setTituloEleitorNumero(String tituloEleitorNumero) {
        this.tituloEleitorNumero = tituloEleitorNumero;
    }

    public String getTituloZona() {
        return tituloZona;
    }

    public void setTituloZona(String tituloZona) {
        this.tituloZona = tituloZona;
    }

    public String getTituloSecao() {
        return tituloSecao;
    }

    public void setTituloSecao(String tituloSecao) {
        this.tituloSecao = tituloSecao;
    }

    public String getPisPasep() {
        return pisPasep;
    }

    public void setPisPasep(String pisPasep) {
        this.pisPasep = pisPasep;
    }

    public String getCnhNumero() {
        return cnhNumero;
    }

    public void setCnhNumero(String cnhNumero) {
        this.cnhNumero = cnhNumero;
    }

    public String getCnhCategoria() {
        return cnhCategoria;
    }

    public void setCnhCategoria(String cnhCategoria) {
        this.cnhCategoria = cnhCategoria;
    }

    public Date getCnhDataExpedicao() {
        return cnhDataExpedicao;
    }

    public void setCnhDataExpedicao(Date cnhDataExpedicao) {
        this.cnhDataExpedicao = cnhDataExpedicao;
    }

    public Date getCnhDataVencimento() {
        return cnhDataVencimento;
    }

    public void setCnhDataVencimento(Date cnhDataVencimento) {
        this.cnhDataVencimento = cnhDataVencimento;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getContaBanco() {
        return contaBanco;
    }

    public void setContaBanco(String contaBanco) {
        this.contaBanco = contaBanco;
    }

    public String getContaBancoTipo() {
        return contaBancoTipo;
    }

    public void setContaBancoTipo(String contaBancoTipo) {
        this.contaBancoTipo = contaBancoTipo;
    }

    public String getCpfConjuge() {
        return cpfConjuge;
    }

    public void setCpfConjuge(String cpfConjuge) {
        this.cpfConjuge = cpfConjuge;
    }

    public String getNomeConjuge() {
        return nomeConjuge;
    }

    public void setNomeConjuge(String nomeConjuge) {
        this.nomeConjuge = nomeConjuge;
    }

    public Date getDataNascimentoConjuge() {
        return dataNascimentoConjuge;
    }

    public void setDataNascimentoConjuge(Date dataNascimentoConjuge) {
        this.dataNascimentoConjuge = dataNascimentoConjuge;
    }

    public String getRgConjuge() {
        return rgConjuge;
    }

    public void setRgConjuge(String rgConjuge) {
        this.rgConjuge = rgConjuge;
    }

    public String getRgOrgEmissorConjuge() {
        return rgOrgEmissorConjuge;
    }

    public void setRgOrgEmissorConjuge(String rgOrgEmissorConjuge) {
        this.rgOrgEmissorConjuge = rgOrgEmissorConjuge;
    }

    public Date getRgDataEmissaoConjuge() {
        return rgDataEmissaoConjuge;
    }

    public void setRgDataEmissaoConjuge(Date rgDataEmissaoConjuge) {
        this.rgDataEmissaoConjuge = rgDataEmissaoConjuge;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public Serializable getId() {
        return empresaCod + filialCod + matricula + cpf;
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
    public int compareTo(TmpCadastroGeral o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }
}
