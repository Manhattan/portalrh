/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.utils;
import java.util.logging.Level;

/**
 *
 * @author tone.lima
 */
public class UtilBeans {

    /**
     * Definie o nível de log para toda aplicação
     */
    public static final Level LEVEL_LOG = Level.INFO;
    /**
     * Define qual Unidade de Persistência a aplicação está utilizando.
     */
    public static final String PERSISTENCE_UNIT = "portalrhPU";
    /**
     * Define qual Banco de Dados aplicação está utilizando.
     */
    public static final String DATABASE_NAME = "dbMANsup";
    /**
     * Define qual Usuário tem acesso ao banco de dados.
     */
    public static final String DATABASE_USER = "tone.lima";
    /**
     * Define a Senha para conexão com o banco de dados. Senha referente ao
     * DATABASE_USER.
     */ 
    public static final String DATABASE_PASS = "Mn160391";
}

