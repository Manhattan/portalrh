/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.MunicipioFolha;
import br.com.grupopibb.portalrh.utils.UtilBeans;
import java.util.ArrayList;
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
public class MunicipioFolhaFacade extends AbstractEntityBeans<MunicipioFolha, String> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public MunicipioFolhaFacade() {
        super(MunicipioFolha.class, MunicipioFolhaFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<MunicipioFolha> findAll() {
        return listPesq("MunicipioFolha.selectAll");
    }
    
    public List<String> findAllUf(){
        List<MunicipioFolha> lista = listPesq("MunicipioFolha.selectAllUf");
        Object[] lo = lista.toArray();
        List<String> ufList = new ArrayList<String>();
        for (Object obj : lo){
            if (obj instanceof String){
                ufList.add(obj.toString());
            }
        } 
        return ufList;
    }
 
    public List<MunicipioFolha> listPesqParam(final String uf) {
        if (uf != null) {
            Map<String, Object> params = getMapParams();
            paramsUf(params, uf);
            return listPesqParam("MunicipioFolha.findByUf", params);
        }
        return new ArrayList<MunicipioFolha>();
    }
    
    private void paramsUf(Map<String, Object> params, final String uf) {
        params.put("uf", uf); 
    }
}
