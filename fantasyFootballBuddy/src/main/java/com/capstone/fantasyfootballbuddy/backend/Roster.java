
package com.capstone.fantasyfootballbuddy.backend;

import java.util.ArrayList;
import java.util.List;

public class Roster {

    private String rosterName;
    private List<Player> players;

    public Roster(String rosterName) {
        
        this.rosterName = rosterName;
        this.players = new ArrayList<>();
        
    }

    public String getRosterName() {
        
        return rosterName; 
        
    }

    public void addPlayer(Player player) {
        
        players.add(player);
        
    }


    public void removePlayer(Player player) {
        
        players.remove(player);
        
    }

    public List<Player> getPlayers() {
        
        return players;
        
    }
    
    public void setRosterName(String rosterName) {
        
        this.rosterName = rosterName;
        
    }

    public void setPlayers(List<Player> players) {
        
        this.players = players;
        
    }

    @Override
    public String toString() {
        
        return "Roster: " + rosterName + "\nPlayers: " + players;
        
    }
}
