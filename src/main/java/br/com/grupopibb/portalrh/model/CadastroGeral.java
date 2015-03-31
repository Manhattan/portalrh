/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.model;

import br.com.grupopibb.portalrh.model.commons.EntityInterface;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "CADGERAL")
@NamedQueries({
    @NamedQuery(name = "CadastroGeral.selectRange",
            query = " SELECT DISTINCT c FROM CadastroGeral c " //left join c.contrato con left join con.cargo car
            + " WHERE (:cpf2 = 'todos' OR c.cpf LIKE :cpf) "
            + " AND (:nome2 = 'todos' OR c.nome LIKE :nome) "
            + " AND (:rg2 = 'todos' OR c.rg LIKE :rg) "
            + " AND (:nomeMae2 = 'todos' OR c.nomeMae LIKE :nomeMae) "
            + " AND (:situacao2 = 'todos' OR c.situacao = :situacao) "
            //+ " AND (:cargo2 = 'todos' OR car.descricao LIKE :cargo) "
            + " ORDER BY c.nome "),
    @NamedQuery(name = "CadastroGeral.countRange",
            query = " SELECT COUNT(DISTINCT c) FROM CadastroGeral c " //left join c.contrato con left join con.cargo car
            + " WHERE (:cpf2 = 'todos' OR c.cpf LIKE :cpf) "
            + " AND (:nome2 = 'todos' OR c.nome LIKE :nome) "
            + " AND (:rg2 = 'todos' OR c.rg LIKE :rg) "
            + " AND (:nomeMae2 = 'todos' OR c.nomeMae LIKE :nomeMae) "
            + " AND (:situacao2 = 'todos' OR c.situacao = :situacao) " 
        //+ " AND (:cargo2 = 'todos' OR car.descricao LIKE :cargo) "
            )
})
public class CadastroGeral implements EntityInterface<CadastroGeral> {

