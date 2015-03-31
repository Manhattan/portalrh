/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupopibb.portalrh.business;

import br.com.grupopibb.portalrh.utils.UtilBeans;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/** 
 *
 * @author administrator
 */
@Stateless
@LocalBean
public class ConnectionFactory {

    public Connection getConnection(String user, String pass) {
        try {
            String database = UtilBeans.DATABASE_NAME;
            System.out.println("Tentativa de conexao em:" + database);
            return DriverManager.getConnection("jdbc:sqlserver://10.1.1.65:1433;databaseName=" + database, user, pass);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Connection getConnectionOpen(){
        return getConnection(UtilBeans.DATABASE_USER, UtilBeans.DATABASE_PASS);
    }
}

