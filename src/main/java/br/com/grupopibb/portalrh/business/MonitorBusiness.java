package br.com.grupopibb.portalrh.business;

import br.com.grupopibb.portalrh.model.Funcionario;
import br.com.grupopibb.portalrh.model.Monitor;
import br.com.grupopibb.portalrh.types.EnumSistemaMonitor;
import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class MonitorBusiness {

    /**
     * Define um novo registro de acesso para monitoramento.
     * @param funcionario Funcionario logado.
     * @return Novo Monitor com data e hora atuais. 
     */
    public Monitor getNewMonitor(Funcionario funcionario) {
        if (funcionario == null){
            return null;
        }
        String host = null;
        try {
            host = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
        } catch (NullPointerException e){
            host = "unknown";
        } catch(ClassCastException ex){
            host = "unknown";
        }
        if (host == null) {
            host = "unknown";
        }
        return new Monitor(funcionario, new Date(), EnumSistemaMonitor.PRH, host);
    }
}
