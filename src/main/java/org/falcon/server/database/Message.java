package org.falcon.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Message extends DataBaseManagment{
    private int id;
    private int replyId;
    private String message;
    private String user;
    private boolean replyCondition;

    public Message(String user, String message) {
        /* Constructor to insert message */
        super();
        this.user = user;
        this.id = (int) (Math.random() * 1500);
        this.message = message;
        this.replyCondition = false;
    }

    public Message(String user, String message, int replyId) {
        /* Constructor to reply message */
        super();
        this.user = user;
        this.id = (int) (Math.random() * 1500);
        this.replyId = replyId;
        this.message = message;
        this.replyCondition = true;
    }

    public Message(int id) {
        /* Constructor to get message from id */
        super();
        this.id = id;
        System.out.println(this.id + "constructor");
    }

    public Message(String user) {
        /* Constructor to get message from user */
        super();
        this.user = user;
    }

    public void insertMessage() throws SQLException, ClassNotFoundException {
        initDataBase();

        String sql = "INSERT INTO MESSAGE_USER VALUES(?,?,?,?,?,?)";

        Date utilDate = new java.util.Date();

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, this.user);
        stmt.setInt(2, this.id);
        stmt.setString(3, this.message);
        stmt.setTimestamp(4, new java.sql.Timestamp(utilDate.getTime()));

        if(!replyCondition) {
            stmt.setInt(5, 0);
            stmt.setString(6, null);

            stmt.executeUpdate();
            System.out.println("Message has been INSERT successfully");
        } else {
            stmt.setInt(5, this.replyId);
            stmt.setString(6, this.message);

            stmt.executeUpdate();
            System.out.println("Message has been REPLY successfully");
        }
        stmt.close();
        conn.close();

    }

    public List<String> getRecentMessage() throws SQLException {
        initDataBase();
        String sql = "SELECT * FROM MESSAGE_USER ORDER BY POST_DATE ";

        stmt = conn.prepareStatement(sql);
        ResultSet dbResult = stmt.executeQuery();

        List<String> messages = new ArrayList<>();

        while(dbResult.next()) {
            String message = dbResult.getString("MESSAGE") + " from: " +
                    dbResult.getString("NAME") + " posted: " +
                    dbResult.getTimestamp("POST_DATE");
            messages.add(message);
        }

        stmt.close();
        conn.close();

        return messages;
    }

    public List<String> getMessageFromUser() throws SQLException {
        initDataBase();
        String sql = "SELECT MESSAGE, POST_DATE, NAME FROM MESSAGE_USER ORDER BY POST_DATE;";

        stmt = conn.prepareStatement(sql);
        ResultSet dbResult = stmt.executeQuery();

        List<String> messages = new ArrayList<>();

        while(dbResult.next()) {
            if(dbResult.getString("NAME").equals(this.user)) {
                String message = dbResult.getString("MESSAGE") + "publish at: " +
                        dbResult.getString("POST_DATE") + ";";
                messages.add(message);
            }
        }

        stmt.close();
        conn.close();

        return messages;
    }

    public String getMessageFromId() throws SQLException {
        initDataBase();
        String sql = "SELECT MESSAGE FROM MESSAGE_USER WHERE ID = (?);";

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1,this.id);
        ResultSet dbResult = stmt.executeQuery();

        String result = "Nothing";

        while(dbResult.next())
            result = dbResult.getString("MESSAGE");

        stmt.close();
        conn.close();

        return result ;
    }





}
