package Spigot.commands;

import Spigot.Utils.TeleportUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomTPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length == 0) {

                Location randomLocation = TeleportUtils.findSafeLocation(player);

                player.teleport(randomLocation);

                player.sendMessage(ChatColor.RED + "Teleported to Random Location!!!");
                player.sendMessage(ChatColor.AQUA + "New Coordinates: " + ChatColor.LIGHT_PURPLE + randomLocation.getX() + " " + randomLocation.getY() + " " + randomLocation.getZ());

            }
        }
        return true;
    }
}