    @Id
    @Column(name = "CPF", length = 11)
    private String cpf;
    /**/
    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;
    /**/
    @Column(name = "NOMEPAI", length = 40)
    private String nomePai;
    /**/
    @Column(name = "NOMEMAE", length = 40)
    private String nomeMae;
    /**/
    @Column(name = "CIDNASC", length = 20, nullable = false)
    private String cidadeNascimento;
    /**/
    @Column(name = "UFNASC", length = 2)
    private String ufNascimento;
    /**/
    @Column(name = "ENDERECO", length = 35, nullable = false)
    private String endereco;
    /**/
    @Column(name = "BAIRRO", length = 20, nullable = false)
    private String bairro;
    /**/
    @Column(name = "CEP", length = 8, nullable = false)
    private String cep;
    /**/
    @Column(name = "CIDADE", length = 20, nullable = false)
    private String cidade;
    /**/
    @Column(name = "UF", length = 2, nullable = false)
    private String uf;
    /**/
    @Column(name = "FONE", length = 13)
    private String fone;
    /**/
    @Column(name = "SEXO", length = 10)
    private String sexo;
    /**/
    @Column(name = "GRINSTR", length = 2, nullable = false)
    private String grauInstrucao;
    /**/
    @Column(name = "ESTCIVIL", length = 2)
    private String estadoCivil;
    /**/
    @Column(name = "IDENTIDADE", length = 20, nullable = false)
    private String rg;
    /**/
    @Column(name = "ORGAOIDENT", length = 10, nullable = false)
    private String rgOrgaoEmissor;
    /**/
    @Column(name = "CARTPROF", length = 13)
    private String ctps;
    /**/
    @Column(name = "PISPASEP", length = 11)
    private String pisPasep;
    /**/
    @Column(name = "TITELEITOR", length = 20)
    private String tituloEleitorNumero;
    /**/
    @Column(name = "CONTABANCO", length = 20)
    private String contaBanco;
    /**/
    @Column(name = "NUMDEPSF")
    private Integer numeroDependenteSalFamilia;
    /**/
    @Column(name = "NUMDEPIR")
    private Integer numeroDependenteImpRenda;
    /**/
    @Column(name = "BANCO", length = 3)
    private String banco;
    /**/
    @Column(name = "AGENCIA", length = 5)
    private String agencia;
    /**/
    @Column(name = "OBS", length = 30)
    private String observacao;
    /**/
    @ManyToOne(targetEntity = RacaCor.class)
    @JoinColumn(name = "RACACOR", referencedColumnName = "CODIGO")
    private RacaCor racaCor;
    /**/
    @Column(name = "PortDefic", length = 1)
    private String portadorDeficiencia;
    /**/
    @Column(name = "CODDEFIC", length = 1)
    private String codigoDeficiencia;
    /*
     @OneToMany(targetEntity = Contrato.class, fetch = FetchType.LAZY)
     @JoinColumn(name = "CPF", insertable = false, updatable = false)
     private List<Contrato> contrato;
     */
    /**/
    @Transient
    private Deficiencia deficiencia;
    /**/
    @ManyToOne(targetEntity = NacionalidadeFolha.class)
    @JoinColumn(name = "NACIONALIDADE", referencedColumnName = "CodNacio_Folha")
    private NacionalidadeFolha nacionalidade;
    /**/
    @Column(name = "UFCTPS", length = 2)
    private String ctpsUf;
    /**/
    @Column(name = "NUMERO_RES", length = 5)
    private String numero;
    /**/
    @Column(name = "COMPEND", length = 40)
    private String complementoEndereco;
    /**/
    @Column(name = "EMAIL", length = 244)
    private String email;
    /**/
    @Column(name = "CELULAR", length = 10)
    private String celular;
    /**/
    @Column(name = "TITZONA", length = 5)
    private String tituloZona;
    /**/
    @Column(name = "CONJUGECPF", length = 11)
    private String cpfConjuge;
    /**/
    @Column(name = "CONJUGENOME", length = 100)
    private String nomeConjuge;
    /**/
    @Column(name = "CONJUGEIDENT", length = 20)
    private String rgConjuge;
    /**/
    @Column(name = "CONJUGEIDORGAO", length = 10)
    private String rgOrgEmissorConjuge;
    /**/
    @Column(name = "CONJUGEIDDTEXP", length = 10)
    private String rgDataEmissaoConjuge;
    /**/
    @Column(name = "CONJUGENASC", length = 10)
    private String dataNascimentoConjuge;
    /**/
    @Column(name = "COMPLEMENTO", length = 30)
    private String complemento;
    /**/
    @ManyToOne(targetEntity = MunicipioFolha.class)
    @JoinColumn(name = "CODMUNICIPIO", referencedColumnName = "CODNAC")
    private MunicipioFolha municipio;
    /**/
    @Column(name = "SERIE_CTPS", length = 6)
    private String ctpsSerie;
    /**/
    @Column(name = "CNH", length = 20)
    private String cnhNumero;
    /**/
    @Column(name = "CAT_CNH", length = 2)
    private String cnhCategoria;
    /**/
    @Column(name = "CERT_RESERV", length = 30)
    private String reservistaNumero;
    /**/
    @Column(name = "CAT_RESERV", length = 2)
    private String reservistaCategoria;
    /**/
    @Column(name = "TITSECAO", length = 5)
    private String tituloSecao;
    /**/
    @Temporal(TemporalType.DATE)
    @Column(name = "DTIDENT")
    private Date rgDataEmissao;
    /**/
    @Temporal(TemporalType.DATE)
    @Column(name = "DTCTPS")
    private Date ctpsData;
    /**/
    @Temporal(TemporalType.DATE)
    @Column(name = "VECMENTO_CNH")
    private Date cnhDataVencimento;
    /**/
    @Temporal(TemporalType.DATE)
    @Column(name = "DTNASC")
    private Date dataNascimento;
    /**/
    @Column(name = "SITUACAO", length = 20)
    private String situacao;
    /**/
    @Temporal(TemporalType.DATE)
    @Column(name = "DTEXP_CNH")
    private Date cnhDataExpedicao;
    /**/
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_RESERV")
    private Date reservistaData;
    /**/
    @Column(name = "UFIDENT", length = 2)
    private String rgUf;
    /**/
    @ManyToOne(targetEntity = MunicipioFolha.class)
    @JoinColumn(name = "CODMUNICNASC", referencedColumnName = "CODNAC")
    private MunicipioFolha municipioNascimento;
    /**/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DTCADASTRO")
    private Date dataCadastro;
    /**/
    @Column(name = "DIG_CTPS", length = 1)
    private String ctpsDigito;
    /**/
    @Column(name = "TMP_CARTPROF", length = 13)
    private String tmpCarteiraProfissional;
    /**/
    @Column(name = "DIGITOCONTA", length = 1)
    private String contaBancoDigito;
    /**/
    @Column(name = "DIGITOAGENCIA", length = 1)
    private String agenciaDigito;
    /**/
    @Column(name = "TIPOCONTA", length = 1)
    private String contaBancoTipo;
    /**/
    @OneToMany(mappedBy = "cadastroGeral", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dependente> dependentes;
    /**/
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "FOTO")
    private Serializable foto;
    /**/
    @Transient
    private GrauInstrucao grauInst;

