/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.business;

import br.com.grupopibb.portalrh.model.Deficiencia;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class DeficienciaBusiness {
    
    public Deficiencia getFisica(){
        return new Deficiencia(1, "Física");
    }
    
    public Deficiencia getAuditiva(){
        return new Deficiencia(2, "Auditiva");
    }
    
    public Deficiencia getVisual(){
        return new Deficiencia(3, "Visual");
    }
    
    public Deficiencia getMental(){
        return new Deficiencia(4, "Mental");
    }
    
    public Deficiencia getMultipla(){
        return new Deficiencia(5, "Múltipla");
    }
    
    public Deficiencia getReabilitado(){
        return new Deficiencia(6, "Reabilitado");
    }
}
