/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.business;

import br.com.grupopibb.portalrh.model.EstadoCivil;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class EstadoCivilBusiness {
    
    public EstadoCivil getSolteiro(){
        return new EstadoCivil("0", "Solteiro");
    }
    
    public EstadoCivil getCasado(){
        return new EstadoCivil("1", "Casado");
    }
    
    public EstadoCivil getDesquitado(){
        return new EstadoCivil("2", "Desquitado");
    }

    public EstadoCivil getDivorciado(){
        return new EstadoCivil("3", "Divorciado");
    }
    
    public EstadoCivil getViuvo(){
        return new EstadoCivil("4", "Viúvo");
    }
}