    @Override
    public Serializable getId() {
        return this.cpf;
    }

    @Override
    public String getLabel() {
        return this.nome;
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
    public int compareTo(CadastroGeral o) {
        return this.getId().toString().compareTo(o.getId().toString());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.cpf != null ? this.cpf.hashCode() : 0);
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
        final CadastroGeral other = (CadastroGeral) obj;
        if ((this.cpf == null) ? (other.cpf != null) : !this.cpf.equals(other.cpf)) {
            return false;
        }
        return true;
    }
    /*
     public String getCargo() {
     try {
     return contrato.get(0).getCargo().getDescricao();
     } catch (NullPointerException e) {
     return "";
     } catch (IndexOutOfBoundsException ex) {
     return "";
     }
     }
     */

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

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
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

    public String getTituloEleitorNumero() {
        return tituloEleitorNumero;
    }

    public void setTituloEleitorNumero(String tituloEleitorNumero) {
        this.tituloEleitorNumero = tituloEleitorNumero;
    }

    public String getContaBanco() {
        return contaBanco;
    }

    public void setContaBanco(String contaBanco) {
        this.contaBanco = contaBanco;
    }

    public Integer getNumeroDependenteSalFamilia() {
        return numeroDependenteSalFamilia;
    }

    public void setNumeroDependenteSalFamilia(Integer numeroDependenteSalFamilia) {
        this.numeroDependenteSalFamilia = numeroDependenteSalFamilia;
    }

    public Integer getNumeroDependenteImpRenda() {
        return numeroDependenteImpRenda;
    }

