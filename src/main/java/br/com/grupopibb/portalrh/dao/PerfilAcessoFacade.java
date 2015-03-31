/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.PerfilAcesso;
import br.com.grupopibb.portalrh.utils.UtilBeans;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class PerfilAcessoFacade extends AbstractEntityBeans<PerfilAcesso, Integer> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public PerfilAcessoFacade() {
        super(PerfilAcesso.class, PerfilAcessoFacade.class);
    }
}
