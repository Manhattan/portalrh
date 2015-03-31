/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.Parametros;
import br.com.grupopibb.portalrh.utils.UtilBeans;
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
public class ParametrosFacade extends AbstractEntityBeans<Parametros, String> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public ParametrosFacade() {
        super(Parametros.class, ParametrosFacade.class);
    }

    public Parametros find(final String mes, final String ano, final String codFolha) {
        Map<String, Object> params = getMapParams();
        paramsFiltro(params, mes, ano, codFolha);
        return pesqParam("Parametros.find", params);
    }

    private void paramsFiltro(Map<String, Object> params, final String mes, final String ano, final String codFolha) {
        params.put("mes", mes);
        params.put("ano", ano);
        params.put("codFolha", codFolha);
    }
}
