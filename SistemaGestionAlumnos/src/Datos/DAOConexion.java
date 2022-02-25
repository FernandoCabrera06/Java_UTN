package Datos;

import java.sql.*;

/**
 *
 * @author fernando
 */
public class DAOConexion {

    protected Connection conn;
    protected PreparedStatement consulta;
    protected ResultSet datos;

    public void conectar() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.conn = DriverManager.getConnection("jdbc:mysql://" + "127.0.0.1" + "/" + "db_SGA", "root", "mysql");
    }

    public void desconectar() throws SQLException {
        this.conn.close();
        this.consulta.close();
    }

    public void desconectar(ResultSet rs) throws SQLException {
        this.desconectar();
        rs.close();
    }

}
