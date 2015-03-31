package br.com.grupopibb.portalrh.utils;

import java.text.Normalizer;
import java.util.InputMismatchException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tone.lima
 */
public final class StringBeanUtils {
    

    /**
     * Define a variação da String para LIKE fixa no final e variavel no inicio.
     * Ex: "%aria"
     */
    public static final int LIKE_START = 0;
    /**
     * Define a variação da String para LIKE fixa no inicio e variavel no final.
     * Ex: "Mar%"
     */
    public static final int LIKE_END = 1;
    /**
     * Define a variação da String para LIKE variavel no inicio e variavel no
     * final. Ex: "%ari%"
     */
    public static final int LIKE_MIDDLE = 2;

    /**
     * Classe exclusiva de métodos estaticos, não pode ser instânciada.
     */
    private StringBeanUtils() {
    }

    /**
     * Acerta o parametro para null ou concatena com % para ser incluido em
     * consultas do tipo: <i>(:nome is null or upper(o.nome) like
     * upper(:nome))</i>.
     *
     * @param string
     * @param likeType Utilizar as constantes:<br>
     * UtilBeans.StringBeanUtils.LIKE_START<br>
     * UtilBeans.StringBeanUtils.LIKE_END<br>
     * UtilBeans.StringBeanUtils.LIKE_MIDDLE<br>
     * @return java.lang.String ou null
     */
    public static String acertaNomeParaLike(String string,
            final int likeType) {
        if (StringUtils.isBlank(string)) {
            string = null;
        } else {
            switch (likeType) {
                case LIKE_START:
                    string = "%" + string;
                    break;
                case LIKE_MIDDLE:
                    string = "%" + string + "%";
                    break;
                case LIKE_END:
                    string = string + "%";
                    break;
            }

        }
        return string;
    }

    /**
     * Valida se um IP v.4 é valido.
     *
     * @param ip
     * @return true se o IP for válido.
     */
    public static boolean validarIPV4(String ip) {
        if (ip == null) {
            return false;
        }
        if (ip.trim().equals("")) {
            return false;
        }
        if (ip.indexOf("-") >= 0) {
            return false;
        }
        String[] strPartes = ip.replace('.', '-').split("-");
        if (strPartes.length != 4) {
            return false;
        }
        for (int i = 0; i < strPartes.length; i++) {
            String strPedaco = strPartes[i];
            if (strPedaco == null) {
                return false;
            }
            if (strPedaco.trim().equals("")) {
                return false;
            }
            try {
                int intPedaco = Integer.parseInt(strPedaco);
                if ((i == 0 && intPedaco == 0) || (intPedaco >= 254)) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo validarCPF - Responsavel em validar o CPF
     * 
     * @return Boolean
     * @since 29/12/2006 Devmedia
     */
    public static boolean validarCPF(String cpf) {
        if (cpf == null){
            return false;
        }
      // considera-se erro CPF's formados por uma sequencia de numeros iguais
    if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
        cpf.equals("22222222222") || cpf.equals("33333333333") ||
        cpf.equals("44444444444") || cpf.equals("55555555555") ||
        cpf.equals("66666666666") || cpf.equals("77777777777") ||
        cpf.equals("88888888888") || cpf.equals("99999999999") ||
       (cpf.length() != 11))
       return false;

    char dig10, dig11;
    int sm, i, r, num, peso;

// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
    try {
// Calculo do 1o. Digito Verificador
      sm = 0;
      peso = 10;
      for (i=0; i<9; i++) {              
// converte o i-esimo caractere do CPF em um numero:
// por exemplo, transforma o caractere '0' no inteiro 0         
// (48 eh a posicao de '0' na tabela ASCII)         
        num = (int)(cpf.charAt(i) - 48); 
        sm = sm + (num * peso);
        peso = peso - 1;
      }

      r = 11 - (sm % 11);
      if ((r == 10) || (r == 11))
         dig10 = '0';
      else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

// Calculo do 2o. Digito Verificador
      sm = 0;
      peso = 11;
      for(i=0; i<10; i++) {
        num = (int)(cpf.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso - 1;
      }

      r = 11 - (sm % 11);
      if ((r == 10) || (r == 11))
         dig11 = '0';
      else dig11 = (char)(r + 48);

// Verifica se os digitos calculados conferem com os digitos informados.
      if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
         return true;
      else return false;
    } catch (InputMismatchException erro) {
        return false;
    }
  }
    

    /**
     * Código Java de uma classe com os métodos de validação de CNPJ de acordo
     * com as regras da Receita Federal.
     *
     * @param str_cnpj
     * @return retorna verdadeiro (true) para CNPJ válido e falso (false) para
     * CNPJ inválido
     */
    public static boolean validarCNPJ(String str_cnpj) {
        if (str_cnpj == null) {
            return false;
        }
        if (str_cnpj.length() != 14) {
            return false;
        }
        int soma = 0, aux, dig;
        String cnpj_calc = str_cnpj.substring(0, 12);
        char[] chr_cnpj = str_cnpj.toCharArray();

        /*
         * Primeira parte
         */
        for (int i = 0; i < 4; i++) {
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
            }
        }
        for (int i = 0; i < 8; i++) {
            if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
            }
        }
        dig = 11 - (soma % 11);

        cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

        /*
         * Segunda parte
         */
        soma = 0;
        for (int i = 0; i < 5; i++) {
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
            }
        }
        for (int i = 0; i < 8; i++) {
            if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
            }
        }
        dig = 11 - (soma % 11);
        cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

        return str_cnpj.equals(cnpj_calc);
    }

    /**
     * Formata o CPF passado para ###.###.###-##.
     *
     * @param value CPF a ser formatado.
     * @return CPF formatado.
     */
    public static String formatCPF(String value) {
        if (value != null && !value.equals("")) {
            String cpf1 = value.toString().substring(0, 3);
            String cpf2 = value.toString().substring(3, 6);
            String cpf3 = value.toString().substring(6, 9);
            String cpf4 = value.toString().substring(9);
            return cpf1 + "." + cpf2 + "." + cpf3 + "-" + cpf4;
        }
        return value == null ? "" : value.toString();
    }

    /**
     * http://javafree.uol.com.br/topic-871337-Remover-acentuacao.html
     * @param value
     * @return 
     */
    public static String normalize(String value) {
        value = Normalizer.normalize(value, Normalizer.Form.NFD);
        value = value.replaceAll("[^\\p{ASCII}]", "");
        return value;
    }
}
