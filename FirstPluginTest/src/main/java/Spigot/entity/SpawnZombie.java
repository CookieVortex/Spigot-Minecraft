package Spigot.entity;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class SpawnZombie implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.YELLOW + "Вы заспавнили Зомби");
            Zombie zombie = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);

            zombie.setCustomName("Enchanted Zombie");
            zombie.addScoreboardTag("Test");
            zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 1));
            zombie.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000, 1));

            ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET);
            ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
            ItemStack leggings = new ItemStack(Material.NETHERITE_LEGGINGS);
            ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
            ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);

            Enchantment protectionEnchantment = Enchantment.PROTECTION_ENVIRONMENTAL;
            Enchantment fireAspectEnchantment = Enchantment.FIRE_ASPECT;
            Enchantment lootingEnchantment = Enchantment.LOOT_BONUS_MOBS;
            Enchantment sweepingEdgeEnchantment = Enchantment.SWEEPING_EDGE;
            Enchantment knockbackEnchantment = Enchantment.KNOCKBACK;

            helmet.addUnsafeEnchantment(protectionEnchantment, 1);
            chestplate.addUnsafeEnchantment(protectionEnchantment, 1);
            leggings.addUnsafeEnchantment(protectionEnchantment, 1);
            boots.addUnsafeEnchantment(protectionEnchantment, 1);

            sword.addUnsafeEnchantment(fireAspectEnchantment, 1);
            sword.addUnsafeEnchantment(lootingEnchantment, 1);
            sword.addUnsafeEnchantment(sweepingEdgeEnchantment, 1);
            sword.addUnsafeEnchantment(knockbackEnchantment, 2);

            zombie.getEquipment().setHelmet(helmet);
            zombie.getEquipment().setChestplate(chestplate);
            zombie.getEquipment().setLeggings(leggings);
            zombie.getEquipment().setBoots(boots);
            zombie.getEquipment().setItemInMainHand(sword);
            return true;
        }
        return true;
    }
}

