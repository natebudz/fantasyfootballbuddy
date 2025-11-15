
package com.capstone.fantasyfootballbuddy.backend;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;

public class TestAPI {
    
    public static String grabNFLTeamString() {
        
        String text = "";
        
        try {
            
            AsyncHttpClient client = new DefaultAsyncHttpClient();
            text = client.prepare("GET", "https://tank01-nfl-live-in-game-real-time-statistics-nfl.p.rapidapi.com/getNFLTeams?sortBy=standings&rosters=false&schedules=false&topPerformers=true&teamStats=true&teamStatsSeason=2024")
            .setHeader("x-rapidapi-key", "f027be2167mshaca5ecb6034fd3cp146ad6jsne92e690fdb46")
            .setHeader("x-rapidapi-host", "tank01-nfl-live-in-game-real-time-statistics-nfl.p.rapidapi.com")
            .execute()
            .toCompletableFuture()
            .thenApply(response -> response.getResponseBody())
            .join();

            client.close();

        } catch(Exception e) {

            System.out.println("Unable to grab data from API");
            e.printStackTrace();

        } 
    
        return text;
        
    } 

    public static String grabNFLPlayerString() {
        
        String text = "";
        
        try {
            
            AsyncHttpClient client = new DefaultAsyncHttpClient();
            text = client.prepare("GET", "https://tank01-nfl-live-in-game-real-time-statistics-nfl.p.rapidapi.com/getNFLPlayerList")
            .setHeader("x-rapidapi-key", "f027be2167mshaca5ecb6034fd3cp146ad6jsne92e690fdb46")
            .setHeader("x-rapidapi-host", "tank01-nfl-live-in-game-real-time-statistics-nfl.p.rapidapi.com")
            .execute()
            .toCompletableFuture()
            .thenApply(response -> response.getResponseBody())
            .join();

            client.close();

        } catch(Exception e) {

            System.out.println("Unable to grab data from API");
            e.printStackTrace();

        } 
    
        return text;
        
    }
}
