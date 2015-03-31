/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.Filial;
import br.com.grupopibb.portalrh.utils.UtilBeans;
import java.util.List;
import java.util.Map;
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
public class FilialFacade extends AbstractEntityBeans<Filial, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public FilialFacade() {
        super(Filial.class, FilialFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Filial> findByEmpresa(final String empresaCod){
        Map<String, Object> params = getMapParams();
        paramsEmpresa(params, empresaCod);
        return listPesqParam("Filial.findByEmpresa", params);
    }
    
    private void paramsEmpresa(Map<String, Object> params, final String empresaCod){
        params.put("empresaCod", empresaCod);
    }
}
