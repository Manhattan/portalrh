/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.business;

import br.com.grupopibb.portalrh.model.Parentesco;
import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class ParentescoBusiness {
    
    public Parentesco getFilho(){
        return new Parentesco("FILHO(A)", "Filho(a)");
    }
    
    public Parentesco getConjuge(){
        return new Parentesco("CONJUGE", "Conjuge");
    }
    
    public Parentesco getEnteado(){
        return new Parentesco("3", "Enteado");
    }
    
    public Parentesco getAvo(){
        return new Parentesco("4", "Avo");
    }
    
    public Parentesco getOutros(){
        return new Parentesco("OUTROS", "Outros");
    }
    
}
