/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.common;

import com.zaxxer.hikari.HikariDataSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author S2028398
 */
public class DataSourceUtility {

    public static DataSource getDatasource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setInitializationFailTimeout(0);
        dataSource.setMaximumPoolSize(5);

        dataSource.setDataSourceClassName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
        
        dataSource.addDataSourceProperty("url", "jdbc:sqlserver://LPTAXN73\\SQLEXPRESS14:2010;databaseName=SchoolSystemDB");
        dataSource.addDataSourceProperty("user", "terry");
        dataSource.addDataSourceProperty("password", "P@sswords.");

        return dataSource;
    }

    public static DataSource getDatasourceLookup() {
        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:/schoolDS");
            return dataSource;
        } catch (NamingException ex) {
            Logger.getLogger(DataSourceUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
