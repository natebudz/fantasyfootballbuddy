package com.capstone.fantasyfootballbuddy;

import com.capstone.fantasyfootballbuddy.backend.DBManager;
import com.capstone.fantasyfootballbuddy.backend.NFLPlayerData;
import com.capstone.fantasyfootballbuddy.backend.NFLTeamData;
import com.capstone.fantasyfootballbuddy.backend.NFLGameData;
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    @FXML private VBox playerVBox;
    @FXML private VBox hotSeatVBox;
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
    @FXML private ComboBox<String> selectTeam;
    @FXML private VBox swapTeamVBox;
    @FXML private Label qbrValue;
    @FXML private Label passerRatingValue;
    @FXML private Label timesSackedValue;
    @FXML private Label passAttemptsValue;
    @FXML private Label yardsPerPassValue;
    @FXML private Label passingTDValue;
    @FXML private Label passingYardsValue;
    @FXML private Label interceptionsValue;
    @FXML private Label passCompletionsValue;
    @FXML private Label receptionsValue;
    @FXML private Label receivingTDValue;
    @FXML private Label longestReceptionValue;
    @FXML private Label targetsValue;
    @FXML private Label receivingYardsValue;
    @FXML private Label receivingAverageValue;
    @FXML private Label rushingAverageValue;
    @FXML private Label rushingYardsValue;
    @FXML private Label rushingAttemptsValue;
    @FXML private Label longestRushValue;
    @FXML private Label rushingTDValue;
    @FXML private HBox qbrBox;
    @FXML private HBox passerRatingBox;
    @FXML private HBox timesSackedBox;
    @FXML private HBox passAttemptsBox;
    @FXML private HBox yardsPerPassBox;
    @FXML private HBox passingTDBox;
    @FXML private HBox passingYardsBox;
    @FXML private HBox interceptionsBox;
    @FXML private HBox passCompletionsBox;
    @FXML private HBox receptionsBox;
    @FXML private HBox receivingTDBox;
    @FXML private HBox longestReceptionBox;
    @FXML private HBox targetsBox;
    @FXML private HBox receivingYardsBox;
    @FXML private HBox receivingAverageBox;
    @FXML private HBox rushingAverageBox;
    @FXML private HBox rushingYardsBox;
    @FXML private HBox rushingAttemptsBox;
    @FXML private HBox longestRushBox;
    @FXML private HBox rushingTDBox;

    private StackPane[] playerPanes;
    private Label[] names;
    private Label[] positions;
    private Label[] teams;
    private StackPane hotSeatPane;
   
    private final List<String> savedPlayers = new ArrayList<>();
    private final String PANESTYLE = "-fx-border-color: grey; -fx-border-width: 1;";
    
    @FXML
    private void initialize() {
        

        
        mainHBox.setVisible(true);
        saveVBox.setVisible(false);
        searchVBox.setVisible(false);
        swapTeamVBox.setVisible(false);

        searchField.textProperty().addListener((obs, oldText, newText) -> updateResults(newText));

        playerList.setOnMouseClicked(e -> {
            
            String selected = playerList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                
                addPlayerChip(selected);
                searchField.clear();
                playerList.getItems().clear();
                
            }
        });
        
        for (Node paneNode : playerVBox.getChildren()) {
            
            if (paneNode instanceof StackPane) {
                
                StackPane pane = (StackPane) paneNode;
                pane.setOnMouseEntered(e -> pane.setStyle("-fx-background-color: lightblue;"));
                pane.setOnMouseExited(e -> pane.setStyle(PANESTYLE));
                
                pane.setOnMouseClicked(e -> moveToHotSeat(pane));
                
            }
        }
        
        hotSeatVBox.setOnMouseClicked(e -> moveBackLeft());
        
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

            try (ResultSet results = stmt.executeQuery()) {
                
                while (results.next()) {
                    playerList.getItems().add(results.getString("full_name"));
                    
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
        swapTeamVBox.setVisible(false);
        
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
        swapTeamVBox.setVisible(false);
        
    }
    
    @FXML
    private void handleNextButton() {
        
        savedPlayers.forEach(System.out::println);
        mainHBox.setVisible(false);
        searchVBox.setVisible(false);
        saveVBox.setVisible(true);
        swapTeamVBox.setVisible(false);
        
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
        swapTeamVBox.setVisible(false);
        
    }
    
    @FXML
    private void handleBackRosterButton() {
        
        
        rosterNameField.setText("");
        saveVBox.setVisible(false);
        mainHBox.setVisible(false);
        searchVBox.setVisible(true);
        swapTeamVBox.setVisible(false);
        
    }
    
    @FXML
    private void handleEditTeamButton() {
        
        System.out.println("Nothing yet");
        
    }
    
    @FXML private void handleSwapTeamButton() {
        
        loadSwapTeams();
        saveVBox.setVisible(false);
        mainHBox.setVisible(false);
        searchVBox.setVisible(false);
        swapTeamVBox.setVisible(true);
        moveBackLeft();
        
    }
    
    @FXML private void handleSelectTeamOKButton() {
        
        String selectedTeam = selectTeam.getValue();
        grabRosterFromFile(selectedTeam);
        
        saveVBox.setVisible(false);
        searchVBox.setVisible(false);
        mainHBox.setVisible(true);
        swapTeamVBox.setVisible(false);
        
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
    
    
    private void loadSwapTeams() {
        
        ArrayList<String> addedTeams = new ArrayList<>();
        selectTeam.getItems().clear();
    
        try (BufferedReader br = new BufferedReader(new FileReader("rosters.csv"))) {
            
            String line;
            while ((line = br.readLine()) != null) {
                
                String[] values = line.split(",");

                if (values.length > 0) {
                    
                    String teamName = values[0].trim();
                    
                    if (!addedTeams.contains(teamName)) {
                        
                        addedTeams.add(teamName);
                        selectTeam.getItems().add(teamName);
                        
                    }
                }
            }
            
            if (!selectTeam.getItems().isEmpty()) {

                selectTeam.getSelectionModel().select(0);

            }
        } catch (Exception e) {
            
            System.out.println("Unable to load teams");
            e.printStackTrace();
            
        }
    }
    
    private void moveToHotSeat(StackPane pane) {
        
        if (hotSeatPane == null) {

            playerVBox.getChildren().remove(pane);
            hotSeatVBox.getChildren().add(pane);
            hotSeatPane = pane;
            
            String position = grabHotSeatPosition();
            
            if (position.equals("QB")) {
                
                qbStatPull(grabHotSeatName());
                
            }
            else if ((position.equals("RB") || position.equals("FB"))) {
                
                rbStatPull(grabHotSeatName());
                
            }
            else if ((position.equals("WR") || position.equals("TE"))) {
                
                wrStatPull(grabHotSeatName());
                
            }
        }
    }
    
    private void moveBackLeft() {
        
        if (hotSeatPane != null) {

            hotSeatVBox.getChildren().remove(hotSeatPane);
            playerVBox.getChildren().add(hotSeatPane);
            hotSeatPane = null;
            
            qbrBox.setVisible(false);
            passerRatingBox.setVisible(false);
            timesSackedBox.setVisible(false);
            passAttemptsBox.setVisible(false);
            yardsPerPassBox.setVisible(false);
            passingTDBox.setVisible(false);
            passingYardsBox.setVisible(false);
            interceptionsBox.setVisible(false);
            passCompletionsBox.setVisible(false);
            receptionsBox.setVisible(false);
            receivingTDBox.setVisible(false);
            longestReceptionBox.setVisible(false);
            targetsBox.setVisible(false);
            receivingYardsBox.setVisible(false);
            receivingAverageBox.setVisible(false);
            rushingAverageBox.setVisible(false);
            rushingYardsBox.setVisible(false);
            rushingAttemptsBox.setVisible(false);
            longestRushBox.setVisible(false);
            rushingTDBox.setVisible(false);
            
            
            qbrBox.setManaged(false);
            passerRatingBox.setManaged(false);
            timesSackedBox.setManaged(false);
            passAttemptsBox.setManaged(false);
            yardsPerPassBox.setManaged(false);
            passingTDBox.setManaged(false);
            passingYardsBox.setManaged(false);
            interceptionsBox.setManaged(false);
            passCompletionsBox.setManaged(false);
            receptionsBox.setManaged(false);
            receivingTDBox.setManaged(false);
            longestReceptionBox.setManaged(false);
            targetsBox.setManaged(false);
            receivingYardsBox.setManaged(false);
            receivingAverageBox.setManaged(false);
            rushingAverageBox.setManaged(false);
            rushingYardsBox.setManaged(false);
            rushingAttemptsBox.setManaged(false);
            longestRushBox.setManaged(false);
            rushingTDBox.setManaged(false);
            
            qbrValue.setText("99");
            passerRatingValue.setText("99");
            timesSackedValue.setText("99");
            passAttemptsValue.setText("99");
            yardsPerPassValue.setText("99");
            passingTDValue.setText("99");
            passingYardsValue.setText("99");
            interceptionsValue.setText("99");
            passCompletionsValue.setText("99");
            rushingAverageValue.setText("99");
            rushingYardsValue.setText("99");
            rushingAttemptsValue.setText("99");
            longestRushValue.setText("99");
            rushingTDValue.setText("99");
            receptionsValue.setText("99");
            receivingTDValue.setText("99");
            longestReceptionValue.setText("99");
            targetsValue.setText("99");
            receivingYardsValue.setText("99");
            receivingAverageValue.setText("99");
 
        }
    }
    
    private void qbStatPull(String name) {
        
        qbrBox.setVisible(false);
        passerRatingBox.setVisible(false);
        timesSackedBox.setVisible(true);
        passAttemptsBox.setVisible(true);
        yardsPerPassBox.setVisible(true);
        passingTDBox.setVisible(true);
        passingYardsBox.setVisible(true);
        interceptionsBox.setVisible(true);
        passCompletionsBox.setVisible(true);
        rushingAverageBox.setVisible(true);
        rushingYardsBox.setVisible(true);
        rushingAttemptsBox.setVisible(true);
        longestRushBox.setVisible(true);
        rushingTDBox.setVisible(true);

        qbrBox.setManaged(false);
        passerRatingBox.setManaged(false);
        timesSackedBox.setManaged(true);
        passAttemptsBox.setManaged(true);
        yardsPerPassBox.setManaged(true);
        passingTDBox.setManaged(true);
        passingYardsBox.setManaged(true);
        interceptionsBox.setManaged(true);
        passCompletionsBox.setManaged(true);
        rushingAverageBox.setManaged(true);
        rushingYardsBox.setManaged(true);
        rushingAttemptsBox.setManaged(true);
        longestRushBox.setManaged(true);
        rushingTDBox.setManaged(true);

        receptionsBox.setVisible(false);
        receivingTDBox.setVisible(false);
        longestReceptionBox.setVisible(false);
        targetsBox.setVisible(false);
        receivingYardsBox.setVisible(false);
        receivingAverageBox.setVisible(false);

        receptionsBox.setManaged(false);
        receivingTDBox.setManaged(false);
        longestReceptionBox.setManaged(false);
        targetsBox.setManaged(false);
        receivingYardsBox.setManaged(false);
        receivingAverageBox.setManaged(false);

        
        
        String statSql = "SELECT ps.stat_id, ps.season_stat_value "
        + "FROM nflplayerseasonstat ps "
        + "JOIN nflplayer p ON ps.player_id = p.player_id "
        + "WHERE p.first_name = ? "
        + "AND p.last_name = ? "
        + "AND ps.stat_id IN (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(statSql)) {
                
            String[] fullName = name.split(" ", 2);
            String firstName = fullName[0];
            String lastName = fullName[1];
            
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, 1);
            stmt.setInt(4, 2);
            stmt.setInt(5, 3);
            stmt.setInt(6, 4);
            stmt.setInt(7, 5);
            stmt.setInt(8, 6);
            stmt.setInt(9, 7);
            stmt.setInt(10, 8);
            stmt.setInt(11, 10);
            stmt.setInt(12, 17);
            stmt.setInt(13, 18);
            stmt.setInt(14, 19);
            stmt.setInt(15, 20);
            stmt.setInt(16, 21);

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                
                int statId = results.getInt("stat_id");
                double value = results.getDouble("season_stat_value");
                
                String statValue = String.valueOf(value);

                switch (statId) {
                    
                    case 1:
                        qbrValue.setText(statValue);
                        break;
                    case 2:
                        passerRatingValue.setText(statValue);
                        break;
                    case 3:
                        timesSackedValue.setText(statValue);
                        break;
                    case 4:
                        passAttemptsValue.setText(statValue);
                        break;
                    case 5:
                        yardsPerPassValue.setText(statValue);
                        break;
                    case 6:
                        passingTDValue.setText(statValue);
                        break;
                    case 7:
                        passingYardsValue.setText(statValue);
                        break;
                    case 8:
                        interceptionsValue.setText(statValue);
                        break;
                    case 10:
                        passCompletionsValue.setText(statValue);
                        break;
                    case 17:
                        rushingAverageValue.setText(statValue);
                        break;
                    case 18:
                        rushingYardsValue.setText(statValue);
                        break;
                    case 19:
                        rushingAttemptsValue.setText(statValue);
                        break;
                    case 20:
                        longestRushValue.setText(statValue);
                        break;
                    case 21:
                        rushingTDValue.setText(statValue);
                        break;
                    default:
                        break;
                        
                }
            }
        } catch (Exception e) {

            System.out.println("error");
            e.printStackTrace();

        }
    }
   
    private void rbStatPull(String name) {
        
        receptionsBox.setVisible(true);
        receivingTDBox.setVisible(true);
        longestReceptionBox.setVisible(true);
        targetsBox.setVisible(true);
        receivingYardsBox.setVisible(true);
        receivingAverageBox.setVisible(true);
        rushingAverageBox.setVisible(true);
        rushingYardsBox.setVisible(true);
        rushingAttemptsBox.setVisible(true);
        longestRushBox.setVisible(true);
        rushingTDBox.setVisible(true);
        

        receptionsBox.setManaged(true);
        receivingTDBox.setManaged(true);
        longestReceptionBox.setManaged(true);
        targetsBox.setManaged(true);
        receivingYardsBox.setManaged(true);
        receivingAverageBox.setManaged(true);
        rushingAverageBox.setManaged(true);
        rushingYardsBox.setManaged(true);
        rushingAttemptsBox.setManaged(true);
        longestRushBox.setManaged(true);
        rushingTDBox.setManaged(true);
        
        
        qbrBox.setVisible(false);
        passerRatingBox.setVisible(false);
        timesSackedBox.setVisible(false);
        passAttemptsBox.setVisible(false);
        yardsPerPassBox.setVisible(false);
        passingTDBox.setVisible(false);
        passingYardsBox.setVisible(false);
        interceptionsBox.setVisible(false);
        passCompletionsBox.setVisible(false);


        qbrBox.setManaged(false);
        passerRatingBox.setManaged(false);
        timesSackedBox.setManaged(false);
        passAttemptsBox.setManaged(false);
        yardsPerPassBox.setManaged(false);
        passingTDBox.setManaged(false);
        passingYardsBox.setManaged(false);
        interceptionsBox.setManaged(false);
        passCompletionsBox.setManaged(false);
        
        
        String statSql = "SELECT ps.stat_id, ps.season_stat_value "
        + "FROM nflplayerseasonstat ps "
        + "JOIN nflplayer p ON ps.player_id = p.player_id "
        + "WHERE p.first_name = ? "
        + "AND p.last_name = ? "
        + "AND ps.stat_id IN (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(statSql)) {
                
            String[] fullName = name.split(" ", 2);
            String firstName = fullName[0];
            String lastName = fullName[1];
            
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, 11);
            stmt.setInt(4, 12);
            stmt.setInt(5, 13);
            stmt.setInt(6, 14);
            stmt.setInt(7, 15);
            stmt.setInt(8, 16);
            stmt.setInt(9, 17);
            stmt.setInt(10, 18);
            stmt.setInt(11, 19);
            stmt.setInt(12, 20);
            stmt.setInt(13, 21);

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                
                int statId = results.getInt("stat_id");
                double value = results.getDouble("season_stat_value");
                
                String statValue = String.valueOf(value);

                switch (statId) {
                    
                    case 11:
                        receptionsValue.setText(statValue);
                        break;
                    case 12:
                        receivingTDValue.setText(statValue);
                        break;
                    case 13:
                        longestReceptionValue.setText(statValue);
                        break;
                    case 14:
                        targetsValue.setText(statValue);
                        break;
                    case 15:
                        receivingYardsValue.setText(statValue);
                        break;
                    case 16:
                        receivingAverageValue.setText(statValue);
                        break;
                    case 17:
                        rushingAverageValue.setText(statValue);
                        break;
                    case 18:
                        rushingYardsValue.setText(statValue);
                        break;
                    case 19:
                        rushingAttemptsValue.setText(statValue);
                        break;
                    case 20:
                        longestRushValue.setText(statValue);
                        break;
                    case 21:
                        rushingTDValue.setText(statValue);
                    default:
                        break;
                        
                }
            }
        } catch (Exception e) {

            System.out.println("error");
            e.printStackTrace();

        } 
    }
    
    
    private void wrStatPull(String name) {
        
        rbStatPull(name);
        
        receptionsBox.setVisible(true);
        receivingTDBox.setVisible(true);
        longestReceptionBox.setVisible(true);
        targetsBox.setVisible(true);
        receivingYardsBox.setVisible(true);
        receivingAverageBox.setVisible(true);
        rushingAverageBox.setVisible(true);
        rushingYardsBox.setVisible(true);
        rushingAttemptsBox.setVisible(true);
        longestRushBox.setVisible(true);
        rushingTDBox.setVisible(true);
        

        receptionsBox.setManaged(true);
        receivingTDBox.setManaged(true);
        longestReceptionBox.setManaged(true);
        targetsBox.setManaged(true);
        receivingYardsBox.setManaged(true);
        receivingAverageBox.setManaged(true);
        rushingAverageBox.setManaged(true);
        rushingYardsBox.setManaged(true);
        rushingAttemptsBox.setManaged(true);
        longestRushBox.setManaged(true);
        rushingTDBox.setManaged(true);
        
        
        qbrBox.setVisible(false);
        passerRatingBox.setVisible(false);
        timesSackedBox.setVisible(false);
        passAttemptsBox.setVisible(false);
        yardsPerPassBox.setVisible(false);
        passingTDBox.setVisible(false);
        passingYardsBox.setVisible(false);
        interceptionsBox.setVisible(false);
        passCompletionsBox.setVisible(false);


        qbrBox.setManaged(false);
        passerRatingBox.setManaged(false);
        timesSackedBox.setManaged(false);
        passAttemptsBox.setManaged(false);
        yardsPerPassBox.setManaged(false);
        passingTDBox.setManaged(false);
        passingYardsBox.setManaged(false);
        interceptionsBox.setManaged(false);
        passCompletionsBox.setManaged(false);

    }
    
    private String grabHotSeatName() {
        
        if (hotSeatPane != null) {
            Label hotSeatPlayerNameLabel = (Label) hotSeatPane.getChildren().get(0);
            String playerName = hotSeatPlayerNameLabel.getText();
            return playerName;
            
        }
        
        return null;
        
    }
    
    private String grabHotSeatPosition() {
        
        if (hotSeatPane != null) {

            Label hotSeatPlayerPositionLabel = (Label) hotSeatPane.getChildren().get(2);
            String playerPosition = hotSeatPlayerPositionLabel.getText();
            return playerPosition;
            
        }
        
        return null;
        
    }
}
