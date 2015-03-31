/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.CentroCusto;
import br.com.grupopibb.portalrh.model.Contrato;
import br.com.grupopibb.portalrh.utils.UtilBeans;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class ContratoFacade extends AbstractEntityBeans<Contrato, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public ContratoFacade() {
        super(Contrato.class, ContratoFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Contrato findByCentroECpf(final CentroCusto centro, final String cpf, final String mes) {
        Map<String, Object> params = getMapParams();
        paramsCentro(params, centro, cpf, mes);
        List<Contrato> contratos = listPesqParam("Contrato.findByCentroECpf", params); 
        if (contratos != null && !contratos.isEmpty()){
            return contratos.get(0);
        }
        return null;
    }

    public List<String> findCpfByCentro(final CentroCusto centro, final String mes) {
        Map<String, Object> params = getMapParams();
        paramsAllCpf(params, centro, mes);
        Query q = getEntityManager().createNamedQuery("Contrato.findCpfByCentro");
        for (String chave : params.keySet()) {
            q.setParameter(chave, params.get(chave));
        }
        Object[] listObj = (Object[]) q.getResultList().toArray();
        List<String> listStr = new ArrayList<String>();
        for (Object obj : listObj) {
            if (obj instanceof String) {
                listStr.add(obj.toString());
            }
        }
        return listStr;
    }

    private void paramsCentro(Map<String, Object> params, final CentroCusto centro, final String cpf, final String mes) {
        params.put("empresaCod", centro.getEmpresa());
        params.put("filialCod", centro.getFilial());
        params.put("centroCod", centro.getCodigo());
        params.put("cpf", cpf);
        params.put("mes", mes);
    }

    private void paramsAllCpf(Map<String, Object> params, final CentroCusto centro, final String mes) {
        params.put("empresaCod", centro.getEmpresa());
        params.put("filialCod", centro.getFilial());
        params.put("centroCod", centro.getCodigo());
        params.put("mes", mes);
    }
}
