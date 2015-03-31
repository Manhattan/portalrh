package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.CentroCusto;
import br.com.grupopibb.portalrh.model.Funcionario;
import br.com.grupopibb.portalrh.model.FuncionarioCentro;
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
public class FuncionarioCentroFacade extends AbstractEntityBeans<FuncionarioCentro, Integer> {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public FuncionarioCentroFacade() {
        super(FuncionarioCentro.class, FuncionarioCentroFacade.class);
    }
    
    public FuncionarioCentro find(final Funcionario funcionario, final CentroCusto centro){
        Map<String,Object> params = getMapParams();
        paramsFiltro(params, funcionario, centro);
        return pesqParam("FuncionarioCentro.find", params);
    }
    
    private void paramsFiltro(Map<String,Object> params, final Funcionario funcionario, final CentroCusto centro){
        params.put("funcionario", funcionario);
        params.put("centro", centro);
    }
}
