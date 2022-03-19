package io.alunity.sql;

import io.alunity.content.commands.all.Voted;
import io.alunity.content.vote_panel.VotePanelManager;
import io.alunity.model.entity.player.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class FoxVote implements Runnable {

    public static final String HOST = "92.204.221.12"; // website ip address
    public static final String USER = "alunity_admin";
    public static final String PASS = "yN6v9V&Jv2$l";
    public static final String DATABASE = "vote_alunity";

    private Player player;
    private Connection conn;
    private Statement stmt;

    public FoxVote(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        try {
            if (!connect(HOST, DATABASE, USER, PASS)) {
                return;
            }

            String name = player.getDisplayName();
            ResultSet rs = executeQuery("SELECT * FROM votes WHERE username='"+name+"' AND claimed=0 AND voted_on != -1");

            while (rs.next()) {
                String ipAddress = rs.getString("ip_address"); // IP check to make sure they're not cheating
                int siteId = rs.getInt("site_id");


                // -- ADD CODE HERE TO GIVE TOKENS OR WHATEVER
                // What rewards do you want to give them for voting ?
                //

                Voted.votePanel(player);
                Voted.rewards(player, 1); // 1 vote per website

                System.out.println("[Vote] Vote claimed by "+name+". (sid: "+siteId+", ip: "+ipAddress+")");

                rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
                rs.updateRow();
            }

            destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean connect(String host, String database, String user, String pass) {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
            return true;
        } catch (SQLException e) {
            System.out.println("Failing connecting to database!");
            return false;
        }
    }

    public void destroy() {
        try {
            conn.close();
            conn = null;
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int executeUpdate(String query) {
        try {
            this.stmt = this.conn.createStatement(1005, 1008);
            int results = stmt.executeUpdate(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public ResultSet executeQuery(String query) {
        try {
            this.stmt = this.conn.createStatement(1005, 1008);
            ResultSet results = stmt.executeQuery(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

