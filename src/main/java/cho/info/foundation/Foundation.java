package cho.info.foundation;

import org.bukkit.plugin.java.JavaPlugin;

public final class Foundation extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        if (getConfig().getBoolean("debug")) {
            getLogger().info("Debug mode is enabled.");
        }



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
