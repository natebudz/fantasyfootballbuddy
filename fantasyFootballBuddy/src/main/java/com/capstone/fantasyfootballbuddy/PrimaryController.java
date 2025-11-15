package com.capstone.fantasyfootballbuddy;

import com.capstone.fantasyfootballbuddy.backend.NFLPlayerData;
import com.capstone.fantasyfootballbuddy.backend.NFLTeamData;
import com.capstone.fantasyfootballbuddy.backend.TestAPI;
import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
        
       //NFLTeamData.sendToTeamTable(TestAPI.grabNFLTeamString());
        
       NFLPlayerData.sendToPlayerTable(TestAPI.grabNFLPlayerString());
    }
}
