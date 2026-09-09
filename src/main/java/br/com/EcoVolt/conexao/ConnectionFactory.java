package br.com.EcoVolt.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getconnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
            Connection conexao = DriverManager.getConnection("dbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm572228", "260497");
            return conexao;
    }
}
