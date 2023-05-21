package Spigot.entity;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

import static org.bukkit.Material.*;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Zombie zombie = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);

        ItemStack enchantedSword = new ItemStack(DIAMOND_SWORD);
        enchantedSword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        enchantedSword.addEnchantment(Enchantment.FIRE_ASPECT, 1);
        
        ItemStack enchantedHelmet = new ItemStack(DIAMOND_HELMET);
        enchantedHelmet.addEnchantment(Enchantment.THORNS, 1);
        enchantedHelmet.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);

        ItemStack enchantedChestplate = new ItemStack(DIAMOND_CHESTPLATE);
        enchantedChestplate.addEnchantment(Enchantment.THORNS, 1);
        enchantedChestplate.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);

        ItemStack enchantedLeggins = new ItemStack(DIAMOND_LEGGINGS);
        enchantedLeggins.addEnchantment(Enchantment.THORNS, 1);
        enchantedLeggins.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);

        ItemStack enchantedBoots = new ItemStack(DIAMOND_BOOTS);
        enchantedBoots.addEnchantment(Enchantment.THORNS, 1);
        enchantedBoots.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);

        ItemStack offHandWeapon = new ItemStack(SHIELD);
        Objects.requireNonNull(zombie.getEquipment()).setItemInOffHand(offHandWeapon);
        EntityEquipment equipment = zombie.getEquipment();
        equipment.setItemInMainHand(enchantedSword);
        equipment.setHelmet(enchantedHelmet);
        equipment.setChestplate(enchantedChestplate);
        equipment.setLeggings(enchantedLeggins);
        equipment.setBoots(enchantedBoots);

        zombie.setCustomName("Enchanted Zombie");
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3000, 10));
        zombie.setHealth(100);
        player.sendMessage(ChatColor.YELLOW + "Вы заспавнили Зомби");
        return true;
    }
}

