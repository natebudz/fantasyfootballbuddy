
package com.capstone.fantasyfootballbuddy.backend;

import java.sql.Connection;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NFLTeamData {
    
    public static void sendToTeamTable(String text) { // add connection later
    
        try {
            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(text);
            JsonNode body = root.get("body");
            
            for (JsonNode team : body) {
                String teamID = team.get("teamID").asText();
                String teamName = team.get("teamName").asText();
                String city = team.get("teamCity").asText();
                String conference = team.get("conferenceAbv").asText();
                String division = team.get("division").asText();
                
                System.out.println(teamID + " " + teamName + " " + city  + " " + conference + " " + division);
            }
        
        } catch (Exception e) {
            
            System.out.println("error");
            
        }
    }     
}
