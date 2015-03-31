/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.business;

import br.com.grupopibb.portalrh.model.Sexo;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class SexoBusiness {
    
    
    public Sexo getMasculino(){
        return new Sexo("1", "Masculino");
    }
    
    public Sexo getFeminino(){
        return new Sexo("2", "Feminino");
    }
}
