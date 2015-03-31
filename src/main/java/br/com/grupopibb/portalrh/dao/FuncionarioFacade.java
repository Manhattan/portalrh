package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.Funcionario;
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
public class FuncionarioFacade extends AbstractEntityBeans<Funcionario, Integer> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FuncionarioFacade() {
        super(Funcionario.class, FuncionarioFacade.class);
    }

    public Funcionario findByLogin(final String login) {
        Map<String, Object> params = getMapParams();
        paramsLogin(params, login);
        return pesqParam("Funcionario.findByLogin", params);
    }

    private void paramsLogin(Map<String, Object> params, final String login) {
        params.put("login", login);
    }
}
