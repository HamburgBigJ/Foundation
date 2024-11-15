package cho.info.foundation.modules.messages;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class Messages {

    public void sendChatMassage(Player player, String message) {
        player.sendMessage(message);
    }

    public void sendActionBarMessage(Player player, String message) {
        player.sendActionBar(message);
    }

    public void sendTitleMessage(Player player, String title, String subtitle) {
        player.sendTitle(title, subtitle, 10, 70, 20);
    }

    public void sendTitleMessage(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    public void sendBroadcastMessage(Player player, World world, String message) {
        for (Player p : world.getPlayers()) {
            p.sendMessage(message);
        }
    }

    public void sendBrodcastActionBarMessage(Player player, World world, String message) {
        for (Player p : world.getPlayers()) {
            p.sendActionBar(message);
        }
    }

    public void sendBroadcastTitleMessage(Player player, World world, String title, String subtitle) {
        for (Player p : world.getPlayers()) {
            p.sendTitle(title, subtitle, 10, 70, 20);
        }
    }


}
