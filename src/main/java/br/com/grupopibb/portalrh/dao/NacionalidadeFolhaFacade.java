/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.NacionalidadeFolha;
import br.com.grupopibb.portalrh.utils.UtilBeans;
import java.util.List;
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
public class NacionalidadeFolhaFacade extends AbstractEntityBeans<NacionalidadeFolha, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public NacionalidadeFolhaFacade() {
        super(NacionalidadeFolha.class, NacionalidadeFolhaFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<NacionalidadeFolha> findAll(){
        return listPesq("NacionalidadeFolha.selectAll");
    }
}
