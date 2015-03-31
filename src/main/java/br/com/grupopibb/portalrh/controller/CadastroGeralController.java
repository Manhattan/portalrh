/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.controller;

import br.com.grupopibb.portalrh.business.CadastroGeralBusiness;
import br.com.grupopibb.portalrh.business.GrauInstrucaoBusiness;
import br.com.grupopibb.portalrh.business.RacaCorBusiness;
import br.com.grupopibb.portalrh.controller.commons.EntityController;
import br.com.grupopibb.portalrh.controller.commons.EntityPagination;
import br.com.grupopibb.portalrh.dao.BancoFolhaFacade;
import br.com.grupopibb.portalrh.dao.CadastroGeralFacade;
import br.com.grupopibb.portalrh.dao.EstadoFacade;
import br.com.grupopibb.portalrh.dao.MunicipioFolhaFacade;
import br.com.grupopibb.portalrh.dao.NacionalidadeFolhaFacade;
import br.com.grupopibb.portalrh.exceptions.EntityException;
import br.com.grupopibb.portalrh.model.CadastroGeral;
import br.com.grupopibb.portalrh.model.CentroCusto;
import br.com.grupopibb.portalrh.model.Dependente;
import br.com.grupopibb.portalrh.model.Estado;
import br.com.grupopibb.portalrh.model.Funcionario;
import br.com.grupopibb.portalrh.model.GrauInstrucao;
import br.com.grupopibb.portalrh.model.MunicipioFolha;
import br.com.grupopibb.portalrh.model.NacionalidadeFolha;
import br.com.grupopibb.portalrh.types.StatusLogin;
import br.com.grupopibb.portalrh.utils.JsfUtil;
import br.com.grupopibb.portalrh.utils.MessageUtils;
import br.com.grupopibb.portalrh.utils.NumberUtils;
import br.com.grupopibb.portalrh.utils.StringBeanUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
@ManagedBean
@ViewScoped
public class CadastroGeralController extends EntityController<CadastroGeral> implements Serializable {

    @EJB
    private CadastroGeralFacade cadastroGeralFacade;
    @EJB
    private NacionalidadeFolhaFacade nacionalidadeFolhaFacade;
    @EJB
    private MunicipioFolhaFacade municipioFolhaFacade;
    @EJB
    private EstadoFacade estadoFacade;
    @EJB
    private GrauInstrucaoBusiness grauInstrucaoBusiness;
    @EJB
    private RacaCorBusiness racaCorBusiness;
    @EJB
    private CadastroGeralBusiness cadastroGeralBusiness;
    @EJB
    private BancoFolhaFacade bancoFolhaFacade;
    private CadastroGeral current;
    private String cpf;
    private String nome;
    private String rg;
    private String nomeMae;
    private String situacao;
    private Estado estado;
    private String cargo;
    private List<GrauInstrucao> grausInstrucao;
    private Funcionario funcionario;
    private CentroCusto centroSelecionado;

    @PostConstruct
    public void init() {
        if (current == null) {
            this.current = getNewEntity();
            newEntity = true;
        }
        mountGrauInstrucao();
    }

    @Override
    public String clean() {
        this.cpf = null;
        this.nome = null;
        this.rg = null;
        this.nomeMae = null;
        this.situacao = null;
        recreateTable();
        return super.clean();
    }

    private void cleanCurrent() {
        if (this.current != null && this.current.getCpf() != null) {
            String c = this.current.getCpf();
            setEntity(getNewEntity());
            this.current.setCpf(c);
        }
    }

    private void initEstado() {
        if (this.current != null && this.current.getUf() == null) {
            if (this.current.getMunicipio() == null || this.current.getMunicipio().getUf() == null) {
                this.current.setUf("CE");
            } else {
                this.current.setUf(this.current.getMunicipio().getUf());
            }
        }
    }

    private void initEstadoNasc() {
        if (this.current != null && this.current.getUfNascimento() == null) {
            if (this.current.getMunicipioNascimento() == null || this.current.getMunicipioNascimento().getUf() == null) {
                this.current.setUfNascimento("CE");
            } else {
                this.current.setUfNascimento(this.current.getMunicipioNascimento().getUf());
            }
        }
    }

    /**
     * Verifica se o CPF do CadastroGeral atual é nulo ou não.
     *
     * @return Verdadeiro ou Falso.
     */
    public boolean isCpfNull() {
        return this.current == null || StringUtils.isBlank(this.current.getCpf());
    }

