package com.capstone.fantasyfootballbuddy;

import com.capstone.fantasyfootballbuddy.backend.DBManager;
import com.capstone.fantasyfootballbuddy.backend.NFLPlayerData;
import com.capstone.fantasyfootballbuddy.backend.NFLTeamData;
import com.capstone.fantasyfootballbuddy.backend.Player;
import com.capstone.fantasyfootballbuddy.backend.Roster;
import com.capstone.fantasyfootballbuddy.backend.TestAPI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PrimaryController {

    @FXML private TextField searchField;
    @FXML private ListView<String> playerList;
    @FXML private FlowPane selectedPlayers;
    @FXML private TextField rosterNameField;
    @FXML private VBox saveVBox;
    @FXML private VBox searchVBox;
    @FXML private HBox mainHBox;
    @FXML private StackPane pStackPane1;
    @FXML private StackPane pStackPane2;
    @FXML private StackPane pStackPane3;
    @FXML private StackPane pStackPane4;
    @FXML private StackPane pStackPane5;
    @FXML private StackPane pStackPane6;
    @FXML private StackPane pStackPane7;
    @FXML private StackPane pStackPane8;
    @FXML private StackPane pStackPane9;
    @FXML private StackPane pStackPane10;
    @FXML private StackPane pStackPane11;
    @FXML private StackPane pStackPane12;
    @FXML private Label pName1;
    @FXML private Label pName2;
    @FXML private Label pName3;
    @FXML private Label pName4;
    @FXML private Label pName5;
    @FXML private Label pName6;
    @FXML private Label pName7;
    @FXML private Label pName8;
    @FXML private Label pName9;
    @FXML private Label pName10;
    @FXML private Label pName11;
    @FXML private Label pName12;
    @FXML private Label pPos1;
    @FXML private Label pPos2;
    @FXML private Label pPos3;
    @FXML private Label pPos4;
    @FXML private Label pPos5;
    @FXML private Label pPos6;
    @FXML private Label pPos7;
    @FXML private Label pPos8;
    @FXML private Label pPos9;
    @FXML private Label pPos10;
    @FXML private Label pPos11;
    @FXML private Label pPos12;
    @FXML private Label pTeam1;
    @FXML private Label pTeam2;
    @FXML private Label pTeam3;
    @FXML private Label pTeam4;
    @FXML private Label pTeam5;
    @FXML private Label pTeam6;
    @FXML private Label pTeam7;
    @FXML private Label pTeam8;
    @FXML private Label pTeam9;
    @FXML private Label pTeam10;
    @FXML private Label pTeam11;
    @FXML private Label pTeam12;

    private StackPane[] playerPanes;
    private Label[] names;
    private Label[] positions;
    private Label[] teams;
   
    private final List<String> savedPlayers = new ArrayList<>();
    
    @FXML
    private void initialize() {
        
        mainHBox.setVisible(true);
        saveVBox.setVisible(false);
        searchVBox.setVisible(false);

        searchField.textProperty().addListener((obs, oldText, newText) -> updateResults(newText));

        playerList.setOnMouseClicked(e -> {
            String selected = playerList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                
                addPlayerChip(selected);
                searchField.clear();
                playerList.getItems().clear();
                
            }
        });
        
        playerPanes = new StackPane[]{
                    
            pStackPane1, pStackPane2, pStackPane3, pStackPane4,
            pStackPane5, pStackPane6, pStackPane7, pStackPane8,
            pStackPane9, pStackPane10, pStackPane11, pStackPane12
                        
        };

        names = new Label[]{
            
            pName1, pName2, pName3, pName4, pName5, pName6,
            pName7, pName8, pName9, pName10, pName11, pName12
                
        };

        positions = new Label[]{
            
            pPos1, pPos2, pPos3, pPos4, pPos5, pPos6,
            pPos7, pPos8, pPos9, pPos10, pPos11, pPos12
                
        };

        teams = new Label[]{
            
            pTeam1, pTeam2, pTeam3, pTeam4, pTeam5, pTeam6,
            pTeam7, pTeam8, pTeam9, pTeam10, pTeam11, pTeam12
                
        };
    }

    private void updateResults(String query) {
        
        playerList.getItems().clear();
        if (query == null || query.isBlank()) return;

        String[] parts = query.trim().split("\\s+");
        String sql;

        if (parts.length == 1) {

            sql = "SELECT first_name || ' ' || last_name AS full_name "
                + "FROM nflplayer "
                + "WHERE first_name ILIKE ? OR last_name ILIKE ? "
                + "LIMIT 25";
            
        } else {

            sql = "SELECT first_name || ' ' || last_name AS full_name "
                + "FROM nflplayer "
                + "WHERE first_name ILIKE ? AND last_name ILIKE ? "
                + "LIMIT 25";
            
        }

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (parts.length == 1) {
                
                stmt.setString(1, parts[0] + "%");
                stmt.setString(2, parts[0] + "%");
                
            } else {
                
                stmt.setString(1, parts[0] + "%");
                stmt.setString(2, parts[1] + "%");
                
            }

            try (ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    playerList.getItems().add(rs.getString("full_name"));
                    
                }
            }
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
    }

    private void addPlayerChip(String playerName) {
        
        boolean alreadyAdded = selectedPlayers.getChildren().stream().anyMatch(c -> {
            
            Label label = (Label) ((HBox) c).getChildren().get(0);
            return label.getText().equals(playerName);
            
        });
        
        if (alreadyAdded) return;
        
        savedPlayers.add(playerName);
        
        HBox chip = new HBox();
        chip.setSpacing(5);
        chip.setPadding(new Insets(5, 10, 5, 10));
        chip.setStyle("-fx-background-color: #e6e6e6; -fx-background-radius: 12; -fx-border-radius: 12;");

        Label nameLabel = new Label(playerName);

        Button closeBtn = new Button("X");
        closeBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: red;");
        closeBtn.setOnAction(e -> {
            
                selectedPlayers.getChildren().remove(chip);
                savedPlayers.remove(playerName);
                
                
            });

        chip.getChildren().addAll(nameLabel, closeBtn);
        selectedPlayers.getChildren().add(chip);
        
    }
    
    
    @FXML
    private void handleCreateTeamButton() {
        
        mainHBox.setVisible(false);
        saveVBox.setVisible(false);
        searchVBox.setVisible(true);
        
    }
    
    @FXML
    private void handleCancelAddButton() {
        
        searchField.setText("");
        playerList.getItems().clear();
        selectedPlayers.getChildren().clear();
        mainHBox.setVisible(true);
        saveVBox.setVisible(false);
        searchVBox.setVisible(false);
        savedPlayers.clear();
        
    }
    
    @FXML
    private void handleNextButton() {
        
        savedPlayers.forEach(System.out::println);
        mainHBox.setVisible(false);
        searchVBox.setVisible(false);
        saveVBox.setVisible(true);
        
    }
    
    @FXML
    private void handleSaveRosterButton() {
        
        
        String rosterName = rosterNameField.getText();
        Roster newRoster = new Roster(rosterName);
        
        String playerSql = "SELECT p.first_name, p.last_name, pos.position_name, t.city, t.team_name "
                + "FROM nflplayer p "
                + "JOIN nflposition pos ON p.position_id = pos.position_id "
                + "JOIN nflteam t ON p.team_id = t.team_id "
                + "WHERE p.first_name = ? AND p.last_name = ?";
        
        
        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(playerSql)) {
            
            for (String player : savedPlayers) {

            String[] fullName = player.split(" ", 2);
            String firstName = fullName[0];
            String lastName = fullName[1];

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);

            Player newPlayer = new Player();

            ResultSet results = stmt.executeQuery();
            
            if (results.next()) {

                newPlayer.setFirstName(results.getString("first_name"));
                newPlayer.setLastName(results.getString("last_name"));
                newPlayer.setPosition(results.getString("position_name"));
                newPlayer.setTeam(results.getString("city") + " " 
                + results.getString("team_name"));

            }
            
            newRoster.addPlayer(newPlayer);
            
            }
            
            writeToRosterFile(newRoster);
            
        } catch (Exception e) {
            
            System.out.println("Error");
            e.printStackTrace();
            
        }
        
        grabRosterFromFile(rosterName);
        rosterNameField.setText("");
        saveVBox.setVisible(false);
        searchVBox.setVisible(false);
        mainHBox.setVisible(true);
        
    }
    
    @FXML
    private void handleBackRosterButton() {
        
        
        rosterNameField.setText("");
        saveVBox.setVisible(false);
        mainHBox.setVisible(false);
        searchVBox.setVisible(true);
        
    }
    
    @FXML
    private void handleEditTeamButton() {
        
        System.out.println("Nothing yet");
        
    }
    
    private void writeToRosterFile(Roster roster) {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rosters.csv", true))) {

            for (Player player : roster.getPlayers()) {
                String line = roster.getRosterName() + "," 
                            + player.getFirstName() + "," 
                            + player.getLastName() + "," 
                            + player.getPosition() + "," 
                            + player.getTeam();

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println("Error");
            e.printStackTrace();

        }
    }
    
    
    private void grabRosterFromFile(String inputTeam) {
        
        List<Player> players = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("rosters.csv"))) {

            String line;
            while ((line = reader.readLine()) != null) {
            
                String[] parts = line.split(",");
                String teamName = parts[0];
                String firstName = parts[1];
                String lastName = parts[2];
                String pos = parts[3];
                String nflTeam = parts[4];

                if (teamName.equals(inputTeam)) {
                    
                    players.add(new Player(firstName, lastName, pos, nflTeam));
                    
                }
            }
        } catch (Exception e) {
            
            System.out.println("Error");
            e.printStackTrace();
            
        }
        
        for (int i = 0; i < 12; i++) {
                
            playerPanes[i].setVisible(false);
            playerPanes[i].setManaged(false);
            
        }

        for (int i = 0; i < players.size() && i < 12; i++) {

            Player p = players.get(i);
            names[i].setText(p.getFirstName() + " " + p.getLastName());
            teams[i].setText(p.getTeam());
            positions[i].setText(p.getPosition());
            playerPanes[i].setVisible(true);
            playerPanes[i].setManaged(true);
            
        }
    }
}
