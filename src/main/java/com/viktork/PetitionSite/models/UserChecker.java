package com.viktork.PetitionSite.models;

import com.viktork.PetitionSite.services.Encryptor;
import com.viktork.PetitionSite.services.MySQLConnector;
import com.viktork.PetitionSite.services.MySQLData;
import com.viktork.PetitionSite.services.MySQLQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.security.MessageDigest;

public class UserChecker {
    private Connection con;
    private HttpServletRequest request;
    public UserChecker(HttpServletRequest request){
        MySQLConnector connector = new MySQLConnector();
        connector.mysql_data(MySQLData.host, MySQLData.user, MySQLData.password);
        connector.mysql_select_db(MySQLData.db);
        connector.mysql_connect();
        this.con=connector.getConnection();
        this.request=request;
    }
    public boolean isRegistered(String name) throws SQLException {
        MySQLQuery query = new MySQLQuery(con);
        ResultSet rs = query.mysql_select("*", "users","`name`=?",name);
        rs.next();
        try {
            rs.getString("id");
        }
        catch(SQLException ex){
            return false;
        }
        return true;
    }
    public void register(String name, String password) throws NoSuchAlgorithmException, SQLException {
        MySQLQuery query = new MySQLQuery(con);

        String encrypted = Encryptor.md5(password);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        ArrayList values = new ArrayList(){{
            add(name);
            add(encrypted);
            add(date);
        }};
        query.mysql_insert("users","name,password,date",values);
    }
    public boolean login(String name,String password) throws NoSuchAlgorithmException, SQLException {
        MySQLQuery query = new MySQLQuery(con);
        ResultSet rs = query.mysql_select("*", "users","`name`=?",name);
        rs.next();
        String encrypted = Encryptor.md5(password);
        if(rs.getString("password").equals(encrypted)){
            HttpSession session = request.getSession();
            session.setAttribute("id",rs.getString("id"));
            return true;
        }
        return false;
    }
}
