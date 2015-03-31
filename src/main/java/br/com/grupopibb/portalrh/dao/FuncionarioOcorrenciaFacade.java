/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.dao;

import br.com.grupopibb.portalrh.dao.commons.AbstractEntityBeans;
import br.com.grupopibb.portalrh.model.CadastroGeral;
import br.com.grupopibb.portalrh.model.CentroCusto;
import br.com.grupopibb.portalrh.model.FuncionarioOcorrencia;
import br.com.grupopibb.portalrh.utils.DateUtils;
import br.com.grupopibb.portalrh.utils.StringBeanUtils;
import br.com.grupopibb.portalrh.utils.UtilBeans;
import java.util.Calendar;
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
public class FuncionarioOcorrenciaFacade extends AbstractEntityBeans<FuncionarioOcorrencia, Integer> {

    @PersistenceContext(unitName = UtilBeans.PERSISTENCE_UNIT)
    private EntityManager em;

    public FuncionarioOcorrenciaFacade() {
        super(FuncionarioOcorrencia.class, FuncionarioOcorrenciaFacade.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<FuncionarioOcorrencia> listPesqParamRange(final Integer fltCod, final Integer fltCodOcor, final String fltDescOcor, final Date fltDtLancto, final Date fltDtIni, final Date fltDtFim, final String fltDetalha, final String fltCpf, final String fltNome, List<String> contCpf, final int[] range) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, fltCod, fltCodOcor, fltDescOcor, fltDtLancto, fltDtIni, fltDtFim, fltDetalha, fltCpf, fltNome, contCpf);
        return listPesqParamRange("FuncionarioOcorrencia.selectRange", params, range[1] - range[0], range[0]);
    }

    public FuncionarioOcorrencia findFalta(final Date data, final CadastroGeral cadGer) {
        Map<String, Object> params = getMapParams();
        paramsFalta(params, data, cadGer);
        return pesqParam("FuncionarioOcorrencia.findFalta", params);
    }

    public List<FuncionarioOcorrencia> findOcorrencia(final Date data, final CadastroGeral cadGer) {
        Map<String, Object> params = getMapParams();
        paramsOcorrencia(params, data, cadGer);
        return listPesqParam("FuncionarioOcorrencia.findOcorrencia", params);
    }

    public boolean isOcorrenciaInPeriodo(final Date dataIni, final Date dataFim, final CadastroGeral cadGer) {
        int days = (int) DateUtils.daysBetween(dataIni, dataFim) + 1;
        Date dataRef = DateUtils.incrementar(dataIni, -1, Calendar.DATE);
        for (int i = 1; i <= days; i++) {
            if (!findOcorrencia(DateUtils.incrementar(dataRef, 1, Calendar.DATE), cadGer).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public Long pesqCount(final Integer fltCod, final Integer fltCodOcor, final String fltDescOcor, final Date fltDtLancto, final Date fltDtIni, final Date fltDtFim, final String fltDetalha, final String fltCpf, final String fltNome, List<String> contCpf) {
        Map<String, Object> params = getMapParams();
        paramsPaginacao(params, fltCod, fltCodOcor, fltDescOcor, fltDtLancto, fltDtIni, fltDtFim, fltDetalha, fltCpf, fltNome, contCpf);
        return pesqCount("FuncionarioOcorrencia.countRange", params);
    }

    private void paramsFalta(Map<String, Object> params, final Date data, final CadastroGeral cadGer) {
        params.put("data", data);
        params.put("cpf", cadGer != null ? cadGer.getCpf() : "0");
    }

    private void paramsOcorrencia(Map<String, Object> params, final Date data, final CadastroGeral cadGer) {
        params.put("data", data);
        params.put("cpf", cadGer != null ? cadGer.getCpf() : "0");
    }

    private void paramsPaginacao(Map<String, Object> params, final Integer fltCod, final Integer fltCodOcor, final String fltDescOcor, final Date fltDtLancto, final Date fltDtIni, final Date fltDtFim, final String fltDetalha, final String fltCpf, final String fltNome, List<String> contCpf) {
        params.put("codigo", fltCod);
        params.put("codOcor", fltCodOcor);
        params.put("descOcor", StringBeanUtils.acertaNomeParaLike(fltDescOcor, StringBeanUtils.LIKE_MIDDLE));
        params.put("dtLancto", fltDtLancto);
        params.put("dtIni", fltDtIni);
        params.put("dtFim", fltDtFim);
        params.put("detalha", StringBeanUtils.acertaNomeParaLike(fltDetalha, StringBeanUtils.LIKE_MIDDLE));
        params.put("cpf", StringBeanUtils.acertaNomeParaLike(fltCpf, StringBeanUtils.LIKE_END));
        params.put("nome", StringBeanUtils.acertaNomeParaLike(fltNome, StringBeanUtils.LIKE_MIDDLE));
        /*    if (contCpf == null || contCpf.isEmpty()) { 
         contCpf = new ArrayList<String>();
         contCpf.add("NE");
         }*/
        //  params.put("contCpf", contCpf);
        params.put("codigo2", fltCod == null ? "todos" : "filtro");
        params.put("codOcor2", fltCodOcor == null ? "todos" : "filtro");
        params.put("descOcor2", StringUtils.isBlank(fltDescOcor) ? "todos" : "filtro");
        params.put("dtLancto2", fltDtLancto == null ? "todos" : "filtro");
        params.put("dtIni2", fltDtIni == null ? "todos" : "filtro");
        params.put("dtFim2", fltDtFim == null ? "todos" : "filtro");
        params.put("detalha2", StringUtils.isBlank(fltDetalha) ? "todos" : "filtro");
        params.put("cpf2", StringUtils.isBlank(fltCpf) ? "todos" : "filtro");
        params.put("nome2", StringUtils.isBlank(fltNome) ? "todos" : "filtro");
    }
}
