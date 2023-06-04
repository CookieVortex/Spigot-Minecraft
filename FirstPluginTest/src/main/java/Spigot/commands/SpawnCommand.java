package Spigot.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            World world = player.getWorld();

            double x = 27.520;
            double y = 106.00000;
            double z = -33.970;

            Location spawnLocation = new Location(world, x, y, z);
            player.teleport(spawnLocation);
            player.sendMessage(ChatColor.GREEN + "Вы были успешно телепортированы на точку возрождения");
        }
        return true;
    }
}
