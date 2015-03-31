/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.RacaCor;
import br.com.grupopibb.portalrh.utils.UtilBeans;
import java.io.Serializable;
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
public class RacaCorFacade extends AbstractEntityBeans<RacaCor, Integer> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public RacaCorFacade() {
        super(RacaCor.class, RacaCorFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
