/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.CadastroGeral;
import br.com.grupopibb.portalrh.utils.StringBeanUtils;
import br.com.grupopibb.portalrh.utils.UtilBeans;
import java.io.Serializable;
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
public class CadastroGeralFacade extends AbstractEntityBeans<CadastroGeral, String> implements Serializable {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public CadastroGeralFacade() {
        super(CadastroGeral.class, CadastroGeralFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<CadastroGeral> listPesqParamRange(final String cpf, final String nome, final String rg, final String nomeMae, final String situacao/*, final String cargo*/, final int[] range) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, cpf, nome, rg, nomeMae, situacao/*, cargo*/);
        return listPesqParamRange("CadastroGeral.selectRange", params, range[1] - range[0], range[0]);
    }

    public Long pesqCount(final String cpf, final String nome, final String rg, final String nomeMae, final String situacao/*, final String cargo*/) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, cpf, nome, rg, nomeMae, situacao/*, cargo*/);
        return pesqCount("CadastroGeral.countRange", params);
    }

    private void paramsPaginacao(Map<String, Object> params, final String cpf, final String nome, final String rg, final String nomeMae, final String situacao/*, final String cargo*/) {
        params.put("cpf", StringBeanUtils.acertaNomeParaLike(cpf, StringBeanUtils.LIKE_END));
        params.put("nome", StringBeanUtils.acertaNomeParaLike(nome, StringBeanUtils.LIKE_MIDDLE));
        params.put("rg", StringBeanUtils.acertaNomeParaLike(rg, StringBeanUtils.LIKE_END));
        params.put("nomeMae", StringBeanUtils.acertaNomeParaLike(nomeMae, StringBeanUtils.LIKE_MIDDLE));
        params.put("situacao", situacao);
        params.put("cpf2", StringUtils.isBlank(cpf) ? "todos" : "filtro");
        params.put("nome2", StringUtils.isBlank(nome) ? "todos" : "filtro");
        params.put("rg2", StringUtils.isBlank(rg) ? "todos" : "filtro");
        params.put("nomeMae2", StringUtils.isBlank(nomeMae) ? "todos" : "filtro");
        params.put("situacao2", StringUtils.isBlank(situacao) ? "todos" : "filtro");
        //params.put("cargo", StringBeanUtils.acertaNomeParaLike(cargo, StringBeanUtils.LIKE_MIDDLE));
        //params.put("cargo2", StringUtils.isBlank(cargo) ? "todos" : "filtro");
    }
}
