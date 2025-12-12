
package com.capstone.fantasyfootballbuddy.backend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class NFLGameData {
    
    public static void sendGameToTable(String text) {

        try {
            
            int seasonYear = 2025;
            
            Connection conn = DBManager.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO nflgame "
                + "(game_id, season_year, week, game_date, home_team_id, away_team_id, home_team_score, away_team_score, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) "
                + "ON CONFLICT (game_id) DO UPDATE SET "
                + "season_year = EXCLUDED.season_year, "
                + "week = EXCLUDED.week, "
                + "game_date = EXCLUDED.game_date, "
                + "home_team_id = EXCLUDED.home_team_id, "
                + "away_team_id = EXCLUDED.away_team_id, "
                + "home_team_score = EXCLUDED.home_team_score, "
                + "away_team_score = EXCLUDED.away_team_score, "
                + "status = EXCLUDED.status;"
            );

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(text);
            JsonNode body = root.get("body");
            JsonNode schedule = body.get("schedule");
            
            for (JsonNode game : schedule) {
                String gameID = game.hasNonNull("gameID") ? game.get("gameID").asText() : "";
                String gameWeek = game.hasNonNull("gameWeek") ? game.get("gameWeek").asText() : "Unknown Week";
                String gameDate = game.hasNonNull("gameDate") ? game.get("gameDate").asText() : "";
                int homeTeamId = game.hasNonNull("teamIDHome") ? game.get("teamIDHome").asInt() : 0;
                int awayTeamId = game.hasNonNull("teamIDAway") ? game.get("teamIDAway").asInt() : 0;
                int homePts = game.hasNonNull("homePts") ? game.get("homePts").asInt() : 0;
                int awayPts = game.hasNonNull("awayPts") ? game.get("awayPts").asInt() : 0;
                String status = game.hasNonNull("gameStatus") ? game.get("gameStatus").asText() : "Unknown";

                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                
                java.sql.Date sqlDate = null;
                
                if (gameDate.isEmpty()) {
                    
                    sqlDate = null;
                    
                } else {
                    
                    java.util.Date javaDate = df.parse(gameDate);
                    sqlDate = new java.sql.Date(javaDate.getTime());
                    
                }
                   
                pstmt.setString(1, gameID);
                pstmt.setInt(2, seasonYear);
                pstmt.setString(3, gameWeek);
                pstmt.setDate(4, sqlDate);
                pstmt.setInt(5, homeTeamId);
                pstmt.setInt(6, awayTeamId);
                pstmt.setInt(7, homePts);
                pstmt.setInt(8, awayPts);
                pstmt.setString(9, status);
               
                pstmt.executeUpdate();
                
            }
        } catch (Exception e) {
            
            System.out.println("Unable to instert into table: nflgame");
            e.printStackTrace();
            
        }
    }     
}


