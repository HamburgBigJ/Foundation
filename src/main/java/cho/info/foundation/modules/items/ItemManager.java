package cho.info.foundation.modules.items;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    private final Plugin plugin;
    private final File itemsFolder;
    private final Map<String, Item> items;

    public ItemManager(Plugin plugin) {
        this.plugin = plugin;
        this.itemsFolder = new File(plugin.getDataFolder(), "items");
        this.items = new HashMap<>();

        if (!itemsFolder.exists()) {
            itemsFolder.mkdirs();
        }

        loadAllItems();
    }

    public void saveItem(String key, Item item) {
        File file = new File(itemsFolder, key + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        item.saveToConfig(config, key);

        try {
            config.save(file);
            items.put(key, item);
        } catch (IOException e) {
            plugin.getLogger().warning("Fehler beim Speichern des Items: " + e.getMessage());
        }
    }

    public Item loadItem(String key) {
        File file = new File(itemsFolder, key + ".yml");
        if (!file.exists()) return null;

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Item item = Item.loadFromConfig(config, key, plugin);
        items.put(key, item);
        return item;
    }

    public void loadAllItems() {
        for (File file : itemsFolder.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".yml")) {
                String key = file.getName().replace(".yml", "");
                loadItem(key);
            }
        }
    }

    public Map<String, Item> getItems() {
        return items;
    }
}