    private boolean isCpfExists(String c) {
        if (c == null || cadastroGeralFacade.find(c) == null) {
            return false;
        } else {
            this.current.setCpf("");
            return true;
        }
    }

    public String validaCpf() {
        if (!StringBeanUtils.validarCPF(this.current.getCpf())) {
            msgCpfInvalido();
            this.current.setCpf("");
        } else {
            if (isCpfExists(this.current.getCpf())) {
                msgCpfExiste();
                this.current.setCpf("");
            } else {
                cleanCurrent();
            }
        }
        return JsfUtil.MANTEM;
    }

    public String validaCpfConjuge() {
        if (!StringBeanUtils.validarCPF(this.current.getCpfConjuge())) {
            msgCpfInvalido();
            this.current.setCpfConjuge("");
        }
        return JsfUtil.MANTEM;
    }

    public String cancelar() {
        this.current = getNewEntity();
        this.cpf = null;
        this.nome = null;
        this.rg = null;
        this.nomeMae = null;
        this.situacao = null;
        this.estado = null;
        this.cargo = null;
        return JsfUtil.MANTEM;
    }

    private void mountGrauInstrucao() {
        grausInstrucao = grauInstrucaoBusiness.mountGrauInstrucao();
    }

    /**
     * Executado antes do bean JSF ser destruído.
     */
    @PreDestroy
    public void end() {
    }

    @Override
    protected void setEntity(CadastroGeral t) {
        this.current = t;
    }

    @Override
    protected CadastroGeral getNewEntity() {
        CadastroGeral cadGer = new CadastroGeral();
        cadGer.setGrauInst(grauInstrucaoBusiness.getNI());
        cadGer.setGrauInstrucao(cadGer.getGrauInst().getCodigo());
        cadGer.setSexo("1");
        cadGer.setEstadoCivil("0");
        cadGer.setNacionalidade(new NacionalidadeFolha("10", "Brasileiro"));
        cadGer.setRacaCor(racaCorBusiness.getParda());
        cadGer.setDependentes(new ArrayList<Dependente>());
        cadGer.setRgUf("CE");
        cadGer.setCtpsUf("CE");
        cadGer.setSituacao("Cadastrado");
        cadGer.setObservacao("");
        return cadGer;
    }

    @Override
    public String prepareCreate() {
        setEntity(getNewEntity());
        this.newEntity = true;
        return JsfUtil.MANTEM;
    }

    @Override
    public String prepareEdit() {
        setEntity(null);
        super.prepareEdit();
        this.current.setGrauInst(grauInstrucaoBusiness.find(this.current.getGrauInstrucao()));
        return JsfUtil.MANTEM;
    }

    public String prepareEdit(String cpf) {
        setEntity(null);
        setEntity(cadastroGeralFacade.find(cpf));
        newEntity = false;
        this.current.setGrauInst(grauInstrucaoBusiness.find(this.current.getGrauInstrucao()));
        return JsfUtil.MANTEM;
    }

