/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.controller;

import br.com.grupopibb.portalrh.model.Dependente;
import br.com.grupopibb.portalrh.utils.JsfUtil;
import br.com.grupopibb.portalrh.utils.MessageUtils;
import br.com.grupopibb.portalrh.utils.StringBeanUtils;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author administrator
 */
@ManagedBean
@ViewScoped
public class DependenteController implements Serializable {

    @PostConstruct
    public void init() {
    }

    @PreDestroy
    public void destroy() {
    }
    private Dependente current;
    private boolean newEntity = false;

    /**
     * Substitui o Dependente atual com o que foi passado por parâmetro.
     *
     * @param dep Novo Dependente.
     */
    public void initDependente(Dependente dep) {
        this.current = dep;
        if (dep == null) {
            newEntity = true;
            this.current = getNewDependente();
        } else {
            newEntity = false;
        }
    }

    /**
     * Verifica se o CPF do Dependente atual é nulo ou não.
     *
     * @return Verdadeiro ou Falso.
     */
    public boolean isCpfNull() {
        return this.current == null || this.current.getCpf() == null;
    }

    /**
     * Verifica se o Nome do Dependente atual é nulo ou não.
     *
     * @return Verdadeiro ou Falso.
     */
    public boolean isNomeNull() {
        return this.current == null || StringUtils.isBlank(this.current.getNome());
    }

    public String validaCpf() {
        if (!StringBeanUtils.validarCPF(this.current.getCpf())) {
            msgCpfInvalido();
            this.current.setCpf("");
        }
        return JsfUtil.MANTEM;
    }

    /**
     * Define o dependente existente atual para ser editado, criando um novo
     * dependente em memória a fim de esvaziar a variável current.
     *
     * @return Dependente.
     */
    public Dependente getCurrentEdited() {
        Dependente dep = new Dependente(this.current);
        this.current = null;
        return dep;
    }

    /**
     * Cria um novo Dependente.
     *
     * @return Novo Dependente.
     */
    private Dependente getNewDependente() {
        return new Dependente();
    }

    public Dependente getCurrent() {
        return current;
    }

    public void setCurrent(Dependente current) {
        this.current = current;
    }

    public boolean isNewEntity() {
        return newEntity;
    }

    public void setNewEntity(boolean newEntity) {
        this.newEntity = newEntity;
    }

    public void msgCpfInvalido() {
        MessageUtils.messageFactoringFull("cpfInvalido1",
                new Object[]{StringBeanUtils.formatCPF(current.getCpf())},
                FacesMessage.SEVERITY_WARN,
                FacesContext.getCurrentInstance());
    }
}
