package cho.info.foundation.modules.playertags;

import org.bukkit.entity.Player;

import java.util.*;

public class PlayerTags {

    private final Map<UUID, Set<String>> playerTags;

    public PlayerTags() {
        this.playerTags = new HashMap<>();
    }

    /**
     * Adds a tag to a player.
     *
     * @param player The player.
     * @param tag    The tag to add.
     * @return True if the tag was added, false if the player already had the tag.
     */
    public boolean addTag(Player player, String tag) {
        UUID uuid = player.getUniqueId();
        playerTags.putIfAbsent(uuid, new HashSet<>());
        return playerTags.get(uuid).add(tag);
    }

    /**
     * Removes a tag from a player.
     *
     * @param player The player.
     * @param tag    The tag to remove.
     * @return True if the tag was removed, false if the player did not have the tag.
     */
    public boolean removeTag(Player player, String tag) {
        UUID uuid = player.getUniqueId();
        if (!playerTags.containsKey(uuid)) {
            return false;
        }
        boolean removed = playerTags.get(uuid).remove(tag);
        if (playerTags.get(uuid).isEmpty()) {
            playerTags.remove(uuid); // Clean up empty tag sets
        }
        return removed;
    }

    /**
     * Gets a list of tags for a player.
     *
     * @param player The player.
     * @return A list of tags.
     */
    public List<String> getTags(Player player) {
        UUID uuid = player.getUniqueId();
        return new ArrayList<>(playerTags.getOrDefault(uuid, Collections.emptySet()));
    }

    /**
     * Checks if a player has a specific tag.
     *
     * @param player The player.
     * @param tag    The tag to check.
     * @return True if the player has the tag, false otherwise.
     */
    public boolean hasTag(Player player, String tag) {
        UUID uuid = player.getUniqueId();
        return playerTags.containsKey(uuid) && playerTags.get(uuid).contains(tag);
    }
}