    @Override
    protected void performDestroy() {
        try {
            getFacade().remove(this.current);
        } catch (EntityException ex) {
            Logger.getLogger(CadastroGeralController.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    private void prepareCadGerToPersiste(CadastroGeral cadGer) {
        cadGer.setGrauInstrucao(cadGer.getGrauInst().getCodigo());
        cadGer.setCidade(cadGer.getMunicipio().getNome());
        cadGer.setCidadeNascimento(cadGer.getMunicipioNascimento().getNome());
        if (cadGer.getNumeroDependenteImpRenda() == null) {
            cadGer.setNumeroDependenteImpRenda(0);
        }
        if (cadGer.getNumeroDependenteSalFamilia() == null) {
            cadGer.setNumeroDependenteSalFamilia(0);
        }
    }

    private void corrigeCidade() {
        int cidIdx;
        int cidNascIdx;
        cidIdx = this.current.getCidade().length();
        cidNascIdx = this.current.getCidadeNascimento().length();

        cidIdx = cidIdx > 20 ? 20 : cidIdx;
        cidNascIdx = cidNascIdx > 20 ? 20 : cidNascIdx;

        this.current.setCidade(this.current.getCidade().substring(0, cidIdx));
        this.current.setCidadeNascimento(this.current.getCidadeNascimento().substring(0, cidNascIdx));
    }

    @Override
    protected String create() {
        try {
            prepareCadGerToPersiste(this.current);
            this.current.setDataCadastro(new Date());
            corrigeCidade();
            newEntity = false;
            getFacade().create(this.current);
            msgCadastroGeralCreated();
            this.current = getNewEntity();
            return JsfUtil.MANTEM;
        } catch (EntityException ex) {
            Logger.getLogger(CadastroGeralController.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected String update() {
        try {
            prepareCadGerToPersiste(this.current);
            corrigeCidade();
            getFacade().update(this.current);
            msgCadastroGeralUpdated();
            this.current = getNewEntity();
            return JsfUtil.MANTEM;
        } catch (EntityException ex) {
            Logger.getLogger(CadastroGeralController.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public EntityPagination getPagination() {
        if (pagination == null) {
            pagination = new EntityPagination(15) {
                @Override
                public int getItemsCount() {
                    return getFacade().pesqCount(cpf, nome, rg, nomeMae, situacao/*, cargo*/).intValue();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().listPesqParamRange(cpf, nome, rg, nomeMae, situacao/*, cargo*/, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public void pesquisar() {
        recreateTable();
    }

    /**
     * Recebe o funcionário logado e redireciona a página de acordo com o status
     * do login.
     *
     * @param func
     * @param statusLogin
     * @return página de acotdo com o status do login.
     */
    public String initFuncionario(Funcionario func, int statusLogin) {
        if (statusLogin == StatusLogin.ATIVO) {
            this.funcionario = func;
            centroSelecionado = funcionario.getCentros().get(0);
            return JsfUtil.CAD_GERAL;
        } else if (statusLogin == StatusLogin.INATIVO) {
            return JsfUtil.LOGIN_ERROR;
        } else {
            return JsfUtil.LOGIN_PAGE;
        }
    }

    public CadastroGeralFacade getFacade() {
        return this.cadastroGeralFacade;
    }

    /**
     * Define o SelectItem[] para as nacionalidades da folha.
     *
     * @return SelectItem[] com nacionalidades da folha.
     */
    public SelectItem[] getNacionalidadeSelect() {
        return JsfUtil.getSelectItems(nacionalidadeFolhaFacade.findAll(), false, FacesContext.getCurrentInstance());
    }

    /**
     * Define o SelectItem[] para os municipios da folha.
     *
     * @return SelectItem[] com municipios da folha.
     */
    public SelectItem[] getMunicipioSelect() {
        if (this.current != null && this.current.getMunicipio() == null) {
            current.setMunicipio(new MunicipioFolha("1241", "CE", "Fortaleza"));
        }
        if (this.current.getUf() == null) {
            initEstado();
        }
        return JsfUtil.getSelectItems(municipioFolhaFacade.listPesqParam(this.current.getUf()), false, FacesContext.getCurrentInstance());
    }

    /**
     * Define o SelectItem[] para os municipios da folha referente à cidade de
     * nascimento do funcionário.
     *
     * @return SelectItem[] com municipios da folha.
     */
    public SelectItem[] getMunicipioNascSelect() {
        if (this.current != null && this.current.getMunicipioNascimento() == null) {
            current.setMunicipioNascimento(new MunicipioFolha("1241", "CE", "Fortaleza"));
        }
        if (this.current.getUfNascimento() == null) {
            initEstadoNasc();
        }
        return JsfUtil.getSelectItems(municipioFolhaFacade.listPesqParam(this.current.getUfNascimento()), false, FacesContext.getCurrentInstance());
    }

    /**
     * Define o SelectItem[] para os estados cadastrados.
     *
     * @return SelectItem[] com estados.
     */
    public SelectItem[] getEstadoSelect() {
        initEstado();
        initEstadoNasc();
        return JsfUtil.getSelectItems(estadoFacade.findAll(), false, FacesContext.getCurrentInstance());
    }

    public Map<String, Object> getEstadoMapSelect() {
        initEstado();
        initEstadoNasc();
        return JsfUtil.getMapItems(municipioFolhaFacade.findAllUf(), false, FacesContext.getCurrentInstance());
    }

    public SelectItem[] getGrauInstrucaoSelect() {
        return JsfUtil.getSelectItems(grausInstrucao, false, FacesContext.getCurrentInstance());
    }

    public SelectItem[] getBancoFolhaSelect() {
        return JsfUtil.getSelectItems(bancoFolhaFacade.findAll(), false, FacesContext.getCurrentInstance());
    }

    private void initializeDependentes(CadastroGeral cadGeral) {
        if (cadGeral == null) {
            throw new NullPointerException("CadastroGeral nao pode ser nulo.");
        } else if (cadGeral.getDependentes() == null) {
            cadGeral.setDependentes(new ArrayList<Dependente>());
        }
    }

    /**
     * Adiciona um dependente na lista de dependentes de um funcionário
     * (cadgeral).
     *
     * @param cadGeral
     * @param dep
     */
    private void addDependente(CadastroGeral cadGeral, Dependente dep) {
        try {
            if (dep != null && StringUtils.isNotBlank(dep.getNome())) {
                initializeDependentes(cadGeral);
                dep.setCadastroGeral(cadGeral);
                cadGeral.getDependentes().add(dep);
            } else {
                msgCampoNomeNI();
            }
        } catch (NullPointerException e) {
            msgErroExecOpe();
            throw e;
        }
    }

    private void removeDependente(CadastroGeral cadGeral, Dependente dep) {
        try {
            int index = cadastroGeralBusiness.indexOfDependente(cadGeral.getDependentes(), dep);
            cadGeral.getDependentes().remove(index);
        } catch (NullPointerException e) {
            msgErroExecOpe();
            throw e;
        }
    }

    /**
     * Atualiza um dependente na lista de dependentes de um funcionário
     * (cadgeral).
     *
     * @param cadGeral
     * @param dep
     */
    private void updateDependente(CadastroGeral cadGeral, Dependente dep) {
        try {
            if (dep != null && StringUtils.isNotBlank(dep.getNome())) {
                int index = cadastroGeralBusiness.indexOfDependente(cadGeral.getDependentes(), dep);
                cadGeral.getDependentes().set(index, dep);
            } else {
                msgCampoNomeNI();
            }
        } catch (NullPointerException e) {
            msgErroExecOpe();
            throw e;
        }
    }

    public void confirmDependente(Dependente dep, boolean newDependente) {
        if (newDependente) {
            dep.setSequencial(incrementDependente(this.current));
            addDependente(this.current, dep);
        } else {
            updateDependente(this.current, dep);
        }
    }

    public void confirmRemoveDependente(Dependente dep) {
        removeDependente(this.current, dep);
    }

    private int incrementDependente(CadastroGeral cadGeral) {
        int itemMaior = 1;
        for (Dependente dep : cadGeral.getDependentes()) {
            while (NumberUtils.isNull(dep.getSequencial(), 0) >= itemMaior) {
                itemMaior++;
            }
        }
        return itemMaior;
    }

    public CadastroGeral getCurrent() {
        return current;
    }

    public void setCurrent(CadastroGeral current) {
        this.current = current;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void msgCadastroGeralCreated() {
        MessageUtils.messageFactoringFull("CadastroGeralCreated",
                new Object[]{StringBeanUtils.formatCPF(current.getCpf())},
                FacesMessage.SEVERITY_INFO,
                FacesContext.getCurrentInstance());
    }

    public void msgCadastroGeralUpdated() {
        MessageUtils.messageFactoringFull("CadastroGeralUpdated",
                new Object[]{StringBeanUtils.formatCPF(current.getCpf())},
                FacesMessage.SEVERITY_INFO,
                FacesContext.getCurrentInstance());
    }

    public void msgErroExecOpe() {
        MessageUtils.messageFactoringFull("erroExecutarOperacao",
                null,
                FacesMessage.SEVERITY_ERROR,
                FacesContext.getCurrentInstance());
    }

    public void msgCampoNomeNI() {
        MessageUtils.messageFactoringFull("campoNomeNI",
                null,
                FacesMessage.SEVERITY_ERROR,
                FacesContext.getCurrentInstance());
    }

    public void msgCpfExiste() {
        MessageUtils.messageFactoringFull("cpfJaCadastrado",
                new Object[]{StringBeanUtils.formatCPF(current.getCpf())},
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgCpfInvalido() {
        MessageUtils.messageFactoringFull("cpfInvalido1",
                new Object[]{StringBeanUtils.formatCPF(current.getCpf())},
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }
}
