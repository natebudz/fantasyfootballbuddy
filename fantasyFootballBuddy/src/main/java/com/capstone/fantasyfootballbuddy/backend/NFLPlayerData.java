
package com.capstone.fantasyfootballbuddy.backend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NFLPlayerData {
    
    public static void sendToPlayerTable(String text) {

        try {
            
            Connection conn = DBManager.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO nflplayer "
                + "(player_id, first_name, last_name, position_id, team_id, status, birthdate, height, weight)" 
                + " VALUES (?,?,?,(SELECT position_id FROM nflposition WHERE position_name = ?),?,?,?,?,?)"
                + "ON CONFLICT (player_id) DO UPDATE SET "
                + "first_name = EXCLUDED.first_name, "
                + "last_name = EXCLUDED.last_name, "
                + "position_id = EXCLUDED.position_id, "
                + "team_id = EXCLUDED.team_id, "
                + "status = EXCLUDED.status, "
                + "birthdate = EXCLUDED.birthdate, "
                + "height = EXCLUDED.height, "
                + "weight = EXCLUDED.weight;");

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(text);
            JsonNode body = root.get("body");
            
            for (JsonNode player : body) {
                int playerID = player.hasNonNull("espnID") ? player.get("espnID").asInt() : 0;
                String name = player.hasNonNull("espnName") ? player.get("espnName").asText() : "No Name";
                String position = player.hasNonNull("pos") ? player.get("pos").asText() : "";
                int teamID = player.hasNonNull("teamID") ? player.get("teamID").asInt() : 0;
                String status = player.hasNonNull("isFreeAgent") ? player.get("isFreeAgent").asText() : "";
                String birthday = player.hasNonNull("bDay") ? player.get("bDay").asText() : "";
                String height = player.hasNonNull("height") ? player.get("height").asText() : "";
                int weight = player.hasNonNull("weight") ? player.get("weight").asInt() : 0;

                String[] fullName = name.split(" ", 2);
                String firstName = fullName[0];
                String lastName = fullName[1];
                DateFormat df = new SimpleDateFormat("M/d/yyyy");
                
                java.sql.Date sqlDate = null;
                
                if (birthday.isEmpty()) {
                    
                    sqlDate = null;
                    
                } else {
                    
                    java.util.Date javaDate = df.parse(birthday);
                    sqlDate = new java.sql.Date(javaDate.getTime());
                    
                }

                pstmt.setInt(1, playerID);
                pstmt.setString(2, firstName);
                pstmt.setString(3, lastName);
                pstmt.setString(4, position);
                pstmt.setInt(5, teamID);
                pstmt.setString(6, status);
                pstmt.setDate(7, sqlDate);
                pstmt.setString(8, height);
                pstmt.setInt(9, weight);
               
                pstmt.executeUpdate();
                
            }
        } catch (Exception e) {
            
            System.out.println("Unable to instert into table: nflplayer");
            e.printStackTrace();
            
        }
    }     
}
