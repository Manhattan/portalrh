/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.types;

/**
 *
 * @author administrator
 */
public enum EnumSituacaoPessoal {

    CADASTRADO("Cadastrado"),
    CONTRATAR("Contratar"),
    CONTRATADO("Contratado"),
    EX_CONTRATADO("Ex-Contratado"),
    CANCELADO("Cancelado"),
    EXAME_PRE_ADM("Exame Pre Adm");
    private String label;

    private EnumSituacaoPessoal(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
