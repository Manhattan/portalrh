/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.controller;

import br.com.grupopibb.portalrh.business.DeficienciaBusiness;
import br.com.grupopibb.portalrh.model.Deficiencia;
import br.com.grupopibb.portalrh.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author administrator
 */
@ManagedBean
@RequestScoped
public class DeficienciaController implements Serializable {
    
    @EJB
    private DeficienciaBusiness deficienciaBusiness;

    public Deficiencia getFisica() {
        return deficienciaBusiness.getFisica();
    }

    public Deficiencia getAuditiva() {
        return deficienciaBusiness.getAuditiva();
    }

    public Deficiencia getVisual() {
        return deficienciaBusiness.getVisual();
    }

    public Deficiencia getMental() {
        return deficienciaBusiness.getMental();
    }

    public Deficiencia getMultipla() {
        return deficienciaBusiness.getMultipla();
    }

    public Deficiencia getReabilitado() {
        return deficienciaBusiness.getReabilitado();
    }

    public SelectItem[] getDeficienciaSelect() {
        List<Deficiencia> deficienciaList = new ArrayList<Deficiencia>();
        deficienciaList.add(getFisica());
        deficienciaList.add(getAuditiva());
        deficienciaList.add(getVisual());
        deficienciaList.add(getMental());
        deficienciaList.add(getMultipla());
        deficienciaList.add(getReabilitado());
        return JsfUtil.getSelectItemsComparable(deficienciaList, false, FacesContext.getCurrentInstance());
    }
    
    public Map<String, Object> getDeficienciaMap() {
        Map<String, Object> deficienciaList = new HashMap<String, Object>();
        deficienciaList.put(getFisica().getNome(), getFisica().getCodigo());
        deficienciaList.put(getAuditiva().getNome(), getAuditiva().getCodigo());
        deficienciaList.put(getVisual().getNome(), getVisual().getCodigo());
        deficienciaList.put(getMental().getNome(), getMental().getCodigo());
        deficienciaList.put(getMultipla().getNome(), getMultipla().getCodigo());
        deficienciaList.put(getReabilitado().getNome(), getReabilitado().getCodigo());
        return deficienciaList;
    }
    
}
