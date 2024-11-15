package cho.info.foundation.modules.playerlogging;

import cho.info.foundation.Foundation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerLogging implements Listener {

    private final Foundation foundation;
    private final PlayerLoggingManager playerLoggingManager;

    public PlayerLogging(Foundation foundation) {
        this.foundation = foundation;
        this.playerLoggingManager = new PlayerLoggingManager(foundation);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();

        if (foundation.getConfig().getBoolean("player-logging")) {
            boolean isFirstJoin = !playerLoggingManager.hasJoinedBefore(playerName);

            if (isFirstJoin) {
                foundation.getLogger().info("Player " + playerName + " is joining for the first time.");
                playerLoggingManager.addPlayer(playerName);
            } else {
                foundation.getLogger().info("Player " + playerName + " has joined before.");
            }

            if (foundation.getConfig().getBoolean("debug")) {
                foundation.getLogger().info("Debug: Player " + playerName + " join status: " + (isFirstJoin ? "First Join" : "Returning Player"));
            }
        }
    }
}
