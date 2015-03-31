/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.grupopibb.portalrh.business.MonitorBusiness;
import br.com.grupopibb.portalrh.business.MonitorScheduler;
import br.com.grupopibb.portalrh.business.MonitorTask;
import br.com.grupopibb.portalrh.business.ValidaLogin;
import br.com.grupopibb.portalrh.dao.FuncionarioCentroFacade;
import br.com.grupopibb.portalrh.dao.FuncionarioFacade;
import br.com.grupopibb.portalrh.dao.MonitorFacade;
import br.com.grupopibb.portalrh.exceptions.EntityException;
import br.com.grupopibb.portalrh.model.CentroCusto;
import br.com.grupopibb.portalrh.model.Funcionario;
import br.com.grupopibb.portalrh.model.Monitor;
import br.com.grupopibb.portalrh.model.PerfilAcesso;
import br.com.grupopibb.portalrh.types.EnumHabilitado;
import br.com.grupopibb.portalrh.types.StatusLogin;
import br.com.grupopibb.portalrh.utils.JsfUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author administrator
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    FuncionarioFacade funcionarioFacade;
    @EJB
    FuncionarioCentroFacade funcionarioCentroFacade;
    @EJB
    ValidaLogin validaLogin;
    @EJB
    private MonitorFacade monitorFacade;
    @EJB
    private MonitorBusiness monitorBusiness;
    @EJB
    private MonitorScheduler monitorScheduler;
    private String login = "";
    private String senha = "";
    private String erroLogin = "Erro ao efetuar Login.";
    private Integer statusLogin = 0;
    private CentroCusto centroSelecionado;
    private Funcionario funcionario;
    private PerfilAcesso perfil;
    private Monitor monitor;
    private MonitorTask monitorTask;
    //------------------------------------------------------
    private final String LOGIN_SENHA_INVALIDOS = "Login e/ou senha inválidos.";
    private final String USUARIO_DESATIVADO = "Usuário desativado.";
    private final String USUARIO_SEM_CENTRO_VINCULADO = "Usuário sem centro(s) de custo vinculado(s).";
    private final String ACESSO_NEGADO = "Acesso negado.";

    @PostConstruct
    public void init() {
    }

    @PreDestroy
    public void end() {
        cleanAll();
    }

    /**
     * Fecha a sessão atual, encaminhando para a página de Login.
     *
     * @return Página de login.
     */
    public String logout() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        session.invalidate();
        return JsfUtil.LOGIN_PAGE;
    }

    private boolean isLoggedUser() {
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            if (((LoginController) sessionMap.get("loginController")).getFuncionario() != null) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void closeLoggedUser() {
        updateUserExitDate();
        cancelMonitorTask();
        funcionario = null;
    }

    private void cancelMonitorTask() {
        if (monitorTask != null) {
            monitorTask.cancel();
            monitorTask = null;
        }
    }

    private void updateUserExitDate() {
        if (monitor != null) {
            monitor.setDataSaida(new Date());
            try {
                monitorFacade.update(this.monitor);
            } catch (EntityException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Desconecta o funcionário atual e retorna para a página de login.
     *
     * @return Página de login.
     */
    public String cleanAll() {
        login = null;
        senha = null;
        statusLogin = null;
        centroSelecionado = null;
        perfil = null;
        closeLoggedUser();
        return JsfUtil.LOGIN_PAGE;
    }

    /**
     * Desconecta o funcionário atual e retorna para a página de login.
     *
     * @return Página de login.
     */
    public String logOut() {
        statusLogin = 0;
        centroSelecionado = null;
        funcionario = null;
        perfil = null;
        return JsfUtil.LOGIN_PAGE;
    }

    public Funcionario getPerformLogin() {
        if (isLoggedUser()) {
            closeLoggedUser();
        }
        int result = validaLogin.performLogin(login, senha);
        if (result == StatusLogin.ATIVO) {
            funcionario = funcionarioFacade.findByLogin(login);
            if (!funcionario.isAtivo()) {
                statusLogin = StatusLogin.INATIVO;
                funcionario = null;
                erroLogin = USUARIO_DESATIVADO;
                return null;
            }

            statusLogin = StatusLogin.ATIVO;

            if (funcionario == null) {
                statusLogin = StatusLogin.INATIVO;
                erroLogin = LOGIN_SENHA_INVALIDOS;
                return null;
            }

            if (centroSelecionado == null || centroSelecionado.getCodigo() == null) {
                if (funcionario.getCentros() == null || funcionario.getCentros().isEmpty()) {
                    statusLogin = StatusLogin.INATIVO;
                    erroLogin = USUARIO_SEM_CENTRO_VINCULADO;
                    return null;
                }
                centroSelecionado = funcionario.getCentros().get(0);
            }

            funcionario.setSenha(senha);
            this.perfil = funcionarioCentroFacade.find(funcionario, centroSelecionado).getPerfil();
            //Cria o registro de acesso para monitoramento e controle de acessos ao sistema.
            this.monitor = monitorBusiness.getNewMonitor(funcionario);
            try {
                monitorFacade.create(monitor);
            } catch (EntityException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            cancelMonitorTask();
            monitorTask = new MonitorTask(this.monitor, monitorFacade, funcionario);
            monitorScheduler.init(this.monitorTask);
            monitorFacade.atualizaMonitor();
            return funcionario;
        } else if (result == StatusLogin.INATIVO) {
            statusLogin = StatusLogin.INATIVO;
            erroLogin = LOGIN_SENHA_INVALIDOS;
            return null;
        }
        statusLogin = StatusLogin.INATIVO;
        erroLogin = ACESSO_NEGADO;
        return null;
    }

    public String mudaCentro(CentroCusto c, String destino) {
        this.centroSelecionado = c;
        this.perfil = funcionarioCentroFacade.find(funcionario, centroSelecionado).getPerfil();
        return destino;
    }

    /**
     * Define se o usuário tem permissão para incluir nova soliciação de compra.
     *
     * @return
     */
    public boolean isIncluiSolicitacao() {
        if (perfil != null && perfil.getIncluiSolicitacao() == EnumHabilitado.S) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Define se o usuário tem permissão para concluir/desconcluir soliciação de
     * compra.
     *
     * @return
     */
    public boolean isConcluiSolicitacao() {
        if (perfil != null && perfil.getConcluiSolicitacao() == EnumHabilitado.S) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRemoveSolicitacao() {
        if (perfil != null && perfil.getRemoveSolicitacao() == EnumHabilitado.S) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se o usuário tem permissão para inclusão e conclusão da
     * solicitação de compra.
     *
     * @return Verdadeiro se tiver acesso a incluir e concluir.
     */
    public boolean isIncluiConcluiSolicitacao() {
        if (isConcluiSolicitacao() && isIncluiSolicitacao()) {
            return true;
        }
        return false;
    }

    public boolean isIncluiEntradaMaterial() {
        if (perfil != null && perfil.getIncluiEntradaMaterial() == EnumHabilitado.S) {
            return true;
        }
        return false;
    }

    public boolean isRemoveEntradaMaterial() {
        if (perfil != null && perfil.getRemoveEntradaMaterial() == EnumHabilitado.S) {
            return true;
        }
        return false;
    }

    public boolean isIncluiSaidaMaterial() {
        if (perfil != null && perfil.getIncluiSaidaMaterial() == EnumHabilitado.S) {
            return true;
        }
        return false;
    }

    public boolean isRemoveSaidaMaterial() {
        if (perfil != null && perfil.getRemoveSaidaMaterial() == EnumHabilitado.S) {
            return true;
        }
        return false;
    }

    public boolean isAutorizaSolicitacao() {
        if (perfil != null && perfil.getAutorizaSolicitacao() == EnumHabilitado.S) {
            return true;
        }
        return false;
    }

    public boolean isIncluiRhCadGer() {
        if (perfil != null && perfil.getIncluiRhCadGer() == EnumHabilitado.S) {
            return true;
        }
        return false;
    }

    public boolean isAlteraRhCadGer() {
        if (perfil != null && perfil.getAlteraRhCadGer() == EnumHabilitado.S) {
            return true;
        }
        return false;
    }

    public boolean isIncluiRhOcorrencia() {
        if (perfil != null && perfil.getIncluiRhOcorrencia() == EnumHabilitado.S) {
            return true;
        }
        return false;
    }

    public boolean isAlteraRhOcorrencia() {
        if (perfil != null && perfil.getAlteraRhOcorrencia() == EnumHabilitado.S) {
            return true;
        }
        return false;
    }

    public boolean isRemoveRhOcorrencia() {
        if (perfil != null && perfil.getExcluiRhOcorrencia() == EnumHabilitado.S) {
            return true;
        }
        return false;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(int statusLogin) {
        this.statusLogin = statusLogin;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public CentroCusto getCentroSelecionado() {
        return centroSelecionado;
    }

    public void setCentroSelecionado(CentroCusto centroSelecionado) {
        this.centroSelecionado = centroSelecionado;
    }

    public PerfilAcesso getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilAcesso perfil) {
        this.perfil = perfil;
    }
}
