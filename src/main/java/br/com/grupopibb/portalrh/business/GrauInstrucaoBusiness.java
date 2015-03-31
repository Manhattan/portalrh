/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.business;

import br.com.grupopibb.portalrh.model.GrauInstrucao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author administrator
 */
@Stateless 
@LocalBean
public class GrauInstrucaoBusiness implements Serializable{
    
    public GrauInstrucao getNI(){
        return new GrauInstrucao("05", "Nao Informado");
    }
    public GrauInstrucao getAnalfabeto(){
        return new GrauInstrucao("01", "Analfabeto");
    }
    public GrauInstrucao getAteQuintoAnoInc(){
        return new GrauInstrucao("02", "Até o 5º ano incompleto do Ensino Fundamental");
    }
    public GrauInstrucao getQuintoAnoComp(){
        return new GrauInstrucao("03", "5º ano completo do ensino fundamental");
    }
    public GrauInstrucao getSextoAoNonoAno(){
        return new GrauInstrucao("04", "Do 6º ano 9º ano do ensino fundamental");
    }
    public GrauInstrucao getEnsinoMedioInc(){
        return new GrauInstrucao("06", "Ensino Médio Incompleto");
    }
    public GrauInstrucao getEnsinoMedioComp(){
        return new GrauInstrucao("07", "Ensino Médio Completo");
    }
    public GrauInstrucao getEnsinoSuperiorInc(){
        return new GrauInstrucao("08", "Ensino Superior Incompleto");
    }
    public GrauInstrucao getEnsinoSuperiorComp(){
        return new GrauInstrucao("09", "Ensino Superior Completo");
    }
    
    public GrauInstrucao getMestradoComp(){
        return new GrauInstrucao("10", "Mestrado Completo");
    }

    public GrauInstrucao getDoutoradoComp(){
        return new GrauInstrucao("11", "Doutorado Completo");
    }

    public List<GrauInstrucao> mountGrauInstrucao() {
        List<GrauInstrucao> grausInstrucao = new ArrayList<GrauInstrucao>();
        grausInstrucao.add(getNI());
        grausInstrucao.add(getAnalfabeto());
        grausInstrucao.add(getAteQuintoAnoInc());
        grausInstrucao.add(getQuintoAnoComp());
        grausInstrucao.add(getSextoAoNonoAno());
        grausInstrucao.add(getEnsinoMedioInc());
        grausInstrucao.add(getEnsinoMedioComp());
        grausInstrucao.add(getEnsinoSuperiorInc());
        grausInstrucao.add(getEnsinoSuperiorComp());
        grausInstrucao.add(getMestradoComp());
        grausInstrucao.add(getDoutoradoComp());
        return grausInstrucao;
    }
    
    public GrauInstrucao find(String id){
        List<GrauInstrucao> grausInstrucao = mountGrauInstrucao();
        for (GrauInstrucao grauIns : grausInstrucao){
            if (grauIns.getCodigo().equals(id)){
                return grauIns;
            }
        }
        return null;
    }
}
