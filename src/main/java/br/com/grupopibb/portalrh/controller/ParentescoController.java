/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.controller;

import br.com.grupopibb.portalrh.business.ParentescoBusiness;
import br.com.grupopibb.portalrh.model.Parentesco;
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
public class ParentescoController implements Serializable {
    @EJB
    private ParentescoBusiness parentescoBusiness;
    
    public Parentesco getFilho(){
        return parentescoBusiness.getFilho();
    }
    
    public Parentesco getConjuge(){
        return parentescoBusiness.getConjuge();
    }
    
    public Parentesco getEnteado(){
        return parentescoBusiness.getEnteado();
    }
    
    public Parentesco getAvo(){
        return parentescoBusiness.getAvo();
    }
    
    public Parentesco getOutros(){
        return parentescoBusiness.getOutros();
    }
}
