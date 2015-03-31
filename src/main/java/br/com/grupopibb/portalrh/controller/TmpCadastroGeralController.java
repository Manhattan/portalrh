/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.controller;

import br.com.grupopibb.portalrh.controller.commons.EntityController;
import br.com.grupopibb.portalrh.controller.commons.EntityPagination;
import br.com.grupopibb.portalrh.dao.CentroCustoFacade;
import br.com.grupopibb.portalrh.dao.EmpresaFacade;
import br.com.grupopibb.portalrh.dao.FilialFacade;
import br.com.grupopibb.portalrh.dao.TmpCadastroGeralFacade;
import br.com.grupopibb.portalrh.model.TmpCadastroGeral;
import br.com.grupopibb.portalrh.utils.DateUtils;
import br.com.grupopibb.portalrh.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author administrator
 */
@ManagedBean
@ViewScoped
public class TmpCadastroGeralController extends EntityController<TmpCadastroGeral> implements Serializable {

    @EJB
    private TmpCadastroGeralFacade tmpCadastroGeralFacade;
    @EJB
    private EmpresaFacade empresaFacade;
    @EJB
    private FilialFacade filialFacade;
    @EJB
    private CentroCustoFacade centroFacade;
    private String mesAno;
    private String cpf;
    private String nome;
    private String rg;
    private String nomeMae;
    private String situacao;
    private String empresa;
    private String filial;
    private String centro;
    private String funcao;
    private String matricula;

    @PostConstruct
    public void init() {
        mesAno = DateUtils.getMonthYearBar(new Date());
    }

    public SelectItem[] getEmpresaSelect() {
        return JsfUtil.getSelectItems(empresaFacade.findAll(), false, FacesContext.getCurrentInstance());
    }

    public SelectItem[] getFilialSelect() {
        return JsfUtil.getSelectItems(filialFacade.findByEmpresa(empresa), false, FacesContext.getCurrentInstance());
    }

    public SelectItem[] getCentroSelect() {
        return JsfUtil.getSelectItems(centroFacade.findByEmpresa(empresa, filial), false, FacesContext.getCurrentInstance());
    }

    public Map<String, Object> getMesAnoSelect() {
        Date data = new Date();
        List<String> lista = new ArrayList<String>();
        lista.add(DateUtils.getMonthYearBar(data));
        lista.add(DateUtils.getMonthYearBar(DateUtils.incrementar(data, -1, Calendar.MONTH)));
        data = null;
        return JsfUtil.getMapItems(lista, false, FacesContext.getCurrentInstance());
    }

    public void cleanFilialCentro() {
        filial = null;
        centro = null;
    }

    @Override
    public String clean() {
        cpf = null;
        nome = null;
        rg = null;
        nomeMae = null;
        situacao = null;
        empresa = null;
        filial = null;
        centro = null;
        funcao = null;
        matricula = null;
        recreateTable();
        return JsfUtil.MANTEM;
    }

    @Override
    protected void setEntity(TmpCadastroGeral t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected TmpCadastroGeral getNewEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void performDestroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private TmpCadastroGeralFacade getFacade() {
        return tmpCadastroGeralFacade;
    }

    @Override
    public EntityPagination getPagination() {
        if (pagination == null) {
            pagination = new EntityPagination(15) {
                @Override
                public int getItemsCount() {
                    return getFacade().pesqCount(cpf, nome, rg, nomeMae, situacao, empresa, filial, centro, funcao, matricula, mesAno.replace("/", "")).intValue();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().listPesqParamRange(cpf, nome, rg, nomeMae, situacao, empresa, filial, centro, funcao, matricula, mesAno.replace("/", ""), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public void pesquisar() {
        recreateTable();
        clearCache();
    }

    private void clearCache() {
        getFacade().clearCache();
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

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }
}
