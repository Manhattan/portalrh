/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.controller;

import br.com.grupopibb.portalrh.business.EstadoCivilBusiness;
import br.com.grupopibb.portalrh.model.EstadoCivil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author administrator
 */
@ManagedBean
@RequestScoped
public class EstadoCivilController implements Serializable {
    @EJB
    private EstadoCivilBusiness estadoCivilBusiness;
    
     public EstadoCivil getSolteiro(){
        return estadoCivilBusiness.getSolteiro();
    }
    
    public EstadoCivil getCasado(){
        return estadoCivilBusiness.getCasado();
    }
    
    public EstadoCivil getDesquitado(){
        return estadoCivilBusiness.getDesquitado();
    }

    public EstadoCivil getDivorciado(){
        return estadoCivilBusiness.getDivorciado();
    }
    
    public EstadoCivil getViuvo(){
        return estadoCivilBusiness.getViuvo();
    }
}
