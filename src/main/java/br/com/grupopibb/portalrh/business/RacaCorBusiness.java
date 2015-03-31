/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.business;

import br.com.grupopibb.portalrh.model.RacaCor;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class RacaCorBusiness {
    
    public RacaCor getIndigena(){
        return new RacaCor(1, "Indígena");
    }
    
    public RacaCor getBranca(){
        return new RacaCor(2, "Branca");
    }
    
    public RacaCor getPreta(){
        return new RacaCor(4, "Preta");
    }
    
    public RacaCor getAmarela(){
        return new RacaCor(6, "Amarela");
    }
    
    public RacaCor getParda(){
        return new RacaCor(8, "Parda");
    }
    
    public RacaCor getNI(){
        return new RacaCor(9, "Ñ Informado");
    }
    
}
