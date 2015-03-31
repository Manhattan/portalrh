/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.controller;

import br.com.grupopibb.portalrh.business.TipoOcorrenciaBusiness;
import br.com.grupopibb.portalrh.controller.commons.EntityController;
import br.com.grupopibb.portalrh.controller.commons.EntityPagination;
import br.com.grupopibb.portalrh.dao.CadastroGeralFacade;
import br.com.grupopibb.portalrh.dao.ContratoFacade;
import br.com.grupopibb.portalrh.dao.FuncionarioOcorrenciaFacade;
import br.com.grupopibb.portalrh.dao.OcorrenciaFacade;
import br.com.grupopibb.portalrh.dao.ParametrosFacade;
import br.com.grupopibb.portalrh.dao.TmpCadastroGeralFacade;
import br.com.grupopibb.portalrh.exceptions.EntityException;
import br.com.grupopibb.portalrh.model.CadastroGeral;
import br.com.grupopibb.portalrh.model.CentroCusto;
import br.com.grupopibb.portalrh.model.Contrato;
import br.com.grupopibb.portalrh.model.FuncionarioOcorrencia;
import br.com.grupopibb.portalrh.model.Ocorrencia;
import br.com.grupopibb.portalrh.model.Parametros;
import br.com.grupopibb.portalrh.model.TmpCadastroGeral;
import br.com.grupopibb.portalrh.types.EnumSituacaoPessoal;
import br.com.grupopibb.portalrh.utils.DateUtils;
import br.com.grupopibb.portalrh.utils.JsfUtil;
import br.com.grupopibb.portalrh.utils.MessageUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
@ManagedBean
@ViewScoped
public class FuncionarioOcorrenciaController extends EntityController<FuncionarioOcorrencia> implements Serializable {

    @EJB
    private FuncionarioOcorrenciaFacade funcionarioOcorrenciaFacade;
    @EJB
    private OcorrenciaFacade ocorrenciaFacade;
    @EJB
    private CadastroGeralFacade cadastroGeralFacade;
    @EJB
    private TmpCadastroGeralFacade tmpCadastroGeralFacade;
    @EJB
    private TipoOcorrenciaBusiness tipoOcorrenciaBusiness;
    @EJB
    private ContratoFacade contratoFacade;
    @EJB
    private ParametrosFacade parametrosFacade;
    private FuncionarioOcorrencia current;
    private CentroCusto centroSelecionado;
    private String cpf;
    private Integer codigoOcorrencia;
    private List<String> contCpf;
    private boolean pesquisaAtiva = false;
    private String mesAno;
    /**/
    private Integer fltCod;
    private Integer fltCodOcor;
    private String fltDescOcor;
    private Date fltDtLancto;
    private Date fltDtIni;
    private Date fltDtFim;
    private String fltDetalha;
    private String fltCpf;
    private String fltNome;
    /**/
    @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;

    public void setLoginController(LoginController loginContronller) {
        this.loginController = loginContronller;
    }

    @PostConstruct
    private void init() {
        if (current == null) {
            current = getNewEntity();
            newEntity = true;
        }
        mesAno = DateUtils.getMonthYearBar(new Date());
    }

    public String cancelar() {
        this.current = getNewEntity();
        this.cpf = null;
        this.codigoOcorrencia = null;
        this.fltCod = null;
        this.fltCodOcor = null;
        this.fltDescOcor = null;
        this.fltDtLancto = null;
        this.fltDtIni = null;
        this.fltDtFim = null;
        this.fltDetalha = null;
        this.fltCpf = null;
        this.fltNome = null;
        return JsfUtil.MANTEM;
    }

    @Override
    public String prepareEdit() {
        super.prepareEdit();
        this.codigoOcorrencia = this.current.getOcorrencia().getCodigo();
        this.cpf = this.current.getCadastroGeral().getCpf();
        return JsfUtil.MANTEM;
    }

