/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import static br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans.getMapParams;
import br.com.grupopibb.portalrh.model.TmpCadastroGeral;
import br.com.grupopibb.portalrh.utils.DateUtils;
import br.com.grupopibb.portalrh.utils.StringBeanUtils;
import br.com.grupopibb.portalrh.utils.UtilBeans;
import java.io.Serializable;
import java.util.Date;
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
public class TmpCadastroGeralFacade extends AbstractEntityBeans<TmpCadastroGeral, Serializable> implements Serializable {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public TmpCadastroGeralFacade() {
        super(TmpCadastroGeral.class, TmpCadastroGeralFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<TmpCadastroGeral> findByCpf(final String cpf) {
        Map<String, Object> params = getMapParams();
        paramsCpf(params, cpf);
        return listPesqParam("TmpCadastroGeral.findByCpf", params);
    }

    public List<TmpCadastroGeral> listPesqParamRange(final String cpf, final String nome, final String rg, final String nomeMae, final String situacao, final String empresa, final String filial, final String centro, final String funcao, final String matricula, final String mesAno, final int[] range) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, cpf, nome, rg, nomeMae, situacao, empresa, filial, centro, funcao, matricula, mesAno);
        return listPesqParamRange("TmpCadastroGeral.selectRange", params, range[1] - range[0], range[0]);
    }

    public Long pesqCount(final String cpf, final String nome, final String rg, final String nomeMae, final String situacao, final String empresa, final String filial, final String centro, final String funcao, final String matricula, final String mesAno) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, cpf, nome, rg, nomeMae, situacao, empresa, filial, centro, funcao, matricula, mesAno);
        return pesqCount("TmpCadastroGeral.countRange", params);
    }

    private void paramsPaginacao(Map<String, Object> params, final String cpf, final String nome, final String rg, final String nomeMae, final String situacao, final String empresa, final String filial, final String centro, final String funcao, final String matricula, final String mesAno) {
        params.put("cpf", StringBeanUtils.acertaNomeParaLike(cpf, StringBeanUtils.LIKE_END));
        params.put("nome", StringBeanUtils.acertaNomeParaLike(nome, StringBeanUtils.LIKE_MIDDLE));
        params.put("rg", StringBeanUtils.acertaNomeParaLike(rg, StringBeanUtils.LIKE_END));
        params.put("nomeMae", StringBeanUtils.acertaNomeParaLike(nomeMae, StringBeanUtils.LIKE_MIDDLE));
        params.put("situacao", situacao);
        params.put("empresa", empresa);
        params.put("filial", filial);
        params.put("centro", centro);
        params.put("funcao", StringBeanUtils.acertaNomeParaLike(funcao, StringBeanUtils.LIKE_MIDDLE));
        params.put("matricula", StringBeanUtils.acertaNomeParaLike(matricula, StringBeanUtils.LIKE_MIDDLE));

        params.put("cpf2", StringUtils.isBlank(cpf) ? "todos" : "filtro");
        params.put("nome2", StringUtils.isBlank(nome) ? "todos" : "filtro");
        params.put("rg2", StringUtils.isBlank(rg) ? "todos" : "filtro");
        params.put("nomeMae2", StringUtils.isBlank(nomeMae) ? "todos" : "filtro");
        params.put("situacao2", StringUtils.isBlank(situacao) ? "todos" : "filtro");
        params.put("empresa2", StringUtils.isBlank(empresa) ? "todos" : "filtro");
        params.put("filial2", StringUtils.isBlank(filial) ? "todos" : "filtro");
        params.put("centro2", StringUtils.isBlank(centro) ? "todos" : "filtro");
        params.put("funcao2", StringUtils.isBlank(funcao) ? "todos" : "filtro");
        params.put("matricula2", StringUtils.isBlank(matricula) ? "todos" : "filtro");

        params.put("ano", mesAno.substring(2, 6));
        params.put("mes", mesAno.substring(0, 2));
    }

    private void paramsCpf(Map<String, Object> params, final String cpf) {
        params.put("cpf", cpf);
    }
    
    public void clearCache(){
        em.flush();
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
    }
}
