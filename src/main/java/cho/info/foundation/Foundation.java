package cho.info.foundation;

import cho.info.foundation.modules.Modules;
import org.bukkit.plugin.java.JavaPlugin;

public final class Foundation extends JavaPlugin {

    public Modules modules;

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

    public void foundationInit() {
        // Initialize modules

        modules = new Modules(this);

    }

    public Modules getModules() {
        return modules;
    }
}
