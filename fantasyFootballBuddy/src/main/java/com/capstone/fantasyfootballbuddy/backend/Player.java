
package com.capstone.fantasyfootballbuddy.backend;
 
public class Player {

    private String firstName;
    private String lastName;
    private String position;
    private String team;

    public Player(){
        
    }
    
    public Player(String firstName, String lastName, String position, String team) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.team = team;
        
    }
    
    public String getFirstName() {
        
        return firstName; 
        
    }
    
    public String getLastName() {
        
        return lastName; 
        
    }
    
    public String getPosition() {
        
        return position; 
        
    }
    
    public String getTeam() {
        
        return team; 
        
    }
    
    public void setFirstName(String firstName) {
            
        this.firstName = firstName;
        
    }

    public void setLastName(String lastName) {
        
        this.lastName = lastName;
        
    }

    public void setPosition(String position) {
        
        this.position = position;
        
    }

    public void setTeam(String team) {
        
        this.team = team;
        
    }

    @Override
    public String toString() {
        
        return firstName + " " + lastName + " (" + position + ", " + team + ")";
        
    }
}

