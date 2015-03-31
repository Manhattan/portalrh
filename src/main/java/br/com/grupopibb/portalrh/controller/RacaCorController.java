/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.controller;

import br.com.grupopibb.portalrh.business.RacaCorBusiness;
import br.com.grupopibb.portalrh.model.RacaCor;
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
public class RacaCorController implements Serializable{
    
   @EJB
   private RacaCorBusiness racaCorBusiness;
    
    public RacaCor getIndigena(){
        return racaCorBusiness.getIndigena();
    }
    
    public RacaCor getBranca(){
        return racaCorBusiness.getBranca();
    }
    
    public RacaCor getPreta(){
        return racaCorBusiness.getPreta();
    }
    
    public RacaCor getAmarela(){
        return racaCorBusiness.getAmarela();
    }
    
    public RacaCor getParda(){
        return racaCorBusiness.getParda();
    }
    
    public RacaCor getNI(){
        return racaCorBusiness.getNI();
    }
}
