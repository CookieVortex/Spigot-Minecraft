package Spigot.permission;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Survival implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(" ")) {
                player.sendMessage(ChatColor.GOLD + "[Survival] " + player.getName() + ", You are now in Survival");
                player.setGameMode(GameMode.SURVIVAL);
                player.setDisplayName(ChatColor.DARK_GRAY + "[Survival] " + ChatColor.WHITE + player.getName());
            }
        }
        return true;
    }
}
