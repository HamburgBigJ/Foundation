package cho.info.foundation.modules;

import cho.info.foundation.Foundation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerLogging implements Listener {

    public Foundation foundation;
    public PlayerLogging(Foundation foundation) {
        this.foundation = foundation;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {


        if(foundation.getConfig().getBoolean("player-logging")) {

            if (foundation.getConfig().getBoolean("debug")) {
                foundation.getLogger().info("Player " + event.getPlayer().getName() + " has joined the server.");
                foundation.getLogger().info("Player " + event.getPlayer().getName() + ". register");
            }



        }


    }

}
