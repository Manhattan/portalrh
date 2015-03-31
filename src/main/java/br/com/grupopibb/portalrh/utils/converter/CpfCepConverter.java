/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.utils.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * 
 * @author administrator
 */
@FacesConverter(value = "cpfCepConverter")
public class CpfCepConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
        if (valor != null && !valor.isEmpty()) {
            valor = valor.toString().replaceAll("[- /.]", "");
            valor = valor.toString().replaceAll("[-()]", "");
        }
        return valor;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
        return valor.toString();
    }
}