    public void setNumeroDependenteImpRenda(Integer numeroDependenteImpRenda) {
        this.numeroDependenteImpRenda = numeroDependenteImpRenda;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public RacaCor getRacaCor() {
        return racaCor;
    }

    public void setRacaCor(RacaCor racaCor) {
        this.racaCor = racaCor;
    }

    public String getPortadorDeficiencia() {
        return portadorDeficiencia;
    }

    public void setPortadorDeficiencia(String portadorDeficiencia) {
        this.portadorDeficiencia = portadorDeficiencia;
    }

    public String getCodigoDeficiencia() {
        return codigoDeficiencia;
    }

    public void setCodigoDeficiencia(String codigoDeficiencia) {
        this.codigoDeficiencia = codigoDeficiencia;
    }
    /*
     public List<Contrato> getContrato() {
     return contrato;
     }

     public void setContrato(List<Contrato> contrato) {
     this.contrato = contrato;
     }
     */

    public Deficiencia getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(Deficiencia deficiencia) {
        this.deficiencia = deficiencia;
    }

    public NacionalidadeFolha getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(NacionalidadeFolha nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCtpsUf() {
        return ctpsUf;
    }

    public void setCtpsUf(String ctpsUf) {
        this.ctpsUf = ctpsUf;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTituloZona() {
        return tituloZona;
    }

    public void setTituloZona(String tituloZona) {
        this.tituloZona = tituloZona;
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

    public String getRgDataEmissaoConjuge() {
        return rgDataEmissaoConjuge;
    }

    public void setRgDataEmissaoConjuge(String rgDataEmissaoConjuge) {
        this.rgDataEmissaoConjuge = rgDataEmissaoConjuge;
    }

    public String getDataNascimentoConjuge() {
        return dataNascimentoConjuge;
    }

    public void setDataNascimentoConjuge(String dataNascimentoConjuge) {
        this.dataNascimentoConjuge = dataNascimentoConjuge;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public MunicipioFolha getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioFolha municipio) {
        this.municipio = municipio;
    }

    public String getCtpsSerie() {
        return ctpsSerie;
    }

    public void setCtpsSerie(String ctpsSerie) {
        this.ctpsSerie = ctpsSerie;
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

    public String getTituloSecao() {
        return tituloSecao;
    }

    public void setTituloSecao(String tituloSecao) {
        this.tituloSecao = tituloSecao;
    }

    public Date getRgDataEmissao() {
        return rgDataEmissao;
    }

    public void setRgDataEmissao(Date rgDataEmissao) {
        this.rgDataEmissao = rgDataEmissao;
    }

    public Date getCtpsData() {
        return ctpsData;
    }

    public void setCtpsData(Date ctpsData) {
        this.ctpsData = ctpsData;
    }

    public Date getCnhDataVencimento() {
        return cnhDataVencimento;
    }

    public void setCnhDataVencimento(Date cnhDataVencimento) {
        this.cnhDataVencimento = cnhDataVencimento;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getCnhDataExpedicao() {
        return cnhDataExpedicao;
    }

    public void setCnhDataExpedicao(Date cnhDataExpedicao) {
        this.cnhDataExpedicao = cnhDataExpedicao;
    }

    public Date getReservistaData() {
        return reservistaData;
    }

    public void setReservistaData(Date reservistaData) {
        this.reservistaData = reservistaData;
    }

    public String getRgUf() {
        return rgUf;
    }

    public void setRgUf(String rgUf) {
        this.rgUf = rgUf;
    }

    public MunicipioFolha getMunicipioNascimento() {
        return municipioNascimento;
    }

    public void setMunicipioNascimento(MunicipioFolha municipioNascimento) {
        this.municipioNascimento = municipioNascimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getCtpsDigito() {
        return ctpsDigito;
    }

    public void setCtpsDigito(String ctpsDigito) {
        this.ctpsDigito = ctpsDigito;
    }

    public String getTmpCarteiraProfissional() {
        return tmpCarteiraProfissional;
    }

    public void setTmpCarteiraProfissional(String tmpCarteiraProfissional) {
        this.tmpCarteiraProfissional = tmpCarteiraProfissional;
    }

    public String getContaBancoDigito() {
        return contaBancoDigito;
    }

    public void setContaBancoDigito(String contaBancoDigito) {
        this.contaBancoDigito = contaBancoDigito;
    }

    public String getAgenciaDigito() {
        return agenciaDigito;
    }

    public void setAgenciaDigito(String agenciaDigito) {
        this.agenciaDigito = agenciaDigito;
    }

    public String getContaBancoTipo() {
        return contaBancoTipo;
    }

    public void setContaBancoTipo(String contaBancoTipo) {
        this.contaBancoTipo = contaBancoTipo;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    public Serializable getFoto() {
        return foto;
    }

    public void setFoto(Serializable foto) {
        this.foto = foto;
    }

    public GrauInstrucao getGrauInst() {
        return grauInst;
    }

    public void setGrauInst(GrauInstrucao grauInst) {
        this.grauInst = grauInst;
    }
}