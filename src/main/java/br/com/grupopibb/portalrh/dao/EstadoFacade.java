/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.Estado;
import br.com.grupopibb.portalrh.model.MunicipioFolha;
import br.com.grupopibb.portalrh.types.EnumPais;
import br.com.grupopibb.portalrh.utils.UtilBeans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class EstadoFacade extends AbstractEntityBeans<Estado, Serializable> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public EstadoFacade() {
        super(Estado.class, EstadoFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<String> findListUf(){
        List<Estado> estadoList = findAll();
        List<String> stringList = new ArrayList<String>();
        for (Estado est : estadoList){
            stringList.add(est.getUf());
        }
        return stringList;
    }

    public Estado findByPaisUf(final String paisUf) {
        Map<String, Object> params = getMapParams();
        paramsPaisUf(params, paisUf);
        return pesqParam("Estado.findByPaisUf", params);
    }

    private void paramsPaisUf(Map<String, Object> params, final String paisUf) {
        EnumPais pais = EnumPais.getForLabel(StringUtils.left(paisUf, 2));
        params.put("pais", pais);
        params.put("uf", StringUtils.right(paisUf, 2));
    }

    public Estado findByMunicipio(final MunicipioFolha municipio) {
        if (municipio != null) {
            return findByPaisUf(EnumPais.BR.name() + municipio.getUf());
        }
        return null;
    }
}
