/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.Ocorrencia;
import br.com.grupopibb.portalrh.utils.StringBeanUtils;
import br.com.grupopibb.portalrh.utils.UtilBeans;
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
public class OcorrenciaFacade extends AbstractEntityBeans<Ocorrencia, Integer> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public OcorrenciaFacade() {
        super(Ocorrencia.class, OcorrenciaFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Ocorrencia> listPesqParamRange(final Integer codigo, final String descricao, final String sefip, final String rais, final int[] range) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, codigo, descricao, sefip, rais);
        return listPesqParamRange("Ocorrencia.selectRange", params, range[1] - range[0], range[0]);
    }

    public Long pesqCount(final Integer codigo, final String descricao, final String sefip, final String rais) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, codigo, descricao, sefip, rais);
        return pesqCount("Ocorrencia.countRange", params);
    }

    private void paramsPaginacao(Map<String, Object> params, final Integer codigo, final String descricao, final String sefip, final String rais) {
        params.put("codigo", codigo);
        params.put("descricao", StringBeanUtils.acertaNomeParaLike(descricao, StringBeanUtils.LIKE_MIDDLE));
        params.put("sefip", StringBeanUtils.acertaNomeParaLike(sefip, StringBeanUtils.LIKE_MIDDLE));
        params.put("rais", StringBeanUtils.acertaNomeParaLike(rais, StringBeanUtils.LIKE_MIDDLE));
        params.put("codigo2", codigo == null ? "todos" : "filtro");
        params.put("descricao2", StringUtils.isBlank(descricao) ? "todos" : "filtro");
        params.put("sefip2", StringUtils.isBlank(sefip) ? "todos" : "filtro");
        params.put("rais2", StringUtils.isBlank(rais) ? "todos" : "filtro");

    }
}
