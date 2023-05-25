package Spigot.permission;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class PermissionTag implements Listener {
    private final Plugin plugin;

    public PermissionTag(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (player.hasPermission("op")) {
            event.setFormat(ChatColor.RED + "[Admin] " + ChatColor.WHITE + player.getDisplayName() + ": " + message);
        } else {
            event.setFormat(ChatColor.DARK_GRAY + "[Survival] " + ChatColor.WHITE + player.getDisplayName() + ": " + message);
        }
    }
}