package Spigot.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HologramCommand implements CommandExecutor {

    private JavaPlugin plugin;

    public HologramCommand() {
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            ArmorStand hologram = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
            hologram.setVisible(false);
            hologram.setCustomNameVisible(true);
            hologram.setCustomName(ChatColor.RED + "Player level gives you the following bonuses:");
            hologram.setGravity(false);

            ArmorStand hologram2 = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, -0.3, 0), EntityType.ARMOR_STAND);
            hologram2.setVisible(false);
            hologram2.setCustomNameVisible(true);
            hologram2.setCustomName("§7Beginner - DAMAGE_RESISTANCE (lvl 1)");
            hologram2.setGravity(false);

            ArmorStand hologram3 = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, -0.6, 0), EntityType.ARMOR_STAND);
            hologram3.setVisible(false);
            hologram3.setCustomNameVisible(true);
            hologram3.setCustomName("§3Intermediate - REGENERATION (lvl 1)");
            hologram3.setGravity(false);

            ArmorStand hologram4 = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, -0.9, 0), EntityType.ARMOR_STAND);
            hologram4.setVisible(false);
            hologram4.setCustomNameVisible(true);
            hologram4.setCustomName("§dAdvanced - JUMP (lvl 2)");
            hologram4.setGravity(false);

            ArmorStand hologram5 = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, -1.3, 0), EntityType.ARMOR_STAND);
            hologram5.setVisible(false);
            hologram5.setCustomNameVisible(true);
            hologram5.setCustomName("§5Expert - INCREASE_DAMAGE (lvl 2)");
            hologram5.setGravity(false);

            ArmorStand hologram6 = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, -1.6, 0), EntityType.ARMOR_STAND);
            hologram6.setVisible(false);
            hologram6.setCustomNameVisible(true);
            hologram6.setCustomName("§9GrandMaster - INCREASE_DAMAGE (lvl 3) + HEAL (lvl 2)");
            hologram6.setGravity(false);

            ArmorStand hologram7 = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, -1.9, 0), EntityType.ARMOR_STAND);
            hologram7.setVisible(false);
            hologram7.setCustomNameVisible(true);
            hologram7.setCustomName("§4Master - SPEED (lvl 3) + NIGHT_VISION");
            hologram7.setGravity(false);

            player.sendMessage(ChatColor.GREEN + "Голограмма была установлена.");
        }
        return true;
    }
}

