package com.viktork.PetitionSite.services;

import java.sql.*;
import java.util.ArrayList;

public class MySQLQuery {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement ptmt;

    public MySQLQuery(Connection conn){
        this.conn=conn;
    }
    public ResultSet mysql_select(String data, String table){
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " + data + " FROM " + table);
            return rs;
        }
        catch(Exception se){
            System.out.println("QUERY ERROR!");
        }

        return null;
    }
    public ResultSet mysql_select(String data,String table,String condition) throws SQLException {
        stmt=conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT "+data+" FROM "+table+" WHERE "+condition);
        return rs;
    }
    public ResultSet mysql_select(String data,String table,String condition,String value) throws SQLException {
        ptmt=conn.prepareStatement("SELECT "+data+" FROM "+table+" WHERE "+condition);
        ptmt.setString(1,value);
        ResultSet rs = ptmt.executeQuery();
        return rs;
    }
    public void mysql_insert(String table, String keys, ArrayList values) throws SQLException {
        StringBuilder val = new StringBuilder();
        for(int i=0; i< values.size();i++){
            val.append("?,");
        }
        val.deleteCharAt(values.size()*2-1);
        ptmt = conn.prepareStatement("INSERT INTO "+table+" ("+keys+") VALUES ("+val+")");
        TypeChecker t = new TypeChecker();
        for(int i=0;i<values.size();i++){
            if(t.isInteger(values.get(i))){
                ptmt.setInt(i+1, (Integer) values.get(i));
            }
            else if(t.isDate(values.get(i))){
                ptmt.setDate(i+1, (Date) values.get(i));
            }
            else{
                ptmt.setString(i+1, (String) values.get(i));
            }
        }
        ptmt.executeUpdate();
    }
    public void mysql_update(String table,String key,String value,String condition) throws SQLException {
        ptmt = conn.prepareStatement("UPDATE "+table+" SET "+key+" =? WHERE "+condition);
        ptmt.setString(1,value);
        ptmt.executeUpdate();
    }
}
