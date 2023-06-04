package Spigot.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HologramCommandTwo implements CommandExecutor {

    private JavaPlugin plugin;

    public HologramCommandTwo() {
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            ArmorStand hologram = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
            hologram.setVisible(false);
            hologram.setCustomNameVisible(true);
            hologram.setCustomName(ChatColor.GOLD + "Use /rtp for random teleport");
            hologram.setGravity(false);

            player.sendMessage(ChatColor.GREEN + "Голограмма 2 была установлена.");
        }
        return true;
    }
}

