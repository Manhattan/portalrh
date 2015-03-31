/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.controller;

import br.com.grupopibb.portalrh.business.SexoBusiness;
import br.com.grupopibb.portalrh.model.Sexo;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author administrator
 */
@ManagedBean
@RequestScoped
public class SexoController {

    @EJB
    private SexoBusiness sexoBusiness;

    public Sexo getMasculino() {
        return sexoBusiness.getMasculino();
    }

    public Sexo getFeminino() {
        return sexoBusiness.getFeminino();
    }
}
