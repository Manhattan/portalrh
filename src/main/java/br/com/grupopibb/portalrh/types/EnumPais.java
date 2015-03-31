/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.types;

/**
 *
 * @author administrator
 */
public enum EnumPais {

    BR("BR");
    /**
     * Label do Enum.
     */
    private String label;

    private EnumPais(String label) { 
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
    public static EnumPais getForLabel(String lab) {
        EnumPais enumm;
        for (EnumPais e : EnumPais.values()) {
            if (e.getLabel().equals(lab)) {
                enumm = e;
                return enumm;
            }
        }
        return null;
    }
}
