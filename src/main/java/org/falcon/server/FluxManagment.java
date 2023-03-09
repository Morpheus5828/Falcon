package org.falcon.server;

import org.falcon.server.database.Follower;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FluxManagment {
    private Map<String, ConcurrentLinkedQueue<String>> usersFlux;
    private List<String> followers;

    public FluxManagment() {
        this.usersFlux = new HashMap<>();
    }

    public void addNewFlux(String username) throws SQLException {
        this.followers = new Follower(username).getFollowers();
        if(this.followers.size() != 0) {
            this.usersFlux.put(username, new ConcurrentLinkedQueue<String>());
        }
    }




}
