package cho.info.foundation.modules.playertags;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class CustomPlayerData {

    private final File dataFile;
    private final FileConfiguration dataConfig;

    public CustomPlayerData(File pluginDataFolder) {
        // Erstelle den "data"-Ordner, falls er nicht existiert
        File dataFolder = new File(pluginDataFolder, "data");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        // Initialisiere die Datei innerhalb des "data"-Ordners
        this.dataFile = new File(dataFolder, "customPlayerData.yml");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Konnte customPlayerData.yml nicht erstellen: " + e.getMessage());
            }
        }

        // Lade die Datei
        this.dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    /**
     * Setzt einen benutzerdefinierten Wert für einen Spieler.
     *
     * @param playerUUID Die UUID des Spielers.
     * @param key        Der benutzerdefinierte Schlüssel.
     * @param value      Der Wert (kann INT, STRING, BOOL, DOUBLE sein).
     */
    public void setData(UUID playerUUID, String key, Object value) {
        dataConfig.set("players." + playerUUID + "." + key, value);
        save();
    }

    /**
     * Holt einen benutzerdefinierten Wert als String.
     *
     * @param playerUUID Die UUID des Spielers.
     * @param key        Der benutzerdefinierte Schlüssel.
     * @return Der gespeicherte Wert als String.
     */
    public String getString(UUID playerUUID, String key) {
        return dataConfig.getString("players." + playerUUID + "." + key, null);
    }

    /**
     * Holt einen benutzerdefinierten Wert als Integer.
     *
     * @param playerUUID Die UUID des Spielers.
     * @param key        Der benutzerdefinierte Schlüssel.
     * @return Der gespeicherte Wert als Integer.
     */
    public int getInt(UUID playerUUID, String key) {
        return dataConfig.getInt("players." + playerUUID + "." + key, 0);
    }

    /**
     * Holt einen benutzerdefinierten Wert als Boolean.
     *
     * @param playerUUID Die UUID des Spielers.
     * @param key        Der benutzerdefinierte Schlüssel.
     * @return Der gespeicherte Wert als Boolean.
     */
    public boolean getBoolean(UUID playerUUID, String key) {
        return dataConfig.getBoolean("players." + playerUUID + "." + key, false);
    }

    /**
     * Holt einen benutzerdefinierten Wert als Double.
     *
     * @param playerUUID Die UUID des Spielers.
     * @param key        Der benutzerdefinierte Schlüssel.
     * @return Der gespeicherte Wert als Double.
     */
    public double getDouble(UUID playerUUID, String key) {
        return dataConfig.getDouble("players." + playerUUID + "." + key, 0.0);
    }

    /**
     * Entfernt einen benutzerdefinierten Schlüssel.
     *
     * @param playerUUID Die UUID des Spielers.
     * @param key        Der benutzerdefinierte Schlüssel.
     */
    public void removeData(UUID playerUUID, String key) {
        dataConfig.set("players." + playerUUID + "." + key, null);
        save();
    }

    /**
     * Speichert die Datei.
     */
    private void save() {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            System.err.println("Konnte customPlayerData.yml nicht speichern: " + e.getMessage());
        }
    }
}