    /**
     * Habilita o registro atual para ser inserido ou alterado.
     *
     * @return true = Passível de inserção ou alteração; false = Impassível de
     * inserção ou alteração.
     */
    public boolean isEnableFunOc() {
        if (newEntity && loginController.isIncluiRhOcorrencia()) {
            return true;
        } else if (!newEntity && loginController.isAlteraRhOcorrencia() && isFuncionarioPermitido() && isOcorrenciaComPeriodoAberto()) {
            if (isCadastroCompleto() && DateUtils.daysBetween(this.current.getData(), new Date()) <= 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Habilita o registro atual para ser excluído ou não.
     *
     * @return true = Passível de exclusão; false = Impassível de exclusão.
     */
    public boolean isEnableDeleteFunOc() {
        return (!newEntity && loginController.isRemoveRhOcorrencia() && isFuncionarioPermitido() && isOcorrenciaComPeriodoAberto());
    }

    /**
     * Verifica se o registro de ocorrência selecionado está com o período de
     * encerramento aberto.
     *
     * @return true = está disponível para alteração; falso = não está
     * disponível para alteração.
     */
    public boolean isOcorrenciaComPeriodoAberto() {
        //DEFINE O PARAMETRO DO MES ATUAL
        Date dataAtual = new Date();
        //DEFINE O PARAMETRO DO MES ANTERIOR
        Date dataAnterior = DateUtils.addMonths(new Date(), -1);

        //Dia de encerramento do mês atual
        Integer diaAtual = parametrosFacade.find(StringUtils.right("0" + DateUtils.getMonth(dataAtual), 2), DateUtils.getYear(dataAtual) + "", "1").getDiaEncerramento();
        //Dia de encerramento do mês anterior
        Integer diaAnterior = parametrosFacade.find(StringUtils.right("0" + DateUtils.getMonth(dataAnterior), 2), DateUtils.getYear(dataAnterior) + "", "1").getDiaEncerramento();

        //DATA DE LANCAMENTO NO MÊS ATUAL
        if (DateUtils.getYearMonth(current.getDataIni()).equals(DateUtils.getYearMonth(dataAtual))) {
            //se a data do lançamento for maior que o dia limite do mês atual
            if (DateUtils.getDay(current.getDataIni()) > diaAtual) {
                return true;
                //se a data do lançamento e a data atual forem menor ou iguais ao dia limite do mês atual
            } else if (DateUtils.getDay(current.getDataIni()) <= diaAtual && DateUtils.getDay(dataAtual) <= diaAtual) {
                return true;
            } else {
                return false;
            }
            //DATA DE LANCAMENTO NO MÊS ANTERIOR
        } else if (DateUtils.getYearMonth(current.getDataIni()).equals(DateUtils.getYearMonth(dataAnterior))) {
            //se a data de lançamento for maior que o dia de encerramento anterior e a data atual estiver entre o encerramento anterior e o encerramento atual
            if (DateUtils.getDay(current.getDataIni()) > diaAnterior
                    && dataAtual.getTime() <= DateUtils.setDay(dataAtual, diaAtual).getTime()
                    && dataAtual.getTime() > DateUtils.setDay(dataAnterior, diaAnterior).getTime()) {
                return true;
            }
        }
        return false;
    }

    public boolean isPesquisaAtiva() {
        return pesquisaAtiva;
    }

    public boolean isHasFalta() {
        if (this.current != null && this.current.getTipoOcorrencia() != null && this.current.getTipoOcorrencia() == 2) {
            if (funcionarioOcorrenciaFacade.findFalta(this.current.getDataIni(), this.current.getCadastroGeral()) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se já existe ocorrência lançada para o funcionário selecionado e
     * no período informado (Data Ini - Data Fin).
     *
     * @return true = Já existe ocorrência; false = Não existe ocorrência.
     */
    public boolean isHasOcorrencia() {
        if (this.current != null && this.current.getTipoOcorrencia() != null) {
            return (funcionarioOcorrenciaFacade.isOcorrenciaInPeriodo(this.current.getDataIni(), this.current.getDataFim(), this.current.getCadastroGeral()));
        }
        return false;
    }

    public void pesquisaFuncOcorrencias() {
        pesquisaAtiva = true;
    }

    @PreDestroy
    public void end() {
    }

    public Map<String, Object> getMesAnoSelect() {
        Date data = new Date();
        List<String> lista = new ArrayList<String>();
        lista.add(DateUtils.getMonthYearBar(data));
        lista.add(DateUtils.getMonthYearBar(DateUtils.incrementar(data, -1, Calendar.MONTH)));
        data = null;
        return JsfUtil.getMapItems(lista, false, FacesContext.getCurrentInstance());
    }

    public void initCentroSelecionado(CentroCusto centro) {
        this.centroSelecionado = centro;
    }

    @Override
    protected void setEntity(FuncionarioOcorrencia t) {
        this.current = t;
    }

    @Override
    protected FuncionarioOcorrencia getNewEntity() {
        FuncionarioOcorrencia funOc = new FuncionarioOcorrencia();
        funOc.setData(new Date());
        newEntity = true;
        return funOc;
    }

    public String initOcorrencia(Ocorrencia oc) {
        this.current.setOcorrencia(oc);
        if (oc != null) {
            this.codigoOcorrencia = oc.getCodigo();
            this.current.setTipoOcorrencia(oc.getTipo());
            this.current.setDetalhamento(oc.getDescricao());
            updateDataFimIfFalta();
        }
        //this.newEntity = false;
        return JsfUtil.MANTEM;
    }

    /*    public String initCadastroGeral(CadastroGeral cadGer) {
     this.current.setCadastroGeral(cadGer);
     if (cadGer != null) {
     this.cpf = cadGer.getCpf();
     }
     if (!isFuncionarioPermitido()) {
     this.current.setCadastroGeral(null);
     msgFuncionarioNaoPermitido();
     }
     return JsfUtil.MANTEM;
     }
     */
    public String initCadastroGeral(String cpf) {
        if (current.getDataIni() == null) {
            msgInformeADataInicial();
            return JsfUtil.MANTEM;
        }
        List<TmpCadastroGeral> lista = getTmpCadastroGeral(cpf);
        if (lista == null || lista.isEmpty()) {
            msgFuncionarioNaoEncontrado(cpf);
        } else {
            TmpCadastroGeral tCadGer = getTmpCadastroGeralOfCentro(lista, DateUtils.getMonthYear(this.current.getDataIni()));
            if (tCadGer == null) {
                msgFuncionarioNaoPertenceCentro();
            } else if (!isTmpCadGerContratado(tCadGer)) {
                msgFuncionarioInativo();
            } else if (!isTmpCadGerExistsInAtualMonth(tCadGer, current.getDataIni())) {
                msgFuncionarioInativoMesAtual();
            } else {
                this.current.setCadastroGeral(cadastroGeralFacade.find(cpf));
                this.cpf = cpf;
                return JsfUtil.MANTEM;
            }
        }
        this.current.setCadastroGeral(null);
        return JsfUtil.MANTEM;
    }

    public String getTipoOcorrencia() {
        if (this.current == null || this.current.getTipoOcorrencia() == null || this.current.getTipoOcorrencia() > 3) {
            return "";
        }
        return tipoOcorrenciaBusiness.getTipoOcorrencia(this.current.getTipoOcorrencia());
    }

    public String getTipoOcorrencia(Integer codigo) {
        try {
            return tipoOcorrenciaBusiness.getTipoOcorrencia(this.current.getTipoOcorrencia());
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void findOcorrencia() {

        if (this.codigoOcorrencia == null) {
            this.current.setOcorrencia(null);
            this.current.setOcorrencia(null);
            this.current.setTipoOcorrencia(null);
            this.current.setDetalhamento(null);
        } else {
            Ocorrencia oc = ocorrenciaFacade.find(this.codigoOcorrencia);
            if (oc == null) {
                msgOcorrenciaNaoEncontrada();
                this.current.setOcorrencia(null);
            } else {
                this.current.setOcorrencia(oc);
                this.current.setTipoOcorrencia(oc.getTipo());
                this.current.setDetalhamento(oc.getDescricao());
                updateDataFimIfFalta();
            }
        }
    }

    public void findCadastroGeral() {
        CadastroGeral cad = null;
        if (cpf != null) {
            cad = cadastroGeralFacade.find(this.cpf);
        }
        if (cad == null) {
            this.current.setCadastroGeral(new CadastroGeral());
            msgCpfNaoEncontrado();
        } else if (isFuncionarioPermitido()) {
            this.current.setCadastroGeral(cad);
        } else if (this.current.getDataIni() == null) {
            msgInformeADataInicial();
        } else {
            this.current.setCadastroGeral(new CadastroGeral());
            msgFuncionarioNaoPermitido();
        }
    }

    public boolean isFuncionarioPermitido() {
        if (current == null) {
            return false;
        }
        String mes = StringUtils.right("0" + String.valueOf(DateUtils.getMonth(current.getDataIni())), 2);
        Contrato cont = contratoFacade.findByCentroECpf(loginController.getCentroSelecionado(), this.cpf, mes);
        if (cont != null && cont.getCpf().equals(this.cpf)) {
            this.current.setContrato(cont);
            return true;
        }
        return false;
    }

    /**
     * Pesquisa os registros da entidade TmpCadastroGeral pelo CPF.
     *
     * @param cpf
     * @return Lista com o mesmo funcionário em diversos centros e matrículas.
     */
    private List<TmpCadastroGeral> getTmpCadastroGeral(String cpf) {
        return tmpCadastroGeralFacade.findByCpf(cpf);
    }

    private TmpCadastroGeral getTmpCadastroGeralOfCentro(List<TmpCadastroGeral> listCadGer, String mesAno) {
        for (TmpCadastroGeral tCadGer : listCadGer) {
            if (tCadGer.getEmpresaCod().equals(centroSelecionado.getEmpresa())
                    && tCadGer.getFilialCod().equals(centroSelecionado.getFilial())
                    && tCadGer.getCentroCod().equals(centroSelecionado.getCodigo())
                    && mesAno.equals(tCadGer.getMes() + tCadGer.getAno())) {
                return tCadGer;
            }
        }
        return null;
    }

    private boolean isTmpCadGerContratado(TmpCadastroGeral tCadGer) {
        return (tCadGer.getSituacao().toString().equalsIgnoreCase(EnumSituacaoPessoal.CONTRATADO.getLabel())
                || tCadGer.getSituacao().toString().equalsIgnoreCase(EnumSituacaoPessoal.CADASTRADO.getLabel()));
    }

    private boolean isTmpCadGerExistsInAtualMonth(TmpCadastroGeral tCadGer, Date dataIni) {
        return tCadGer != null && (tCadGer.getAno() + tCadGer.getMes()).equals(DateUtils.getYearMonth(dataIni));
    }

    public boolean isNewEntity() {
        return newEntity;
    }

    private void updateCadGerOnAlterDate() {
        if (this.current.getDataIni() == null && cpf == null) {
            this.current.setCadastroGeral(null);
        } else if (this.cpf == null) {
            this.current.setCadastroGeral(null);
        } else if (!isTmpCadGerExistsInAtualMonth(getTmpCadastroGeralOfCentro(getTmpCadastroGeral(this.cpf), DateUtils.getMonthYear(this.current.getDataIni())), current.getDataIni())) {
            msgFuncionarioInativoMesAtual();
            this.current.setCadastroGeral(null);
        }
    }

    public void updateDataFimIfFalta() {
        if (this.current != null && this.current.getTipoOcorrencia() != null && this.current.getTipoOcorrencia() == 2) {
            this.current.setDataFim(this.current.getDataIni());
        }
        if (dataIniMaiorDataFim()) {
            msgDataIniMaiorDataFim();
        }
        updateCadGerOnAlterDate();
    }

    public void updateDataIniIfFalta() {
        if (this.current != null && this.current.getTipoOcorrencia() != null && this.current.getTipoOcorrencia() == 2) {
            this.current.setDataIni(this.current.getDataFim());
        }
        if (dataIniMaiorDataFim()) {
            msgDataIniMaiorDataFim();
        }
        updateCadGerOnAlterDate();
    }

    public boolean dataIniMaiorDataFim() {
        if (current != null && current.getDataIni() != null && current.getDataFim() != null && current.getDataIni().getTime() > current.getDataFim().getTime()) {
            return true;
        }
        return false;
    }

    public void pesquisar() {
        recreateTable();
    }

    private FuncionarioOcorrenciaFacade getFacade() {
        return funcionarioOcorrenciaFacade;
    }

    @Override
    protected void performDestroy() {
    }

    @Override
    public String prepareCreate() {
        if (loginController.isIncluiRhOcorrencia()) {
            return super.prepareCreate();
        } else {
            msgUsuarioSemAutorizacao();
            return JsfUtil.MANTEM;
        }
    }

    public boolean isCadastroCompleto() {
        if (this.current == null
                || this.current.getCadastroGeral() == null
                || this.current.getCadastroGeral().getCpf() == null
                || this.current.getDataIni() == null
                || this.current.getDataFim() == null
                || this.current.getOcorrencia() == null
                || this.current.getOcorrencia().getCodigo() == null) {
            return false;
        }
        return true;
    }

    @Override
    protected String create() {
        updateDataFimIfFalta();
        if (!isCadastroCompleto()) {
            msgCadastroNaoCompleto();
        } else {
            try {
                this.current.setContrato(contratoFacade.findByCentroECpf(centroSelecionado, cpf, StringUtils.substring("0" + DateUtils.getMonth(current.getDataIni()), 0, 2)));
                this.current.rebuildByContrato();
                if (this.current.getOcorrencia().getCodigo() != 31) {
                    this.current.setChave(null);
                }
                if (isHasOcorrencia()) {
                    msgOcorrenciaExistente();
                } else if (dataIniMaiorDataFim()) {
                    msgDataIniMaiorDataFim();
                } else {
                    this.current.setUsuarioCadastro(loginController.getLogin());
                    getFacade().create(this.current);
                    recreateTable();
                    msgFuncionarioOcorrenciaCreated();
                }
            } catch (EntityException ex) {
                msgErroExecOpe();
                Logger
                        .getLogger(FuncionarioOcorrenciaController.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                cancelar();
            }
        }
        return JsfUtil.MANTEM;
    }

    @Override
    protected String update() {
        updateDataFimIfFalta();
        if (!isCadastroCompleto()) {
            msgCadastroNaoCompleto();
        } else {
            try {
                this.current.rebuildByContrato();
                if (this.current.getOcorrencia().getCodigo() != 31) {
                    this.current.setChave(null);
                }
                if (isHasFalta()) {
                    msgFaltaExistente();
                } else {
                    getFacade().update(this.current);
                    recreateTable();
                    msgFuncionarioOcorrenciaUpdated();
                }
            } catch (EntityException ex) {
                msgErroExecOpe();
                Logger
                        .getLogger(FuncionarioOcorrenciaController.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                cancelar();
            }
        }
        return JsfUtil.MANTEM;
    }

    public String delete() {
        try {
            if (isEnableDeleteFunOc()) {
                getFacade().remove(this.current);
                recreateTable();
                msgFuncionarioOcorrenciaDeleted();
            } else {
                msgFuncionarioNaoPermitido();
            }
        } catch (EntityException ex) {
            Logger.getLogger(FuncionarioOcorrenciaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cancelar();
        }
        return JsfUtil.MANTEM;
    }

    @Override
    public EntityPagination getPagination() {
        if (pagination == null) {
            pagination = new EntityPagination(15) {
                @Override
                public int getItemsCount() {
                    return getFacade().pesqCount(fltCod, fltCodOcor, fltDescOcor, fltDtLancto, fltDtIni, fltDtFim, fltDetalha, fltCpf, fltNome, contCpf).intValue();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().listPesqParamRange(fltCod, fltCodOcor, fltDescOcor, fltDtLancto, fltDtIni, fltDtFim, fltDetalha, fltCpf, fltNome, contCpf, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public FuncionarioOcorrencia getCurrent() {
        return current;
    }

    public void setCurrent(FuncionarioOcorrencia current) {
        this.current = current;
    }

    public CentroCusto getCentroSelecionado() {
        return centroSelecionado;
    }

    public void setCentroSelecionado(CentroCusto centroSelecionado) {
        this.centroSelecionado = centroSelecionado;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getCodigoOcorrencia() {
        return codigoOcorrencia;
    }

    public void setCodigoOcorrencia(Integer codigoOcorrencia) {
        this.codigoOcorrencia = codigoOcorrencia;
    }

    public Integer getFltCod() {
        return fltCod;
    }

    public void setFltCod(Integer fltCod) {
        this.fltCod = fltCod;
    }

    public Integer getFltCodOcor() {
        return fltCodOcor;
    }

    public void setFltCodOcor(Integer fltCodOcor) {
        this.fltCodOcor = fltCodOcor;
    }

    public String getFltDescOcor() {
        return fltDescOcor;
    }

    public void setFltDescOcor(String fltDescOcor) {
        this.fltDescOcor = fltDescOcor;
    }

    public Date getFltDtLancto() {
        return fltDtLancto;
    }

    public void setFltDtLancto(Date fltDtLancto) {
        this.fltDtLancto = fltDtLancto;
    }

    public Date getFltDtIni() {
        return fltDtIni;
    }

    public void setFltDtIni(Date fltDtIni) {
        this.fltDtIni = fltDtIni;
    }

    public Date getFltDtFim() {
        return fltDtFim;
    }

    public void setFltDtFim(Date fltDtFim) {
        this.fltDtFim = fltDtFim;
    }

    public String getFltDetalha() {
        return fltDetalha;
    }

    public void setFltDetalha(String fltDetalha) {
        this.fltDetalha = fltDetalha;
    }

    public String getFltCpf() {
        return fltCpf;
    }

    public void setFltCpf(String fltCpf) {
        this.fltCpf = fltCpf;
    }

    public String getFltNome() {
        return fltNome;
    }

    public void setFltNome(String fltNome) {
        this.fltNome = fltNome;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    private void msgOcorrenciaNaoEncontrada() {
        MessageUtils.messageFactoringFull("ocorenciaNaoEncontrada",
                new Object[]{this.codigoOcorrencia},
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgCpfNaoEncontrado() {
        MessageUtils.messageFactoringFull("cpfNaoEncontrado1",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgFuncionarioNaoPermitido() {
        MessageUtils.messageFactoringFull("funcionarioNaoPermitido",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgCadastroNaoCompleto() {
        MessageUtils.messageFactoringFull("cadastroNaoCompleto",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgErroExecOpe() {
        MessageUtils.messageFactoringFull("erroExecutarOperacao",
                null,
                FacesMessage.SEVERITY_ERROR,
                FacesContext.getCurrentInstance());
    }

    private void msgFuncionarioOcorrenciaCreated() {
        MessageUtils.messageFactoringFull("FuncionarioOcorrenciaCreated",
                null,
                FacesMessage.SEVERITY_INFO,
                FacesContext.getCurrentInstance());
    }

    private void msgFuncionarioOcorrenciaUpdated() {
        MessageUtils.messageFactoringFull("FuncionarioOcorrenciaUpdated",
                null,
                FacesMessage.SEVERITY_INFO,
                FacesContext.getCurrentInstance());
    }
    
    private void msgFuncionarioOcorrenciaDeleted() {
        MessageUtils.messageFactoringFull("FuncionarioOcorrenciaDeleted",
                null,
                FacesMessage.SEVERITY_INFO,
                FacesContext.getCurrentInstance());
    }

    private void msgUsuarioSemAutorizacao() {
        MessageUtils.messageFactoringFull("usuarioSemAutorizacao",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgInformeADataInicial() {
        MessageUtils.messageFactoringFull("informeADataInicial",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgFaltaExistente() {
        MessageUtils.messageFactoringFull("faltaExistenteParaEsteCpf",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgOcorrenciaExistente() {
        MessageUtils.messageFactoringFull("ocorrenciaExistenteParaEsteCpf",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgDataIniMaiorDataFim() {
        MessageUtils.messageFactoringFull("dataIniMaiorDataFim",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgFuncionarioNaoEncontrado(String cpf) {
        MessageUtils.messageFactoringFull("funcionarioNaoEncontrado",
                new Object[]{cpf},
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgFuncionarioNaoPertenceCentro() {
        MessageUtils.messageFactoringFull("funcionarioNaoPertenceCentro",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgFuncionarioInativo() {
        MessageUtils.messageFactoringFull("funcionarioInativo",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }

    private void msgFuncionarioInativoMesAtual() {
        MessageUtils.messageFactoringFull("funcionarioInativoMesAtual",
                null,
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }
}
