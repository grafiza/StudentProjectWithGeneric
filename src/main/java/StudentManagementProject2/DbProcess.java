package StudentManagementProject2;

import java.sql.*;
public class DbProcess {
    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    public Connection getCon() {
        return con;
    }
    public void setCon(Connection con) {
        this.con = con;
    }
    public Statement getSt() {
        return st;
    }
    public void setSt(Statement st) {
        this.st = st;
    }
    public PreparedStatement getPs() {
        return ps;
    }
    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }
    public void setConnection() {
        try {
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcnt_db", "techpront", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void setStatement() {
        try {
            this.st = this.con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void setPreparedStatement(String query) {
        try {
            this.ps = this.con.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
