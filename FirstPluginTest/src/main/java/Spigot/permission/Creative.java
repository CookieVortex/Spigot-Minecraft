package Spigot.permission;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Creative implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("Creative")) {
                player.sendMessage(ChatColor.RED + "[Creative] " + player.getName() + ", You are now in creative");
                player.setGameMode(GameMode.CREATIVE);
                player.setDisplayName(ChatColor.GREEN + "[Creative] " + ChatColor.WHITE + player.getName());

            } else {
                player.sendMessage(ChatColor.RED + "You don't have permission to do that");
                player.setGameMode(GameMode.SURVIVAL);
                player.setDisplayName(ChatColor.GRAY + "[Player] " + ChatColor.WHITE + player.getName());
            }
        }
        return true;
    }
}