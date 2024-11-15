package cho.info.foundation.modules.playerlogging;

import cho.info.foundation.Foundation;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerLoggingManager {

    private final File dataFile;
    private final FileConfiguration playerData;

    public PlayerLoggingManager(Foundation foundation) {
        // Create "data" folder if it doesn't exist
        File dataFolder = new File(foundation.getDataFolder(), "data");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        // Initialize the data file
        dataFile = new File(dataFolder, "playerData.yml");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                foundation.getLogger().severe("Could not create playerData.yml: " + e.getMessage());
            }
        }

        // Load the YAML file
        playerData = YamlConfiguration.loadConfiguration(dataFile);
    }

    /**
     * Checks if a player has joined before.
     *
     * @param playerName The player's name.
     * @return true if the player has joined before, false otherwise.
     */
    public boolean hasJoinedBefore(String playerName) {
        return playerData.contains("players." + playerName);
    }

    /**
     * Adds a player to the data file.
     *
     * @param playerName The player's name.
     */
    public void addPlayer(String playerName) {
        playerData.set("players." + playerName, true);
        saveData();
    }

    /**
     * Saves the YAML data to the file.
     */
    private void saveData() {
        try {
            playerData.save(dataFile);
        } catch (IOException e) {
            System.err.println("Could not save playerData.yml: " + e.getMessage());
        }
    }

}
