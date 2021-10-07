package com.viktork.PetitionSite.models;

import com.viktork.PetitionSite.services.MySQLConnector;
import com.viktork.PetitionSite.services.MySQLData;
import com.viktork.PetitionSite.services.MySQLQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteProcessor {
    private ResultSet rs;
    private Connection con;

    private String userId;
    public VoteProcessor(HttpServletRequest request) {
        MySQLConnector connector = new MySQLConnector();
        connector.mysql_data(MySQLData.host, MySQLData.user, MySQLData.password);
        connector.mysql_select_db(MySQLData.db);
        connector.mysql_connect();
        con=connector.getConnection();
        MySQLQuery query = new MySQLQuery(con);
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        this.userId=id;
        try {
            ResultSet rs = query.mysql_select("*", "users", "id=" + id);
            rs.next();
            this.rs=rs;
        }
        catch(SQLException ex){
            System.out.println("QUERY ERROR");
        }
    }
    public void vote(String id){
        try {
            String votes = rs.getString("votes");
            MySQLQuery query = new MySQLQuery(con);
            if (votes != null) {
                votes+=id+"-";
                query.mysql_update("users","votes",votes,"id="+userId);
                query.mysql_update("petitions","votes",String.valueOf(getVotes(id)+1),"id="+id);
            }
            else{
                String v = id+"-";
                query.mysql_update("users","votes",v,"id="+userId);
                query.mysql_update("petitions","votes",String.valueOf(getVotes(id)+1),"id="+id);

            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public boolean isset(String id) {
        try {
            String votes = rs.getString("votes");
            if (votes != null) {
                String[] ids = votes.split("-");
                for(String thisId : ids){
                    if(thisId.equals(id)){
                        return true;
                    }
                }
            }
        }
        catch(SQLException ex){
            System.out.println("EMPTY LIST");
        }
        return false;
    }
    private int getVotes(String id) throws SQLException {
        MySQLQuery query = new MySQLQuery(con);
        ResultSet rs = query.mysql_select("*", "petitions", "id=" + id);
        rs.next();
        return rs.getInt("votes");
    }
}
