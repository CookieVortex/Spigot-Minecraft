package Spigot.entity;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpawnCommandSecond implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Skeleton skeleton = (Skeleton) player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
        skeleton.setCustomName("Skeleton");
        skeleton.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10000, 1000));
        skeleton.getFireTicks();
        player.sendMessage(ChatColor.YELLOW + "Вы заспавнили Скелета");
        return false;
    }
}
