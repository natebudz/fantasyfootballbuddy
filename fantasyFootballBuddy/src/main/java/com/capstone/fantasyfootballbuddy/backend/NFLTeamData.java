
package com.capstone.fantasyfootballbuddy.backend;

import java.sql.Connection;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.PreparedStatement;

public class NFLTeamData {
    
    public static void sendToTeamTable(String text) {

        try {
            
            Connection conn = DBManager.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO nflteam (team_id, team_name, city, conference, division)" 
                    + " VALUES (?,?,?,?,?)"
                    + " ON CONFLICT (team_id) DO NOTHING;");
            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(text);
            JsonNode body = root.get("body");
            
            for (JsonNode team : body) {
                int teamID = team.get("teamID").asInt();
                String teamName = team.get("teamName").asText();
                String city = team.get("teamCity").asText();
                String conference = team.get("conferenceAbv").asText();
                String division = team.get("division").asText();
                
                System.out.println(teamID + " " + teamName + " " + city  + " " + conference + " " + division);
                
                pstmt.setInt(1, teamID);
                pstmt.setString(2, teamName);
                pstmt.setString(3, city);
                pstmt.setString(4, conference);
                pstmt.setString(5, division);
                
                pstmt.executeUpdate();
                
            }
        
        } catch (Exception e) {
            
            System.out.println("Unable to instert into table: nflteam");
            e.printStackTrace();
            
        }
    }     
}
