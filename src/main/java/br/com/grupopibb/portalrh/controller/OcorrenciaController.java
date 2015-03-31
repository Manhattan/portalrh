/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.controller;

import br.com.grupopibb.portalrh.controller.commons.EntityController;
import br.com.grupopibb.portalrh.controller.commons.EntityPagination;
import br.com.grupopibb.portalrh.dao.OcorrenciaFacade;
import br.com.grupopibb.portalrh.model.Ocorrencia;
import br.com.grupopibb.portalrh.utils.JsfUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author administrator
 */
@ManagedBean
@ViewScoped
public class OcorrenciaController extends EntityController<Ocorrencia> implements Serializable {

    @EJB
    private OcorrenciaFacade ocorrenciaFacade;
    private Ocorrencia current;
    private Integer codigo;
    private String descricao;
    private String sefip;
    private String rais;

    @Override
    public String clean() {
        this.codigo = null;
        this.descricao = null;
        this.sefip = null;
        this.rais = null;
        recreateTable();
        return super.clean();
    }

    @Override
    protected void setEntity(Ocorrencia t) {
        this.current = t;
    }

    @Override
    protected Ocorrencia getNewEntity() {
        return new Ocorrencia();
    }

    @Override
    protected void performDestroy() {
    }

    private OcorrenciaFacade getFacade() {
        return ocorrenciaFacade;
    }

    @Override
    protected String create() {
        return JsfUtil.MANTEM;
    }

    @Override
    protected String update() {
        return JsfUtil.MANTEM;
    }

    @Override
    public EntityPagination getPagination() {
        if (pagination == null) {
            pagination = new EntityPagination(15) {
                @Override
                public int getItemsCount() {
                    return getFacade().pesqCount(codigo, descricao, sefip, rais).intValue();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().listPesqParamRange(codigo, descricao, sefip, rais, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public void pesquisar() {
        recreateTable();
    }

    public Ocorrencia getCurrent() {
        return current;
    }

    public void setCurrent(Ocorrencia current) {
        this.current = current;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSefip() {
        return sefip;
    }

    public void setSefip(String sefip) {
        this.sefip = sefip;
    }

    public String getRais() {
        return rais;
    }

    public void setRais(String rais) {
        this.rais = rais;
    }
}
