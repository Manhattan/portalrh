/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.utils.converter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.grupopibb.portalrh.business.GrauInstrucaoBusiness;
import br.com.grupopibb.portalrh.dao.BancoFolhaFacade;
import br.com.grupopibb.portalrh.dao.EstadoFacade;
import br.com.grupopibb.portalrh.dao.MunicipioFolhaFacade;
import br.com.grupopibb.portalrh.dao.NacionalidadeFolhaFacade;
import br.com.grupopibb.portalrh.dao.RacaCorFacade;
import br.com.grupopibb.portalrh.model.BancoFolha;
import br.com.grupopibb.portalrh.model.Estado;
import br.com.grupopibb.portalrh.model.GrauInstrucao;
import br.com.grupopibb.portalrh.model.MunicipioFolha;
import br.com.grupopibb.portalrh.model.NacionalidadeFolha;
import br.com.grupopibb.portalrh.model.RacaCor;
import br.com.grupopibb.portalrh.types.EnumPais;
import br.com.grupopibb.portalrh.utils.DateUtils;
import br.com.grupopibb.portalrh.utils.JsfUtil;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 *
 */
@ManagedBean
@RequestScoped
public class ConverterController {

    @EJB
    private EstadoFacade estadoFacade;
    @EJB
    private MunicipioFolhaFacade municipioFacade;
    @EJB
    private RacaCorFacade racaCorFacade;
    @EJB
    private NacionalidadeFolhaFacade nacionalidadeFolhaFacade;
    @EJB
    private GrauInstrucaoBusiness grauInstrucaoBusiness;
    @EJB
    private BancoFolhaFacade bancoFolhaFacade;

    //====================
    // Conversor (Estado)
    //====================
    /**
     * Conversor para classe Estado.
     */
    @FacesConverter(forClass = Estado.class, value = "estadoConverter")
    public static class EstadoConverter implements Converter {

        @Override
        public Object getAsObject(final FacesContext facesContext,
                final UIComponent component, final String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConverterController controller =
                    JsfUtil.getController("converterController", facesContext);
            if (value.length() == 0) {
                return null;
            }
            return controller.estadoFacade.findByPaisUf(EnumPais.BR.name() + value);
        }

