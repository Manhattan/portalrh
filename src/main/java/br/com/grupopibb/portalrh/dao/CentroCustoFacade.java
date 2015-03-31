/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.CentroCusto;
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
public class CentroCustoFacade extends AbstractEntityBeans<CentroCusto, String>{

    
    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public CentroCustoFacade() {
        super(CentroCusto.class, CentroCustoFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<CentroCusto> findByEmpresa(final String empresaCod, final String filialCod){
        Map<String, Object> params = getMapParams();
        paramsFilial(params, empresaCod, filialCod);
        return listPesqParam("CentroCusto.findByFilial", params);
    }
    
    private void paramsFilial(Map<String, Object> params, final String empresaCod, final String filialCod){
        params.put("empresaCod", empresaCod);
        params.put("filialCod", filialCod);
    }
}
