package Spigot.permission;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class MyEventListener implements Listener {
    private final Plugin plugin;

    public MyEventListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (player.hasPermission("Creative")) {
            event.setFormat(ChatColor.AQUA + "[Creative] " + ChatColor.WHITE + player.getDisplayName() + ": " + message);
            player.setGameMode(GameMode.CREATIVE);
        } else {
            event.setFormat(ChatColor.GRAY + "[Survival] " + ChatColor.WHITE + player.getDisplayName() + ": " + message);
            player.setGameMode(GameMode.SURVIVAL);
        }
    }
}