        @Override
        public String getAsString(final FacesContext facesContext,
                final UIComponent component, final Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Estado || object instanceof String) {
                return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object
                        + " is of type " + object.getClass().getName()
                        + "; expected type: "
                        + Estado.class.getName());
            }
        }
    }

    //====================
    // Conversor (MunicipioFolha)
    //====================
    /**
     * Conversor para classe MunicipioFolha.
     */
    @FacesConverter(forClass = MunicipioFolha.class, value = "municipioConverter")
    public static class MunicipioConverter implements Converter {

        @Override
        public Object getAsObject(final FacesContext facesContext,
                final UIComponent component, final String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConverterController controller =
                    JsfUtil.getController("converterController", facesContext);
            if (value.length() == 0) {
                return null;
            }
            return controller.municipioFacade.find(String.valueOf(value));
        }

        @Override
        public String getAsString(final FacesContext facesContext,
                final UIComponent component, final Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MunicipioFolha) {
                MunicipioFolha o = (MunicipioFolha) object;
                return o.getId().toString();
            } else {
                throw new IllegalArgumentException("object " + object
                        + " is of type " + object.getClass().getName()
                        + "; expected type: "
                        + MunicipioFolha.class.getName());
            }
        }
    }

    //====================
    // Conversor (RacaCor)
    //====================
    /**
     * Conversor para classe RacaCor.
     */
    @FacesConverter(forClass = RacaCor.class, value = "racaCorConverter")
    public static class RacaCorConverter implements Converter {

        @Override
        public Object getAsObject(final FacesContext facesContext,
                final UIComponent component, final String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            if (!StringUtils.isNumeric(value)) {
                return null;
            }
            ConverterController controller =
                    JsfUtil.getController("converterController", facesContext);
            if (value.length() == 0) {
                return null;
            }
            return controller.racaCorFacade.find(Integer.valueOf(value));
        }

        @Override
        public String getAsString(final FacesContext facesContext,
                final UIComponent component, final Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof RacaCor) {
                RacaCor o = (RacaCor) object;
                return o.getId().toString();
            } else {
                throw new IllegalArgumentException("object " + object
                        + " is of type " + object.getClass().getName()
                        + "; expected type: "
                        + RacaCor.class.getName());
            }
        }
    }

    //====================
    // Conversor (NacionalidadeFolha)
    //====================
    /**
     * Conversor para classe NacionalidadeFolha.
     */
    @FacesConverter(forClass = NacionalidadeFolha.class, value = "nacionalidadeConverter")
    public static class NacionalidadeFolhaConverter implements Converter {

        @Override
        public Object getAsObject(final FacesContext facesContext,
                final UIComponent component, final String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConverterController controller =
                    JsfUtil.getController("converterController", facesContext);
            if (value.length() == 0) {
                return null;
            }
            return controller.nacionalidadeFolhaFacade.find(value);
        }

        @Override
        public String getAsString(final FacesContext facesContext,
                final UIComponent component, final Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof NacionalidadeFolha) {
                NacionalidadeFolha o = (NacionalidadeFolha) object;
                return o.getId().toString();
            } else {
                throw new IllegalArgumentException("object " + object
                        + " is of type " + object.getClass().getName()
                        + "; expected type: "
                        + NacionalidadeFolha.class.getName());
            }
        }
    }
    //====================
    // Conversor (GrauInstrucao)
    //====================

    /**
     * Conversor para classe GrauInstrucao.
     */
    @FacesConverter(forClass = GrauInstrucao.class, value = "grauInstrucaoConverter")
    public static class GrauInstrucaoConverter implements Converter {

        @Override
        public Object getAsObject(final FacesContext facesContext,
                final UIComponent component, final String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConverterController controller =
                    JsfUtil.getController("converterController", facesContext);
            if (value.length() == 0) {
                return null;
            }
            return controller.grauInstrucaoBusiness.find(value);
        }

        @Override
        public String getAsString(final FacesContext facesContext,
                final UIComponent component, final Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof GrauInstrucao) {
                GrauInstrucao o = (GrauInstrucao) object;
                return o.getCodigo().toString();
            } else {
                throw new IllegalArgumentException("object " + object
                        + " is of type " + object.getClass().getName()
                        + "; expected type: "
                        + GrauInstrucao.class.getName());
            }
        }
    }

    @FacesConverter(forClass = Date.class, value = "dateDMAConverter")
    public static class DateDMAConverter implements Converter {

        @Override
        public Object getAsObject(final FacesContext facesContext,
                final UIComponent component, final String value) {
            if (value == null || value.isEmpty()) {
                return null;
            }
            return value;
        }

        @Override
        public String getAsString(final FacesContext facesContext,
                final UIComponent component, final Object object) {
            if (object == null || object.toString().isEmpty()) {
                return null;
            }
            if (object instanceof Date) {
                return DateUtils.getDataFormatada("dd/MM/yyyy", (Date) object);
            }
            if (object instanceof String) {
                return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object
                        + " is of type " + object.getClass().getName()
                        + "; expected type: "
                        + Date.class.getName());
            }
        }
    }

    //====================
    // Conversor (BancoFolha)
    //====================
    /**
     * Conversor para classe BancoFolha.
     */
    @FacesConverter(forClass = BancoFolha.class, value = "bancoFolhaConverter")
    public static class BancoFolhaConverter implements Converter {

        @Override
        public Object getAsObject(final FacesContext facesContext,
                final UIComponent component, final String value) {
            if (value == null || value.length() < 3) {
                return null;
            }
            ConverterController controller =
                    JsfUtil.getController("converterController", facesContext);
             BancoFolha banco = controller.bancoFolhaFacade.find(StringUtils.left(value, 3));
             if (banco != null){
                 return banco;
             }
             return new BancoFolha(StringUtils.left(value, 3), "", "");
        }

        @Override
        public String getAsString(final FacesContext facesContext,
                final UIComponent component, final Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof BancoFolha) {
                BancoFolha o = (BancoFolha) object;
                return o.getLabel();
            }
            if (object instanceof String) {

                ConverterController controller =
                        JsfUtil.getController("converterController", facesContext);
                BancoFolha banco = controller.bancoFolhaFacade.find(object.toString());
                if (banco != null) {
                    return banco.getLabel();
                }
                return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object
                        + " is of type " + object.getClass().getName()
                        + "; expected type: "
                        + BancoFolha.class.getName()
                        + " or "
                        + String.class.getName());
            }
        }
    }
}
