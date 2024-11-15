package cho.info.foundation.modules.items;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Item {

    private final Plugin plugin;

    // Item-Eigenschaften
    private String name;
    private Material material;
    private List<String> lore;
    private List<String> enchantments;
    private List<String> flags;
    private boolean unbreakable;
    private int customModelData;
    private List<String> customTags;

    // Konstruktor
    public Item(Plugin plugin) {
        this.plugin = plugin;
        this.lore = new ArrayList<>();
        this.enchantments = new ArrayList<>();
        this.flags = new ArrayList<>();
        this.customTags = new ArrayList<>();
        this.material = Material.STONE; // Standardmaterial
    }

    // Setter und Getter
    public void setName(String name) {
        this.name = name;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void addLore(String line) {
        this.lore.add(line);
    }

    public void addEnchantment(String enchantment) {
        this.enchantments.add(enchantment);
    }

    public void addFlag(String flag) {
        this.flags.add(flag);
    }

    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }

    public void setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
    }

    public void addCustomTag(String tag) {
        this.customTags.add(tag);
    }

    // Konvertiere in ItemStack
    public ItemStack toItemStack() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();

        if (meta != null) {
            if (name != null) meta.setDisplayName(name.replace("&", "§"));
            if (!lore.isEmpty()) meta.setLore(lore);

            for (String flag : flags) {
                try {
                    meta.addItemFlags(ItemFlag.valueOf(flag));
                } catch (IllegalArgumentException e) {
                    plugin.getLogger().warning("Ungültiger ItemFlag: " + flag);
                }
            }

            meta.setUnbreakable(unbreakable);
            if (customModelData > 0) meta.setCustomModelData(customModelData);
            itemStack.setItemMeta(meta);

            // Verzauberungen hinzufügen
            for (String enchant : enchantments) {
                String[] parts = enchant.split(":");
                if (parts.length == 2) {
                    try {
                        itemStack.addUnsafeEnchantment(
                                org.bukkit.enchantments.Enchantment.getByName(parts[0]),
                                Integer.parseInt(parts[1])
                        );
                    } catch (Exception e) {
                        plugin.getLogger().warning("Fehler beim Hinzufügen von Verzauberungen: " + e.getMessage());
                    }
                }
            }
        }
        return itemStack;
    }

    // Speichere das Item in einer YAML-Datei
    public void saveToConfig(FileConfiguration config, String key) {
        config.set(key + ".name", name);
        config.set(key + ".material", material.name());
        config.set(key + ".lore", lore);
        config.set(key + ".enchantments", enchantments);
        config.set(key + ".flags", flags);
        config.set(key + ".unbreakable", unbreakable);
        config.set(key + ".custommodeldata", customModelData);
        config.set(key + ".customtags", customTags);
    }

    // Lade ein Item aus einer YAML-Datei
    public static Item loadFromConfig(FileConfiguration config, String key, Plugin plugin) {
        Item item = new Item(plugin);

        item.setName(config.getString(key + ".name"));
        item.setMaterial(Material.matchMaterial(config.getString(key + ".material")));
        item.lore = config.getStringList(key + ".lore");
        item.enchantments = config.getStringList(key + ".enchantments");
        item.flags = config.getStringList(key + ".flags");
        item.setUnbreakable(config.getBoolean(key + ".unbreakable"));
        item.setCustomModelData(config.getInt(key + ".custommodeldata"));
        item.customTags = config.getStringList(key + ".customtags");

        return item;
    }
}
