/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.business;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class TipoOcorrenciaBusiness {

    public Map<String, Object> getTiposOcorrencia() {
        Map<String, Object> tipos = new HashMap<String, Object>();
        tipos.put("Ocorrencia", 0);
        tipos.put("Ausencia", 1);
        tipos.put("Falta", 2);
        return tipos;
    }

    public String getTipoOcorrencia(int tipo) {
        switch (tipo) {
            case 0:
                return "Ocorrência";
            case 1:
                return "Ausência";
            case 2:
                return "Falta";
            default:
                return "";
        }
    }
}
